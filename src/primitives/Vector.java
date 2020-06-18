package primitives;

/**
 * A class that represents a vector in 3d space
 *
 * @author David Zimberknopf and Daniel Grunberger
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
        return new Vector(_point.getAsDoubleX() + other.getPoint().getAsDoubleX(),
                _point.getAsDoubleY() + other.getPoint().getAsDoubleY(),
                _point.getAsDoubleZ() + other.getPoint().getAsDoubleZ());
    }

    /**
     * Substract one vector (other) from the other (this)
     *
     * @param other
     * @return new Vector [this - other]
     */
    public Vector subtract(Vector other) {
        return new Vector(_point.getAsDoubleX() - other.getPoint().getAsDoubleX(),
                _point.getAsDoubleY() - other.getPoint().getAsDoubleY(),
                _point.getAsDoubleZ() - other.getPoint().getAsDoubleZ());
    }

    /**
     * Multiplies the vector by constant
     *
     * @param constant
     * @return new Vector [constant * this]
     */
    public Vector scale(double constant) {
        return new Vector(_point.getAsDoubleX() * constant, _point.getAsDoubleY() * constant,
                _point.getAsDoubleZ() * constant);
    }

    /**
     * Dot product of two vectors
     *
     * @param other
     * @return double - dot product of the vectors [this (.) other]
     */
    public double dotProduct(Vector other) {
        double x1 = _point.getAsDoubleX();
        double y1 = _point.getAsDoubleY();
        double z1 = _point.getAsDoubleZ();
        double x2 = other.getPoint().getAsDoubleX();
        double y2 = other.getPoint().getAsDoubleY();
        double z2 = other.getPoint().getAsDoubleZ();
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    /**
     * Cross product of two vectors
     *
     * @param other
     * @return new Vector
     */
    public Vector crossProduct(Vector other) {
        double x1 = _point.getAsDoubleX();
        double y1 = _point.getAsDoubleY();
        double z1 = _point.getAsDoubleZ();
        double x2 = other.getPoint().getAsDoubleX();
        double y2 = other.getPoint().getAsDoubleY();
        double z2 = other.getPoint().getAsDoubleZ();
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
        return _point.getAsDoubleX() * _point.getAsDoubleX() + _point.getAsDoubleY() * _point.getAsDoubleY() + _point.getAsDoubleZ() * _point.getAsDoubleZ();
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
        _point = new Point3D(_point.getAsDoubleX() / len, _point.getAsDoubleY() / len, _point.getAsDoubleZ() / len);
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

    /**
     *  creates a vnormal vector to the vector that calls the function
     * @return
     */
    public Vector normalToVector()
    {
        int min=0;
        double coordinate;
        if(this.getPoint()._x.get()>0)
        {
            coordinate = this.getPoint()._x.get();
        }
        else
            coordinate=-this.getPoint()._x.get();
        if(Math.abs(this.getPoint()._y.get())<coordinate)
        {
            coordinate=1;
            if(this.getPoint()._y.get()>0)
                coordinate=this.getPoint()._y.get();
            else
                coordinate=-this.getPoint()._y.get();
        }
        if(Math.abs(this.getPoint()._z.get())<coordinate)
        {
            coordinate=2;//last coordinate that we are checking so no need to reassign coordinate
        }
        if(coordinate==0)//x is the smallest
            return new Vector(0,-this.getPoint()._z.get(),this.getPoint()._y.get()).normalize();
        if(coordinate==1)//y is the the smallest
            return new Vector(-this.getPoint()._z.get(),0,this.getPoint()._x.get()).normalize();
        //z is the smallest
        return new Vector(this.getPoint()._y.get(),-this.getPoint()._x.get(),0).normalize();
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
        return _point.equals(vector.getPoint());
    }

}