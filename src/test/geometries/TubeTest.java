package test.geometries; 

import geometries.Tube;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;

/** 
* Tube Tester. 
* 
* @author <Authors name> 
* @since <pre>mar 19, 2020</pre> 
* @version 1.0 
*/ 
public class TubeTest { 

/** 
* 
* Method: getNormal(Point3D other) 
* 
*/ 
@Test
public void testGetNormal() throws Exception {
    Ray ray = new Ray(new Point3D(0,0,0), new Vector(1,0,0));
    Tube tube = new geometries.Tube(ray, 1);

    Point3D point = new Point3D(1, 1, 0);

    Vector tNormal = tube.getNormal(point);

    assertTrue("Error: Tube getNormal not returning correct value", tNormal.equals(new Vector(0,1,0)));
} 


} 
