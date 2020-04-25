package oophazi;
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Cable implements Serializable {

    /**
     * Output socket
     */
    private Socket socketFrom;

    /**
     *  Input socket
     */
    private Socket socketTo;

    /**
     * Default private constructor
     */
    private Cable() {
    }

    /**Konstruktor alap socketek beállításával.
     *
     * @param from Kimenettel rendelkező eszköz
     * @param to   Bemenettel rendelkező eszköz
     */
    public Cable( Socket from,  Socket to) {
        socketFrom = from;
        socketTo = to;
    }

    /**
     * 
     */
    public void send() {
        ArrayList<Data> datas = socketFrom.getOwner().getStoredDatas();
        for (var data:
             datas) {
            socketTo.getOwner().receive(data);
        }
        socketFrom.getOwner().setValidData(true);

    }


    /**
     * @return A cél aljzat
     */
    public Socket getSocketTo() {
        return socketTo;
    }

    /**
     *
     * @return Forrás aljzat
     */
    public Socket getSocketFrom(){ return socketFrom;}

}