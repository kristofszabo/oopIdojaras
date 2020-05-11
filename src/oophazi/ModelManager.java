package oophazi;
import oophazi.exceptions.*;

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
    public void load(ModelManager manager) {
        devices = manager.devices;
        monitors = manager.monitors;
        sensors = manager.sensors;
        cables = manager.cables;
    }




    /**
     * @param name A keresett eszköz neve
     */
    public Device findDeviceByName(String name) throws DeviceNotFoundException {
        for (Device device:
             devices) {
            if(device.getName().equals(name)){
                return device;
            }
        }
        throw new DeviceNotFoundException();
    }

    /**
     * @param from Az eszköz neve amely kimenetéhez csatlakozik a to
     * @param to Az eszköz neve amely bemenetére csatolja a from-t
     */
    public void addCable(String from, String to) throws NoFreeInputSocketException, DeviceNotFoundException {
        Cable cable = new Cable(findDeviceByName(from).getFreeOutputSocket(), findDeviceByName(to).getFreeInputSocket());
        cables.add(cable);
    }

    /**
     * @param from Az eszköz amelyből kimegy a kábel
     * @param to Az eszköz amelybe bemegy a kábel
     */
    public void removeCable(String from, String to) throws CableNotFoundException, DeviceNotFoundException {
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
    public void removeDevice(String deviceName) throws DeviceNotFoundException {
        Device device = findDeviceByName(deviceName);
        removeCablesFromDevice(device);
        if(devices.contains(device)){
            devices.remove(device);
        }

        if(sensors.contains(device)){
            sensors.remove(device);
        }

        if(monitors.contains(device)){
            monitors.remove(device);
        }
        //TODO: ERROR
    }

    public void removeCablesFromDevice(Device device){
        removeInputCablesFromDevice(device);
        removeOutputCablesFromDevice(device);
    }

    private void removeInputCablesFromDevice(Device device){
        for(Socket s: device.getOutputSockets()){
            Cable c = s.getCable();
            if(c!=null){
                cables.remove(c);
                c.getSocketFrom().setCable(null);
                c.getSocketTo().setCable(null);
            }
        }
    }


    private void removeOutputCablesFromDevice(Device device){
        for(Socket s: device.getInputSockets()){
            Cable c = s.getCable();
            if(c!=null){
                cables.remove(c);
                c.getSocketFrom().setCable(null);
                c.getSocketTo().setCable(null);
            }
        }
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
            s.setDataTime(localDateTime);
            s.send();
        }
        for (Device d:
             devices) {
            d.setIsValidData(false);
        }
    }

    public Monitor findMonitorByName(String name) throws MonitorNotFoundException {
        for (Monitor monitor:
             monitors) {
            if(monitor.getName().equals(name)){
                return monitor;
            }
        }
        throw new MonitorNotFoundException();
    }

    public Sensor findSensorByName(String name) throws SensorNotFoundException {
        for (Sensor sensor:
                sensors) {
            if(sensor.getName().equals(name)){
                return sensor;
            }
        }
        throw new SensorNotFoundException();
    }
}