package Ausgabe;

import Algorithmus.OutputDatenKollektor;
import Controller.Controler;
import Model.Exceptions.Ausgabe_Exception;

import java.io.IOException;
import java.sql.SQLOutput;

/**
 * Die Klasse, welche die run()-Methode des Schreiber-thread implementiert.
 */
public class SchreiberThread extends Thread {
    private Controler ct;
    private String _zielordnerPfad;
    private schreibInterface schreiber;
    private final int id;

    /**
     * @param _ct            Eine Referenz auf die Instanz der zentrale Datenklasse Controler, innerhalb derer Input- und output-Datensaetze "verteilt" werden.
     * @param zielOrdnerPfad Der Pfad zum Zielordner, welcher entweder mit dem Ordner der Eingabedateien identisch oder optional angegeben werden kann.
     */
    public SchreiberThread(Controler _ct, String zielOrdnerPfad, int _id) {
        this._zielordnerPfad = zielOrdnerPfad;
        this.ct = _ct;
        this.id = _id;
    }

    /**
     * Die Implementierung der run() - Methode des fuer das Schreiben in Dateien zustaendigen Threads.
     * Hier werden über das schreibInterface-Attribut, die jeweiligen Output-Daten
     * sowie der Pfad gesetzt und die Daten ausgeschrieben.
     */
    @Override
    public void run() {
        while (true) {
            try {
                OutputDatenKollektor outPutDaten = ct.getOutputDatenQueue().take();

                if(outPutDaten.isIstSchlussStein()){
                    ct.alleDateienGeschrieben.set(true);
                    System.out.println("Schreiber" + this.id + " hat letztes Datenpaket ausgeschrieben");
                    return;
                }

                schreiber.setSchreibDaten(_zielordnerPfad, outPutDaten); // Pfad durchreichen;
            } catch (InterruptedException e) {
                        e.printStackTrace();
            }
            try {
                schreiber.schreibeInDatei();
            } catch (Ausgabe_Exception | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Ein Setter für einen benutzerspezifischen Schreiber.
     *
     * @param _schreiber Eine Objektinstanz einer benutzerspezifischen Schreiber Instanz, welche das schreibInterface implementieren muss.
     */
    public void setSchreiber(schreibInterface _schreiber) {
        this.schreiber = _schreiber;
    }
}
