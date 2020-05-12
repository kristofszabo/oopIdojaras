package oophazi.exceptions;

/**
 * Kábel már létezik kivétel
 */
public class CableExistsException extends Exception {
    public CableExistsException() {
        super("Ilyen kábel már létezik");
    }
}
