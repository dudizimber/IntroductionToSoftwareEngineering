package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a Plane in 3D space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Plane implements Geometry {
    private Point3D _point;
    private Vector _normal;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on a point and the normal
     *
     * @param point  Point3D
     * @param normal Vector
     */
    public Plane(Point3D point, Vector normal) {
        _point = point;
        _normal = normal;
    }

    /**
     * Constructor based on three 3D points
     *
     * @param firstPoint   Point3D
     * @param secondPoint   Point3D
     * @param thirdPoint Point3D
     */
    public Plane(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {
        _point = firstPoint;

        Vector a = secondPoint.subtract(firstPoint);
        Vector b = thirdPoint.subtract(firstPoint);
        _normal = a.crossProduct(b).normalize();

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
    public List<Point3D> findIntersections(Ray ray) {

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

        if (t <= 0) return null;
        else return List.of(ray.getPoint(t));
    }

    @Override
    public String toString() {
        return "Plane:\n - " + this._point.toString() + "\n - " + this._normal.toString();
    }

}
