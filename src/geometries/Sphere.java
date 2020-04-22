package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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

    /**
     * Finds the intersections of a Ray with the current Object
     *
     * @param ray The ray to intersect
     * @return List of the intersections - 3D points
     */
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getVector();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(ray.getPoint(_radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getPoint(t1), ray.getPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getPoint(t1));
        else
            return List.of(ray.getPoint(t2));
    }

    @Override
    public String toString() {
        return "Sphere:\n - " + this._center.toString() + "\n - " + super.toString();
    }


}