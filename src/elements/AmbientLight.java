package elements;

import primitives.Color;

/**
 * Ambient Light
 *
 * @author David Zimberknopf & Daniel Grunberger
 */
public class AmbientLight {
    Color _intensity;

    /**
     * Creates an Ambient Light using a Color and the coefficient
     *
     * @param intensity   The Color of the Ambient Light
     * @param coefficient Attenuation coefficient
     */
    public AmbientLight(Color intensity, double coefficient) {
        this._intensity = new Color(intensity).scale(coefficient);
    }

    /**
     * Get for Intensity
     *
     * @return the java.awt.Color of the intensity of the Ambient Light
     */
    public Color getIntensity() {
        return _intensity;
    }

}
