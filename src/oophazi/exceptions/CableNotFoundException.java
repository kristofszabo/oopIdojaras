package oophazi.exceptions;

/**
 * Nem található kábel kivétel
 */
public class CableNotFoundException extends Exception {
    public CableNotFoundException() {
        super("A kábel nem található");
    }
}
