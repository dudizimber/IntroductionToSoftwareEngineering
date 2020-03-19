package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class that represents a 3D Tube
 */

class Tube extends RadialGeometry {

    Ray _axisRay;

    /****** CONSTRUCTORS *******/
    /**
     * Constructor with axisRay and radius
     * 
     * @param axisRay
     * @param radius
     */
    Tube(Ray axisRay, double radius) {
        super(radius);
        _axisRay = axisRay;
    }

    /****** GETTERS *******/

    public Ray geAxisRay() {
        return _axisRay;
    }

    /****** FUNCTIONS *******/

    /**
     * @param other - The other point
     * @return normal
     */
    Vector getNormal(Point3D other) {
        return null;
    }

    @Override
    public String toString() {
        return "Tube:\n" + " - " + this._axisRay.toString() + "\n - " + super.toString();
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null || !(obj instanceof Tube))
    //         return false;
    //     Tube tube = (Tube) obj;
    //     return _axisRay == tube.geAxisRay() && _radius == tube.getRadius();
    // }

}