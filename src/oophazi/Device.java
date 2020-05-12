package oophazi;
import oophazi.exceptions.NoFreeInputSocketException;
import oophazi.exceptions.NoFreeOutputSocketException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Egy eszközt szimbolizáló osztály
 */
public abstract class Device implements Serializable {

    /**
     * Bemeneti aljzatok száma
     */
    private int inputSocketNumber;
    /**
     * Kimenetei aljzatok sázma
     */
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
     * Default constructor, ami privát
     */
    private Device() {

    }

    public boolean getIsValidData(){
        return  isValidData;
    }


    /**
     *
     * @param name  Az eszköz neve
     * @param inputSocketNumber Az eszköz bemeneti aljzatainak száma
     * @param outputSocketNumber Az eszköz kimeneti aljzatainak száma
     */
    public Device(String name, int inputSocketNumber, int outputSocketNumber){
        this();
        storedDatas= new ArrayList<>();
        this.name=name;
        this.inputSocketNumber= inputSocketNumber;
        this.outputSocketNumber = outputSocketNumber;
        outputSockets = new Socket[outputSocketNumber];
        for(int i = 0; i<outputSockets.length;++i){
            outputSockets[i]=new Socket(this);
        }
        inputSockets = new Socket[inputSocketNumber];
        for(int i = 0; i<inputSockets.length;++i){
            inputSockets[i]=new Socket(this);
        }
    }

    /**
     *
     * @return Szabad bemeneti aljzat
     * @throws NoFreeInputSocketException
     */
    public Socket getFreeInputSocket() throws NoFreeInputSocketException {
        for (Socket socket : inputSockets) {
            if (socket.getCable() == null) {
                return socket;
            }
        }
        throw new NoFreeInputSocketException();
    }

    /** Megnézi, hogy van e az eszközön szabad kimeneti aljzat és ha van vissza adja azt.
     *
     * @return Szabad kimeneti aljzat
     * @throws NoFreeOutputSocketException
     */
    public Socket getFreeOutputSocket() throws NoFreeOutputSocketException {
        for (Socket socket : outputSockets) {
            if (socket.getCable() == null) {
                return socket;
            }
        }
        throw new NoFreeOutputSocketException();
    }


    /**Getter name-hez
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**Setter name-hez
     *
     * @param name eszköz új neve
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Minden jelenleg rendelkezésre álló adat tovább küldése a kimeneti a kábeleken keresztül
     */
    public void send() {
        for (Data data:
             storedDatas) {
            for (Socket socket:
                 outputSockets) {
                if(socket != null){
                    if(socket.getCable()!=null){
                        socket.getCable().send();

                    }
                }
            }
        }
        isValidData=true;
        broadcastFinish();

    }



    /**
     * Minden eszköz értesítése, amely hozzá van kapcsolva az eszközhöz, hogy befejezte a küldést és
     * ha tud akkor küldje tovább a kapott adatot.
     */
    public void broadcastFinish(){
        for (Socket socket :
                outputSockets) {
            if(socket.getCable()!=null){
                var notifiedOwner = socket.getCable().getSocketTo().getOwner();
                if(notifiedOwner.canSend()){
                    notifiedOwner.send();
                }
            }

        }
    }

    /**
     * @param data Fogadandó adat
     */
    public void receive(Data data) {
        storedDatas.add(data);
    }

    /** Vissza adja, hogy minden előző eszköz elküldte már az adatát
     *
     * @return Minden inputba csatalakoztatott eszköz elkészült e már a küldéssel
     */
    public boolean canSend(){
        for (Socket socket:
                inputSockets) {
            if(socket.getCable()!=null){
                if(!socket.getCable().getSocketFrom().getOwner().getIsValidData()){
                    return false;
                }
            }
        }
        return true;
    }

    public void clean(){
        storedDatas.clear();
    }

    /**
     * Vissza adja hogy az tud e tárolni adatot
     * @return tud e?
     */
    public boolean canStoreData(){
        return false;
    }



    /**setter a tárolt adatokhoz
     *
     * @return storedDatas
     */
    public ArrayList<Data> getStoredDatas() {
        return storedDatas;
    }

    /**
     *
     * @param b Valid e a benne szereplő adat
     */
    public void setIsValidData(boolean b){
        isValidData = b;
    }

    /**getter a bemeneti aljzatokhoz
     *
     * @return inputSockets
     */
    public Socket[] getInputSockets() {
        return inputSockets;
    }

    /** Getter a kimeneti aljzatokhoz
     *
     * @return outputSockets
     */
    public Socket[] getOutputSockets() {
        return outputSockets;
    }

    @Override
    public String toString() {
        return "Device{" +
                "inputSocketNumber=" + inputSocketNumber +
                ", outputSocketNumber=" + outputSocketNumber +
                ", storedDatas=" + storedDatas +
                ", inputSockets=" + Arrays.toString(inputSockets) +
                ", outputSockets=" + Arrays.toString(outputSockets) +
                ", name='" + name + '\'' +
                ", isValidData=" + isValidData +
                '}';
    }
}