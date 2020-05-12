package oophazi.exceptions;


/**
 * Ugyan azt a kábelt nem lehet ugyan abba az eszközbe csatlakoztatni kivétel
 */
public class CableCantConnectToSelfException extends Exception {
    public CableCantConnectToSelfException(){
        super("Nem lehet önmagába kötni egy kábelt se");
    }
}
