
package oophazi;
import oophazi.Data;

import java.util.*;
/**
 * 
 */
public abstract class Sensor extends Device {



    /**
     * 
     */
    public Device device;

    /**
     * 
     */
    private Data currentData;

    /**
     * @param name Eszköz név
     */
    public Sensor(String name) {
        super(name,0,1);
    }


    /**
     * @return Vissza adja a jelenlegi Adatot, amit tartalmaz
     */
    public Data getData() {
        return currentData;
    }

    /**
     * @param data Átállítja a jellenlegi adatot, amit tartalmaz
     */
    public void setData(Data data) {
        this.currentData = data;
    }



}