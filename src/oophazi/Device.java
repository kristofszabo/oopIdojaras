package oophazi;
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Device implements Serializable {


    private int inputSocketNumber;

    private int outputSocketNumber;


    /**
     * Az eszközben lévő aktuális adatok
     */
    private ArrayList<Data> storedDatas;

    /**
     * Az eszköz bemeneti aljzatai
     */
    private Socket[] inputSockets;

    /**
     * Az eszköz kimeneti aljzatai
     */
    private Socket[] outputSockets;

    /**
     * Az eszköz neve
     */
    private String name;

    /**
     * Egy igaz/hamis típusú változó, ami jelzi, hogy már tovább küldte e az adatait.
     */
    private boolean isValidData = false;

    /**
     * Default constructor
     */
    private Device() {
        storedDatas = new ArrayList<>();
    }

    public boolean getIsValidData(){
        return  isValidData;
    }


    /**
     *
     * @param name Eszköz név
     */
    public Device(String name, int inputSocketNumber, int outputSocketNumber){
        this();
        this.name=name;
        this.inputSocketNumber= inputSocketNumber;
        this.outputSocketNumber = outputSocketNumber;
        outputSockets = new Socket[outputSocketNumber];
        inputSockets = new Socket[inputSocketNumber];

    }


    /**
     *
     * @return Szabad bemeneti socket
     */
    public Socket getFreeInputSocket(){
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
    public Socket getFreeOutputSocket(){
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

    public boolean canShowData(){
        return false;
    }

    public ArrayList<Data> getStoredDataBetweenDates(Date dateFrom, Date dateTo){
        ArrayList<Data> datas=new ArrayList<>();
        for (Data data:
             storedDatas) {
            if(data.getMeasuredTime().after(dateFrom)&& data.getMeasuredTime().before(dateTo)){
                datas.add(data);
            }
        }
        return datas;
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

    /**
     *
     * @return inputSockets
     */
    public Socket[] getInputSockets() {
        return inputSockets;
    }

    /**
     *
     * @return outputSockets
     */
    public Socket[] getOutputSockets() {
        return outputSockets;
    }
}