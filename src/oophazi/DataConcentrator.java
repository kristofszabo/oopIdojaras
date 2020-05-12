package oophazi;

import java.util.ArrayList;

/**
 * Egy adatkoncentrátor szimbolizáló osztály
 */
public class DataConcentrator extends Device {

    /**
     * Beállítja az alap 8 bemenetet és 1 kimenetet és a nevét az eszköznek
     */
    public DataConcentrator(String name) {
        super(name, 8, 1);
    }


    @Override
    public String toString() {
        return "(DataConcentrator) " + super.toString();
    }
}