package oophazi;
import oophazi.exceptions.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 
 */
public class ModelManager implements Serializable {

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public ArrayList<Monitor> getMonitors() {
        return monitors;
    }

    public ArrayList<Cable> getCables() {
        return cables;
    }

    /**
     * Az összes modellben megtalálható eszköz
     */
    private ArrayList<Device> devices;

    /**
     * Az összes modellben megtalálható szenzor
     */
    private ArrayList<Sensor> sensors;

    /**
     * Az összes modellben megtalálható monitor
     */
    private ArrayList<Monitor> monitors;

    /**
     * Az összes modellben megtalálható kábel
     */
    private ArrayList<Cable> cables;


    /**
     * A modell aktuális ideje
     */
    private LocalDateTime localDateTime = LocalDateTime.now();

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Végig nézi az eszközöket, hogy van e már eszköz ilyen névvel
     *
     * @param name név kikeresése
     * @return igaz, ha van már ilyen név
     */
    public boolean checkIfNameExists(String name){
        for(Device d: devices){
            if(d.getName().equals(name)){
                return true;
            }
        }
        return false;
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
    public void addCable(String from, String to) throws NoFreeInputSocketException, DeviceNotFoundException, NoFreeOutputSocketException, CableExistsException {
        if(isCableAlreadyExists(from,to)){
            throw new CableExistsException();
        }
        if(from.equals(to)){
            return;
        }
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


    public boolean isCableAlreadyExists(String from, String to){
        for (Cable c:
             cables) {
            if(c.getSocketFrom().getOwner().getName().equals(from) && c.getSocketTo().getOwner().getName().equals(to)){
                return true;
            }
        }
        return false;
    }

    /**
     * @param sensor Az érzékelő amelyet hozzá akarjuk adni a modellhez.
     */
    public void addSensor(Sensor sensor) throws NameCollisionException {
        addDevice(sensor);
        sensors.add(sensor);
    }

    public void addMonitor(Monitor monitor) throws NameCollisionException {
        addDevice(monitor);
        monitors.add(monitor);
    }

    /**
     * @param device Az eszköz amelyet hozzá akarunk adni a modellhez.
     *
     * @throws NameCollisionException ha van már ilyen nevű device
     */
    public void addDevice(Device device) throws NameCollisionException {
        if(checkIfNameExists(device.getName())){
            throw new NameCollisionException();
        }
        devices.add(device);

    }

    public void updateDeviceName(String oldName, String newName) throws DeviceNotFoundException, NameCollisionException {
        if(checkIfNameExists(newName)){
            throw new NameCollisionException();
        }
        findDeviceByName(oldName).setName(newName);
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

    public void step() {
        for(Sensor s: sensors){

            s.receive(new Data(s.getStoredDatas().get(0)));
            s.setDataTime(localDateTime);
            s.send();
        }

        cleanModel();

    }

    private void cleanModel(){
        for (Device d:
                devices) {
            d.setIsValidData(false);
            if(!d.canStoreData()){
                d.clean();
            }
        }
    }

    /**
     * Vissza ad egy monitort az adott néven
     * @param name a monitor neve
     * @return a monitor
     * @throws MonitorNotFoundException ha nem talált monitort
     */
    public Monitor findMonitorByName(String name) throws MonitorNotFoundException {
        for (Monitor monitor:
             monitors) {
            if(monitor.getName().equals(name)){
                return monitor;
            }
        }
        throw new MonitorNotFoundException();
    }

    /**
     * Vissza adja, a szenzort az adott névvel
     * @param name a keresendő szenzor neve
     * @return a keresett szenzor
     * @throws SensorNotFoundException ha nem talált ilyen szenzort
     */
    public Sensor findSensorByName(String name) throws SensorNotFoundException {
        for (Sensor sensor:
                sensors) {
            if(sensor.getName().equals(name)){
                return sensor;
            }
        }
        throw new SensorNotFoundException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Modellben szereplő információk:\n");
        sb.append("Eszközök: \n");
        for (Device d:
             devices) {
            sb.append(d);
            sb.append("\n");
        }
        sb.append("Kábelek: \n");
        for (Cable c :
                cables) {
            sb.append(c);

            sb.append("\n");
        }
        sb.append("localDateTime=");
        sb.append(localDateTime);
        return sb.toString();
    }
}