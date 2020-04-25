package oophazi;
import java.util.*;

/**
 * 
 */
public class DataStorage extends Device {

    /**
     * Default constructor
     */
    public DataStorage(String name) {
        super(name);
        ArrayList<Socket> socketsIn = getInputSockets();
        ArrayList<Socket> socketsOut = getInputSockets();

        socketsOut.add(new Socket(this));
        for(int i = 0; i < 8;i++){
            socketsIn.add(new Socket(this));
        }
    }

}