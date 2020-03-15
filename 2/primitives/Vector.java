package primitives;

public class Vector {
    Point3D _point;

    public Vector(final double x, final double y, final double z) {
        if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z))
            throw new IllegalArgumentException("Vector cannot be zero");
        _point = new Point3D(x, y, z);
    }

    Vector(final Coordinate x, final Coordinate y, final Coordinate z) {
        if (Util.isZero(x.get()) && Util.isZero(y.get()) && Util.isZero(z.get()))
            throw new IllegalArgumentException("Vector cannot be zero");
        _point = new Point3D(x, y, z);
    }

    Vector(final Point3D point) {
        if (Util.isZero(point.getX().get()) && Util.isZero(point.getY().get()) && Util.isZero(point.getZ().get()))
            throw new IllegalArgumentException("Vector cannot be zero");
        _point = new Point3D(point);
    }

    public Vector(final Vector vector) {
        _point = new Point3D(vector.getPoint());
    }

    public Point3D getPoint() {
        return _point;
    }

    public Vector add(Vector other) {
        return new Vector(_point.getX().get() + other.getPoint().getX().get(), _point.getY().get() + other.getPoint().getY().get(), _point.getZ().get() + other.getPoint().getZ().get());
    }
    
    public Vector subtract(Vector other) {
        return new Vector(_point.getX().get() - other.getPoint().getX().get(), _point.getY().get() - other.getPoint().getY().get(), _point.getZ().get() - other.getPoint().getZ().get());
    }

    public Vector scale(double constant) {
        return new Vector(_point.getX().get() * constant, _point.getY().get() * constant, _point.getZ().get() * constant);
    }

    public double dotProduct(Vector other) {
        return _point.getX().get() * other.getPoint().getX().get() + _point.getY().get() * other.getPoint().getY().get() + _point.getZ().get() * other.getPoint().getZ().get();
    }

    public Vector crossProduct(Vector other) {
        double newX = _point.getY().get() * other.getPoint().getZ().get() - _point.getZ().get() * other.getPoint().getY().get();
        double newY = _point.getZ().get() * other.getPoint().getX().get() - _point.getX().get() * other.getPoint().getZ().get();
        double newZ = _point.getX().get() * other.getPoint().getY().get() - _point.getY().get() * other.getPoint().getX().get();
        return new Vector(newX, newY, newZ);
    }

    public double lengthSquared() {
        return _point.distanceSquared(new Point3D(0, 0, 0));
    }
    
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize() {
        double len = this.length();
        Vector newVector = this.scale(1/len);
        _point = newVector.getPoint();
        return this;
    }

    public Vector normalized() {
        Vector vec = new Vector(this);
        return vec.normalize();
    }
    @Override
    public String toString() {
        return this._point.toString();
    }

}