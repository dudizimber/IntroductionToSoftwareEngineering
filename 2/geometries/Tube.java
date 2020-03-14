package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

class Tube extends RadialGeometry {

    Ray _axisRay;

    Tube(Ray axisRay, double radius) {
        super(radius);
        _axisRay = axisRay;
    }

    public Ray geAxisRay() {
        return _axisRay;
    }

    Vector getNormal(Point3D other) {
        return null;
    }

}