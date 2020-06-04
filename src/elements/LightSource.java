package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface that represents a source of the light
 */
public interface LightSource {

    /**
     * Get the color in a given point
     *
     * @param p the point to check for the intensity
     * @return the color
     */
    Color getIntensity(Point3D p);

    /**
     * Get vector L, given a point
     *
     * @param p the given point
     * @return the vector
     */
    Vector getL(Point3D p);

    /**
     * Returns distance from given point to light
     *
     * @param p the point to check
     * @return the distance
     */
    double getDistance(Point3D p);
}
