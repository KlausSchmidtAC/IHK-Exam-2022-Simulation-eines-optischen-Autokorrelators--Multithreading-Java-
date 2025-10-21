package Main;

import Algorithmus.AKF_ALGO2;
import Algorithmus.AKF_Loeser;
import Algorithmus.RechnerThread;
import Ausgabe.Schreiber;
import Ausgabe.SchreiberThread;
import Ausgabe.schreibInterface;
import Controller.Controler;
import Eingabe.*;
import Model.Exceptions.DatensatzInkonsistentException;
import Model.Exceptions.EingabeException;
import java.io.IOException;

/**
 * Das ist die Main-Klasse.
 * Sie beinhaltet die statische main() - Methode zum Starten des gewünschten Softwaresystems.
 */
public class Main {

    /**
     * @param args Die Kommandozeilenparameter als String[] args.
     *             Der erste String muss eine relative oder absolute Pfadangabe zum Ordner der Testdateien darstellen. Ein zweiter String als Angabe eines Zielordners ist optional.
     * @throws EingabeException               Exception für Fehlererscheinungen beim Einlesen zu Pfadangaben und Zugriffrechten.
     * @throws DatensatzInkonsistentException Exception zu fehlerhaft strukturierten Datensätzen in eingelesenen Dateien.
     * @throws IOException                    Diese Exception zeigt in unserem Fall auf, dass die Prozessausführung des Tools ABC außerhalb der JVM aufgrund von falschen Parameterübergaben an das Tool oder einer defekten Installation scheiterte.
     */
    public static void main(String[] args) throws EingabeException, DatensatzInkonsistentException, IOException, InterruptedException {

        if (args.length != 2 && args.length != 1) {
            System.err.println("Das Programm muss mit java -jar <Programmpfad> <Eingabedatei> [<Ausgabedatei>] aufgerufen werden.");
            System.exit(0);
        }

        String zielOrdnerpfad = "";

        if (args.length == 1) {
            zielOrdnerpfad = args[0];


        } else {
            zielOrdnerpfad = args[1];
        }

        Controler ct = new Controler();

        LeseThread leserThread = new LeseThread(ct, args[0]);
        leseInterface leser = new Leser();
        leserThread.setLeser(leser);

        VermittlerThread vermittlerThread = new VermittlerThread(ct);

        RechnerThread rechnerThread1 = new RechnerThread(ct,1);
        AKF_Loeser algo = new AKF_ALGO2();
        rechnerThread1.setAlgo(algo);


        RechnerThread rechnerThread2 = new RechnerThread(ct, 2);
        AKF_Loeser algo2 = new AKF_ALGO2();
        rechnerThread2.setAlgo(algo2);


        SchreiberThread schreiberThread1 = new SchreiberThread(ct, zielOrdnerpfad,1);
        schreibInterface schreiber = new Schreiber();
        schreiberThread1.setSchreiber(schreiber);


        SchreiberThread schreiberThread2 = new SchreiberThread(ct, zielOrdnerpfad,2);
        schreibInterface schreiber2 = new Schreiber();
        schreiberThread2.setSchreiber(schreiber2);


        final long timeStart = System.currentTimeMillis();
        leserThread.start();
        vermittlerThread.start();
        rechnerThread1.start();
        rechnerThread2.start();

        schreiberThread1.start();
        schreiberThread2.start();

        leserThread.join();
        vermittlerThread.join();

        rechnerThread1.join();
        rechnerThread2.join();

        schreiberThread1.join();
        schreiberThread2.join();


        final long timeEnd = System.currentTimeMillis();
        String time = "Verlaufszeit (mit Ausgabe): " + (timeEnd - timeStart) + " Millisek.";
        System.out.println(" \n " + ".Berechnungen durchgefuehrt in " + time);

        /**
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores);
         **/
    }
}
