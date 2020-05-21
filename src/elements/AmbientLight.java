package elements;

import primitives.Color;

/**
 * Ambient Light
 *
 * @author David Zimberknopf & Daniel Grunberger
 */
public class AmbientLight extends Light {

    /**
     * Creates an Ambient Light using a Color and the coefficient
     *
     * @param intensity   The Color of the Ambient Light
     * @param coefficient Attenuation coefficient
     */
    public AmbientLight(Color intensity, double coefficient){ super(new Color(intensity).scale(coefficient));}

}
