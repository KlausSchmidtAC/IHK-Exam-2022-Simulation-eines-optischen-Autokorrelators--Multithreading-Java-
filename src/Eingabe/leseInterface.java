package Eingabe;

import Model.Exceptions.DatensatzInkonsistentException;
import Model.Exceptions.EingabeException;

/**
 * Das Interface fuer die Implementierung des Leseverhaltens der Leser-instanzen.
 */
public interface leseInterface {
    /**
     * @param id Die Id-des eingelesenen Datensatzes wird "von aussen" mitgeteilt, da sie im Dateinamen steht.
     * @return
     * @throws EingabeException               Wird bei untauglichen Benutzereingaben bezueglich des Ordners der Eingabedateien geworfen.
     * @throws DatensatzInkonsistentException Wird bei inkonsistenten Datensaetzen geworfen.
     */
    InputDatenKollektor liesEin(int id) throws EingabeException, DatensatzInkonsistentException;

    /**
     * @param _ordnerPfad Setzt den Pfad zum Zielordner mt den Dateien in dem das Interface implementierenden Leser-Objekt.
     */
    void setOrdnerPfad(String _ordnerPfad);

}
