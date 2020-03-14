package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane {
    Point3D _point;
    Vector _normal;

    Plane(Point3D point, Vector normal) {
        _point = point;
        _normal = normal;
    }

    Plane(Point3D one, Point3D two, Point3D three) {
        _point = one;

        _normal = null;

    }

    public Vector getNormal() {
        return _normal;
    }
}
