package Model.Exceptions;

/**
 * Eine Exceptionklasse fuer inkonsistente Datensaetze.
 */
public class DatensatzInkonsistentException extends Exception {

    public DatensatzInkonsistentException(String s) {
        super(s);
        System.err.println(s);
    }

}
