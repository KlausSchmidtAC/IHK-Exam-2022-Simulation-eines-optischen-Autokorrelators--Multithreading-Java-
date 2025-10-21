package Ausgabe;

import Algorithmus.OutputDatenKollektor;
import Model.Exceptions.Ausgabe_Exception;

import java.io.IOException;

/**
 * Das Interface zur Implementierung des Schreibverhaltens.
 */
public interface schreibInterface {
    /**
     * Dies ist die Methodensignatur zur Methode, welche in Dateien schreibt. Implementiert in Schreiber-Instanzen.
     *
     * @throws Ausgabe_Exception kann geworfen werden, falls Fehler beim Schreiben auftreten.
     */
    void schreibeInDatei() throws Ausgabe_Exception, IOException;

    /**
     * Dies ist die Methodensignatur zur Methode, welche alle noetigen Daten fuer den Schreibvorgang festlegt. implementiert in Schreiber-Instanzen.
     *
     * @param _zielPfad Der zielPfad zum Ordner, in dem die Ausgabedateien zu schreiben sind.
     * @param _out      Die Datencontainerklasse, welche die die auszugebenden Inhalte enthaelt.
     */
    void setSchreibDaten(String _zielPfad, OutputDatenKollektor _out);
}
