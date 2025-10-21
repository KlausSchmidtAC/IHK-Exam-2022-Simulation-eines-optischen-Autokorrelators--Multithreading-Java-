package Eingabe;

import java.util.ArrayList;

/**
 * Eine reine Datenhaltungsklasse zur Datenspeicherung der aufbereiteten Daten aus der Eingabedatei in entsprechenden Datewnstrukturen.
 * Diese Klasse ist die Grundlage zur Weiterverarbeitung der Daten.
 */
public class InputDatenKollektor {


    private ArrayList<Integer> xWerte;
    private ArrayList<Integer> yWerte;
    private int datenID = -1;
    private boolean istSchlusstein = true;

    /**
     * Die Member werden zuerst "leer" initialisiert und dann, falls das Einlesen erfolgreich verlief, durch die Leser-Instanz von außen "gesettet".
     */
    public InputDatenKollektor() {
        xWerte = new ArrayList<>();
        yWerte = new ArrayList<>();
    }

    public InputDatenKollektor(boolean _istSchlusstein) {
        this.istSchlusstein = _istSchlusstein;
    }

    // es folgen alle noetigen Setter und Getter fuer die Liste der X,Y-Werte und die datenID.
    public ArrayList<Integer> getxWerte() {
        return xWerte;
    }

    public void setxWerte(ArrayList<Integer> xWerte) {
        this.xWerte = xWerte;
    }

    public ArrayList<Integer> getyWerte() {
        return yWerte;
    }

    public void setyWerte(ArrayList<Integer> yWerte) {
        this.yWerte = yWerte;
    }

    public int getDatenID() {
        return datenID;
    }

    public boolean isIstSchlusstein() {return  istSchlusstein;}

    public void setIstSchlusstein(boolean _istSchlussstein) {this.istSchlusstein = _istSchlussstein;}

    public void setDatenID(int datenID) {
        this.datenID = datenID;
    }
}
