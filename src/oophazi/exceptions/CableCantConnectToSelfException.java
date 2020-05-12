package oophazi.exceptions;

public class CableCantConnectToSelfException extends Exception {
    public CableCantConnectToSelfException(){
        super("Nem lehet önmagába kötni egy kábelt se");
    }
}
