package oophazi.exceptions;

/**
 * Nem található szabad kimeneti aljzat kivétel
 *
 */
public class NoFreeOutputSocketException extends NoFreeSocketException {
    public NoFreeOutputSocketException(){
        super("Nem található szabad kimeneti aljzat");
    }
}
