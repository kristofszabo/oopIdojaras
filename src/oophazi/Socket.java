package oophazi;
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Socket implements Serializable {

    /**
     * Default constructor
     */
    public Socket() {
    }


    /**
     * 
     */
    private Cable cable;

    /**
     * 
     */
    private Device owner;




    /**
     * @return owner
     */
    public Device getOwner() {
        // TODO implement here
        return null;
    }

    /**
     * @param cable Kábel beállító
     */
    public void setCable(Cable cable) {
        // TODO implement here
    }

    /**
     * @return Vissza adja a sockethez tartozó kábelt
     */
    public Cable getCable() {
        // TODO implement here
        return null;
    }

}