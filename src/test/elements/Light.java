package test.elements;

import primitives.Color;

/**
 * The type Light.
 */
public abstract class Light {

    /**
     * The Intensity of the color
     */
    protected Color _intensity;

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
