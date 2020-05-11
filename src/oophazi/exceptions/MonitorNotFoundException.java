package oophazi.exceptions;

/**
 * monitor nem található kivétel
 */
public class MonitorNotFoundException extends DeviceNotFoundException {
    public MonitorNotFoundException(){
        super("A megadott névvel monitor nem található");
    }
}
