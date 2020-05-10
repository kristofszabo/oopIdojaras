
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


    private String measure;

    /**
     * @param name Eszköz név
     */
    public Sensor(String name) {
        super(name,0,1);
    }




}