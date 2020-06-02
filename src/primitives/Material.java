package primitives;

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

    public Material(double _kD, double _kS, int _nShininess,double _kT,double  _kR){
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
        this._kT = _kT;
        this._kR = _kR;
    }

    /**
     * Returs the kD coefficient
     * @return
     */
    public double getkD() {
        return _kD;
    }
    /**
     *
     * @return
     */
    public double getkT() {
        return _kT;
    }
    /**
     *Returs the kT coefficient
     * @return
     */
    public double getkR() {
        return _kR;
    }
    /**
     *Returs the kR coefficient
     * @return
     */
    public double getkS() {
        return _kS;
    }
    /**
     *Returs the kS coefficient
     * @return
     */
    public int getNShininess() {
        return _nShininess;
    }
}
