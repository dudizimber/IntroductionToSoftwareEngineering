package geometries;

import primitives.Point3D;

public class Triangle extends Polygon {

    Triangle(Point3D one, Point3D two, Point3D three) {
        super( new Point3D[] {one, two, three} );
    }
@Override
public String toString() {
     return super.toString();
}
}