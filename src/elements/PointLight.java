package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    protected Point3D _position;
    protected double _kC, _kL, _kQ;

    /**
     * Instantiates a new Directional light passing the color as argument.
     *
     * @param color the color of the light
     */
    public PointLight(Color color, Point3D position, double kC, double kL, double kQ) {
        super(color);
        this._position = new Point3D(position);
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
    }

    /**
     * Get intensity of the color in a given point
     *
     * @param p the point to check for the intensity
     * @return the color intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dSquared = p.distanceSquared(_position);
        double d = p.distance(_position);
        return _intensity.reduce(_kC + _kL * d + _kQ * dSquared);
    }

    /**
     * Get vector L, given a point
     *
     * @param p the given point
     * @return the vector
     */
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) return null;
        return p.subtract(_position).normalize();
    }
}
