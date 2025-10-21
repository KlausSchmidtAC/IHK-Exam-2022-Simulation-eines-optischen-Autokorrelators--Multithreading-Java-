package Algorithmus;

import Eingabe.InputDatenKollektor;

/**
 * Das Interface zur Implementierung der mathematischen Algorithmen: Glaetten der X-Werte, Normierung der Y-Werte, Bestimmungen der Huellendenkurve und der Pulsbreite.
 */
public interface AKF_Loeser {

    /**
     * Rechnet die X-Eingabewerte zuerst in Pikosekunden um und "glaettet" die Werte anschließend
     */
    void bestimmeXk();

    /**
     * Normiert die gegebenen Y-Werte, so dass diese im Intervall (0,1] liegen.
     */
    void normiereY();

    /**
     * Traegt für die Simulation der oberen Einhüllenden entsprechende "zuletzt" maximale Y-Wert aus der Liste der normierten Y-Werte auf.
     */
    void bestimmeHuellende();

    /**
     * @return ein FHWM-Objekt, welches die relevanten Objekte zur Pulsbreitenbestimmung kapselt.
     */
    void bestimmeFWHM();

    /**
     * @return die ungerade Ganzzahl n, welche zur Berechnung der Glaettung erforderlich ist.
     */
    int bestimme_n();

    /**
     * @return OutPutDatenKollektor. Gibt nach erfolgreicher Berechnung aller 4 Algorthmen ein Objekt mit den gekapselten Loesununsmengen zurueck.:
     */
    OutputDatenKollektor holeLoesungsMengen();

    /**
     * Ein Setter für die Rohdatenverarbeitung in der Algorithmusklasse.
     *
     * @param _inPut Eine Instanz von InpuDatenKollektor mit Rohdaten aus einer Datei.
     */
    void setInput(InputDatenKollektor _inPut);

}
