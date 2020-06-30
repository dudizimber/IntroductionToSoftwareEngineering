package primitives;

/**
 * A class that represents a point in 3d space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class Point3D {

    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    public static Point3D ZERO = new Point3D(0, 0, 0);

    // ****** CONSTRUCTORS *******

    /**
     * Constructor based on three doubles that we turn into coordinates
     *
     * @param x value
     * @param y value
     * @param z value
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * Constructor based on 3 coordinates
     *
     * @param x value
     * @param y value
     * @param z value
     */
    Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = x;
        _y = y;
        _z = z;
    }

    //****** GETTERS *******/

    /**
     * gets the coordinate x
     *
     * @return x value
     */
    public double getX() {
        return _x._coord;
    }

    /**
     * Gets the coordinate y
     *
     * @return y value
     */
    public double getY() {
        return _y._coord;
    }

    /**
     * Gets the coordinate z
     *
     * @return z value
     */
    public double getZ() {
        return _z._coord;
    }

    //****** FUNCTIONS *******/

    /**
     * Returns a new Vector which it's coordinates are the subtraction of other
     * point by this point
     *
     * @param other point to subtract
     * @return new Vector created by the subtraction [other - this]
     */
    public Vector subtract(Point3D other) {
        return new Vector(_x._coord - other._x._coord, _y._coord - other._y._coord, _z._coord - other._z._coord);
    }

    /**
     * Returns a new Vector which it's coordinates are the sum of other point and
     * this point
     * 
     * @param other Point3D
     * @return new Vector created by the sum [other + this]
     */
    public Point3D add(Vector other) {
        double x = _x._coord + other.getPoint()._x._coord;
        double y = _y._coord + other.getPoint()._y._coord;
        double z = _z._coord + other.getPoint()._z._coord;
        return new Point3D(x, y, z);
    }

    /**
     * Return the squared distance of two 3d points
     *
     * @param other Point3D
     * @return double The distance between [this, other]
     */
    public double distanceSquared(Point3D other) {
        double newX = _x._coord - other._x._coord;
        double newY = _y._coord - other._y._coord;
        double newZ = _z._coord - other._z._coord;
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
        return point._x.equals(_x) && point._y.equals(_y) && point._z.equals(_z);
    }

}