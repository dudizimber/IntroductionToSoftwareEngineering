package renderer;


import elements.Camera;
import elements.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static geometries.Intersectables.GeoPoint;
import static primitives.Util.alignZero;

/**
 * The renderer
 *
 * @author David Zimberknopf & Daniel Grunberger
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;
    private static final double GLOSS_BLUR_DISTANCE = 100d;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private int _numOfGlossBlurRays = 0;
    private int _numOfSampleRays = 0;
    private int _threads = 1;
    private boolean _print = false; // printing progress percentage

    /**
     * Instantiates a new Render.
     *
     * @param _scene       the scene to render
     * @param _imageWriter the image writer class to create the image
     */
    public Render(Scene _scene, ImageWriter _imageWriter) {
        this._scene = _scene;
        this._imageWriter = _imageWriter;
    }

    /**
     * Renders the image.
     */
    public void renderImage() {
        final Camera camera = _scene.getCamera();
        final double distance = _scene.getDistance();
        // Number of pixels in the row of View Plane
        final int nX = _imageWriter.getNx();
        // Number of pixels in the column of View Plane
        final int nY = _imageWriter.getNy();
        final double width = _imageWriter.getWidth();
        final double height = _imageWriter.getHeight();

        // Multi-threading option 1
        final Pixel thePixel = new Pixel(nY, nX);
        // Generate threads
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {
                    List<Ray> rays = camera.constructRaysThroughPixel(nX, nY, pixel.col, pixel.row, distance, width, height, _numOfSampleRays);
                    _imageWriter.writePixel(pixel.col, pixel.row, calcColor(rays).getColor());
                }
            });
        }
        // Start threads
        for (Thread thread : threads)
            thread.start();

        // Print percents on the console
        thePixel.print();

        // Ensure all threads have finished
        for (Thread thread : threads)
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println("failure");
            }
        if (_print)
            System.out.println("\r100%");
    }

    /**
     * Prints the grid in a fixed interval
     *
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, java.awt.Color color) {
        double rows = this._imageWriter.getNx();
        double columns = _imageWriter.getNy();

        for (int col = 0; col < columns; col++)
            for (int row = 0; row < rows; row++)
                if (col % interval == 0 || row % interval == 0)
                    _imageWriter.writePixel(row, col, color);

    }

    /**
     * Calculate the color intensity for a list of rays
     *
     * @param rays the list
     * @return color intensity
     */
    private Color calcColor(List<Ray> rays) {
        Color bkg = _scene.getBackground();
        Color color = Color.BLACK;
        for (Ray ray : rays) {
            GeoPoint intersectionPoint = findClosestIntersection(ray);
            color = color.add(intersectionPoint == null ? bkg : calcColor(intersectionPoint, ray));
        }
        int size = rays.size();
        if (size > 1)
            color = color.reduce(size);
        return color;
    }

    /**
     * Access the ImageWriter writeToImage method
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * Calculate the color intensity in a point
     *
     * @param geopoint the point for which the color is required
     * @param inRay    the ray of the color
     * @return ray tracing color result
     */
    private Color calcColor(GeoPoint geopoint, Ray inRay) {
        Color color = calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
        color = color.add(_scene.getAmbientLight().getIntensity());
        return color;
    }

    /**
     * Calculate the color intensity in a point
     *
     * @param geoPoint the point for which the color is required
     * @param inRay    the ray of the color
     * @param level    current recursion level
     * @param k        accumulated color attenuation factor
     * @return the color
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
        Color result = geoPoint.geometry.getEmission();
        Vector v = inRay.getVector();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return result;

        result = result.add(getLightSourcesColors(geoPoint, k, v, n, nv));

        // Reflection and Refraction (transparency) recursive calculation
        if (level == 1)
            return Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        double kr = material.getkR();
        double kt = material.getKT();
        double gb = material.getGlossBlur();
        double kkr = k * kr;
        double kkt = k * kt;
        //if the refraction is bigger than the minimum of calc color
        if (kkt > MIN_CALC_COLOR_K)
            result = result.add(calcColorRecursion(constructRefractedRay(geoPoint.point, inRay, n), n, gb, level, kt, kkt));
        //if the reflection is bigger than the minimum of calc color
        if (kkr > MIN_CALC_COLOR_K)
            result = result.add(calcColorRecursion(constructReflectedRay(geoPoint.point, inRay, n, nv), n, gb, level, kr, kkr));

        return result;
    }

    private Color calcColorRecursion(Ray ray, Vector n, double radius, int level, double k, double kk) {
        List<Ray> beam = ray.createBeamOfRays(n, GLOSS_BLUR_DISTANCE, this._numOfGlossBlurRays, radius);
        Color tempColor = primitives.Color.BLACK;
        Color bkg = _scene.getBackground();
        for (Ray r : beam) {
            GeoPoint geoPoint = findClosestIntersection(r);//find the closest point to the reflection ray's p0
            //calls the recursion th find the rest of the color and then scales it with the reflection
            tempColor = tempColor.add((geoPoint == null ? bkg : calcColor(geoPoint, r, level - 1, kk).scale(k)));
        }
        double size = beam.size();
        if (size > 1)
            tempColor = tempColor.reduce(size);
        return tempColor;
    }

    /**
     * Constructs refracted ray with point , ray and vector
     *
     * @param pointGeo geometry and point of intersection
     * @param inRay    original ray
     * @param n        normal vector to the surface at the intersection point
     * @return the ray
     */
    private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getVector(), n);
    }

    /**
     * Constructs reflected ray with point , ray and vector
     *
     * @param pointGeo geometry and point of intersection
     * @param inRay    original ray
     * @param n        normal vector to the surface at the intersection point
     * @param vn       dot-product of ray direction and n
     * @return the ray
     */
    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n, double vn) {
        Vector v = inRay.getVector();
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, r, n);
    }

    /**
     * return the light source effect - diffusive and specular components from all light sources accumlated
     *
     * @param geoPoint geometry and point to calculate light effects
     * @param k        accumulated attenuation factor
     * @param v        direction of original ray from point of view
     * @param n        normal to the surface at the point
     * @param nv       dot-product of v and n
     * @return the resulting color
     */
    private Color getLightSourcesColors(GeoPoint geoPoint, double k, Vector v, Vector n, double nv) {
        Color result = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        double ks = material.getKS();
        double kd = material.getKD();
        int nsh = material.getNShininess();
        //for each light source in the scene's light sources
        for (LightSource lightSource : _scene.getLights()) {
            Vector l = lightSource.getL(geoPoint.point);//the lights direction from geopoint
            double ln = alignZero(l.dotProduct(n));
            //if the dot product between the normal and the light direction times the dot product btween the normal and the normal vector between the camera and geopoint
            if (ln * nv > 0) {
                double transparency = transparency(lightSource, l, n, geoPoint);
                if (transparency * k > MIN_CALC_COLOR_K) {
                    Color il = lightSource.getIntensity(geoPoint.point).scale(transparency);
                    result = result.add(calcDiffusive(kd, ln, il), calcSpecular(ks, l, n, ln, v, nsh, il));
                }
            }
        }
        return result;
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusion factor
     * @param nl dot-product of normal and light vectors
     * @param ip light source intensity at the point
     * @return the color
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) nl = -1 * nl;
        return ip.scale(nl * kd);
    }

    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular factor
     * @param l          light vector to the point
     * @param n          normal vector
     * @param nl         dot-product of normal and light vectors
     * @param v          original ray direction (from point of view)
     * @param nShininess shininess factor
     * @param ip         light intensity at the point
     * @return the color
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        Vector r = l.add(n.scale(-2 * nl));
        double minusVr = -alignZero(r.dotProduct(v));
        if (minusVr <= 0)
            return Color.BLACK;
        return ip.scale(ks * Math.pow(minusVr, nShininess));
    }

    /**
     * Calculates the transparency factor for a shadow ray
     *
     * @param light    light source
     * @param l        light vector to the point
     * @param n        normal to the surface at the point
     * @param geopoint geometry and point of intersectoin
     * @return the transparency factor (for shadow ray)
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray shadowRay = new Ray(geopoint.point, lightDirection, n);

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(shadowRay);
        if (intersections == null)
            return 1d;

        double lightDistance = light.getDistance(geopoint.point);
        double ktr = 1d;
        for (GeoPoint gp : intersections)
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getKT();
                if (ktr < MIN_CALC_COLOR_K)
                    return 0.0;
            }
        return ktr;
    }

    /**
     * Find intersections of a ray with the scene geometries and get the
     * intersection point that is closest to the ray head. If there are no
     * intersections, null will be returned.
     *
     * @param ray intersecting the scene
     * @return the closest point
     */
    public GeoPoint findClosestIntersection(Ray ray) {
        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D ray_p0 = ray.getPoint();
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
        if (intersections == null)
            return null;
        for (GeoPoint geoPoint : intersections) {
            double distance = ray_p0.distance(geoPoint.point);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = geoPoint;
            }
        }
        return closestPoint;
    }

    /**
     * Set number of rays in gloss/blur beam
     *
     * @param numOfRays number of rays
     * @return renderer itself
     */
    public Render setNumOfGlossBlurRays(int numOfRays) {
        this._numOfGlossBlurRays = numOfRays;
        return this;
    }

    /**
     * Set number of sample rays for super sampling - rays in each direction of grid
     *
     * @param numOfRays number of rays horizontal and vertical
     * @return renderer itself
     */
    public Render setNumOfSampleRays(int numOfRays) {
        this._numOfSampleRays = numOfRays;
        return this;
    }

    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of coress less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
        if (threads != 0)
            _threads = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            _threads = cores <= 2 ? 1 : cores;
        }
        return this;
    }

    /**
     * Set debug printing on
     *
     * @return the Render object itself
     */
    public Render setDebugPrint() {
        _print = true;
        return this;
    }

    /**
     * Pixel is an internal helper class whose objects are associated with a Render
     * object that they are generated in scope of. It is used for multithreading in
     * the Renderer and for follow up its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each
     * thread.
     *
     * @author Dan
     */
    private class Pixel {
        public volatile int row = 0;
        public volatile int col = -1;
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         *
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print)
                System.out.printf("\r %02d%%", _percents);
        }

        /**
         * Default constructor for secondary Pixel objects
         */
        public Pixel() {
        }

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object
         * - this function is critical section for all the threads, and main Pixel
         * object data is the shared data of this critical section.<br/>
         * The function provides next pixel number each call.
         *
         * @param target target secondary Pixel object to copy the row/column of the
         *               next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print,
         * if it is -1 - the task is finished, any other value - the progress
         * percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_print && _counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                target.row = this.row;
                target.col = this.col;
                if (_print && _counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         *
         * @param target target secondary Pixel object to copy the row/column of the
         *               next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (_print && percents > 0)
                synchronized (this) {
                    notifyAll();
                }
            if (percents >= 0)
                return true;
            if (_print)
                synchronized (this) {
                    notifyAll();
                }
            return false;
        }

        /**
         * Debug print of progress percentage - must be run from the main thread
         */
        public void print() {
            if (_print)
                while (_percents < 100)
                    try {
                        synchronized (this) {
                            wait();
                        }
                        System.out.println();
                        System.out.printf("\r %02d%%", _percents);
                    } catch (Exception e) {
                        System.out.println("failure");
                    }
        }
    }
}
