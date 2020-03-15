package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 * This class represents a plane (point , normal)
 */
public class Plane {
    Point3D _point;
    Vector _normal;
/**
 * The point and normal that represent the plane
 * @param point
 * @param normal
 */
    Plane(Point3D point, Vector normal) {
        _point = point;
        _normal = normal;
    }
/**
 * The three vectors that represent the plane
 * @param one
 * @param two
 * @param three
 */
    Plane(Point3D one, Point3D two, Point3D three) {
        _point = one;

        _normal = null;

    }

    public Vector getNormal() {
        return _normal;
    }
    @Override
    public String toString() {
        return "Point : "+this._point.toString()+" Vector: "+this._normal.toString();
    }
}
