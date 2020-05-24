package test.geometries;

import geometries.Intersectables.GeoPoint;
import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Plane Tester.
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class PlaneTest {

    Plane plane = new Plane(new Point3D(1, 0, 0), new Vector(0, 0, 1));

    /**
     * Test method for {@link Plane#getNormal(Point3D)} )}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point3D a = new Point3D(1, 1, 1);
        Point3D b = new Point3D(-2, -3, -4);
        Point3D c = new Point3D(5, 7, 8);
        Plane plane = new Plane(a, b, c);
        Vector normal = new Vector(2.0 / 3, 1.0 / 3, -2.0 / 3);
        assertTrue("Error: Plane getNormal not returning correct value", normal.equals(plane.getNormal(null)));
    }


    /**
     * Test method for {@link Plane#findIntersections(Ray)}}
     */
    @Test
    public void testFindIntersections() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line intersects Plane (1 points)

        Ray ray = new Ray(new Point3D(1, 1, -3), new Vector(1, 1, 1));

        List result = plane.findIntersections(ray);
        List expected = List.of(new GeoPoint(plane, new Point3D(4, 4, 0)));

        assertEquals("Does not return the correct intersection", result, expected);

        // TC02: Ray's line does not intersects Plane (0 points)

        ray = new Ray(new Point3D(0, 1, 1), new Vector(1, 1, 1));

        result = plane.findIntersections(ray);

        assertEquals("Returning inexistent intersection", result, null);


        // =============== Boundary Values Tests ==================


        // TC03: Ray's parallel to Plane - included (0 points)

        assertNull("Parallel ray included in plane - should return null", plane.findIntersections(new Ray(new Point3D(7, 8, 0), new Vector(9, 1, 0)))
        );


        // TC04: Ray's parallel to Plane - not included (0 points)
        assertNull("Parallel ray not included in plane - should return null", plane.findIntersections(new Ray(new Point3D(1, 1, -3), new Vector(0, 1, 0)))
        );


        // TC05: Ray's orthogonal to Plane - before (1 points)
        assertEquals("Orthogonal ray starts before plane - should return one intersection", List.of(new GeoPoint(plane, new Point3D(1, 1, 0))),
                plane.findIntersections(new Ray(new Point3D(1, 1, -3), new Vector(0, 0, 9.5)))
        );

        // TC06: Ray's orthogonal to Plane - in (0 points)
        assertNull("Orthogonal ray starts in plane - should return null", plane.findIntersections(new Ray(new Point3D(8, 17, 0), new Vector(0, 0, 1)))
        );

        // TC07: Ray's orthogonal to Plane - after (0 points)
        assertNull("Orthogonal ray starts after plane - should return null", plane.findIntersections(new Ray(new Point3D(17, 17, 19), new Vector(0, 0, 21)))
        );

        // TC08: Ray starts in Plane but not orthogonal nor parallel (0 points)
        assertNull("Ray (not orthogonal nor parallel) starts in plane - should return null", plane.findIntersections(new Ray(new Point3D(17, 13, 0), new Vector(-2, -7, 19))));


        // TC09: Ray starts in Plane Q Point but not orthogonal nor parallel (0 points)
        assertNull("Ray (not orthogonal nor parallel) starts in Q Point of plane - should return null", plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-2, -7, 19)))
        );
    }
} 
