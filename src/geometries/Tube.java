package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class that represents a Tube in 3D space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */

public class Tube extends RadialGeometry {

    private Ray _axisRay;

    /****** CONSTRUCTORS *******/
    /**
     * Constructor with axisRay and radius
     *
     * @param axisRay Ray
     * @param radius  double
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
        double PPo = other.distance(_axisRay.getPoint());
        Vector v = _axisRay.getVector();
        double height = Math.sqrt(Math.pow(PPo, 2) - Math.pow(super._radius, 2));
        Point3D center = _axisRay.getPoint().add(v.scale(height));

        return center.subtract(other).normalize();
    }

    @Override
    public String toString() {
        return "Tube:\n" + " - " + this._axisRay.toString() + "\n - " + super.toString();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}