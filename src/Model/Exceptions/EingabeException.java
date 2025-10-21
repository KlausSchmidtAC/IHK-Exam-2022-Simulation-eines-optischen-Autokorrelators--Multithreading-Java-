package Model.Exceptions;

/**
 * Eine Exceptionklasse fuer fehlerhafte Benutzereingaben.
 */
public class EingabeException extends Exception {

    public EingabeException(String s) {
        super(s);
        System.err.println(s);
    }

    public EingabeException(String message, String... replace) {
        super(String.format(message, replace));
        System.err.println(String.format(message, replace));
    }

}
