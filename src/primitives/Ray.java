package primitives;

/**
 * A class that represents a Ray, or a half-line in
 * 3D space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */

public class Ray {

    private Point3D _point;
    private Vector _vector;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on point and vector
     *
     * @param point  Point3D
     * @param vector Vector
     */
    public Ray(Point3D point, Vector vector) {
        _point = point;
        _vector = vector.normalize();
    }

    /****** GETTERS *******/

    public Point3D getPoint() {
        return _point;
    }

    public Vector getVector() {
        return _vector;
    }


    /****** FUNCTIONS *******/

    public Point3D getPoint(double length) {
        return Util.isZero(length) ? _point : _point.add(_vector.scale(length));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getPoint().equals(ray.getPoint()) &&
                getVector().equals(ray.getVector());
    }

}