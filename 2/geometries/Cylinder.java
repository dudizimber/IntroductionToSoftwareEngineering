package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
    double _height;

    Cylinder(double height, double radius, Ray axisRay) {
        super(axisRay, radius);
        _height = height;
    }
    
    public double getHeight() {
        return _height;
    }

    Vector getNormal(Point3D other) {
        return null;
    }
    @Override
    public String toString() {
        return super.toString()+" Height: "+this._height;
    }
}