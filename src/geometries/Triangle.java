package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;

import java.util.List;

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
        return super.findIntersections(ray);
    }

    @Override
    public String toString() {
        return "Triangle:\n - " + super.toString();
    }


}