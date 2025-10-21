package Ausgabe;

import Algorithmus.OutputDatenKollektor;
import Model.Exceptions.Ausgabe_Exception;
import Model.Exceptions.DatensatzInkonsistentException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Diese Klasse hat die Aufgabe die durch den Algorithmus bereitgestellten Loesungsdaten in eine Ausgabedatei zu schreiben.
 * Um das Ausgabeformat ind der Ausgabedatei eventuell umstandslos veraendern zu koennen, wird die Schnittstelle schreibInterface implementiert.
 */
public class Schreiber implements schreibInterface {
    private String zielPfad;
    private OutputDatenKollektor outPut;


    /**
     * Erzeugt eine leere Schreiber-Instanz. Sie ist erst nach dem Setzen der Schreiberdaten funktionsfaehig.
     */
    public Schreiber() {
    }

    /**
     * @throws Ausgabe_Exception wird geworfen, falls entweder der Zielordner nicht erstellt werden kann bzw wenn Schreibrechte fehlen.
     *                           Die Methode ist die Routine zum Schreiben der Daten als String in die Zieldatei im vom User angegebenen Ordner und zum Pruefen von Fehlerfaellen.
     */
    @Override
    public void schreibeInDatei() throws Ausgabe_Exception, IOException {

        final File ausgabeDatei = new File(zielPfad + "\\out" + this.outPut.getDatenID() + ".txt");

        //  Ordnererstellung für Ausgabedatei (falls angegeben)
        if (ausgabeDatei.getParentFile() != null && !ausgabeDatei.getParentFile().exists()) {
            boolean ordnerErstellt = ausgabeDatei.getParentFile().mkdirs();
            try {
                if (!ordnerErstellt) {
                    // fehlende Schreibrechte zur Ordnererstellung.
                    throw new Ausgabe_Exception("Der die Ausgabedatei enthaltende Ordner im Zielpfad %s konnte nicht erstellt werden. Sie besitzen eventuell keine Schreibrechte", zielPfad);
                }
            } catch (Ausgabe_Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

        try {
            FileWriter fw = new FileWriter(ausgabeDatei);

            String ret = erstelleLoesungsString();
            if (ret.equals("")) {
                throw new DatensatzInkonsistentException("Datenverarbeitung ist fehlgeschlagen!");
            } else {
                System.out.printf("Schreibe AKF-Werte in die Datei %s\n ", zielPfad + "\\out" + this.outPut.getDatenID() + ".txt");
            }
            fw.write(ret);
            fw.close();
        } catch (IOException | DatensatzInkonsistentException e) {
            // Schreibrechte sind nicht da.
            System.err.printf("Die Datei %s konnte nicht für die Ausgabe verwandt werden: %s", zielPfad, e.getMessage());
            System.exit(0);
        }

    }

    /**
     * @return Hilfsmethode fuer die schreibeInDatei()-Methode, welche den Ausgabestring mittels der StringBuilder-Klasse erstellt.
     */
    private String erstelleLoesungsString() {

        StringBuilder sb = new StringBuilder();
        sb.append(outPut.getFhwmDatenZeile()).append("\n").append(outPut.getSpaltenHeader());

        for (int i = 0; i < outPut.getNormierteIntensitaetYN().size(); i++) {
            sb.append(outPut.getxGlatt().get(i)).append("\t").append(outPut.getNormierteIntensitaetYN().get(i)).append("\t").append(outPut.getHuellendenWerte()[i]).append("\n");
        }

        return sb.toString();
    }

    /**
     * @param _zielPfad Die Pfadangabe zum angegebenen oder ausgelassenen Zielordner mit den Testdateien.
     * @param _out      Es wird eine durch den Schreiber-Thread eine Referenz auf einen OutputdatenK-Kollektor uebergeben und gesetzt, damit die Daten des AKF-Simulators abgerufen werden koennen.
     */
    @Override
    public void setSchreibDaten(String _zielPfad, OutputDatenKollektor _out) {
        this.outPut = _out;
        this.zielPfad = _zielPfad;
    }


}
