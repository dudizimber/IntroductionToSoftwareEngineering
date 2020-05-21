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
    default List<GeoPoint> findIntersections(Ray ray) {
        return findIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Find intersections of a Ray with the Object(s), passing the maximum distance
     *
     * @param ray         The Ray to intersect
     * @param maxDistance Maximum distance for intersection
     * @return List of intersection points
     */
    List<GeoPoint> findIntersections(Ray ray, double maxDistance);

    /*
     default List<GeoPoint> findIntersections(Ray ray) {
        return findIntersections(ray, Double.POSITIVE_INFINITY);
    };

    List<GeoPoint> findIntersections(Ray ray, double maxD);
     */

    /**
     * This class represents a point in an Intersectable Object
     */
    class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * Instantiates a new GeoPoint with a Geometry and a Point
         *
         * @param geometry the geometry of the point
         * @param point    the point in the geometry
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public Point3D getPoint() {
            return point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null) return false;
            if (!(obj instanceof GeoPoint))
                return false;
            GeoPoint geoPoint = (GeoPoint) obj;
            return this.point.equals(geoPoint.point) && this.geometry.equals(geoPoint.geometry);
        }

    }

}
