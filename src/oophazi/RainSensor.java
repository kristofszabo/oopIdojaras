package oophazi;
import java.util.*;

/**
 * Egy eső szenzort szimbolizáló osztály
 */
public class RainSensor extends Sensor{

    /**
     * Default constructor
     */
    public RainSensor(String name) {
        super(name);
    }


    @Override
    public String toString() {
        return "RainSensor{}";
    }
}