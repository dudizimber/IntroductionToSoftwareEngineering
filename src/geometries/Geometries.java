package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents every Geometry in 3D space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Geometries implements Intersectables {

    private List<Intersectables> _geometries;

    public Geometries() {
        //  ArrayList is better for storing and accessing data.
        _geometries = new ArrayList<>();
    }

    public Geometries(Intersectables... geometries) {
        _geometries = new ArrayList<>();
        add(geometries);
    }

    public void add(Intersectables... geometries) {
        for (Intersectables geometry : geometries) {
            _geometries.add(geometry);
        }
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        if (_geometries.isEmpty()) return null;

        List<GeoPoint> intersections = null;

        for (Intersectables geometry : _geometries) {
            List<GeoPoint> geometryIntersections = geometry.findIntersections(ray);

            if (geometryIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<Point3D>();
                intersections.addAll(geometryIntersections);
            }
        }

        return intersections;
    }
}
