import javafx.geometry.Point3D;

public class Vector {
    Point3D _point;

    Vector(final double x, final double y, final double z) {
        if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z))
            throw IllegalArgumentException("Vector cannot be zero");
        _point = new Point(x, y, z);
    }

    Vector(final Coordinate x, final Coordinate y, final Coordinate z) {
        if (Util.isZero(x.get()) && Util.isZero(y.get()) && Util.isZero(z.get()))
            throw IllegalArgumentException("Vector cannot be zero");
        _point = new Point(x, y, z);
    }

    Vector(final Point3D point) {
        if (Util.isZero(point.getX()) && Util.isZero(point.getY()) && Util.isZero(point.getZ()))
            throw IllegalArgumentException("Vector cannot be zero");
        _point = new Point(point);
    }

    Vector(final Vector vector) {
        _point = new Point(vector.getPoint());
    }

    public Point3D getPoint() {
        return _point;
    }

    public Vector add(Vector other) {
        return new Vector(_point.getX() + other.getPoint().getX(), _point.getY() + other.getPoint().getY(), _point.getZ() + other.getPoint().getZ());
    }
    
    public Vector subtract(Vector other) {
        return new Vector(_point.getX() - other.getPoint().getX(), _point.getY() - other.getPoint().getY(), _point.getZ() - other.getPoint().getZ());
    }

    public Vector scale(double constant) {
        return new Vector(_point.getX() * constant, _point.getY() * constant, _point.getZ() * constant);
    }

    public double dotProduct(Vector other) {
        return _point.getX() * other.getPoint().getX() + _point.getY() * other.getPoint().getY() + _point.getZ() * other.getPoint().getZ();
    }

    public double lengthSquared() {
        return _point.distanceSquared(ZERO);
    }
    
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize() {
        _point.scale(1/length());
        return this;
    }

    public Vector normalized() {
        Vector vec = new Vector(this);
        return vec.normalized();
    }

}