package primitives;
/**
 * The class represents a vector
 */
public class Vector {
    Point3D _point;
/**
 * Constructor based on three numbers that we turn into coordinates
 * Cant be zero
 * @param x
 * @param y
 * @param z
 */
    public Vector(final double x, final double y, final double z) {
        if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z))
            throw new IllegalArgumentException("Vector cannot be zero");
        _point = new Point3D(x, y, z);
    }
/**
 * Constructor based on three coordinates
 * Cant be zero
 * @param x
 * @param y
 * @param z
 */
    Vector(final Coordinate x, final Coordinate y, final Coordinate z) {
        if (Util.isZero(x.get()) && Util.isZero(y.get()) && Util.isZero(z.get()))
            throw new IllegalArgumentException("Vector cannot be zero");
        _point = new Point3D(x, y, z);
    }
/**
 * Construcotr based on point
 * Cant be zero
 * @param point
 */
    Vector(final Point3D point) {
        if (Util.isZero(point.getX().get()) && Util.isZero(point.getY().get()) && Util.isZero(point.getZ().get()))
            throw new IllegalArgumentException("Vector cannot be zero");
        _point = new Point3D(point);
    }
/**
 * Constructor based on vector
 * @param vector
 */
    public Vector(final Vector vector) {
        _point = new Point3D(vector.getPoint());
    }

    public Point3D getPoint() {
        return _point;
    }
/**
 * Sum between two vectors
 * @param other
 */
    public Vector add(Vector other) {
        return new Vector(_point.getX().get() + other.getPoint().getX().get(), _point.getY().get() + other.getPoint().getY().get(), _point.getZ().get() + other.getPoint().getZ().get());
    }
    /**
     * Substract one vector from the other
     * @param other
     * @return new vector 
     */
    public Vector subtract(Vector other) {
        return new Vector(_point.getX().get() - other.getPoint().getX().get(), _point.getY().get() - other.getPoint().getY().get(), _point.getZ().get() - other.getPoint().getZ().get());
    }
/**
 * Multiplies the vector with a constant
 * @param constant
 * @return new vector
 */
    public Vector scale(double constant) {
        return new Vector(_point.getX().get() * constant, _point.getY().get() * constant, _point.getZ().get() * constant);
    }
/**
 * dot product between two vectors
 * @param other
 * @return the dot product
 */
    public double dotProduct(Vector other) {
        return _point.getX().get() * other.getPoint().getX().get() + _point.getY().get() * other.getPoint().getY().get() + _point.getZ().get() * other.getPoint().getZ().get();
    }
/**
 * Cross product between two vectors
 * @param other
 * @return new vector
 */
    public Vector crossProduct(Vector other) {
        double newX = _point.getY().get() * other.getPoint().getZ().get() - _point.getZ().get() * other.getPoint().getY().get();
        double newY = _point.getZ().get() * other.getPoint().getX().get() - _point.getX().get() * other.getPoint().getZ().get();
        double newZ = _point.getX().get() * other.getPoint().getY().get() - _point.getY().get() * other.getPoint().getX().get();
        return new Vector(newX, newY, newZ);
    }
/**
 * 
 * @return squared lenght of vector
 */
    public double lengthSquared() {
        return _point.distanceSquared(new Point3D(0, 0, 0));
    }
    /**
     * 
     * @return lenght of vector
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }
/**
 * Normalize this vector
 * @return 
 */
    public Vector normalize() {
        double len = this.length();
        Vector newVector = this.scale(1/len);
        _point = newVector.getPoint();
        return this;
    }
/**
 * 
 * @return new vector that is normalized (not the same one)
 */
    public Vector normalized() {
        Vector vec = new Vector(this);
        return vec.normalize();
    }
    @Override
    public String toString() {
        return this._point.toString();
    }

}