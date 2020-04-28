package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

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

    /**
     * Constructs Ray through a single pixel of the screen
     *
     * @param nX             Number of pixels in X axis
     * @param nY             Number of pixels in Y axis
     * @param j              The current pixel in Y axis
     * @param i              The current pixel in X axis
     * @param screenDistance Distance from camera to screen
     * @param screenWidth    Screen width
     * @param screenHeight   Screen height
     * @return the generated ray
     * @throws IllegalArgumentException if distance to screen is zero
     */
    public Ray constructRayThroughPixel(int nX, int nY,
                                        int j, int i, double screenDistance,
                                        double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("Distance to screen cannot be zero");
        }

        Point3D pc = _p0.add(_vectorTowards.scale(screenDistance));

        double ry = screenHeight / nY;
        double rx = screenWidth / nX;

        double yi = ((i - nY / 2d) * ry + ry / 2d);
        double xj = ((j - nX / 2d) * rx + rx / 2d);

        Point3D pij = pc;

        if (!isZero(xj)) {
            pij = pij.add(_vectorRight.scale(xj));
        }
        if (!isZero(yi)) {
            pij = pij.add(_vectorUp.scale(-yi));
        }

        Vector vij = pij.subtract(_p0);

        return new Ray(_p0, vij);

    }


}
