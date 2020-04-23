package test.geometries;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Geometries Tester.
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class GeometriesTest {

    /**
     * Test method for {@link Geometries#findIntersections(Ray)}
     */
    @Test
    public void testFindIntersections() {

        Geometries geometriesListOne = new Geometries();

        // =============== Boundary Values Tests ==================

        // TC01: Empty geometry list (0 points)
        assertNull("Empty geometries - should return null", geometriesListOne.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(2, 1, 1))));

        geometriesListOne.add(new Sphere(new Point3D(1.0, 0.0, 0.0), 1.0));
        geometriesListOne.add(new Plane(new Point3D(1.0, 1.0, 0.0), new Vector(0.0, 0.0, 1.0)));
        geometriesListOne.add(new Triangle(new Point3D(1.0, 0.0, 0.0), new Point3D(0.0, 1.0, 0.0), new Point3D(0.0, 0.0, 1.0)));

        //TC02: No intersections with any geometry (0 points)
        assertNull("No intersections - should return null", geometriesListOne.findIntersections(new Ray(new Point3D(0.0, 0.0, 2.0), new Vector(0.0, -1.0, 0.0))));

        //TC03: One intersection with one geometry (1 point)
        assertEquals("Wrong number of intersections - expected 1", 1, geometriesListOne.findIntersections(new Ray(new Point3D(0.0, 5.0, -1.0), new Vector(0.0, 0.0, 1.0))).size());

        //TC04: Intersections with all geometries (3 points)
        Geometries geometriesListTwo = new Geometries();
        geometriesListTwo.add(new Sphere(new Point3D(0.0, 5.0, 1.0), 1.0));
        geometriesListTwo.add(new Plane(new Point3D(0.0, 1.0, 2.0), new Vector(0.0, -1.0, 0.0)));
        geometriesListTwo.add(new Triangle(new Point3D(0.4, 3.0, 1.0), new Point3D(0.0, 3.0, 1.0), new Point3D(2.0, 3.0, 5.0)));
        assertEquals("Wrong number of intersections - expected 3", 3, geometriesListTwo.findIntersections(new Ray(Point3D.ZERO, new Vector(0.0, 5.0, 1.0))).size());


        // ============ Equivalence Partitions Tests ==============

        // TC05: Few Geometries are intersected, but not all of them (2 points)
        assertEquals("Wrong number of intersections - expected 2", 2, geometriesListOne.findIntersections(new Ray(new Point3D(1.0, 0.0, -1.0), new Vector(0.0, 0.0, 1.0))).size());

    }


} 
