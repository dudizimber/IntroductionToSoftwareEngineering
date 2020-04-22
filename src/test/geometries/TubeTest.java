package test.geometries;

import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertTrue;

/**
 * Tube Tester.
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class TubeTest {

    /**
     * Test method for {@link Tube#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0));
        Tube tube = new geometries.Tube(ray, 1);
        Point3D point = new Point3D(1, 1, 0);
        Vector tNormal = tube.getNormal(point);
        assertTrue("Error: Tube getNormal not returning correct value", tNormal.equals(new Vector(0, -1, 0)));
    }


} 
