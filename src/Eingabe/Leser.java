package Eingabe;

import Model.Exceptions.DatensatzInkonsistentException;
import Model.Exceptions.EingabeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Diese Klasse dient dem Einlesen der Daten aus der Quelldatei und dem Initialisieren der Daten in passenden Datenstrukturen auf einer Instanz des InputDatenKollektor.
 */
public class Leser implements leseInterface {
    private String quellPfad;


    /**
     * Gibt eine leere Leser-Instranz zurueck. Sie ist erst funktionsfaehig, wenn der String quellPfad gesetzt wurde.
     */
    public Leser() {

    }

    /**
     * Die konkrete Methode zum Einlesen der Positionswerte des Spiegels (X-Werte) und der Detektorwerte (Y-Werte).
     *
     * @throws EingabeException               Diese Exception signalisiert Fehler bei der Eingabe des Zielverzeichnis mit den Testdateien.
     * @throws DatensatzInkonsistentException Diese Exception signalisiert inkonsistente Datensätze.
     */
    @Override
    public InputDatenKollektor liesEin(int id) throws EingabeException, DatensatzInkonsistentException {
        InputDatenKollektor inputDaten = new InputDatenKollektor();
        Scanner sc = null;
        final File eingabeDatei = new File(quellPfad);

        if (!eingabeDatei.exists() || !eingabeDatei.isFile()) {
            throw new EingabeException("Die Textdatei %s existiert nicht.", quellPfad);
        }

        try {
            sc = new Scanner(eingabeDatei);
        }
        // Keine Leserechte
        catch (FileNotFoundException e) {
            throw new EingabeException("Die Datei %s konnte nicht eingelesen werden: %s", quellPfad, e.getMessage());
        }


        int datenSatz_ct = 0;
        int kommentarZeilenCounter = 0;


        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (istBeschreibungsZeile(line)) {
                ++kommentarZeilenCounter;
            }

            if (!istBeschreibungsZeile(line)) {
                ++datenSatz_ct;
                // Split am Delimiter Tabulator
                String[] line_arr = line.trim().split("\t");

                try {
                    if (line_arr.length != 2) {
                        throw new DatensatzInkonsistentException("Es gibt eine inkonsistente Daten-Zeile mit fehlenden Werten fuer X und Y in der Datei \"" + id + ".txt\"!");
                    }
                } catch (DatensatzInkonsistentException e) {
                    System.out.println(e.getMessage());
                    return null;
                }

                try {
                    int x = Integer.parseInt(line_arr[1]);
                    inputDaten.getxWerte().add(x);
                } catch (NumberFormatException e) {
                    System.out.println("X_Wert liegen in inkorrektem Datentyp vor in der Datei \\\"\" + id + \".txt\\\" " + e.getMessage());
                    return null;
                }
                try {
                    int y = Integer.parseInt(line_arr[0]);
                    if (y < 0) {
                        throw new DatensatzInkonsistentException("Es existieren negative Y-Werte kleiner 0 in der Datei \"" + id + ".txt\", was nicht dem Sachverhalt entsprechen kann.");
                    } else {
                        inputDaten.getyWerte().add(y);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Y_Wert liegen in inkorrektem Datentyp vor in der Datei \"" + id + ".txt\" " + e.getMessage());
                    return null;
                } catch (DatensatzInkonsistentException e) {
                    System.err.println(e.getMessage());
                    sc.close();
                    return null;
                }
            }
        }
        try {
            if (datenSatz_ct <= 0) {
                throw new DatensatzInkonsistentException("Fehler: Die Datei \"" + id + ".txt\" enthaelt keine keinerlei Datensaetze!");  // eigene Exception
            }
        } catch (DatensatzInkonsistentException e) {
            System.err.println(e.getMessage());
            sc.close();
            return null;
        }
        if (kommentarZeilenCounter != 1) {
            System.out.println("Die Datei Die Datei \"" + id + ".txt\" besitzt keine Kommentarzeile, wird aber verarbeitet.");
        }
        sc.close();

        inputDaten.setDatenID(id);
        inputDaten.setIstSchlusstein(false);

        return inputDaten;
    }

    /**
     * @param _ordnerPfad Setzt den Pfad zum Zielordner mt den Dateien in dem das Interface implementierenden Leser-Objekt.
     */
    @Override
    public void setOrdnerPfad(String _ordnerPfad) {
        this.quellPfad = _ordnerPfad;
    }


    /**
     * @param line einzulesende Zeile. Methode prueft, obt diese Zeile eine Kommentarzeile ist.
     * @return
     */
    private boolean istBeschreibungsZeile(String line) {
        return line.startsWith("#");
    }
}


