package test.elements;


import elements.Camera;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
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


    /**
     * Test method for {@link Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}
     */
    @Test
    public void testIntegrationSphere() {


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
        count = 0;
        sphere = new Sphere(new Point3D(0, 0, 2.5), 2.5);
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sphere.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Should return eighteen intersections", 18, count);

        //TC03: Ten intersection points
        count = 0;
        sphere = new Sphere(new Point3D(0, 0, 2), 2);
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sphere.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Should return ten intersections", 10, count);

        //TC04: Nine intersection points
        count = 0;
        sphere = new Sphere(new Point3D(0, 0, 2.5), 4);
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sphere.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Should return nine intersections", 9, count);

        //TC05: Zero intersection points
        count = 0;
        sphere = new Sphere(new Point3D(0, 0, -1), .5);
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sphere.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Should return zero intersections", 0, count);


    }

    /**
     * Test method for {@link Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}
     */
    @Test
    public void testIntegrationPlane() {


        //TC01: Nine intersection points - Plane 1
        count = 0;
        Plane plane = new Plane(new Point3D(0, 0, 3), new Vector(0, 0, 1));
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                results = plane.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null) count += results.size();
            }
        }
        assertEquals(
                "Should return nine intersections",
                9,
                count
        );

        //TC02: Nine intersection points - Plane 2
        count = 0;
        plane = new Plane(new Point3D(0, 0, 3), new Vector(0, -0.5, 1));
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = plane.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Should return nine intersections", 9, count);

        //TC03: Six intersection points
        count = 0;
        plane = new Plane(new Point3D(0, 0, 3), new Vector(0, -3, 1));
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = plane.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Should return six intersections", 6, count);

    }

    /**
     * Test method for {@link Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}
     */
    @Test
    public void testIntegrationTriangle() {


        //TC01: One intersection point
        count = 0;
        Triangle triangle = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = triangle.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Should return one intersection", 1, count);

        //TC02: Two intersection points
        count = 0;
        triangle = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = triangle.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Should return two intersections", 2, count);


    }


}
