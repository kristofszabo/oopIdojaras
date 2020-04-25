package oophazi;
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Device implements Serializable {

    /**
     * Az eszközben lévő aktuális adatok
     */
    private ArrayList<Data> storedDatas;

    /**
     * Az eszköz bemeneti aljzatai
     */
    private ArrayList<Socket> inputSockets;

    /**
     * Az eszköz kimeneti aljzatai
     */
    private ArrayList<Socket> outputSockets;

    /**
     * Az eszköz neve
     */
    private String name;

    /**
     * Egy igaz/hamis típusú változó, ami jelzi, hogy már tovább küldte e az adatait.
     */
    private boolean isValidData;


    public boolean getIsValidData(){
        return  isValidData;
    }
    /**
     * Default constructor
     */
    public Device() {
    }

    /**
     *
     * @param name Eszköz név
     */
    public Device(String name){
        this.name=name;
    }


    /**
     *
     * @return Szabad bemeneti socket
     */
    private Socket getFreeInputSocket(){
        for (Socket socket : inputSockets) {
            if (socket.getCable() == null) {
                return socket;
            }
        }
        return null; //TODO: ERROR
    }

    /**
     *
     * @return Szabad kimeneti socket
     */
    private Socket getFreeOutputSocket(){
        for (Socket socket : inputSockets) {
            if (socket.getCable() == null) {
                return socket;
            }
        }
        return null; //TODO: ERROR
    }


    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     */
    public void send() {
        for (Data data:
             storedDatas) {
            for (Socket socket:
                 outputSockets) {
                if(socket != null){
                    socket.getCable().send();
                }
            }
        }
    }

    /**
     * @param data Fogadandó adat
     */
    public void receive(Data data) {
        storedDatas.add(data);
        if(canSend()){
            send();
        }
    }

    /**
     *
     * @return Minden inputba csatalakoztatott eszköz elkészült e már a küldéssel
     */
    private boolean canSend(){
        for (Socket socket:
                inputSockets) {
            if(!socket.getCable().getSocketFrom().getOwner().getIsValidData()){
                return false;
            }
        }
        return true;
    }


    /**
     * @return storedDatas
     */
    public ArrayList<Data> getStoredDatas() {
        return storedDatas;
    }

    public void setIsValidData(boolean b){
        isValidData = b;
    }
}