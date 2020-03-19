package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class that represents a 3D Sphere
 */
class Sphere extends RadialGeometry {
    Point3D _center;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on center point and radius
     */
    Sphere(Point3D center, double radius) {
        super(radius);
        _center = center;
    }

    /****** GETTERS *******/

    public Point3D getCenter() {
        return _center;
    }

    /****** FUNCTIONS *******/

    /**
     * 
     * @param point
     * @return normal
     */
    Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public String toString() {
        return "Sphere:\n - " + this._center.toString() + "\n - " + super.toString();
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null || !(obj instanceof Sphere))
    //         return false;
    //     Sphere sph = (Sphere) obj;
    //     return _center == sph.getCenter() && getRadius() == sph.getRadius();
    // }

}