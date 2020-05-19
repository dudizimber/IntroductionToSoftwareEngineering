package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.isZero;

public class SpotLight extends PointLight {
    private Vector _direction;

    /**
     * Instantiates a new Spot light passing the color as argument, and the direction vector.
     *
     * @param color     the color of the light
     * @param direction the direction of the spot light
     */
    public SpotLight(Color color, Vector direction, Point3D position, double kC, double kL, double kQ) {
        super(color, position, kC, kL, kQ);
        this._direction = direction;
    }

    /**
     * Get intensity of the color in a given point
     *
     * @param p the point to check for the intensity
     * @return the color intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));
        if (isZero(projection)) {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);
        Color pointLightIntensity = super.getIntensity(p);
        return pointLightIntensity.scale(factor);
    }

    /**
     * Get vector L, given a point
     *
     * @param p the given point
     * @return the vector
     */
    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }
}
