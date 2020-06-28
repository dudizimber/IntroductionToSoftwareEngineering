package renderer;


import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Intersectables;
import geometries.Intersectables.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The renderer
 *
 * @author David Zimberknopf & Daniel Grunberger
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;
    private int _numOfRays;
    private double _rayDistance;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final boolean SAMPLE_RAYS_IMPROVED = false;
    private static final int NUM_OF_SAMPLE_RAYS = 8; //64 rays


    /**
     * Constructor with default values for num of rays and ray distance
     *
     * @param _scene
     * @param _imageWriter
     */
    public Render(Scene _scene, ImageWriter _imageWriter) {
       this ( _scene,_imageWriter, 1,0);
    }

    /**
     * Instantiates a new Render.
     *
     * @param _scene       the scene to render
     * @param _imageWriter the image writer class to create the image
     */
    public Render(Scene _scene, ImageWriter _imageWriter, int _numOfRays , int _rayDistance) {
        this._scene = _scene;
        this._imageWriter = _imageWriter;
        this._numOfRays = _numOfRays;
        this._rayDistance = _rayDistance;
    }

    /**
     * Renders the image.
     */
    public void renderImage() {
        Color background = _scene.getBackground();
        Camera camera = _scene.getCamera();
        Intersectables geometries = _scene.getGeometries();
        double distance = _scene.getDistance();
        AmbientLight ambientLight = _scene.getAmbientLight();
        // Number of pixels in the row of View Plane
        int nx = _imageWriter.getNx();
        // Number of pixels in the column of View Plane
        int ny = _imageWriter.getNy();

        Ray ray;
        if (!SAMPLE_RAYS_IMPROVED) {
            for (int row = 0; row < ny; row++) {
                for (int column = 0; column < nx; column++) {
                    ray = camera.constructRayThroughPixel(nx, ny, column, row, distance, _imageWriter.getWidth(), _imageWriter.getHeight());
                    List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                    if (intersectionPoints == null) {
                        _imageWriter.writePixel(column, row, background);
                    } else {
                        GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                        _imageWriter.writePixel(column, row, new Color(calcColor(closestPoint, ray).getColor()));
                    }
                }
            }
        }
        else {
            for (int row = 0; row < ny; row++){
                for (int column = 0; column < nx; column++) {
                    List<Ray> rays = camera.constructRaysThroughPixel(nx , ny , column , row ,  distance, _imageWriter.getWidth(), _imageWriter.getHeight() , NUM_OF_SAMPLE_RAYS);
                    _imageWriter.writePixel(column, row, new Color(calcColor(rays).getColor()));
                }
            }
            }
    }

    /**
     * Finding the closest point to the position of the camera.
     *
     * @param intersectionPoints list of points from which should get the closest one
     * @return the closest point to the camera
     */
    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        GeoPoint result = null;
        double min = Double.MAX_VALUE;
        Point3D p0 = this._scene.getCamera().getP0();
        for (GeoPoint pt : intersectionPoints) {
            double distance = p0.distance(pt.point);
            if (distance < min) {
                min = distance;
                result = pt;
            }
        }
        return result;
    }

    /**
     * Prints the grid in a fixed interval
     *
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, Color color) {
        double rows = this._imageWriter.getNx();
        double columns = _imageWriter.getNy();

        for (int col = 0; col < columns; col++)
            for (int row = 0; row < rows; row++)
                if (col % interval == 0 || row % interval == 0)
                    _imageWriter.writePixel(row, col, color);

    }

    /**
     * Access the ImageWriter writeToImage method
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * Calculate the color intensity for a list of rays
     * @param rays the list
     * @return color intensity
     */
    private Color calcColor(List<Ray> rays){
        Color color = Color.BLACK;
        for (Ray ray : rays) {
            GeoPoint intersectionPoint = findClosestIntersection(ray);
            if (intersectionPoint == null) {
                color = color.add(_scene.getBackground());
            } else {
                color = color.add(calcColor(intersectionPoint, ray));
            }
        }
        color = color.reduce(rays.size());
        return color;
    }

    /**
     * Calculate the color intensity in a point
     *
     * @param geopoint the point for which the color is required
     * @param inRay    the ray of the color
     * @return
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
     * @param level
     * @param k
     * @return the color
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
        List<Ray>beam = new LinkedList<>();
        double kr = geoPoint.geometry.getMaterial().getkR();
        double kt = geoPoint.geometry.getMaterial().getkT();
        double kkr = k * kr;
        double kkt = k * kt;
        Color result = geoPoint.geometry.getEmission();
        if (level == 1) {
            return Color.BLACK;
        }
        Point3D pointGeo = geoPoint.point;
        Vector v = inRay.getVector();
        //  Vector v = pointGeo.subtract(_scene.getCamera().getP0()).normalize();
        Vector n = geoPoint.geometry.getNormal(pointGeo);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return result;
        }
        double transparency=0;//transparency
        for (LightSource lightSource : _scene.getLights())//for each light source in the scene's light sources
        {
            Vector l = lightSource.getL(geoPoint.point);//the lights direction from geopoint
            if (alignZero( geoPoint.geometry.getNormal(geoPoint.point).dotProduct(l)) * alignZero( geoPoint.geometry.getNormal(geoPoint.point).dotProduct(v)) > 0)//if the dot product between the normal and the light direction times the dot product btween the normal and the normal vector between the camera and geopoint
            {
                transparency = transparency(lightSource, l,  geoPoint.geometry.getNormal(geoPoint.point), geoPoint);
                if (transparency * k > MIN_CALC_COLOR_K) {
                    Material material = geoPoint.geometry.getMaterial();
                    double ln =l.dotProduct( geoPoint.geometry.getNormal(geoPoint.point));
                    result = result.add(calcDiffusive(
                            material.getkD(),
                            ln,
                            lightSource.getIntensity(geoPoint.point)),
                            calcSpecular(
                                    material.getkS(), l,
                                    geoPoint.geometry.getNormal(geoPoint.point),
                                    ln,
                                    v,
                                    material.getNShininess(),
                                    lightSource.getIntensity(geoPoint.point)));
                }
            }
        }

        if (kr > MIN_CALC_COLOR_K)//if the reflection is bigger than the minimum of calc color
        {
            Ray reflection= constructReflectedRay(geoPoint.point,inRay , geoPoint.geometry.getNormal(geoPoint.point));
            if (this._numOfRays==0 ||this._rayDistance<0)
                beam.add(reflection);
            else
                beam = reflection.createBeamOfRays(geoPoint.geometry.getNormal(geoPoint.point), this._scene.getDistance(), this._numOfRays);
            primitives.Color tempColor = primitives.Color.BLACK;
            for(Ray r :beam)
            {
                GeoPoint reflectedGp = findClosestIntersection(r);//find the closest point to the reflection ray's p0
                if (reflectedGp != null)//if such a point exists
                {
                    tempColor = tempColor.add(calcColor(reflectedGp, r, level - 1, kr).scale(kr));//calls the recursion th find the rest of the color and then scales it with the reflection
                }
            }
            result = result.add(tempColor.reduce(beam.size()));


        }
        if (kt > MIN_CALC_COLOR_K)//if the refraction is bigger than the minimum of calc color
        {
            Ray refraction = constructRefractedRay(geoPoint.point, inRay, geoPoint.geometry.getNormal(geoPoint.point));//constructs a refracted ray
            if(this._numOfRays==0 ||this._rayDistance<0)
                beam.add(refraction);
            else
                beam = refraction.createBeamOfRays(geoPoint.geometry.getNormal(geoPoint.point), this._scene.getDistance(), this._numOfRays);
            primitives.Color tempColor = primitives.Color.BLACK;
            for(Ray r :beam) {
                GeoPoint refractedGp = findClosestIntersection(r);//find the closest point to the refracted ray's p0
                if (refractedGp != null)//if such a point exists
                {
                    tempColor = tempColor.add(calcColor(refractedGp, r, level - 1, kt).scale(kt));//calls the recursion to find the rest of the color and then scales it with the refracted

                }
            }
            result = result.add(tempColor.reduce(beam.size()));
        }

        return result;
    }

    /**
     * Constructs refracted ray with point , ray and vector
     *
     * @param pointGeo the point
     * @param inRay    the ray
     * @param n        the vector
     * @return the ray
     */
    private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getVector(), n);
    }

    /**
     * Constructs reflected ray with point , ray and vector
     *
     * @param pointGeo
     * @param inRay
     * @param n
     * @return the ray
     */
    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) {
        Vector v = inRay.getVector();
        double vn = v.dotProduct(n);
        if (alignZero(vn) == 0) {
            return null;
        }
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, r, n);
    }

    /**
     * return the light source colors
     *
     * @param geoPoint
     * @param k
     * @param result
     * @param v
     * @param n
     * @param nv
     * @param nShininess
     * @param kd
     * @param ks
     * @return the color
     */
    private Color getLightSourcesColors(GeoPoint geoPoint, double k, Color result, Vector v, Vector n, double nv, int nShininess, double kd, double ks) {
        Point3D pointGeo = geoPoint.point;
        List<LightSource> lightSources = _scene.getLights();
        if (lightSources != null) {
            for (LightSource lightSource : lightSources) {
                Vector l = lightSource.getL(pointGeo);
                double nl = alignZero(n.dotProduct(l));
                if (nl * nv > 0) {
                    if (unshaded(lightSource, l, n, geoPoint)) {
                        double ktr = transparency(lightSource, l, n, geoPoint);
                        if (ktr * k > MIN_CALC_COLOR_K) {
                            Color ip = lightSource.getIntensity(pointGeo).scale(ktr);
                            result = result.add(
                                    calcDiffusive(kd, nl, ip),
                                    calcSpecular(ks, l, n, nl, v, nShininess, ip));
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd
     * @param nl
     * @param ip
     * @return the color
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) nl = -1 * nl;
        return ip.scale(nl * kd);
    }

    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks
     * @param l
     * @param n
     * @param nl
     * @param v
     * @param nShininess
     * @param ip         light intensity at the point
     * @return diffusive component of light reflection*
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        Vector r = l.add(n.scale(-2 * nl));
        double minusVr = -alignZero(r.dotProduct(v));
        if (minusVr <= 0) {
            return Color.BLACK;
        }
        return ip.scale(ks * Math.pow(minusVr, nShininess));
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
        if (ray == null) {
            return null;
        }
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
     * Return the transparency
     *
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return the transparency
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        Point3D pointGeo = geopoint.point;

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) {
            return 1d;
        }
        double lightDistance = light.getDistance(pointGeo);
        double ktr = 1d;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(pointGeo) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getkT();
                if (ktr < MIN_CALC_COLOR_K) {
                    return 0.0;
                }
            }
        }
        return ktr;
    }

    /**
     * Verify that there is no shadow between the point and the light source
     *
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return if the is shadow or not
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        Point3D pointGeo = geopoint.point;
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay, light.getDistance(pointGeo));
        if (intersections == null) {
            return true;
        }
        double lightDistance = light.getDistance(pointGeo);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(pointGeo) - lightDistance) <= 0
                    || gp.geometry.getMaterial().getkT() == 0) {
                return false;
            }
        }
        return true;
    }
}
