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
        assertEquals("Should return two intersections", 2, getIntersectionsFromSphere(sphere, cam1));

        //TC02: Eighteen intersection points
        count = 0;
        sphere = new Sphere(new Point3D(0, 0, 2.5), 2.5);
        assertEquals("Should return eighteen intersections", 18, getIntersectionsFromSphere(sphere, cam2));

        //TC03: Ten intersection points
        count = 0;
        sphere = new Sphere(new Point3D(0, 0, 2), 2);
        assertEquals("Should return 10 intersections", 10, getIntersectionsFromSphere(sphere, cam2));

        //TC04: Nine intersection points
        sphere = new Sphere(new Point3D(0, 0, 2.5), 4);
        assertEquals("Should return nine intersections", 9, getIntersectionsFromSphere(sphere, cam2));

        //TC05: Zero intersection points
        sphere = new Sphere(new Point3D(0, 0, -1), .5);
        assertEquals("Should return zero intersections", 0, getIntersectionsFromSphere(sphere, cam1));

    }

    /**
     * Get the number of intersections with the sphere using a camera
     *
     * @param sphere the sphere to intersect
     * @param cam    the camera to use for checking intersections
     * @return number of intersections
     */
    private int getIntersectionsFromSphere(Sphere sphere, Camera cam) {
        int count = 0;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sphere.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        return count;
    }

    /**
     * Test method for {@link Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}
     */
    @Test
    public void testIntegrationPlane() {

        //TC01: Nine intersection points - Plane 1
        Plane plane = new Plane(new Point3D(0, 0, 3), new Vector(0, 0, 1));
        assertEquals(
                "Should return nine intersections",
                getIntersectionsFromPlane(plane),
                9
        );

        //TC02: Nine intersection points - Plane 2
        plane = new Plane(new Point3D(0, 0, 3), new Vector(0, -0.5, 1));
        assertEquals("Should return nine intersections", 9, getIntersectionsFromPlane(plane));

        //TC03: Six intersection points
        plane = new Plane(new Point3D(0, 0, 3), new Vector(0, -3, 1));
        assertEquals("Should return six intersections", 6, getIntersectionsFromPlane(plane));

    }

    /**
     * Get the number of intersections with the plane using the camera 1
     *
     * @param plane the plane to intersect
     * @return number of intersections
     */
    private int getIntersectionsFromPlane(Plane plane) {
        int count = 0;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = plane.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        return count;
    }

    /**
     * Test method for {@link Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}
     */
    @Test
    public void testIntegrationTriangle() {

        //TC01: One intersection point
        count = 0;
        Triangle triangle = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        assertEquals("Should return one intersection", 1, getIntersectionsFromTriangle(triangle));

        //TC02: Two intersection points
        triangle = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        assertEquals("Should return two intersections", 2, getIntersectionsFromTriangle(triangle));

    }

    /**
     * Get the number of intersections with the triangle using the camera 1
     *
     * @param triangle the triangle to intersect
     * @return number of intersections
     */
    private int getIntersectionsFromTriangle(Triangle triangle) {
        int count = 0;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = triangle.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        return count;
    }


}
