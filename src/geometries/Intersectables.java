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

    /**
     * Find intersections of a Ray with the Object(s)
     *
     * @param ray The Ray to intersect
     * @return List of intersection points
     */
    List<Point3D> findIntersections(Ray ray);
}
