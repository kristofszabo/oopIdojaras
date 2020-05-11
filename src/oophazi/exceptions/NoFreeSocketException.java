package oophazi.exceptions;

/**
 * Nincs szabad aljzat kivétel
 */
public class NoFreeSocketException extends Exception {
    public NoFreeSocketException(){
        super("Nem található üres aljzat az eszközön");
    }
    public NoFreeSocketException(String message){
        super(message);
    }
}
