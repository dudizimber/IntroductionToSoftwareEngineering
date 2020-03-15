package primitives;
/**
 * A 3d point class
 */
public class Point3D {

    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    //final Point3D ZERO = new Point3D(0, 0, 0);
/**
 * Constructor based on three numbers that we turn into coordinates
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
 * @param point
 */
    Point3D(final Point3D point) {
        _x = point.getX();
        _y = point.getY();
        _z = point.getZ();
    }

    public Coordinate getX() {
        return _x;
    }

    public Coordinate getY() {
        return _y;
    }

    public Coordinate getZ() {
        return _z;
    }
/**
 * Subtracts a 3d point from the vector
 * @param other - The 3d point
 * @return the new vector
 */
    public Vector subtract(Point3D other) {
        return new Vector(other.getX().get() - _x.get(), other.getY().get() - _y.get(), other.getZ().get() - _z.get());
    }
/**
 * Adds a vector to a 3D point
 * @param other
 * @return new Point
 */
    public Point3D add(Vector other) {
        return new Point3D(_x.get() + other.getPoint().getX().get(), _y.get() + other.getPoint().getY().get(), _z.get() + other.getPoint().getZ().get());
    }
/**
 * Return the squared  distance of the vector to the 3D point
 * @param other
 * @return
 */
    public double distanceSquared(Point3D other) {
        double newX = _x.get() - other.getX().get();
        double newY = _y.get() - other.getY().get();
        double newZ = _z.get() - other.getZ().get();
        return (newX * newX) + (newY * newY) + (newZ * newZ);
    }
/**
 * Returns the distance of the vector to the  3d point
 * @param other
 * @return
 */
    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }
    @Override
    public String toString() {
        return "x=" +this._x.toString()+" y= "+this._y.toString()+" z= "+this._z.toString();
    }
}