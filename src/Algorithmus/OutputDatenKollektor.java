package Algorithmus;

import Model.FHWM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Eine reine Klasse zur Datenhaltung.
 * Sammelt alle relevanten Loesungsdaten in den anggebenen Membern, falls der LoeserThread einen Datensatz erfolgreich verarbeitet.
 */
public class OutputDatenKollektor {
    private FHWM fhwm;
    private String fhwmDatenZeile;
    private String spaltenHeader;
    private ArrayList<Double> normierteIntensitaetYN;
    private ArrayList<Double> xGlatt;
    private double[] huellendenWerte;
    private int datenID;
    private boolean istSchlussStein;

    /**
     * Erzeugt einen im Sinne der Ausgabe vollstendigen Outputdaten-Kollektor.
     */
    public OutputDatenKollektor(FHWM _fhwm, ArrayList<Double> _xGlatt, ArrayList<Double> _normierteIntensitaet, double[] _huellendenWerte, int datenID) {
        this.fhwm = _fhwm;
        this.fhwmDatenZeile = "# " + "FWHM = " + String.format(Locale.US, "%.16e", this.fhwm.getFhwm()) + ", " + this.fhwm.getL() + ", " + this.fhwm.getR();
        this.spaltenHeader = "# " + "pos " + "int\t" + "env\n";
        this.normierteIntensitaetYN = _normierteIntensitaet;
        this.huellendenWerte = _huellendenWerte;
        this.xGlatt = _xGlatt;
        this.datenID = datenID;
        this.istSchlussStein = false;
    }

    /**
     * Erzeugt das AbschlussObjjekt für die Output-Daten-Queue.
     */
    public  OutputDatenKollektor() {
        this.istSchlussStein = true;
    }

    /**
     * @return Gibt die FHWM Werte als String-Datenzeile zurueck.
     */
    public String getFhwmDatenZeile() {
        return fhwmDatenZeile;
    }


    /**
     * @return Gibt die Ueberschrift aller Ausgabespalten als String zurueck.
     */
    public String getSpaltenHeader() {
        return spaltenHeader;
    }

    /**
     * @param spaltenHeader Setzt die Spaltenueberschriften durch einen uebergebenen String.
     */
    public void setSpaltenHeader(String spaltenHeader) {
        this.spaltenHeader = spaltenHeader;
    }

    /**
     * @return Gibt die Liste mit den normierten Intensitaetswerten zurueck.
     */
    public ArrayList<Double> getNormierteIntensitaetYN() {
        return normierteIntensitaetYN;
    }

    /**
     * @return Gibt das double-Array mit den Werten der approximierten Werten der Einhuellendenfunktion der Y-Werte zurueck.
     */
    public double[] getHuellendenWerte() {
        return huellendenWerte;
    }

    /**
     * @return Gibt die Liste der geglaetteten X-Werte zurueck.
     */
    public ArrayList<Double> getxGlatt() {
        return xGlatt;
    }


    /**
     * @return Gibt die jeweilige Datensatz-ID als Integer zurueck.
     */
    public int getDatenID() {
        return datenID;
    }

    public boolean isIstSchlussStein() {
        return istSchlussStein;
    }
}
