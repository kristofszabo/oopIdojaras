package oophazi.exceptions;


/**
 * Sensor nem található kivétel
 */
public class SensorNotFoundException extends DeviceNotFoundException {
    public SensorNotFoundException() {
        super("Nem található szenzor ezzel a névvel");
    }
}
