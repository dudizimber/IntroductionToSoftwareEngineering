package renderer;


import elements.Camera;
import elements.LightSource;
import geometries.Intersectables;
import geometries.Intersectables.GeoPoint;
import primitives.*;
import scene.Scene;

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
        Color background = _scene.getBackground();
        Camera camera = _scene.getCamera();
        Intersectables geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

        // Number of pixels in the row of View Plane
        int nx = _imageWriter.getNx();
        // Number of pixels in the column of View Plane
        int ny = _imageWriter.getNy();

        Ray ray;
        for (int row = 0; row < ny; row++) {
            for (int column = 0; column < nx; column++) {
                ray = camera.constructRayThroughPixel(nx, ny, row, column, distance, _imageWriter.getWidth(), _imageWriter.getHeight());
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(column, row, calcColor(closestPoint));
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
     * Calculate the color intensity in a point
     *
     * @param geopoint the point for which the color is required
     * @return the color intensity
     */
    private Color calcColor(GeoPoint geopoint) {
        Color result = _scene.getAmbientLight().getIntensity();
        result = result.add(geopoint.getGeometry().getEmission());
        List<LightSource> lights = _scene.getLights();

        Vector v = geopoint.getPoint().subtract(_scene.getCamera().getP0()).normalize();
        Vector n = geopoint.getGeometry().getNormal(geopoint.getPoint());

        Material material = geopoint.getGeometry().getMaterial();
        int nShininess = material.getNShininess();
        double kd = material.getKD();
        double ks = material.getKS();

        if (lights != null)
            for (LightSource lightSource : lights) {
                Vector l = lightSource.getL(geopoint.getPoint());
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));
                if (sign(nl) == sign(nv)) {
                    Color ip = lightSource.getIntensity(geopoint.getPoint());
                    result = result.add(
                            calcDiffusive(kd, nl, ip),
                            calcSpecular(ks, l, n, nl, v, nShininess, ip)
                    );
                }
            }

        return result;
    }

    private boolean sign(double value) {
        return value > 0d;
    }

    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) nl = -1 * nl;
        return ip.scale(nl * kd);
    }

    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        double p = nShininess;
        Vector r = l.add(n.scale(-2 * nl));
        double minusVr = -alignZero(r.dotProduct(v));
        if (minusVr <= 0) {
            return Color.BLACK;
        }
        return ip.scale(ks * Math.pow(minusVr, p));
    }

}
