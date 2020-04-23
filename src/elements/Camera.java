package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The type Camera.
 *
 * @author David Zimberknopf & Daniel Grunberger
 */
public class Camera {
    private Point3D _p0;
    private Vector _vectorTowards;
    private Vector _vectorUp;
    private Vector _vectorRight;


    /******* CONSTRUCTORS *******/

    /**
     * Instantiates a new Camera.
     *
     * @param _p0            the p0
     * @param _vectorTowards the vector towards
     * @param _vectorUp      the vector up
     * @throws IllegalArgumentException if vectors up and towards are not orthogonal
     */
    public Camera(Point3D _p0, Vector _vectorTowards, Vector _vectorUp) {

        if (_vectorTowards.dotProduct(_vectorUp) != 0)
            throw new IllegalArgumentException("Vectors Up and Towards are not orthogonal");

        this._p0 = new Point3D(_p0);
        this._vectorTowards = _vectorTowards.normalized();
        this._vectorUp = _vectorUp.normalized();
        this._vectorRight = this._vectorTowards.crossProduct(this._vectorUp);
    }


    /******* GETTERS  *******/

    /**
     * Gets point 0
     *
     * @return the p 0
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * Gets vector towards.
     *
     * @return the vector towards
     */
    public Vector getVectorTowards() {
        return _vectorTowards;
    }

    /**
     * Gets vector up.
     *
     * @return the vector up
     */
    public Vector getVectorUp() {
        return _vectorUp;
    }


    /**
     * Gets vector right.
     *
     * @return the vector right
     */
    public Vector getVectorRight() {
        return _vectorRight;
    }


    /***** FUNCTIONS *******/

    public Ray constructRayThroughPixel(int nX, int nY,
                                        int j, int i, double screenDistance,
                                        double screenWidth, double screenHeight) {
        return null;
    }


}
