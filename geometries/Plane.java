package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents a 3D Plane
 */
public class Plane {
    private Point3D _point;
    private Vector _normal;

    /****** CONTRUCTORS *******/

    /**
     * Constructor based on a point and the normal
     * 
     * @param point Point3D
     * @param normal Vector
     */
    Plane(Point3D point, Vector normal) {
        _point = point;
        _normal = normal;
    }

    /**
     * Constructor based on three 3D points
     * 
     * @param one Point3D
     * @param two Point3D
     * @param three Point3D
     */
    public Plane(Point3D one, Point3D two, Point3D three) {
        _point = one;

        Vector a = two.subtract(one);
        Vector b = three.subtract(one);
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
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane:\n - " + this._point.toString() + "\n - " + this._normal.toString();
    }

}
