package oophazi;
import java.util.*;

/**
 * 
 */
public class Monitor extends Device {

    /**
     * Default constructor
     */
    public Monitor(String name) {
        super(name);
        getInputSockets().add(new Socket(this));

    }




}