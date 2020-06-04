package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 *Models point light source with direction
 *
 */
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
        this._direction = new Vector(direction).normalized();
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
        if (alignZero(projection)<= 0) {
            return Color.BLACK;
        }
        Color pointLightIntensity = super.getIntensity(p);
        return pointLightIntensity.scale(projection);
    }



}
