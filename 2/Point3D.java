class Point3D {

    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    final Point3D ZERO = new Point3D(0, 0, 0);

    Point3D(final double x, final double y, final double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    Point3D(final Coordinate x, final Coordinate y, final Coordinate z) {
        _x = x.get();
        _y = y.get();
        _z = z.get();
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
        return _Y;
    }

    public Coordinate getZ() {
        return _z;
    }

    public Vector subtract(Point3D other) {
        // TODO
    }

    public Point3D add(Vector other) {
        // TODO
    }

    public double distanceSquared(Point3D other) {
        double newX = _x - other.getX();
        double newY = _y - other.getY();
        double newZ = _z - other.getZ();
        return (newX * newX) + (newY * newY) + (newZ * newZ);
    }

    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }
}