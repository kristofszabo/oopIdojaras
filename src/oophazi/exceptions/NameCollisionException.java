package oophazi.exceptions;

public class NameCollisionException extends Exception {
    public NameCollisionException(){
        super("Név ütközés történt");
    }
}
