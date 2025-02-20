package oophazi;
import java.util.*;

/**
 * Egy hőmérséklet érzékelőt szimbolizáló osztály
 */
public class TemperatureSensor extends Sensor {


    /**
     * @param name Eszköz név
     */
    public TemperatureSensor(String name) {
        super(name,"Celsius");
    }

    @Override
    public String toString() {
        return "(TemperatureSensor) " + super.toString();
    }
}