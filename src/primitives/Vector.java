package primitives;

/**
 * A class that represents a vector in 3d space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Vector {
    private Point3D _point;

    //****** CONSTRUCTORS *******/

    /**
     * Constructor based on three numbers that we turn into coordinates Throws
     * Exception if all params are zero
     *
     * @param x value
     * @param y value
     * @param z value
     */
    public Vector(final double x, final double y, final double z) {
        _point = new Point3D(x, y, z);
        if (_point.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector cannot be zero");
    }

    /**
     * Constructor based on 3d point Throws Exception if all params are zero.
     *
     * @param point head point
     */
    public Vector(final Point3D point) {
        _point = point;
        if (_point.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector cannot be zero");
    }

    //****** GETTERS *******/

    public Point3D getPoint() {
        return _point;
    }

    //****** FUNCTIONS *******/

    /**
     * Sum of two vectors
     *
     * @param other vector to add
     * @return new Vector [other + this]
     */
    public Vector add(Vector other) {
        return new Vector(_point._x._coord + other._point._x._coord,
                _point._y._coord + other._point._y._coord,
                _point._z._coord + other._point._z._coord);
    }

    /**
     * Substract one vector (other) from the other (this)
     *
     * @param other vector to subtract
     * @return new Vector [this - other]
     */
    public Vector subtract(Vector other) {
        return new Vector(_point._x._coord - other._point._x._coord,
                _point._y._coord - other._point._y._coord,
                _point._z._coord - other._point._z._coord);
    }

    /**
     * Multiplies the vector by constant
     *
     * @param constant number to scale by
     * @return new Vector [constant * this]
     */
    public Vector scale(double constant) {
        return new Vector(_point._x._coord * constant, _point._y._coord * constant,
                _point._z._coord * constant);
    }

    /**
     * Dot product of two vectors
     *
     * @param other another vector
     * @return double - dot product of the vectors [this (.) other]
     */
    public double dotProduct(Vector other) {
        double x1 = _point._x._coord;
        double y1 = _point._y._coord;
        double z1 = _point._z._coord;
        double x2 = other._point._x._coord;
        double y2 = other._point._y._coord;
        double z2 = other._point._z._coord;
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    /**
     * Cross product of two vectors
     *
     * @param other another vector
     * @return new Vector
     */
    public Vector crossProduct(Vector other) {
        double x1 = _point._x._coord;
        double y1 = _point._y._coord;
        double z1 = _point._z._coord;
        double x2 = other._point._x._coord;
        double y2 = other._point._y._coord;
        double z2 = other._point._z._coord;
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
        return _point._x._coord * _point._x._coord + _point._y._coord * _point._y._coord + _point._z._coord * _point._z._coord;
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
        _point = new Point3D(_point._x._coord / len, _point._y._coord / len, _point._z._coord / len);
        return this;
    }

    /**
     * Returns a new normalized copy of this Vector
     *
     * @return new normalized copy of this Vector
     */
    public Vector normalized() {
        return new Vector(_point).normalize();
    }

    /**
     * creates a vnormal vector to the vector that calls the function
     *
     * @return orthogonal unit vector
     */
    public Vector normalToVector() {
        double coordinate;
        if (this._point._x._coord > 0) {
            coordinate = this._point._x._coord;
        } else
            coordinate = -this._point._x._coord;
        if (Math.abs(this._point._y._coord) < coordinate) {
            if (this._point._y._coord > 0)
                coordinate = this._point._y._coord;
            else
                coordinate = -this._point._y._coord;
        }
        if (Math.abs(this._point._z._coord) < coordinate) {
            coordinate = 2;//last coordinate that we are checking so no need to reassign coordinate
        }
        if (coordinate == 0)//x is the smallest
            return new Vector(0, -this._point._z._coord, this._point._y._coord).normalize();
        if (coordinate == 1)//y is the the smallest
            return new Vector(-this._point._z._coord, 0, this._point._x._coord).normalize();
        //z is the smallest
        return new Vector(this._point._y._coord, -this._point._x._coord, 0).normalize();
    }

    @Override
    public String toString() {
        return "Vector:\n - " + this._point.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector))
            return false;
        Vector vector = (Vector) obj;
        return _point.equals(vector._point);
    }

}