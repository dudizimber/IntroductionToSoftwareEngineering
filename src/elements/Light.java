package elements;

import primitives.Color;

/**
 * The type Light.
 */
public abstract class Light {

    /**
     * The Intensity of the color
     */
    protected Color _intensity;

    /**
     * Constructor bade on color
     * @param color - the intensity
     */
    public Light(Color color) {
        this._intensity = color;
    }

    /**
     * Gets intensity of the color
     *
     * @return the intensity
     */
    public Color getIntensity() {
        return new Color(_intensity);
    }
}
