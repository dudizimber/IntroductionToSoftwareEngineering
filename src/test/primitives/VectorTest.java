package test.primitives;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import primitives.Vector;

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

/** 
* 
* Method: getPoint() 
* 
*/ 
@Test
public void testGetPoint() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: add(Vector other) 
* 
*/ 
@Test
public void testAdd() throws Exception { 
//TODO: Test goes here... 
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
//TODO: Test goes here... 
} 

/** 
*
* Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
*
*/ 
@Test
public void testCrossProduct() throws Exception {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);

    // ============ Equivalence Partitions Tests ==============
    Vector v3 = new Vector(0, 3, -2);
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
//TODO: Test goes here... 
} 

/** 
* 
* Method: length() 
* 
*/ 
@Test
public void testLength() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: normalize() 
* 
*/ 
@Test
public void testNormalize() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: normalized() 
* 
*/ 
@Test
public void testNormalized() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: equals(Object obj) 
* 
*/ 
@Test
public void testEquals() throws Exception { 
//TODO: Test goes here... 
} 


} 
