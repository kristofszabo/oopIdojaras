package oophazi.exceptions;

/**
 * Nem található menü kivétel
 */
public class MenuNotFoundException extends Exception {
    public MenuNotFoundException(){
        super("A menü nem található");
    }
}
