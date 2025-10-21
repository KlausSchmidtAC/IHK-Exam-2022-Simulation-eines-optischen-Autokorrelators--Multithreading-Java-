

package Algorithmus;

import Controller.Controler;
import Eingabe.InputDatenKollektor;


/**
 * Die Kernklasse meines Problemloesers. Sie ist das "Zentrum": Stellt eine Instanz des Output-DatenKollektoren bereit, auf denen ein per Strategy-Pattern gewählte Implementierung
 * der 4 Loesungsalgorithmen arbeitet.
 * NEEE: Sie besitzt Instanzen der Schnittstellenimplementierungen "schreibInterface" und "leseInterface" um flexibel auf Aenderungen in den Ein- und Ausgabeformaten der Eingabe und Ausgabedaten reagieren zu koennen.
 */


public class RechnerThread extends Thread {
    private AKF_Loeser algo;
    private final Controler ct;
    private final int id;

    /**
     * Erstellt eine noch nicht funktionsfaehige Loeserthread-Instanz. Erst nach dem Setzen mit einer Algorithmus-Instanz kann der Thread gestartet werden.
     */
    public RechnerThread(Controler _ct, int _id) {
        this.ct = _ct;
        this.id = _id;
    }

    /**
     * Die run()-Methode des zu startenden Loeserthreads. Sie arbeitet solange mit Rohdatenobjekte aus der Inputdaten-Queue im Controler ab, bis mit Sicherheit keine neuartigen Rohdaten mehr erwartet werden koennen.
     */
    @Override
    public void run() {

        while (true) {
            InputDatenKollektor neuerInput = null;
            try {
                neuerInput = this.ct.getDetektordatenQueue().take();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            if (!neuerInput.isIstSchlusstein()) { // ... Berechnungen durchführen ...
                this.algo.setInput(neuerInput);
                final long timeStart = System.currentTimeMillis();
                algo.bestimmeXk();
                algo.normiereY();
                algo.bestimmeHuellende();
                algo.bestimmeFWHM();
                try {
                    this.ct.getOutputDatenQueue().put(algo.holeLoesungsMengen());
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                final long timeEnd = System.currentTimeMillis();

                String s = "Verlaufszeit der Berechnung von Rechner" + this.id +" : Fuer den Datensatz Nr." + neuerInput.getDatenID() + " = " + (timeEnd - timeStart) + " Millisek.";
                System.out.println(" \n " + s);
                System.out.println("Rechner" + this.id + " fertig");
            } else {
                System.out.println("Rechner" + this.id + " bekam den Schlussstein und beendet sich im naechsten Durchlauf!");
                // this.ct.alleDatenAufbereitet.set(true);
                System.out.println("Alle Daten aufbereitet");
                OutputDatenKollektor schlussOutPut = new OutputDatenKollektor();
                try {
                    this.ct.getOutputDatenQueue().put(schlussOutPut);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    /**
     * Ein Setter für eine bestimmte Implementierung eines gewuenschten Loesungsalgorithmus.
     *
     * @param _algo Eine Instanz mit einer Implementierung der Algorithmusschnittstelle "LoesungsAlgo" zur Problemloesung.
     */
    public void setAlgo(AKF_Loeser _algo) {
        this.algo = _algo;
    }

}
