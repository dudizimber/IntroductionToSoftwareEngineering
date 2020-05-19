package geometries;

import primitives.*;

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
     * @param color       Triangle's color
     * @param firstPoint  - Point on the triangle
     * @param secondPoint - Point on the triangle
     * @param thirdPoint  - Point on the triangle
     */
    public Triangle(Color color, Material material, Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {
        super(color, material, firstPoint, secondPoint, thirdPoint);
    }

    /**
     * Constructor based on the three points, passing black as default color
     *
     * @param firstPoint  - Point on the triangle
     * @param secondPoint - Point on the triangle
     * @param thirdPoint  - Point on the triangle
     */
    public Triangle(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {
        this(Color.BLACK, Material.DEFAULT, firstPoint, secondPoint, thirdPoint);
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