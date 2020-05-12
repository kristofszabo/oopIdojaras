package oophazi;
import java.io.Serializable;
import java.util.*;

/**
 * Egy eszközön lévő aljzatot reprezentáló osztály
 */
public class Socket implements Serializable {

    /**
     * Default private constructor
     */
    private Socket() {
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
     * Getter a tulajdonoshoz
     *
     * @return owner
     */
    public Device getOwner() {
        return owner;
    }

    /**
     * Setter a kábelhez
     *
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