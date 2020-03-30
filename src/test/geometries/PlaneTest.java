package test.geometries;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.assertTrue;

/**
 * Plane Tester.
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class PlaneTest {


    /**
     * Test method for {@link Plane#getNormal()}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point3D a = new Point3D(1, 1, 1);
        Point3D b = new Point3D(-2, -3, -4);
        Point3D c = new Point3D(5, 7, 8);
        Plane plane = new Plane(a, b, c);
        Vector normal = new Vector(2.0 / 3, 1.0 / 3, -2.0 / 3);
        assertTrue("Error: Plane getNormal not returning correct value", normal.equals(plane.getNormal()));
    }

} 
