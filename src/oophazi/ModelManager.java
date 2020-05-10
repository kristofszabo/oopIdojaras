package oophazi;
import oophazi.exceptions.CableNotFoundException;
import oophazi.exceptions.NoFreeInputSocketException;
import oophazi.exceptions.NoFreeOutputSocketException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 
 */
public class ModelManager implements Serializable {

    private ArrayList<Device> devices;
    private ArrayList<Sensor> sensors;
    private ArrayList<Monitor> monitors;

    private ArrayList<Cable> cables;


    private LocalDateTime localDateTime = LocalDateTime.now();

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Default constructor
     */
    public ModelManager() {
        devices = new ArrayList<>();
        monitors = new ArrayList<>();
        sensors = new ArrayList<>();
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
    public void addCable(String from, String to) throws NoFreeInputSocketException {
        Cable cable = new Cable(findDeviceByName(from).getFreeOutputSocket(), findDeviceByName(to).getFreeInputSocket());
        cables.add(cable);
    }

    /**
     * @param from Az eszköz amelyből kimegy a kábel
     * @param to Az eszköz amelybe bemegy a kábel
     */
    public void removeCable(String from, String to) throws CableNotFoundException {
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
        throw new CableNotFoundException();
    }



    /**
     * @param sensor Az érzékelő amelyet hozzá akarjuk adni a modellhez.
     */
    public void addSensor(Sensor sensor) {
        addDevice(sensor);
        sensors.add(sensor);
    }

    public void addMonitor(Monitor monitor){
        addDevice(monitor);
        monitors.add(monitor);
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
        Device device = findDeviceByName(deviceName);
        devices.remove(device);
        sensors.remove(device);
        monitors.remove(device);
        //TODO: ERROR
    }

    @Override
    public String toString() {
        return "ModelManager{" +
                "devices=" + devices +
                ", cables=" + cables +
                '}';
    }

    public void step() {
        for(Sensor s: sensors){
            s.send();
        }
        for (Device d:
             devices) {
            d.setIsValidData(false);
        }
    }

    public Monitor findMonitorByName(String name){
        for (Monitor monitor:
             monitors) {
            if(monitor.getName().equals(name)){
                return monitor;
            }
        }
        return null;
    }

    public Sensor findSensorByName(String name){
        for (Sensor sensor:
                sensors) {
            if(sensor.getName().equals(name)){
                return sensor;
            }
        }
        return null;
    }
}