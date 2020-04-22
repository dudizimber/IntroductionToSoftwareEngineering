package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * An interface for all intersectables 3D objects
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public interface Intersectables {

    List<Point3D> findIntersections(Ray ray);
}
