package primitives;

/**
 * A class that represents a point in 3d space
 */
public class Point3D {

    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    static Point3D ZERO = new Point3D(0, 0, 0);

    /****** CONTRUCTORS *******/

    /**
     * Constructor based on three doubles that we turn into coordinates
     * 
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * Constructor based on 3 coordinates
     * 
     * @param x
     * @param y
     * @param z
     */
    Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = new Coordinate(x.get());
        _y = new Coordinate(y.get());
        _z = new Coordinate(z.get());
    }

    /**
     * Constructor based on a point
     * 
     * @param point
     */
    Point3D(final Point3D point) {
        _x = point.getX();
        _y = point.getY();
        _z = point.getZ();
    }

    /****** GETTERS *******/

    public Coordinate getX() {
        return _x;
    }

    public Coordinate getY() {
        return _y;
    }

    public Coordinate getZ() {
        return _z;
    }

    /****** FUNCTIONS *******/

    /**
     * Returns a new Vector which it's coordinates are the subtraction of other
     * point by this point
     * 
     * @param other Point3D
     * @return new Vector created by the subtraction [other - this]
     */
    public Vector subtract(Point3D other) {
        return new Vector(other.getX().get() - _x.get(), other.getY().get() - _y.get(), other.getZ().get() - _z.get());
    }

    /**
     * Returns a new Vector which it's coordinates are the sum of other point and
     * this point
     * 
     * @param other Point3D
     * @return new Vector created by the sum [other + this]
     */
    public Point3D add(Vector other) {
        return new Point3D(_x.get() + other.getPoint().getX().get(), _y.get() + other.getPoint().getY().get(),
                _z.get() + other.getPoint().getZ().get());
    }

    /**
     * Return the squared distance of two 3d points
     * 
     * @param other
     * @return double The distance between [this, other]
     */
    public double distanceSquared(Point3D other) {
        double newX = _x.get() - other.getX().get();
        double newY = _y.get() - other.getY().get();
        double newZ = _z.get() - other.getZ().get();
        return (newX * newX) + (newY * newY) + (newZ * newZ);
    }

    /**
     * Returns the distance of the vector to the 3d point
     * 
     * @param other
     * @return double The squared root of the distance squared
     */
    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }

    @Override
    public String toString() {
        return "Point3D:\nx = " + this._x.toString() + "\ny = " + this._y.toString() + "\nz = " + this._z.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Point3D))
            return false;
        Point3D point = (Point3D) obj;
        return point.getX() == _x && point.getY() == _y && point.getZ() == _z;
    }

}