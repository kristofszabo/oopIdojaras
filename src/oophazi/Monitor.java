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
        super(name,1,0);


    }

    @Override
    public boolean canShowData() {
        return true;
    }
}