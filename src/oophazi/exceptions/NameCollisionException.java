package oophazi.exceptions;

/**
 * Név ütközés kivétel
 */
public class NameCollisionException extends Exception {
    public NameCollisionException(){
        super("Név ütközés történt");
    }
}
