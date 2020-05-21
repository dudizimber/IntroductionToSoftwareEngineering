package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public interface LightSource {

    Color getIntensity(Point3D p);

    Vector getL(Point3D p);

    double getDistance(Point3D p);
}
