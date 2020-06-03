package test.geometries;

import geometries.Intersectables.GeoPoint;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Triangle Tester.
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class TriangleTest {

    Triangle tri = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(-1, 0, 0));

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
    /**
     * Test method for {@link Triangle#findIntersections(Ray)}}
     */
    @Test
    public void testFindIntersections() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line intersects inside Triangle (1 points)
        assertEquals("Ray intersects triangle - should return one point", List.of(new GeoPoint(tri, new Point3D(0, 0, 0.5))), tri.findIntersections((new Ray(new Point3D(0, 2, 0.5), new Vector(0, -1, 0)))));

        // TC02: Ray's line intersects inside Triangle against edge (0 points)
        assertEquals("Ray intersects triangle against edge - should return null", null, tri.findIntersections((new Ray(new Point3D(0.5, -2, -1), new Vector(0, 1, 0)))));

        // TC03: Ray's line intersects inside Triangle against vertex (0 points)
        assertEquals("Ray intersects triangle against vertex - should return null", null, tri.findIntersections((new Ray(new Point3D(1.5, -2, -0.2), new Vector(0, 1, 0)))));

        // =============== Boundary Values Tests ==================

        // TC04: Ray's line intersects Triangle's edge  (0 points)
        assertEquals("Ray's line intersects Triangle's edge - should return null", null, tri.findIntersections((new Ray(new Point3D(0.5, -2, 0), new Vector(0, 1, 0)))));

        // TC05: Ray's line intersects Triangle's vertex  (0 points)
        assertEquals("Ray's line intersects Triangle's vertex - should return null", null, tri.findIntersections((new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0)))));

        // TC06: Ray's line intersects Triangle's edge outside Triangle  (0 points)
        assertEquals("Ray's line intersects Triangle's outside Triangle - should return null", null, tri.findIntersections((new Ray(new Point3D(2, -2, 0), new Vector(0, 1, 0)))));
    }

} 
