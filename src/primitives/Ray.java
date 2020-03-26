package primitives;

public class Ray {

    Point3D _point;
    Vector _vector;

    public Ray(Point3D point, Vector vector) {
        _point = point;
        _vector = vector.normalize();
    }

    public Point3D getPoint() {
        return _point;
    }

    public void setPoint(Point3D _point) {
        this._point = _point;
    }

    public Vector getVector() {
        return _vector;
    }

    public void setVector(Vector _vector) {
        this._vector = _vector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getPoint().equals(ray.getPoint()) &&
                getVector().equals(ray.getVector());
    }

}