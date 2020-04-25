package oophazi;
import java.util.*;

/**
 * 
 */
public class ModelManager {

    /**
     * Default constructor
     */
    public ModelManager() {
    }




    /**
     * @param name A keresett eszköz neve
     */
    public void findDeviceByName(String name) {
        // TODO implement here
    }

    /**
     * @param from Az eszköz neve amely kimenetéhez csatlakozik a to
     * @param to Az eszköz neve amely bemenetére csatolja a from-t
     */
    public void addCable(String from, String to) {
        // TODO implement here
    }

    /**
     * @param from Az eszköz amelyből kimegy a kábel
     * @param to Az eszköz amelybe bemegy a kábel
     */
    public void removeCable(String from, String to) {
        // TODO implement here
    }



    /**
     * @param sensor Az érzékelő amelyet hozzá akarjuk adni a modellhez.
     */
    public void addSensor(Sensor sensor) {
        // TODO implement here
    }

    /**
     * @param device Az eszköz amelyet hozzá akarunk adni a modellhez.
     */
    public void addDevice(Device device) {
        // TODO implement here
    }

    /**
     * @param deviceName Az eszköz neve amelyet eltávolít a modellből.
     */
    public void removeDevice(String deviceName) {
        // TODO implement here
    }


}