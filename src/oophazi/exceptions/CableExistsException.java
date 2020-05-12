package oophazi.exceptions;

public class CableExistsException extends Exception {
    public CableExistsException() {
        super("Ilyen kábel már létezik");
    }
}
