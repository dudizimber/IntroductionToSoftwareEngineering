package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a Plane in 3D space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Plane extends Geometry {
    private Point3D _point;
    private Vector _normal;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on a point and the normal, default color is black
     *
     * @param point  Point3D
     * @param normal Vector
     */
    public Plane(Point3D point, Vector normal) {
        this(Color.BLACK, Material.DEFAULT, point, normal);
    }

    /**
     * Constructor based on three 3D points, default color is black
     *
     * @param firstPoint  - Point on the plane
     * @param secondPoint - Point on the plane
     * @param thirdPoint  - Point on the plane
     */
    public Plane(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {
        this(Color.BLACK, Material.DEFAULT, firstPoint, secondPoint, thirdPoint);
    }

    /**
     * Constructor based on a point and the normal
     *
     * @param color  - Color of the plane
     * @param point  Point3D
     * @param normal Vector
     */
    public Plane(Color color, Material material, Point3D point, Vector normal) {
        super(color, material);
        _point = point;
        _normal = normal.normalized();
    }

    /**
     * Constructor based on three 3D points
     *
     * @param color - Color of the plane
     * @param firstPoint   - Point on the plane
     * @param secondPoint   - Point on the plane
     * @param thirdPoint - Point on the plane
     */
    public Plane(Color color, Material material, Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {
        super(color, material);
        _point = firstPoint;
        Vector a = secondPoint.subtract(firstPoint);
        Vector b = thirdPoint.subtract(firstPoint);
        _normal = a.crossProduct(b).normalized();
    }

    /****** GETTERS *******/

    /**
     * @return the _point
     */
    public Point3D getPoint() {
        return _point;
    }

    /****** FUNCTIONS *******/

    /**
     * @return normal
     */
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    /**
     * Finds the intersections of a Ray with the current Object
     *
     * @param ray The ray to intersect
     * @return List of the intersections - 3D points
     */
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {

        Point3D p0 = ray.getPoint();
        Vector v = ray.getVector();
        Vector q0p0;
        try {
            q0p0 = _point.subtract(p0);
        } catch (IllegalArgumentException e) {
            // Ray starts from inside plane, therefore there are no intersections
            return null;
        }

        double tDenominator = _normal.dotProduct(v);

        if (isZero(tDenominator)) {
            // Parallel vectors
            return null;
        }

        double tNumerator = _normal.dotProduct(q0p0);

        double t = alignZero(tNumerator / tDenominator);

        if (t <= 0 || (alignZero(t - maxDistance) > 0)) return null;
        return List.of(new GeoPoint(this, ray.getPoint(t)));
    }

    @Override
    public String toString() {
        return "Plane:\n - " + this._point.toString() + "\n - " + this._normal.toString();
    }

}
