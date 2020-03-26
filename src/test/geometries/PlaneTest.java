package test.geometries; 

import geometries.Plane;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import primitives.Point3D;
import primitives.Vector;

/** 
* Plane Tester. 
* 
* @author <Authors name> 
* @since <pre>mar 19, 2020</pre> 
* @version 1.0 
*/ 
public class PlaneTest { 


/** 
* 
* Method: getNormal() 
* 
*/ 
@Test
public void testGetNormal() throws Exception {
    Point3D a = new Point3D(1, 1, 1);
    Point3D b = new Point3D(-2,-3,-4);
    Point3D c = new Point3D(5,7,8);

    Plane plane = new Plane(a, b, c);

    Vector normal = new Vector(2.0/3, 1.0/3, -2.0/3);

    assertTrue("Error: Plane getNormal not returning correct value", normal.equals(plane.getNormal()));
} 

} 
