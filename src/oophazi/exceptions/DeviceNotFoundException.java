package oophazi.exceptions;


/**
 * Eszköz nem található kivétel
 */
public class DeviceNotFoundException extends Exception {
    public DeviceNotFoundException() {
        super("Nem található ilyen névvel eszköz.");
    }
    public DeviceNotFoundException(String message){
        super(message);
    }
}
