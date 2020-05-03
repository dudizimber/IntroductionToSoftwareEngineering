package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectables;
import primitives.Color;

/**
 * The Scene itself
 *
 * @author David Zimberknopf & Daniel Grunberger
 */
public class Scene {
    private final String _name;
    private final Geometries _geometries;
    private Color _background;
    private AmbientLight _ambientLight;
    private Camera _camera;
    private double _distance;

    /**
     * Creates an empty scene with the specified name
     *
     * @param _sceneName The name of the scene
     */
    public Scene(String _sceneName) {
        this._name = _sceneName;
        this._geometries = new Geometries();
    }

    /**
     * Get the geometries
     *
     * @return the geometries
     */
    public Geometries getGeometries() {
        return _geometries;
    }

    /**
     * Get the ambient light
     *
     * @return the ambient light
     */
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * Set the ambient light
     */
    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     * Get the camera
     *
     * @return the camera
     */
    public Camera getCamera() {
        return _camera;
    }

    /**
     * Set the camera
     */
    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    /**
     * Get the distance
     *
     * @return the distance
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * Set the distance
     */
    public void setDistance(double _distance) {
        this._distance = _distance;
    }

    /**
     * Get the background color
     *
     * @return the background color
     */
    public Color getBackground() {
        return this._background;
    }

    /**
     * Set the background color
     */
    public void setBackground(Color _background) {
        this._background = _background;
    }

    /**
     * Add a list of geometries to the scene
     */
    public void addGeometries(Intersectables... intersectables) {
        for (Intersectables i : intersectables)
            _geometries.add(i);
    }

}