package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

class Tube extends RadialGeometry {

    Ray _axisRay;
/**
 * Constructor with axisRay and radius
 * @param axisRay
 * @param radius
 */
    Tube(Ray axisRay, double radius) {
        super(radius);
        _axisRay = axisRay;
    }

    public Ray geAxisRay() {
        return _axisRay;
    }
/**
 * @param other - The other point
 * Returns the normal
 */
    Vector getNormal(Point3D other) {
        return null;
    }
   @Override
   public String toString() {
       return super.toString() +" Ray: "+ this._axisRay.toString();
   }
}