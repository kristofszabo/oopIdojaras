package oophazi.exceptions;

public class NotEnoughParameterException extends Exception {
    public NotEnoughParameterException(){
        super("Nem adott meg elég paramétert");
    }
}
