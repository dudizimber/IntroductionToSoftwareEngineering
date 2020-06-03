package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface that represents the source of the light
 * It has its intensity and vector
 *
 *
 */
public interface LightSource {
    /**
     * Get intensity of the color in a given point
     * @param p - the point
     * @return
     */
    Color getIntensity(Point3D p);

    /**
     * Get vector L, given a point
     * @param p - the point
     * @return
     */
    Vector getL(Point3D p);

    /**
     * Returns distance from given point to light
     * @param p - the point
     * @return
     */
    double getDistance(Point3D p);
}
