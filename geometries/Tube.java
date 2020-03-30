package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class that represents a 3D Tube
 */

public class Tube extends RadialGeometry {

    private Ray _axisRay;

    /****** CONSTRUCTORS *******/
    /**
     * Constructor with axisRay and radius
     * 
     * @param axisRay Ray
     * @param radius double
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        _axisRay = axisRay;

    }

    /****** GETTERS *******/

    public Ray geAxisRay() {
        return _axisRay;
    }

    /****** FUNCTIONS *******/

    /**
     * @param other Point3D
     * @return normal Vector
     */
    public Vector getNormal(Point3D other) {
        double height = 0;
        double PPo = other.distance(_axisRay.getPoint());
        Vector v = _axisRay.getVector();
        height = Math.sqrt(Math.pow(PPo, 2) - Math.pow(super.getRadius(), 2));
        Point3D center = _axisRay.getPoint().add(v.scale(height));

        return center.subtract(other).normalize();
    }

    @Override
    public String toString() {
        return "Tube:\n" + " - " + this._axisRay.toString() + "\n - " + super.toString();
    }

}