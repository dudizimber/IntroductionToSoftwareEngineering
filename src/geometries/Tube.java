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
     * @param axisRay
     * @param radius
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
     * @param point
     * @return normal
     */
    public Vector getNormal(Point3D point) {
        double PPo = point.distance(_axisRay.getPoint());
        Vector v = _axisRay.getVector();
        double height = Math.sqrt(PPo*PPo - super._radius*super._radius);
        Point3D center = _axisRay.getPoint().add(v.scale(height));

        return center.subtract(point).normalize();
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