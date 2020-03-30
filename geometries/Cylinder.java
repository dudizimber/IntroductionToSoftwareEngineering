package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * This class represents a cylinder, which is a Tube with definite height
 */
public class Cylinder extends Tube {
    double _height;

    /****** CONSTRUCTORS *******/

    /**
     * 
     * @param height double
     * @param radius double
     * @param axisRay Ray
     */
    Cylinder(double height, double radius, Ray axisRay) {
        super(axisRay, radius);
        _height = height;
    }

    /****** GETTERS *******/

    public double getHeight() {
        return _height;
    }

    /****** FUNCTIONS *******/

    /**
     * @param other The point to which we want to get the normal
     * @return normal
     */
    @Override
    public Vector getNormal(Point3D other) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder: \n" + " - height: " + this._height + "\n - " + super.toString();
    }

}