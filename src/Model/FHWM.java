package Model;


/**
 * Eine Klasse zur Kapselung aller fuer die Pulsbreite relevanter Daten.
 */
public class FHWM {
    private double fhwm;
    private int L;
    private int R;

    public FHWM() {
        this.fhwm = 0;
        this.L = 0;
        this.R = 0;
    }

    // alle noetigen Getter und Setter fuer die Attribute.

    public double getFhwm() {
        return fhwm;
    }

    public void setFHWM(double _fhwm) {
        this.fhwm = _fhwm;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }
}
