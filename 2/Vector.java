class Vector {
    Point3D _point;


    Vector(final double x, final double y, final double z) {
        _point = new Point(x, y, z);
    }

    Vector(final Coordinate x, final Coordinate y, final Coordinate z) {
        _point = new Point(x, y, z);
    }

    Vector(final Point3D point) {
        _point = new Point(point);
    }

    Vector(final Vector vector) {
        _point = new Point(vector.getPoint());
    }

    public Point3D getPoint() {
        return _point;
    }

}