package test.primitives;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * Vector Tester.
 *
 * @author David Zimberknopf and Daniel Grunberger
 */
public class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for {@link Vector#Vector(double, double, double)}.
     */
    @Test
    public void testVectorZero() {
        // =============== Boundary Values Tests ==================
        try {
            new Vector(0, 0, 0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {
        }
    }


    /**
     * Test method for {@link Vector#add(Vector)}.
     */
    @Test
    public void testAdd() {
        Vector v = new Vector(-1, -2, -3);
        assertTrue("ERROR: add() does not return the correct value", v1.add(v2).equals(v));
    }

    /**
     * Test method for {@link Vector#subtract(Vector)}
     */
    @Test
    public void testSubtract() {
        Vector v = new Vector(3, 6, 9);
        assertTrue("ERROR: subtract() does not return the correct value", v1.subtract(v2).equals(v));
    }

    /**
     * Test method for {@link Vector#scale(double)}
     */
    @Test
    public void testScale() {
        Vector v = new Vector(2, 4, 6);
        assertTrue("ERROR: scale() does not return the correct value", v1.scale(2).equals(v));
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}
     */
    @Test
    public void testDotProduct() {
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
        assertTrue("ERROR: dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
    }

    /**
     * Test method for {@link Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }

    }

    /**
     * Test method for {@link Vector#lengthSquared()}
     */
    @Test
    public void testLengthSquared() {
        assertTrue("ERROR: lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
    }

    /**
     * Test method for {@link Vector#length()}
     */
    @Test
    public void testLength() {
        assertTrue("ERROR: length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
    }

    /**
     * Test method for {@link Vector#normalize()}
     */
    @Test
    public void testNormalize() {
        Vector vCopy = new Vector(v1);
        Vector vCopyNormalize = vCopy.normalize();
        assertFalse("ERROR: normalize() function creates a new vector", vCopy != vCopyNormalize);
        assertTrue("ERROR: normalize() result is not a unit vector", isZero(vCopyNormalize.length() - 1));
    }

    /**
     * Test method for {@link Vector#normalized()}
     */
    @Test
    public void testNormalized() {
        Vector u = v1.normalized();
        assertFalse("ERROR: normalizated() function does not create a new vector", u == v1);
    }

} 
