package test.geometries; 

import geometries.Plane;
import geometries.Triangle;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.assertTrue;

/** 
* Triangle Tester. 
* 
* @author <Authors name> 
* @since <pre>mar 19, 2020</pre> 
* @version 1.0 
*/ 
public class TriangleTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
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

@Test
public void testGetNormal() throws Exception {
// ============ Equivalence Partitions Tests ==============
    Point3D a = new Point3D(1, 1, 1);
    Point3D b = new Point3D(-2,-3,-4);
    Point3D c = new Point3D(5,7,8);
    Triangle tri = new Triangle(a, b, c);
    Vector normal = new Vector(2.0/3, 1.0/3, -2.0/3);
    assertTrue("Error: Triangle getNormal not returning correct value", normal.equals(tri.getNormal(a)));

}


} 
