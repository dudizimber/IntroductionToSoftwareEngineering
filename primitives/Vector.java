package primitives;

/**
 * A class that represents a vector in 3d space
 */
public class Vector {
    private Point3D _point;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on three numbers that we turn into coordinates Throws
     * Exception if all params are zero
     * 
     * @param x
     * @param y
     * @param z
     * 
     */
    public Vector(final double x, final double y, final double z) {
        _point = new Point3D(x, y, z);
        if (_point.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector cannot be zero");
    }

    /**
     * Constructor based on three coordinates Throws Exception if all params are
     * zero.
     * 
     * @param x
     * @param y
     * @param z
     * 
     */
    Vector(final Coordinate x, final Coordinate y, final Coordinate z) {
        _point = new Point3D(x, y, z);
        if (_point.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector cannot be zero");
    }

    /**
     * Constructor based on 3d point Throws Exception if all params are zero.
     * 
     * @param point
     * 
     */
    Vector(final Point3D point) {
        _point = new Point3D(point);
        if (_point.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector cannot be zero");
    }

    /**
     * Constructor based on vector
     * 
     * @param vector
     * 
     */
    public Vector(final Vector vector) {
        _point = new Point3D(vector.getPoint());
    }

    /****** GETTERS *******/

    public Point3D getPoint() {
        return _point;
    }

    /****** FUNCTIONS *******/

    /**
     * Sum of two vectors
     * 
     * @param other
     * @return new Vector [other + this]
     */
    public Vector add(Vector other) {
        return new Vector(_point.getX().get() + other.getPoint().getX().get(),
                _point.getY().get() + other.getPoint().getY().get(),
                _point.getZ().get() + other.getPoint().getZ().get());
    }

    /**
     * Substract one vector (other) from the other (this)
     * 
     * @param other
     * @return new Vector [this - other]
     */
    public Vector subtract(Vector other) {

        return new Vector(_point.getX().get() - other.getPoint().getX().get(),
                _point.getY().get() - other.getPoint().getY().get(),
                _point.getZ().get() - other.getPoint().getZ().get());
    }

    /**
     * Multiplies the vector by constant
     * 
     * @param constant
     * @return new Vector [constant * this]
     */
    public Vector scale(double constant) {
        return new Vector(_point.getX().get() * constant, _point.getY().get() * constant,
                _point.getZ().get() * constant);
    }

    /**
     * Dot product of two vectors The mathematical formula is the sum of the
     * multiplied components
     * 
     * @param other
     * @return double - dot product of the vectors [this (.) other]
     */
    public double dotProduct(Vector other) {
        double x1 = _point.getX().get();
        double y1 = _point.getY().get();
        double z1 = _point.getZ().get();
        double x2 = other.getPoint().getX().get();
        double y2 = other.getPoint().getY().get();
        double z2 = other.getPoint().getZ().get();
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    /**
     * Cross product of two vectors
     *
     * @param other
     * @return new Vector
     */
    public Vector crossProduct(Vector other) {
        double x1 = _point.getX().get();
        double y1 = _point.getY().get();
        double z1 = _point.getZ().get();
        double x2 = other.getPoint().getX().get();
        double y2 = other.getPoint().getY().get();
        double z2 = other.getPoint().getZ().get();
        double newX = y1 * z2 - z1 * y2;
        double newY = z1 * x2 - x1 * z2;
        double newZ = x1 * y2 - y1 * x2;
        return new Vector(newX, newY, newZ);
    }

    /**
     * Squared length of the vector. Measured with the distance between the vector
     * point to vector zero
     * 
     * @return Squared length of the vector
     */
    public double lengthSquared() {
        return Math.pow(_point._x.get(), 2) + Math.pow(_point._y.get(), 2) + Math.pow(_point._z.get(), 2);
    }

    /**
     * Length of the vector Square root of the squared length of the vector
     * 
     * @return Length of the vector
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Normalize this vector Divides each component by the vector's length
     * 
     * @return this
     */
    public Vector normalize() {
        double len = this.length();
        _point = new Point3D(_point._x.get() / len, _point._y.get() / len, _point._z.get() / len);
        return this;
    }

    /**
     * Returns a new normalized copy of this Vector
     * 
     * @return new normalized copy of this Vector
     */
    public Vector normalized() {
        return new Vector(this).normalize();
    }

    @Override
    public String toString() {
        return "Vector:\n - " + this._point.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Vector))
            return false;
        Vector vector = (Vector) obj;
        return _point.equals(vector.getPoint());
    }

}