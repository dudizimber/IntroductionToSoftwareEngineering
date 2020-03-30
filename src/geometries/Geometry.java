package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * A parent class for any geometry in 3D Space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
interface Geometry {
    Vector getNormal(Point3D point);
}