package oophazi;
import java.util.*;

/**
 * Egy szélerőt szimbolizáló osztály
 */
public class WindforceSensor extends Sensor {


    /**
     * @param name Eszköz név
     */
    public WindforceSensor(String name) {
        super(name,"m/s");
    }

    @Override
    public String toString() {
        return "(WindforceSensor) " + super.toString();
    }
}