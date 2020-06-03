package geometries;

import primitives.Color;
import primitives.Material;

/**
 * Radial Geometry class. Used in any Class with radius
 * property
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
abstract class RadialGeometry extends Geometry {
    protected double _radius;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor base on double, passing black as default color
     *
     * @param radius double
     */
    RadialGeometry(double radius) {
        this(Color.BLACK, Material.DEFAULT, radius);
    }

    /**
     * Constructor base on double and color
     *
     * @param color  color of the geometry
     * @param radius double
     */
    RadialGeometry(Color color, Material material, double radius) {
        super(color, material);
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
    /**
     * Get the radius
     * @return
     */
    public double getRadius() {
        return _radius;
    }

    /****** FUNCTIONS *******/

    @Override
    public String toString() {
        return Double.toString(this._radius);
    }
}