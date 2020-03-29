package test.primitives;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

/** 
* Vector Tester. 
* 
* @author <Authors name> 
* @since <pre>mar 19, 2020</pre> 
* @version 1.0 
*/ 
public class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /**
     *
     * Method: vectorZero()
     *
     */
    @Test
    public void testVectorZero() throws Exception {
                // =============== Boundary Values Tests ==================
        try {
            new Vector(0, 0, 0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {
        }
    }


    /**
* 
* Method: add(Vector other) 
* 
*/ 
@Test
public void testAdd() throws Exception {
    Vector v = new Vector(-1, -2, -3);
    assertTrue("ERROR: add() does not return the correct value", v1.add(v2).equals(v));
} 

/** 
* 
* Method: subtract(Vector other) 
* 
*/ 
@Test
public void testSubtract() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: scale(double constant) 
* 
*/ 
@Test
public void testScale() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: dotProduct(Vector other) 
* 
*/ 
@Test
public void testDotProduct() throws Exception {
    assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
    assertTrue("ERROR: dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
} 

/** 
*
* Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
*
*/ 
@Test
public void testCrossProduct() throws Exception {
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
    } catch (Exception e) {}

} 

/** 
* 
* Method: lengthSquared() 
* 
*/ 
@Test
public void testLengthSquared() throws Exception {
    assertTrue("ERROR: lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
} 

/** 
* 
* Method: length() 
* 
*/ 
@Test
public void testLength() throws Exception {
    assertTrue("ERROR: length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
} 

/** 
* 
* Method: normalize() 
* 
*/ 
@Test
public void testNormalize() throws Exception {
    Vector vCopy = new Vector(v1);
    Vector vCopyNormalize = vCopy.normalize();
    assertFalse("ERROR: normalize() function creates a new vector", vCopy != vCopyNormalize);
    assertTrue("ERROR: normalize() result is not a unit vector", isZero(vCopyNormalize.length() - 1));
} 

/** 
* 
* Method: normalized() 
* 
*/ 
@Test
public void testNormalized() throws Exception {
    Vector u = v1.normalized();
    assertFalse("ERROR: normalizated() function does not create a new vector", u == v1);
}

} 
