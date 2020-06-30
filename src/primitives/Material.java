package primitives;

/**
 * Class Material - represents the material of objects
 */
public class Material {
    public static Material DEFAULT = new Material(0, 0, 0);
    final private double _kD, _kS, _kT, _kR;
    final private int _nShininess;
    private double _glossBlur = 0;

    /**
     * Instantiates a new Material.
     *
     * @param _kD         kD coefficient
     * @param _kS         ks coefficient
     * @param _nShininess shininess coefficient
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this(_kD, _kS, _nShininess, 0, 0);
    }

    /**
     * Instantiates a new Material.
     *
     * @param _kD         kD coefficient
     * @param _kS         ks coefficient
     * @param _nShininess shininess coefficient
     * @param _kT         kt coefficient
     * @param _kR         kr coefficient
     */
    public Material(double _kD, double _kS, int _nShininess, double _kT, double _kR) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
        this._kT = _kT;
        this._kR = _kR;
    }

    /**
     * Get the kD coefficient
     */
    public double getKD() {
        return _kD;
    }

    /**
     * Get the kT coefficient
     */
    public double getKT() {
        return _kT;
    }

    /**
     * Get the kR coefficient
     */
    public double getkR() {
        return _kR;
    }

    /**
     * Get the kS coefficient
     */
    public double getKS() {
        return _kS;
    }

    /**
     * Get nShininess coefficient
     */
    public int getNShininess() {
        return _nShininess;
    }

    /**
     * Get glossy reflection and blurry transparency radius
     *
     * @return radius value
     */
    public double getGlossBlur() {
        return _glossBlur;
    }

    /**
     * Set glossy reflection and blurry transparency radius
     *
     * @param value radius value
     * @return material object itself
     */
    public Material setGlossBlur(double value) {
        _glossBlur = value;
        return this;
    }
}
