package oophazi;

import java.util.ArrayList;

/**
 * Egy adatkoncentrátor szimbolizáló osztály
 */
public class DataConcentrator extends Device {

    /**
     * Default constructor
     */
    public DataConcentrator(String name) {
        super(name, 8, 1);
    }


    @Override
    public String toString() {
        return "(DataConcentrator) " + super.toString();
    }
}