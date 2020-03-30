package geometries;

import primitives.Point3D;
import primitives.Vector;

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
     * @param one   Point3D
     * @param two   Point3D
     * @param three Point3D
     */
    public Triangle(Point3D one, Point3D two, Point3D three) {
        super(one, two, three);
    }

    /****** FUNCTIONS *******/

    @Override
    public String toString() {
        return "Triangle:\n - " + super.toString();
    }

    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }


}