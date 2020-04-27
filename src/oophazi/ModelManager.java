package oophazi;
import java.util.*;

/**
 * 
 */
public class ModelManager {

    ArrayList<Device> devices;
    ArrayList<Cable> cables;

    /**
     * Default constructor
     */
    public ModelManager() {
        devices = new ArrayList<>();
        cables = new ArrayList<>();
    }




    /**
     * @param name A keresett eszköz neve
     */
    public Device findDeviceByName(String name) {
        for (Device device:
             devices) {
            if(device.getName().equals(name)){
                return device;
            }
        }
        return null;//TODO: ERROR
    }

    /**
     * @param from Az eszköz neve amely kimenetéhez csatlakozik a to
     * @param to Az eszköz neve amely bemenetére csatolja a from-t
     */
    public void addCable(String from, String to) {
        Cable cable = new Cable(findDeviceByName(from).getFreeOutputSocket(), findDeviceByName(to).getFreeInputSocket());
        cables.add(cable);
    }

    /**
     * @param from Az eszköz amelyből kimegy a kábel
     * @param to Az eszköz amelybe bemegy a kábel
     */
    public void removeCable(String from, String to) {
        Device fromDevice = findDeviceByName(from);
        Device toDevice = findDeviceByName(to);
        for (int i = 0; i < cables.size(); ++i){
            Cable cable = cables.get(i);
            if(cable.getSocketFrom().getOwner()==fromDevice&& cable.getSocketTo().getOwner() == toDevice){
                cable.getSocketTo().setCable(null);
                cable.getSocketFrom().setCable(null);
                cables.remove(i);
                return;
            }
        }
        //TODO: ERROR
    }



    /**
     * @param sensor Az érzékelő amelyet hozzá akarjuk adni a modellhez.
     */
    public void addSensor(Sensor sensor) {
        devices.add(sensor);
    }

    /**
     * @param device Az eszköz amelyet hozzá akarunk adni a modellhez.
     */
    public void addDevice(Device device) {
        devices.add(device);
    }

    /**
     * @param deviceName Az eszköz neve amelyet eltávolít a modellből.
     */
    public void removeDevice(String deviceName) {
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            if(device.getName().equals(deviceName)){
                devices.remove(i);
                return;
            }
        }
        //TODO: ERROR
    }


}