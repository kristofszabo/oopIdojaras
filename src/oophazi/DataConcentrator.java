package oophazi;

import java.util.ArrayList;

/**
 *
 */
public class DataConcentrator extends Device {

    /**
     * Default constructor
     */
    public DataConcentrator(String name) {
        super(name);
        ArrayList<Socket> socketsIn = getInputSockets();
        ArrayList<Socket> socketsOut = getInputSockets();

        socketsIn.add(new Socket(this));
        for(int i = 0; i < 8;i++){
            socketsOut.add(new Socket(this));
        }
    }

}