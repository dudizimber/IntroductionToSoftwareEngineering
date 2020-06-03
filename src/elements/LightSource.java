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

    Color getIntensity(Point3D p);

    Vector getL(Point3D p);

    double getDistance(Point3D p);
}
