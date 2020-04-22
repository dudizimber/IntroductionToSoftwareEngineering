package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * An interface for any geometry in 3D Space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
interface Geometry extends Intersectables {
    Vector getNormal(Point3D point);
}