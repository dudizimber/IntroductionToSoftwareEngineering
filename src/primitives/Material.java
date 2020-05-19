package primitives;

public class Material {
    public static Material DEFAULT = new Material(0, 0, 0);
    private double _kD, _kS;
    private int _nShininess;

    /**
     * Instantiates a new Material.
     *
     * @param _kD         kD coefficient
     * @param _kS         ks coefficient
     * @param _nShininess shininess coefficient
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    public double getKD() {
        return _kD;
    }

    public double getKS() {
        return _kS;
    }

    public int getNShininess() {
        return _nShininess;
    }
}
