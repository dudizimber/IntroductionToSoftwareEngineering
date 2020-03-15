package geometries;

import primitives.Point3D;
import primitives.Vector;

class Sphere extends RadialGeometry {
    Point3D _center;

    Sphere(Point3D center, double radius) {
        super(radius);
        _center = center;
    }

    public Point3D getCenter() {
        return _center;
    }

    Vector getNormal(Point3D point) {
        return null;
    }
    @Override
    public String toString() {
        return super.toString()+" "+this._center.toString();
    }
}