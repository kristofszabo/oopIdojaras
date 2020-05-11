package oophazi.exceptions;


/**
 * Nem található szabad bemenet kivétel
 */
public class NoFreeInputSocketException extends NoFreeSocketException {
    public NoFreeInputSocketException(){
        super("Nem található üres bemeneti aljzat");
    }
}
