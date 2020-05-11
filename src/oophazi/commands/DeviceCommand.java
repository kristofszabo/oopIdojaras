package oophazi.commands;

import oophazi.Device;
import oophazi.ModelManager;
import oophazi.Monitor;
import oophazi.creator.*;
import oophazi.exceptions.DeviceNotFoundException;
import oophazi.exceptions.MonitorNotConnectedException;
import oophazi.exceptions.MonitorNotFoundException;

import java.awt.datatransfer.SystemFlavorMap;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * A device főmenüt szimbolizáló command osztály
 */
public class DeviceCommand extends Command {
    /**
     * Konstruktor, ami inicializálja az almenü commandokat
     */
    public DeviceCommand() {
        super("device");
        commandHashMap = new HashMap<>();
        commandHashMap.put("show", new ShowCommand());
        commandHashMap.put("add", new AddCommand());
        commandHashMap.put("remove", new RemoveCommand());
        commandHashMap.put("update", new UpdateCommand());
        commandHashMap.put("filter", new FilterCommand());
    }

    /**
     * Almenü commandokat tartalmazó hashmap
     */
    private HashMap<String, Command> commandHashMap;


    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        commandHashMap.get(cmd[1]).action(modelManager, cmd);
    }

    /**
     * Egy eszköz megjelenítésére szolgáló almenü commandja
     */
    static class ShowCommand extends Command{

        public ShowCommand() {
            super("show");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            try {
                System.out.println(modelManager.findDeviceByName(cmd[2]));
            } catch (DeviceNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Egy eszköz hozzáadását szolgáló almenü commandja
     */
    static class AddCommand extends Command{

        HashMap<String, DeviceCreator> deviceCreatorHashMap;
        HashMap<String, SensorCreator> sensorCreatorHashMap;
        HashMap<String, MonitorCreator> monitorCreatorHashMap;
        public AddCommand() {
            super("add");
            deviceCreatorHashMap = new HashMap<>();
            sensorCreatorHashMap = new HashMap<>();
            monitorCreatorHashMap = new HashMap<>();

            sensorCreatorHashMap.put("HumiditySensor", new HumiditySensorCreator());
            sensorCreatorHashMap.put("RainSensor", new RainSensorCreator());
            sensorCreatorHashMap.put("TemperatureSensor", new TemperatureSensorCreator());
            sensorCreatorHashMap.put("WindforceSensor", new WindforceSensorCreator());

            deviceCreatorHashMap.put("DataConcentrator", new DataConcentratorCreator());
            deviceCreatorHashMap.put("DataStorage", new DataStorageCreator());


            monitorCreatorHashMap.put("Monitor", new MonitorCreator());
        }
        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            if(
                    deviceCreatorHashMap.containsKey(cmd[2]) |
                    monitorCreatorHashMap.containsKey(cmd[2]) |
                    sensorCreatorHashMap.containsKey(cmd[2])){


                if(cmd[2].contains("Sensor")){
                    modelManager.addSensor(sensorCreatorHashMap.get(cmd[2]).create(cmd[3]));
                }else if(cmd[2].contains("Monitor")){
                    modelManager.addMonitor(monitorCreatorHashMap.get(cmd[2]).create(cmd[3]));
                }else if(cmd[2].contains("Data")){
                    modelManager.addDevice(deviceCreatorHashMap.get(cmd[2]).create(cmd[3]));
                }
            }

        }
    }

    /**
     * Egy eszköz eltávolítását szolgáló almenü commandja
     */
    static class RemoveCommand extends Command{


        public RemoveCommand(){
            super("remove");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {

            try {
                modelManager.removeDevice(cmd[2]);
            } catch (DeviceNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Egy eszköz nevének módosítását szolgáló almenü commandja
     */
    static class UpdateCommand extends Command{

        public UpdateCommand() {
            super("update");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            Device device = null;
            try {
                device = modelManager.findDeviceByName(cmd[2]);
            } catch (DeviceNotFoundException e) {
                e.printStackTrace();
            }
            device.setName(cmd[3]);
        }
    }

    /**
     * Egy monitor dátumok közötti lekérését szolgáló almenü commandja
     */
    static class FilterCommand extends Command{

        public FilterCommand() {
            super("filter");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            if(cmd.length <4){
                System.out.println("A parancs formátuma(dátum formátum:YYYY.MM.DD.): device filter eszköznév dátummikortól dátummeddig");
                return;
            }
            try {
                String[] from = cmd[3].split("\\.");
                LocalDateTime dateFrom = LocalDateTime.of(Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]), 0, 0);
                String[] to = cmd[4].split("\\.");
                LocalDateTime dateTo = LocalDateTime.of(Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]), 0, 0);

                Monitor monitor = modelManager.findMonitorByName(cmd[2]);
                System.out.println(monitor.getStoredDataBetweenDates(dateFrom,dateTo));
            } catch (MonitorNotFoundException | MonitorNotConnectedException e) {
                System.out.println("A monitor nem található, vagy nincs csatlakoztatva semmilyen bemeneti eszközhöz");
            }catch (DateTimeException e){

            }
        }
    }
}
