
package oophazi;
import oophazi.Data;

import java.util.*;
/**
 * 
 */
public abstract class Sensor extends Device {

    /**
     * Default constructor
     */
    public Sensor() {
    }

    /**
     * 
     */
    public Device device;

    /**
     * 
     */
    private Data currentData;




    /**
     * @return Vissza adja a jelenlegi Adatot, amit tartalmaz
     */
    public Data getData() {
        // TODO implement here
        return null;
    }

    /**
     * @param data Átállítja a jellenlegi adatot, amit tartalmaz
     */
    public void setData(Data data) {
        // TODO implement here
    }

    /**
     * Továbbítja a kapott adatot
     */
    public void send() {
        // TODO implement here
    }

}