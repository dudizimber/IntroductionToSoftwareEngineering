package primitives;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class that represents a Ray, or a half-line in
 * 3D space
 *
 * @author David Zimberknopf and Daniel Grunberger
 */

public class Ray {

    private Point3D _point;
    private Vector _vector;
    private static final double DELTA = 0.1;

    /****** CONSTRUCTORS *******/

    /**
     * Constructor based on point , direction and normal
     * @param point
     * @param direction
     * @param normal
     */
    public Ray(Point3D point, Vector direction, Vector normal) {
        _vector = new Vector(direction).normalized();
        Vector n = new Vector(normal);
        double nv = n.dotProduct(direction);
        Vector normalDelta = n.scale((nv > 0 ? DELTA : -DELTA));
        _point = point.add(normalDelta);
    }

    /**
     * Constructor based on point and vector
     *
     * @param point
     * @param vector
     */
    public Ray(Point3D point, Vector vector) {
        this(point, vector, new Vector(point));
    }

    /****** GETTERS *******/
    /**
     * Gets the point
     *
     * @return
     */
    public Point3D getPoint() {
        return _point;
    }

    /**
     * Gets the vector
     * @return
     */
    public Vector getVector() {
        return _vector;
    }


    /****** FUNCTIONS *******/

    /**
     * @param length distance to scale vector
     * @return point in Ray
     */
    public Point3D getPoint(double length) {
        return Util.isZero(length) ? _point : _point.add(_vector.scale(length));
    }

    /**
     * Creates beam of rays randomly in the circle
     * @param n
     * @param distance
     * @param num
     * @return list of rays
     */
    public List<Ray> createBeamOfRays(Vector n, double distance, int num)
    {
        List<Ray> beam=new LinkedList<Ray>();
        beam.add(this);//the original ray that calls the function - there has to be at least one beam
        if(num==1)//if no additional rays were requested here  there is nothing else to do in this function
            return beam;
        Vector w=this.getVector().normalToVector();
        Vector v=this.getVector().crossProduct(w).normalize();

        Point3D center=this.getPoint(distance);
        Point3D randomP=Point3D.ZERO;
        double xRandom,yRandom,random;
        double nDotDirection=alignZero(n.dotProduct(this.getVector()));
        double r=Math.abs(Math.tan(Math.acos(w.dotProduct(v))));
        for(int i=1;i<num;i++)
        {
            xRandom=randomNumber(-1,1);
            yRandom=Math.sqrt(1-Math.pow(xRandom,2));
            random=randomNumber(-r,r);
            if(xRandom!=0)
                randomP=center.add(w.scale(random));
            if(yRandom!=0)
                randomP=center.add(v.scale(random));
            Vector t= randomP.subtract(this.getPoint());
            double normalDotT=alignZero(n.dotProduct(t));
            if(nDotDirection*normalDotT>0)
                beam.add(new Ray(this.getPoint(),t));
        }
        return beam;
    }

    /**
     * Generates random number [min, max)
     * @param min
     * @param max
     * @return random number[min, max)
     */
    public static  double randomNumber(double min , double max)
{
    double  random = Math.random()*(max-min)+ min;
    return random;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getPoint().equals(ray.getPoint()) &&
                getVector().equals(ray.getVector());
    }

}