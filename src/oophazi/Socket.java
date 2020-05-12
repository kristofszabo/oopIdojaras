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
     *  A kábel, aminek az 1ik része csatlakoztatva van az aljzatba.
     */
    private Cable cable;

    /**
     * Az eszköz, amihez az aljzat tartozik.
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

    @Override
    public String toString() {
        return "Aljzat: " +
                "Kábele =" + cable +
                " Tulaja =" + owner.getName();
    }
}