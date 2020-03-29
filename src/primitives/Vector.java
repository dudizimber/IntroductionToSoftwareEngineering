package primitives;

/**
 * A class that represents a vector in 3d space
 */
public class Vector {
    Point3D _point;

    /****** CONTRUCTORS *******/

    /**
     * Constructor based on three numbers that we turn into coordinates Throws
     * Exception if all params are zero
     * 
     * @param x -double
     * @param y - double
     * @param z - double 
     * 
     */
    public Vector(final double x, final double y, final double z) {
        if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z))
            throw new IllegalArgumentException("Vector cannot be zero");
        _point = new Point3D(x, y, z);
    }

    /**
     * Constructor based on three coordinates Throws Exception if all params are
     * zero.
     * 
     * @param x - Coordinate
     * @param y - Coordinate
     * @param z - Coordinate
     * 
     */
    Vector(final Coordinate x, final Coordinate y, final Coordinate z) {
        if (Util.isZero(x.get()) && Util.isZero(y.get()) && Util.isZero(z.get()))
            throw new IllegalArgumentException("Vector cannot be zero");
        _point = new Point3D(x, y, z);
    }

    /**
     * Construcotr based on 3d point Throws Exception if all params are zero.
     * 
     * @param point - Point3D
     * 
     */
    Vector(final Point3D point) {
        if (Util.isZero(point.getX().get()) && Util.isZero(point.getY().get()) && Util.isZero(point.getZ().get()))
            throw new IllegalArgumentException("Vector cannot be zero");
        _point = new Point3D(point);
    }

    /**
     * Constructor based on vector
     * 
     * @param vector - Vector
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
     * @param other - Vector
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
     * @param other - Vector
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
     * @param constant - double
     * @return new Vector [constant * this]
     */
    public Vector scale(double constant) {
        return new Vector(_point.getX().get() * constant, _point.getY().get() * constant,
                _point.getZ().get() * constant);
    }

    /**
     * Dot product of two vectors 
     * 
     * @param other - Vector
     * @return double - dot product of the vectors [this (.) other]
     */
    public double dotProduct(Vector other) {
        /**The mathematical formula is the sum of the
     * multiplied components: Vector A = a1*x + b1*y + c1*z Vector B = a2*x + b2*y +
     * c2*z
     * 
     * A (.) B = a1*a2 + b1*b2 + c1*c2 */
        return _point.getX().get() * other.getPoint().getX().get() + _point.getY().get() * other.getPoint().getY().get()
                + _point.getZ().get() * other.getPoint().getZ().get();
    }

    /**
     * Cross product of two vectors
     * 
     * 
     * 
     * @param other - Vector
     * @return new Vector
     */
    public Vector crossProduct(Vector other) {
        double newX = _point.getY().get() * other.getPoint().getZ().get()
                - _point.getZ().get() * other.getPoint().getY().get();
        double newY = _point.getZ().get() * other.getPoint().getX().get()
                - _point.getX().get() * other.getPoint().getZ().get();
        double newZ = _point.getX().get() * other.getPoint().getY().get()
                - _point.getY().get() * other.getPoint().getX().get();
                
        return new Vector(newX, newY, newZ);
    }

    /**
     * Squared length of the vector. Measured with the distance between the vector
     * point to vector zero
     * 
     * @return Squared lenght of the vector
     */
    public double lengthSquared() {
        return _point.distanceSquared(Point3D.ZERO);
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
        Vector newVector = this.scale(1 / len);
        _point = newVector.getPoint();
        return this;
    }

    /**
     * Returns a new normalized copy of this Vector
     * 
     * @return new normalized copy of this Vector
     */
    public Vector normalized() {
        Vector vec = new Vector(this);
        return vec.normalize();
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
        return _point.getX().get() == vector.getPoint().getX().get() && _point.getY().get() == vector.getPoint().getY().get()
                && _point.getZ().get() == vector.getPoint().getZ().get();
    }

}