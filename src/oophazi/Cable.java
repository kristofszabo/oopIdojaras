package oophazi;
import java.io.Serializable;
import java.util.*;

/**
 * Egy kábelt szimbolizáló osztály
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
        from.setCable(this);
        to.setCable(this);
    }

    /**
     * Tovább küldi a küldő adatait a fogadónak
     */
    public void send() {
        ArrayList<Data> datas = socketFrom.getOwner().getStoredDatas();
        for (var data:
             datas) {
            socketTo.getOwner().receive(data);
        }

    }


    /**Getter a cél aljzathoz
     *
     * @return A cél aljzat
     */
    public Socket getSocketTo() {
        return socketTo;
    }

    /**Getter a forrás aljzathoz
     *
     * @return Forrás aljzat
     */
    public Socket getSocketFrom(){ return socketFrom;}


    @Override
    public String toString() {
        return "Kábel: " +
                "honnan =" + socketFrom.getOwner().getName() +
                ", hova =" + socketTo.getOwner().getName();
    }
}