package geometries;

/**
 * Radial Geometry class. Used in any Class with radius
 * property
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
abstract class RadialGeometry {
    protected double _radius;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor base on double
     *
     * @param radius double
     */
    RadialGeometry(double radius) {
        _radius = radius;
    }

    /**
     * Constructor base on RadialGeometry
     *
     * @param other RadialGeometry
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