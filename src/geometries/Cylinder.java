package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * This class represents a cylinder in 3D space, which is a Tube with finite height
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Cylinder extends Tube {
    private double _height;

    /****** CONSTRUCTORS *******/

    /**
     * @param height  double
     * @param radius  double
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
     * @param other The point to which we want to get the normal - Point3D
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