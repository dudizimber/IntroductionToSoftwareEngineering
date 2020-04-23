package test.elements;


import elements.Camera;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Testing Integration of Camera Class
 *
 * @author David Zimberknopf & Daniel Grunberger
 */
public class CameraIntegrationTest {

    Camera cam1 = new Camera(
            Point3D.ZERO,
            new Vector(0, 0, 1),
            new Vector(0, -1, 0)
    );
    Camera cam2 = new Camera(
            new Point3D(0, 0, -0.5),
            new Vector(0, 0, 1),
            new Vector(0, -1, 0)
    );
    List<Point3D> results;
    int count = 0, Nx = 3, Ny = 3;

    @Test
    void testIntegrationSphere() {


        //TC01: Two intersection points
        Sphere sphere = new Sphere(new Point3D(0, 0, 3), 1.0);

        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = sphere.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null) count += results.size();
            }
        }
        assertEquals(
                "Should return two intersections",
                2,
                count
        );

        //TC02: Eighteen intersection points
        sphere = new Sphere(new Point3D(0, 0, 2.5), 2.5);
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sphere.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Should return eighteen intersections", 18, count);


    }

    @Test
    void testIntegrationPlane() {


    }

    @Test
    void testIntegrationTriangle() {

    }


}
