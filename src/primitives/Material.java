package primitives;

/**
 * Class Material - represents the material of objects
 */
public class Material {
    public static Material DEFAULT = new Material(0, 0, 0);
    private double _kD, _kS, _kT, _kR;
    private int _nShininess;

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
     * Instantiates a new Material from reference.
     *
     * @param material the other material
     */
    public Material(Material material) {
        this(material._kD, material._kS, material._nShininess, material._kT, material._kR);
    }

    /**
     * Get the kD coefficient
     */
    public double getkD() {
        return _kD;
    }

    /**
     * Get the kT coefficient
     */
    public double getkT() {
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
    public double getkS() {
        return _kS;
    }
    /**
     * Get nShininess coefficient
     */
    public int getNShininess() {
        return _nShininess;
    }
}
