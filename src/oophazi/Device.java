package oophazi;
import java.util.*;

/**
 * 
 */
public abstract class Device {

    /**
     * Default constructor
     */
    public Device() {
    }

    /**
     * 
     */
    private ArrayList<Socket> storedDatas;


    /**
     * 
     */
    private ArrayList<Socket> inputSockets;

    /**
     * 
     */
    private ArrayList<Socket> outputSockets;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private boolean validData;




    /**
     * @param device Eszköz amely kimenettel rendelkezik
     */
    public void connectInputDevice(Device device) {
        // TODO implement here
    }

    /**
     * @param device Eszköz amely csatlakoztatva van a kimenetre
     */
    public void disconnectInputDevice(Device device) {
        // TODO implement here
    }

    /**
     * @param device Eszköz, amely rendelkezik bemenettel
     */
    public void connectOutputDevice(Device device) {
        // TODO implement here
    }

    /**
     * @param device Eszköz, amely csatlakoztatva van az egyik kimenetre
     */
    public void disconnectOutputDevice(Device device) {
        // TODO implement here
    }

    /**
     * 
     */
    public void disconnectAllInputDevices() {
        // TODO implement here
    }

    /**
     * 
     */
    public void disconnectAllOutputDevices() {
        // TODO implement here
    }

    /**
     * 
     */
    public void disconnectAllDevices() {
        // TODO implement here
    }

    /**
     * @return name
     */
    public String getName() {
        // TODO implement here
        return "";
    }

    /**
     * 
     */
    public void send() {
        // TODO implement here
    }

    /**
     * @param data Fogadandó adat
     */
    public void receive(Data data) {
        // TODO implement here
    }

    /**
     * @param name Az eszköz neve
     */
    public Device(String name) {
        // TODO implement here
    }

    /**
     * 
     */
    public void Operation1() {
        // TODO implement here
    }

    /**
     * @return storedDatas
     */
    public ArrayList<Data> getStoredDatas() {
        // TODO implement here
        return null;
    }

}