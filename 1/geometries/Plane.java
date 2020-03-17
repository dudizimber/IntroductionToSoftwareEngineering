package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents a 3D Plane
 */
public class Plane {
    Point3D _point;
    Vector _normal;

    /****** CONTRUCTORS *******/

    /**
     * Constructor based on a point and the normal
     * 
     * @param point
     * @param normal
     */
    Plane(Point3D point, Vector normal) {
        _point = point;
        _normal = normal;
    }

    /**
     * Constructor based on three 3D points
     * 
     * @param one
     * @param two
     * @param three
     */
    Plane(Point3D one, Point3D two, Point3D three) {
        _point = one;

        _normal = null;

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
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane:\n - " + this._point.toString() + "\n - " + this._normal.toString();
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null || !(obj instanceof Plane))
    //         return false;
    //     Plane plane = (Plane) obj;
    //     return _normal == plane.getNormal() && _point == plane.getPoint();
    // }

}
