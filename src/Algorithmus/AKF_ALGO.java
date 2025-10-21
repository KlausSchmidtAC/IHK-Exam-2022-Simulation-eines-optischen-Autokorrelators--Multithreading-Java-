package Algorithmus;

import Eingabe.InputDatenKollektor;
import Model.FHWM;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Die Klasse mit der Implementierung der vier Algorithmen zur X-Wert-Glaettung, Normierung der Y-Werte, Bestimmung approximierter Werte zur einhuellenden Funktion und die Pulsbreitenbestimmung.
 */
public class AKF_ALGO implements AKF_Loeser {

    private InputDatenKollektor inPut;
    private int N;
    private ArrayList<Double> xPiko;
    private ArrayList<Double> xGlatt;
    private ArrayList<Double> YNorm;
    private double[] huellende;
    private FHWM fhwm;
    private int idxYMax;
    private int aktuelleDatenID;

    /**
     * Dieser Konstruktor erzeugt nur eine leere Instanz ohne die nötigen Inputdaten. Er ist noetig bei der Instanziierung der Loeserthread-Klasse,
     * bevor deren run() Methode aufgerufen bzw. der Lese-Thread gestartet wird. Ind diesem Vorgang wird die Algo-Klasseninstanz per Setter funktionsfaehig.
     */
    public AKF_ALGO() {
        xPiko = new ArrayList<Double>();
        xGlatt = new ArrayList<Double>();
        YNorm = new ArrayList<Double>();
        fhwm = new FHWM();

    }

    /**
     * Bestimmt wie in der Aufgabe vorgegeben die geglaetteten X-Werte aus der Eingabedatei.
     * Hat eine Laufzeitkomplexitaet von O(n^2) aufgrund zweier verschachtelter Schleifen.
     */
    @Override
    public void bestimmeXk() {
        this.N = inPut.getxWerte().size(); // auch gleich der size() von inPut.yWerte

        for (int i = 0; i < N; i++) {
            xPiko.add(((inPut.getxWerte().get(i) * ((266.3 / (Math.pow(2, 18) - 1)))) - 132.3));
        }

        int n = bestimme_n();
        int tau = (n - 1) / 2;


        for (int k = 0; k < N; k++) {
            double xsum = 0;
            boolean linkerRand = false;
            boolean rechterRand = false;
            int randFenster = 0;
            for (int i = 0; i < n; i++) {
                if (k < tau) {
                    linkerRand = true;
                    randFenster = 2 * k + 1;                    // DAS HIER FEHLTE!!!
                    int maxIndxRandFenster = randFenster - 1;   // DAS HIER FEHLTE!!!

                    xsum += xPiko.get(i);

                    if (i == maxIndxRandFenster) { // DAS HIER FEHLTE!!!
                        break;
                    }
                } else if (k > N - 1 - tau) {
                    rechterRand = true;
                    randFenster = 2 * (N - 1 - k) + 1;      // DAS HIER FEHLTE!!!
                    int maxIndxRandFenster = randFenster - 1; // DAS HIER FEHLTE!!!
                    xsum += xPiko.get(N - 1 - i);

                    if (i == maxIndxRandFenster) { // DAS HIER FEHLTE!!!
                        break;
                    }
                } else {
                    xsum += xPiko.get(k - tau + i);
                }
            }
            if (linkerRand || rechterRand) {
                xGlatt.add((xsum / randFenster)); // DAS HIER FEHLTE!!!
            } else {
                xGlatt.add((xsum / n));
            }
        }

    }



    /**
     * Normiert alle erhaltenen absoluten Y-Werte(Detektorwerte); Laufzeitkomplexitaet O(n)
     */
    @Override
    public void normiereY() {
        int y_Max = 0;
        for (int i = 0; i < N; i++) {
            if (inPut.getyWerte().get(i) > y_Max) {
                y_Max = inPut.getyWerte().get(i);
                idxYMax = i;
            }
        }
        for (int z : inPut.getyWerte()) {
            YNorm.add((z / (double) y_Max));
        }

    }

    /**
     * Bestimmt die Eintraege zur huellendenKurve. Hat eine Laufzeit von O(n).
     */
    @Override
    public void bestimmeHuellende() {
        double lokalesMaxYlinks = 0;
        for (int i = 0; i < N; i++) {
            if (this.YNorm.get(i) > lokalesMaxYlinks) {
                lokalesMaxYlinks = this.YNorm.get(i);
            }
            this.huellende[i] = lokalesMaxYlinks;
            if (i == idxYMax) {
                break;
            }
        }

        double lokalesMaxYrechts = 0;

        for (int i = N - 1; i >= 0; i--) {
            if (this.YNorm.get(i) > lokalesMaxYrechts) {
                lokalesMaxYrechts = this.YNorm.get(i);
            }

            this.huellende[i] = lokalesMaxYrechts;
            if (i == idxYMax) {
                break;
            }
        }
    }

    /**
     * @return Als Rueckgabe wird hier ein FHWM-Objekt mit den passenden Werten erstellt.
     * Als erstes wird die Grundlinie-Hoehe in der Menge der normierten Y-Werte bestimmt. Anschliessend die zur Haelfte des Abstandes (1-Grundlinie) zugehörigen Indize idxL
     * und idXR und die zugehoerige Distanz bzw. Pulsbreite in den geglaetteten X-Werten.
     * Laufzeit O(n).
     */
    @Override
    public void bestimmeFWHM() {

        int indexErstesProzent = (int) (N * 0.01);

        double sumErstesProzent = 0;
        for (int i = 0; i < indexErstesProzent; i++) {
            sumErstesProzent += huellende[i];
        }


        double grundlinie = sumErstesProzent / indexErstesProzent;

        double halb = ((1 + grundlinie) * 0.5);

        int idx1 = 0;
        int idx2;
        int idx3 = 0;
        int idx4;
        int idxL;
        int idxR;

        for (int i = 0; i < N; i++) {
            if (huellende[i] > halb) {
                idx1 = i;
                break;
            }
        }
        idx2 = idx1 - 1;

        for (int i = N - 1; i > 0; i--) {
            if (huellende[i] > halb) {
                idx3 = i;
                break;
            }
        }
        idx4 = idx3 + 1;

        //Kleinsten 2 Abstands-Werte bzgl. Y-Halb!!!
        // Sind nicht automatisch die Indize zu den als erstes auftretenden größeren YHuellenden-Werten!

        double diff1 = (Math.abs(halb - huellende[idx1]));
        double diff2 = (Math.abs(halb - huellende[idx2]));
        double diff3 = (Math.abs(halb - huellende[idx3]));
        double diff4 = (Math.abs(halb - huellende[idx4]));

        // Sortier-Übung
        /**
        ArrayList<Double>diffs= new ArrayList<>(Arrays.asList(diff1, diff2, diff3, diff4));
        Collections.sort(diffs, (o1, o2) -> (o1-o2 <0)? -1:1);
        System.out.println("Listen-sortierung aufsteigend:" + diffs.toString());


        Double[] arr = new Double[]{diff1, diff2,diff3,diff4};
        Arrays.sort(arr, (Double d1, Double d2) -> ((double)d2-(double)d1)<0? -1:1 );
        System.out.println("Array-Sortierung absteigend:" + Arrays.toString(arr));
        **/

        idxL = (diff1<diff2)? idx1 : idx2;

        idxR = (diff3<diff4)? idx3 : idx4;

        double b = (Math.abs(xGlatt.get(idxL) - xGlatt.get(idxR)));

        this.fhwm.setFHWM(b);
        this.fhwm.setL(idxL);
        this.fhwm.setR(idxR);
    }

    /**
     * @return Bestimmt mit der ungeraden positiven Ganzzahl int n die Groesse des Mittelungsfenster.
     */
    @Override
    public int bestimme_n() {
        int ret = 0;
        if (((int) Math.floor(0.002 * this.N)) % 2 == 0) {
            ret = (int) Math.floor(0.002 * this.N) - 1;
        } else
            ret = (int) Math.floor(0.002 * this.N);

        return ret;
    }

    /**
     * @return Gibt nach dem Aufruf aller 4 Algorithmen ein OutputDatenKollektor-Objekt mit allen zur Ausgabe noetigen Daten zurueck.
     */
    @Override
    public OutputDatenKollektor holeLoesungsMengen() {
        return new OutputDatenKollektor(this.fhwm, this.xGlatt, this.YNorm, this.huellende, this.aktuelleDatenID);
    }

    /**
     * In dieser wichtigen Methode werden die Rohdaten pro neuem Objekt aus der Rohdaten-Queue in der Instanz dieser Algorithmusklasse gesetzt und die Insatnz
     * fuer einen neueun Bearbeitungsvorgang dieser Daten vorbereitet.
     *
     * @param _inPut Eine Instanz von InpuDatenKollektor mit Rohdaten aus einer Datei.
     */
    @Override
    public void setInput(InputDatenKollektor _inPut) {
        this.inPut =_inPut;
        this.huellende = new double[this.inPut.getyWerte().size()];
        xPiko = new ArrayList<Double>();
        xGlatt = new ArrayList<Double>();
        YNorm = new ArrayList<Double>();
        aktuelleDatenID = _inPut.getDatenID();
        fhwm = new FHWM();
    }

}
