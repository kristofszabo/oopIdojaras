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
     * @param device A socketet birtokló eszköz
     */
    public Socket(Device device){
        this.owner = device;
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
        return owner;
    }

    /**
     * @param cable Kábel beállító
     */
    public void setCable(Cable cable) {
        this.cable = cable;
    }

    /**
     * @return Vissza adja a sockethez tartozó kábelt
     */
    public Cable getCable() {
        return cable;
    }

}