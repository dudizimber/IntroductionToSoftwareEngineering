package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class that represents a Sphere in 3D space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Sphere extends RadialGeometry {
    private Point3D _center;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on center point and radius
     *
     * @param center Point3D
     * @param radius double
     */
    public Sphere(Point3D center, double radius) {
        super(radius);
        _center = center;
    }

    /****** GETTERS *******/

    public Point3D getCenter() {
        return _center;
    }

    /****** FUNCTIONS *******/

    /**
     * @param point Point3D
     * @return normal
     */
    public Vector getNormal(Point3D point) {
        return _center.subtract(point).normalize();
    }

    @Override
    public String toString() {
        return "Sphere:\n - " + this._center.toString() + "\n - " + super.toString();
    }


}