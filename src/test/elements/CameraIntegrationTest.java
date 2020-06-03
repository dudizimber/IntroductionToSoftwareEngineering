package test.elements;

import elements.Camera;
import geometries.Intersectables.GeoPoint;
import geometries.Intersectables;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
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
    @Test
   public  void constructRayThroughPixelWithSphere1() {
        //TO DO
        Sphere sph =  new Sphere( new Point3D(0, 0, 3), 1);

        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = cam1.constructRayThroughPixel(3,3,j,i,1,3,3);
                List<Intersectables.GeoPoint> results = sph.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",2,count);
        System.out.println("count: "+count);

    }
    @Test
    public void constructRayThroughPixelWithSphere2() {
        Sphere sph = new Sphere( new Point3D(0, 0, 2.5), 2.5);

        List<Intersectables.GeoPoint> results;
        int count = 0;
        // TODO explanations
        int Nx = 3;
        int Ny = 3;

        // TODO explanations
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",18, count);
        System.out.println("count: " + count);
    }
}
