package oophazi.exceptions;

public class DeviceNotFoundException extends Exception {
    public DeviceNotFoundException() {
        super("Nem található ilyen névvel eszköz.");
    }
}
