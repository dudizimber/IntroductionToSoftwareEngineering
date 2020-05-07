package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Triangle class. Is a polygon with 3 points only
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Triangle extends Polygon {

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on the three points.
     *
     * @param First_point   - Point on the triangle
     * @param Second_point  - Point on the triangle
     * @param Third_point - Point on the triangle
     */
    public Triangle(Point3D First_point, Point3D Second_point, Point3D Third_point) {
        super(First_point, Second_point, Third_point);
    }

    /****** FUNCTIONS *******/

    /**
     * Finds the intersections of a Ray with the current Object
     *
     * @param ray The ray to intersect
     * @return List of the intersections - 3D points
     */
    public List<GeoPoint> findIntersections(Ray ray) {

        List<GeoPoint> planeIntersections = _plane.findIntersections(ray);
        if (planeIntersections == null) return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getVector();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double u1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(u1)) return null;
        double u2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(u2)) return null;
        double u3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(u3)) return null;

        return ((u1 > 0 && u2 > 0 && u3 > 0) || (u1 < 0 && u2 < 0 && u3 < 0)) ? planeIntersections : null;

    }

    @Override
    public String toString() {
        return "Triangle:\n - " + super.toString();
    }


}