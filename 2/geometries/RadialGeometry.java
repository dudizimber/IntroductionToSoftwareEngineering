package geometries;

abstract class RadialGeometry {
    double _radius;

    RadialGeometry(double radius) {
        _radius = radius;
    }

    RadialGeometry(RadialGeometry other) {
        _radius = other.getRadius();
    }

    public double getRadius() {
        return _radius;
    }
    @Override
    public String toString() {
        return Double.toString(this._radius);
    }
}