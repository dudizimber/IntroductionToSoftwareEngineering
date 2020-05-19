package geometries;

import primitives.*;

/**
 * This class represents a cylinder in 3D space, which is a Tube with finite height
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Cylinder extends Tube {
    private double _height;

    /****** CONSTRUCTORS *******/

    /**
     * @param height  height of cilynder
     * @param radius  radius of cilynder
     * @param axisRay ray of of cilynder
     */
    Cylinder(Color color, Material material, double height, double radius, Ray axisRay) {
        super(color, material, axisRay, radius);
        _height = height;
    }

    /**
     * @param height  height of cilynder
     * @param radius  radius of cilynder
     * @param axisRay ray of of cilynder
     */
    Cylinder(double height, double radius, Ray axisRay) {
        this(Color.BLACK, Material.DEFAULT, height, radius, axisRay);
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