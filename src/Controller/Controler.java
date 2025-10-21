package Controller;

import Algorithmus.OutputDatenKollektor;
import Eingabe.InputDatenKollektor;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Diese Klasse dient als Schnittstelle zur Datenhaltung und Kontrolle neuer Datensaetze vom Leser-Thread, bevor sie vom Loeserthread weiterverarbeitet werden.
 * Auch der Schreiber-Thread erhaelt von hier aus seine noetigen Outputdaten. Beides wird ueber Queue-Implementierungen realisiert.
 * Sie enthaelt weiterhin flags, die signalisieren, ob DetektorDaten nich nicht vorliegen, alle DatenObjekte verarbeitet wurden oder alle Dateine geschrieben wurden.
 */
public class Controler{

    private final ArrayBlockingQueue<InputDatenKollektor> vorstopperQueue;
    private final ArrayBlockingQueue<InputDatenKollektor> queue_gesammelteDetektordaten;
    private final ArrayBlockingQueue<OutputDatenKollektor> queue_output;
    private final HashSet<Integer> idSet;
    public volatile AtomicBoolean alleDateienGeschrieben = new AtomicBoolean(false);
    // public volatile AtomicBoolean alleDatenAufbereitet = new AtomicBoolean(false);
    public volatile AtomicBoolean alleDatenGeliefert = new AtomicBoolean(false);

    public volatile AtomicBoolean alleDatenImVorstopper = new AtomicBoolean(false);

    /**
     * Initialisiert die leeren Queues.
     */
    public Controler() {
        this.vorstopperQueue = new ArrayBlockingQueue<>(10); // eventuell dynamisch einstellen: Größe je nach Anzahl der Dateien im Ordner
        this.queue_gesammelteDetektordaten = new ArrayBlockingQueue<>(100);
        this.queue_output = new ArrayBlockingQueue<>(50);
        this.idSet = new HashSet<>();
    }

    /**
     * @param inPut Ein InPutDatenKollektor mit allen zur Verarbeitung noetigen X- und Y-Werten, welches dem Loeserthread in einer queue zur Verfügung gestellt wird.
     *            Es wird nur dann in die queue eingefuegt, falls es ein wirklich ein neuartiger Datensatz vom LeseThread "geliefert" wird.
     *            Dies wird ueber ein Hashset kontrolliert, welches die IDs der Datensaetze sammelt, sodass neuartige IDs bzw. auch einen neuen Datensatz fuer die Queue anzeigen.
     * @return
     */
    public boolean addDatenObjekt(InputDatenKollektor inPut) throws InterruptedException {

        if (!idSet.contains(inPut.getDatenID())) {
            vorstopperQueue.add(inPut);
            idSet.add(inPut.getDatenID());
            if(vorstopperQueue.size()==10){
                System.out.println("Vorstopper-Queue ist voll");
                this.alleDatenImVorstopper.set(true);
                return false;
            }
        }
        return true;
    }

    /**
     * @return Legt eine Referenz auf die gesammelten Detektordaten bzw. Inputdaten zum zugriff waehrend der Verarbeitung frei.
     */
    public synchronized ArrayBlockingQueue<InputDatenKollektor> getDetektordatenQueue() {
        return this.queue_gesammelteDetektordaten;
    }

    /**
     * @return Legt eine Referenz auf die gesammelten Outputdaten bzw. Inputdaten zum Zugriff waehrend des Schreibvorgangs frei.
     */
    public synchronized ArrayBlockingQueue<OutputDatenKollektor> getOutputDatenQueue() {
        return this.queue_output;
    }

    public synchronized ArrayBlockingQueue<InputDatenKollektor> getVorstopperQueue() {
        return vorstopperQueue;
    }

}
