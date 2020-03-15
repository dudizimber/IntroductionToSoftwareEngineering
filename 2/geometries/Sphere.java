package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 * Class that represents a sphere
 */
class Sphere extends RadialGeometry {
    Point3D _center;
/**
 * Constructor based on center point and radius
 */
    Sphere(Point3D center, double radius) {
        super(radius);
        _center = center;
    }

    public Point3D getCenter() {
        return _center;
    }
/**
 * 
 * @param point
 * @return normal
 */
    Vector getNormal(Point3D point) {
        return null;
    }
    @Override
    public String toString() {
        return super.toString()+" "+this._center.toString();
    }
}