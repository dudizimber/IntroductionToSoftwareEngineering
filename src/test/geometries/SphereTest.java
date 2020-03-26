package test.geometries; 

import geometries.Sphere;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import primitives.Point3D;
import primitives.Vector;

/** 
* Sphere Tester. 
* 
* @author <Authors name> 
* @since <pre>mar 19, 2020</pre> 
* @version 1.0 
*/ 
public class SphereTest { 

/** 
* 
* Method: getNormal(Point3D point) 
* 
*/ 
@Test
public void testGetNormal() throws Exception {

    Point3D center = new Point3D(0,0,0);
    Point3D point = new Point3D(1, 0,0);

    Vector normal = new Vector(1, 0, 0);

    Sphere sphere = new Sphere(center, 1);
    Vector sNormal = sphere.getNormal(point);

    assertTrue("Error: Sphere getNormal not returning correct value", sNormal.equals(normal));

} 



} 
