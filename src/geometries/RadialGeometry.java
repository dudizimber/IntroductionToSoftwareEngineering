package geometries;

/**
 * Radial Geometry class
 */
abstract class RadialGeometry {
    double _radius;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor base on double
     * 
     * @param radius
     */
    RadialGeometry(double radius) {
        _radius = radius;
    }

    /**
     * Constructor base on RadialGeometry
     * 
     * @param other
     */

    RadialGeometry(RadialGeometry other) {
        _radius = other.getRadius();
    }

    /****** GETTERS *******/

    public double getRadius() {
        return _radius;
    }

    /****** FUNCTIONS *******/

    @Override
    public String toString() {
        return Double.toString(this._radius);
    }
}