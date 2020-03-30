package test.geometries;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.assertTrue;

/**
 * Triangle Tester.
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class TriangleTest {

    /**
     * Test method for {@link Triangle#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
// ============ Equivalence Partitions Tests ==============
        Point3D a = new Point3D(1, 1, 1);
        Point3D b = new Point3D(-2, -3, -4);
        Point3D c = new Point3D(5, 7, 8);
        Triangle tri = new Triangle(a, b, c);
        Vector normal = new Vector(2.0 / 3, 1.0 / 3, -2.0 / 3);
        assertTrue("Error: Triangle getNormal not returning correct value", normal.equals(tri.getNormal(a)));

    }


} 
