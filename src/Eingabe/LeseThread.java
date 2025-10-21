package Eingabe;

import Controller.Controler;
import Model.Exceptions.DatensatzInkonsistentException;
import Model.Exceptions.EingabeException;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Diese Lesethread-Klasse leistet in seiner run-Methode das fortlaufende Einlesen und die Uebertragung der Inhalte 10 Dateien als Endlosschleife.
 */
public class LeseThread extends Thread {
    private final Controler ct;
    private final String ordnerpfad;
    private final Set<String> dateiNamen;
    private InputDatenKollektor inPutPuffer; // für das regelmaeßige zur Verfuegung stellen im 0.05 Sekunden-Takt
    private leseInterface leser;


    public LeseThread(Controler _ct, String _ordnerpfad) throws IOException {
        this.ct = _ct;
        this.ordnerpfad = _ordnerpfad;
        this.dateiNamen = listeDateien(ordnerpfad);
    }

    /**
     * Diese Methode wird im Thread zum kontinuierlichen Einlesen Datensätzen ausgefuehrt.
     * Abbruchbedingung für den Thread ist, falls in der Controler Klasse das flag "alleDateienGeschrieben" auf true steht.
     */
    @Override
    public void run() {

        Iterator<String> pathIterator = dateiNamen.iterator();

        while (!ct.alleDateienGeschrieben.get()) { //...

            String naechsterPfad = "";
            if (!pathIterator.hasNext()) {
                pathIterator = dateiNamen.iterator();
            }
            try {
                naechsterPfad = pathIterator.next();
            } catch (NoSuchElementException e) {
                System.out.println("Der angegebene Ordner enthaelt keinerlei Dateien. Programmende.");
                System.exit(0);
            }

            String[] idArr = naechsterPfad.trim().split("\\.");
            leser.setOrdnerPfad(ordnerpfad + "/" + naechsterPfad);
            try {
                this.inPutPuffer = leser.liesEin(Integer.parseInt(idArr[0]));   // Hier findet das Überschreiben der vorherigen Daten im inPutPuffer-Attribut statt.
                if (this.inPutPuffer == null) {
                    dateiNamen.remove(naechsterPfad);
                    pathIterator = dateiNamen.iterator();
                    if (dateiNamen.size() == 0) {
                        throw new DatensatzInkonsistentException("Alle Dateien sind inkonsistent. Beende Simulation!");
                    }
                } else {
                    this.ct.addDatenObjekt(inPutPuffer);
                }
            } catch (DatensatzInkonsistentException | EingabeException | InterruptedException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

    }

    /**
     * Diese Methode nutzt einen Directory-Stram, um ueber alle Eintraege im angegebenen Ordner zu iterieren um alle Path-Objekte als String abspeichern zu koennen.
     *
     * @param ordner Der Pfad zum Zielordner.
     * @return Ein Set von Strings mit allen im Ordner befindlichen Dateinamen.
     * @throws IOException
     */
    private Set<String> listeDateien(String ordner) throws IOException {
        Set<String> dateiListe = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(ordner))) {
            for (Path pfad : stream) {
                if (!Files.isDirectory(pfad) && !pfad.toString().contains("out")) {
                    dateiListe.add(pfad.getFileName()
                            .toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Fehler:" + e.getMessage() + " ist kein gueltiger Ornder mit Inputdateien! Beende Simulation");
            System.exit(0);
        }
        return dateiListe;
    }


    /**
     * Ein Setter fuer eine benutzerspezifische Leser Instanz.
     *
     * @param _leser Eine Instanz eines benutzspezifischen Lesers, welche das leseInterface implementieren muss.
     */
    public void setLeser(leseInterface _leser) {
        this.leser = _leser;
    }

}




