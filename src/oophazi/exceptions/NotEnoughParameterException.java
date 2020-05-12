package oophazi.exceptions;

/**
 * Menü kivétel nem adott meg a felhasználó elég paramétert az adott menühöz
 */
public class NotEnoughParameterException extends Exception {
    public NotEnoughParameterException(){
        super("Nem adott meg elég paramétert a választott menühöz");
    }
}
