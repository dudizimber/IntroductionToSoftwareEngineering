package primitives;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.randomNumber;

/**
 * A class that represents a Ray, or a half-line in
 * 3D space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */

public class Ray {

    private Point3D _point;
    private Vector _vector;
    private static final double DELTA = 0.1;

    //****** CONSTRUCTORS *******/

    /**
     * Constructor based on point , direction and normal
     *
     * @param point     head point
     * @param direction ray direction
     * @param normal    linbe'~s vector to move the head by
     */
    public Ray(Point3D point, Vector direction, Vector normal) {
        _vector = direction.normalized();
        double nv = normal.dotProduct(direction);
        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        _point = point.add(normalDelta);
    }

    /**
     * Constructor based on point and vector
     *
     * @param point     ray head
     * @param direction ray direction
     */
    public Ray(Point3D point, Vector direction) {
        _point = point;
        _vector = direction.normalized();
    }

    //****** GETTERS *******/

    /**
     * Gets the point
     *
     * @return the point
     */
    public Point3D getPoint() {
        return _point;
    }

    /**
     * Gets the ray'~s direction vector
     *
     * @return the vector
     */
    public Vector getVector() {
        return _vector;
    }


    //****** FUNCTIONS *******/

    /**
     * @param length distance to scale vector
     * @return point in Ray
     */
    public Point3D getPoint(double length) {
        return Util.isZero(length) ? _point : _point.add(_vector.scale(length));
    }

    /**
     * Creates beam of rays randomly in the circle
     *
     * @param n        normal to the surface where the beam starts
     * @param distance distance of target area
     * @param num      amount of rays
     * @return list of rays
     */
    public List<Ray> createBeamOfRays(Vector n, double distance, int num, double r) {
        if (num <= 1 || distance <= 0 || r <= 0) return List.of(this);
        List<Ray> beam = new LinkedList<>();
        beam.add(this);//the original ray that calls the function - there has to be at least one beam
        if (num == 1)//if no additional rays were requested here  there is nothing else to do in this function
            return beam;
        Vector w = this._vector.normalToVector();
        Vector v = this._vector.crossProduct(w).normalize();

        Point3D center = this.getPoint(distance);
        Point3D randomP = Point3D.ZERO;
        double xRandom, yRandom;
        double nDotDirection = alignZero(n.dotProduct(this._vector));
        for (int i = 1; i < num; i++) {
            xRandom = randomNumber(-r, r);
            yRandom = randomNumber(-r, r);
            if (xRandom * xRandom + yRandom * yRandom > r * r) continue;
            if (xRandom != 0)
                randomP = center.add(w.scale(xRandom));
            if (yRandom != 0)
                randomP = center.add(v.scale(yRandom));
            Vector t = randomP.subtract(this._point);
            double normalDotT = alignZero(n.dotProduct(t));
            if (nDotDirection * normalDotT > 0)
                beam.add(new Ray(this._point, t));
        }
        return beam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return _point.equals(ray._point) &&
                _vector.equals(ray._vector);
    }

}