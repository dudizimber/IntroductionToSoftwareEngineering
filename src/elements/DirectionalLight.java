package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *
 * Models a light source with its direction
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector _direction;

    /**
     * Instantiates a new Directional light passing the color as argument.
     *
     * @param color the color of the light
     */
    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this._direction = direction.normalized();
    }

    /**
     * Get intensity of the color in a given point
     *
     * @param p the point to check for the intensity
     * @return the color intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    /**
     * Get vector L, given a point
     *
     * @param p the given point
     * @return the vector
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }
}
