package geometries;

import primitives.Point3D;
/**
 * Triangle class. Is a polygon with 3 points only
 */
public class Triangle extends Polygon {
/**
 * Constructor based on the three points. Call to the father`s constructor
 */
    Triangle(Point3D one, Point3D two, Point3D three) {
        super( new Point3D[] {one, two, three} );
    }
@Override
public String toString() {
     return super.toString();
}
}