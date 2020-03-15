package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
 * This class represents a cylinder
 */
public class Cylinder extends Tube {
    double _height;
/**
 * 
 * @param height
 * @param radius
 * @param axisRay
 */
    Cylinder(double height, double radius, Ray axisRay) {
        super(axisRay, radius);
        _height = height;
    }
    
    public double getHeight() {
        return _height;
    }
/**
 * @param other The point to which we want to get the normal
 * @return normal
 */
    Vector getNormal(Point3D other) {
        return null;
    }
    @Override
    public String toString() {
        return super.toString()+" Height: "+this._height;
    }
}