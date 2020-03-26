package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Triangle class. Is a polygon with 3 points only
 */
public class Triangle extends Polygon {

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on the three points.
     */
    public Triangle(Point3D one, Point3D two, Point3D three) {
        super(new Point3D[] { one, two, three });
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

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null || !(obj instanceof Triangle))
    //         return false;
    //     Triangle tri = (Triangle) obj;
    //     return _plane == tri._plane && _vertices == tri._vertices;
    // }

}