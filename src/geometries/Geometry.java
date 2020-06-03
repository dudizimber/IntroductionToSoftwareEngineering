package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * An interface for any geometry in 3D Space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public abstract class Geometry implements Intersectables {
    protected Color _emission;
    protected Material _material;

    /**
     * Instantiates a new Geometry with the emission and material as parameter.
     *
     * @param color the emission color
     */
    public Geometry(Color color, Material material) {
        this._emission = color;
        this._material = material;
    }

    /**
     * Instantiates a new Geometry with the emission as parameter, and default material.
     *
     * @param color the emission color
     */
    public Geometry(Color color) {
        this(color, new Material(0, 0, 0));
    }

    /**
     * Instantiates a new Geometry with the emission as Black color
     */
    public Geometry() {
        this(Color.BLACK, new Material(0, 0, 0));
    }

    /**
     * Get the normal vector
     * @param point
     * @return
     */
    public abstract Vector getNormal(Point3D point);

    /**
     * Gets emission color
     *
     * @return The emission Color
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * Gets material
     *
     * @return The material
     */
    public Material getMaterial() {
        return _material;
    }

}