package primitives;

public class Ray {

    private Point3D _point;
    private Vector _vector;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on point and vector
     *
     * @param point Point3D
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getPoint().equals(ray.getPoint()) &&
                getVector().equals(ray.getVector());
    }

}