package test.geometries;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.assertTrue;

/**
 * Sphere Tester.
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class SphereTest {

    /**
     * Test method for {@link Sphere#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
// ============ Equivalence Partitions Tests ==============
        Point3D center = new Point3D(0, 0, 0);
        Point3D point = new Point3D(1, 0, 0);
        Vector normal = new Vector(1, 0, 0);
        Sphere sphere = new Sphere(center, 1);
        Vector sNormal = sphere.getNormal(point);
        assertTrue("Error: Sphere getNormal not returning correct value", sNormal.equals(normal));

    }



} 
