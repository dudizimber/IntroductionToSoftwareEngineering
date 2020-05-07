package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * An interface for any geometry in 3D Space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
abstract class Geometry implements Intersectables {
    protected Color _emmission;


    /**
     * Instantiates a new Geometry with the emission as parameter.
     *
     * @param color the emission color
     */
    public Geometry(Color color) {
        _emmission = color;
    }

    /**
     * Instantiates a new Geometry with the emission as Black color
     */
    public Geometry() {
        _emmission = Color.BLACK;
    }

    public abstract Vector getNormal(Point3D point);

    /**
     * Gets emission color
     *
     * @return The emission Color
     */
    public Color getEmmission() {
        return _emmission;
    }
}