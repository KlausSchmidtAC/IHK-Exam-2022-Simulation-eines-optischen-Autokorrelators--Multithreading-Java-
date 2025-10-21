package Model.Exceptions;

/**
 *Eine Exception-Klasse fue fehlerhafte Ausgabefaelle.
 */
public class Ausgabe_Exception extends Exception {

    /**
     * @param s
     */
    public Ausgabe_Exception(String s) {
        super(s);
        System.err.println(s);
    }

    /**
     * @param message
     * @param replace
     */
    public Ausgabe_Exception(String message, String... replace) {
        super(String.format(message, replace));
        System.err.println(String.format(message, replace));
    }
}
