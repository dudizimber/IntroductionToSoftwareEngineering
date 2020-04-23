package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * An interface for all intersectables 3D Geometries
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public interface Intersectables {

    /**
     * Find intersections of a Ray with the Geometries
     *
     * @param ray The Ray to intersect
     * @return List of intersection points, null if not intersections found
     */
    List<Point3D> findIntersections(Ray ray);
}
