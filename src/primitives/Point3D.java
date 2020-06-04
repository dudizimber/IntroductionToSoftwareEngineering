package primitives;

/**
 * A class that represents a point in 3d space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Point3D {

    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    public static Point3D ZERO = new Point3D(0, 0, 0);

    /****** CONSTRUCTORS *******/

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
    public Point3D(final Point3D point) {
        _x = point.getX();
        _y = point.getY();
        _z = point.getZ();
    }

    /****** GETTERS *******/
    /**
     * ets the coordinate x
     * @return
     */
    public Coordinate getX() {
        return _x;
    }
    /**
     *Gets the coordinate y
     * @return
     */
    public Coordinate getY() {
        return _y;
    }
    /**
     *Gets the coordinate z
     * @return
     */
    public Coordinate getZ() {
        return _z;
    }
    /**
     *GGets the coordinate x as double
     * @return
     */
    public double getAsDoubleX() {
        return _x.get();
    }
    /**
     *Gets the coordinate y as double
     * @return
     */
    public double getAsDoubleY() {
        return _y.get();
    }
    /**
     *Gets the coordinate z as double
     * @return
     */
    public double getAsDoubleZ() {
        return _z.get();
    }

    /****** FUNCTIONS *******/

    /**
     * Returns a new Vector which it's coordinates are the subtraction of other
     * point by this point
     *
     * @param other
     * @return new Vector created by the subtraction [other - this]
     */
    public Vector subtract(Point3D other) {
        return new Vector(getAsDoubleX() - other.getAsDoubleX(), getAsDoubleY() - other.getAsDoubleY(), getAsDoubleZ() - other.getAsDoubleZ());
    }

    /**
     * Returns a new Vector which it's coordinates are the sum of other point and
     * this point
     * 
     * @param other Point3D
     * @return new Vector created by the sum [other + this]
     */
    public Point3D add(Vector other) {
        double x = getAsDoubleX() + other.getPoint().getAsDoubleX();
        double y = getAsDoubleY() + other.getPoint().getAsDoubleY();
        double z = getAsDoubleZ() + other.getPoint().getAsDoubleZ();
        return new Point3D(x, y, z);
    }

    /**
     * Return the squared distance of two 3d points
     *
     * @param other Point3D
     * @return double The distance between [this, other]
     */
    public double distanceSquared(Point3D other) {
        double newX = getAsDoubleX() - other.getAsDoubleX();
        double newY = getAsDoubleY() - other.getAsDoubleY();
        double newZ = getAsDoubleZ() - other.getAsDoubleZ();
        return (newX * newX) + (newY * newY) + (newZ * newZ);
    }

    /**
     * Returns the distance of the vector to the 3d point
     *
     * @param other Point3D
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
        if (obj == null) return false;
        if (!(obj instanceof Point3D))
            return false;
        Point3D point = (Point3D) obj;
        return point.getX().equals(_x) && point.getY().equals(_y) && point.getZ().equals(_z);
    }

}