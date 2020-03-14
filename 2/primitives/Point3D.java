package primitives;

public class Point3D {

    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    //final Point3D ZERO = new Point3D(0, 0, 0);

    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = new Coordinate(x.get());
        _y = new Coordinate(y.get());
        _z = new Coordinate(z.get());
    }

    Point3D(final Point3D point) {
        _x = point.getX();
        _y = point.getY();
        _z = point.getZ();
    }

    public Coordinate getX() {
        return _x;
    }

    public Coordinate getY() {
        return _y;
    }

    public Coordinate getZ() {
        return _z;
    }

    public Vector subtract(Point3D other) {
        return new Vector(other.getX().get() - _x.get(), other.getY().get() - _y.get(), other.getZ().get() - _z.get());
    }

    public Point3D add(Vector other) {
        return new Point3D(_x.get() + other.getPoint().getX().get(), _y.get() + other.getPoint().getY().get(), _z.get() + other.getPoint().getZ().get());
    }

    public double distanceSquared(Point3D other) {
        double newX = _x.get() - other.getX().get();
        double newY = _y.get() - other.getY().get();
        double newZ = _z.get() - other.getZ().get();
        return (newX * newX) + (newY * newY) + (newZ * newZ);
    }

    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }
}