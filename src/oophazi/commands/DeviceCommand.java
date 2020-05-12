package oophazi.commands;

import oophazi.Data;
import oophazi.ModelManager;
import oophazi.Monitor;
import oophazi.creator.*;
import oophazi.exceptions.*;

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
        commandHashMap.put("list", new ListCommand());
    }

    /**
     * Almenü commandokat tartalmazó hashmap
     */
    private HashMap<String, Command> commandHashMap;


    @Override
    public void action(ModelManager modelManager, String[] cmd) throws NotEnoughParameterException, MenuNotFoundException {
        if(commandHashMap.containsKey(cmd[1])){
            commandHashMap.get(cmd[1]).action(modelManager, cmd);

        }else{
            throw new MenuNotFoundException();
        }
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
                System.err.println(e.getMessage());
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

        /**
         * Eszköz típusa alapján hozzá adja a megfelelő listához
         * @param modelManager a model amin végzi a műveletet
         * @param cmd a bemeneti parancs
         */
        @Override
        public void action(ModelManager modelManager, String[] cmd) throws NotEnoughParameterException {
            if(cmd.length<4){
                throw new NotEnoughParameterException();
            }
            if(
                    deviceCreatorHashMap.containsKey(cmd[2]) |
                    monitorCreatorHashMap.containsKey(cmd[2]) |
                    sensorCreatorHashMap.containsKey(cmd[2])){

                try{
                    if(cmd[2].contains("Sensor")){
                        modelManager.addSensor(sensorCreatorHashMap.get(cmd[2]).create(cmd[3]));
                    }else if(cmd[2].contains("Monitor")){
                        modelManager.addMonitor(monitorCreatorHashMap.get(cmd[2]).create(cmd[3]));
                    }else if(cmd[2].contains("Data")){
                        modelManager.addDevice(deviceCreatorHashMap.get(cmd[2]).create(cmd[3]));
                    }
                }catch (NameCollisionException e) {
                    System.err.println(e.getMessage());
                }

            }else{
                System.err.println("Nincs ilyen típusú eszköz");
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

        /**Eltávolítja az adott névvel rendelkező eszközt
         *
         * @param modelManager model amin végzi a műveletet
         * @param cmd a bemeneti parancs
         */
        @Override
        public void action(ModelManager modelManager, String[] cmd) {

            try {
                modelManager.removeDevice(cmd[2]);
            } catch (DeviceNotFoundException e) {
                System.err.println(e.getMessage());
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

        /**Átnevez egy eszközt
         *
         * @param modelManager a model amin a műveletet akarjuk elvégezni
         * @param cmd a bemeneti parancs
         */
        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            try {
                modelManager.updateDeviceName(cmd[2],cmd[3]);
            } catch (DeviceNotFoundException | NameCollisionException e) {
                System.err.println(e.getMessage());
            }

        }
    }

    /**
     * Egy monitor dátumok közötti lekérését szolgáló almenü commandja
     */
    static class FilterCommand extends Command{

        public FilterCommand() {
            super("filter");
        }

        /**
         * Ki írja a monitorhoz tartozó adatokat
         *
         * @param modelManager A model amin a művelet elvégződik
         * @param cmd A bemeneti parancs
         */
        @Override
        public void action(ModelManager modelManager, String[] cmd) throws NotEnoughParameterException {
            if(cmd.length <5){
                System.out.println("A parancs formátuma(dátum formátum:YYYY.MM.DD.): device filter eszköznév dátummikortól dátummeddig");
                throw new NotEnoughParameterException();
            }
            try {
                String[] from = cmd[3].split("\\.");
                LocalDateTime dateFrom = LocalDateTime.of(Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]), 0, 0);
                String[] to = cmd[4].split("\\.");
                LocalDateTime dateTo = LocalDateTime.of(Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]), 23, 59);

                Monitor monitor = modelManager.findMonitorByName(cmd[2]);
                var datas = monitor.getStoredDataBetweenDates(dateFrom,dateTo);
                for(Data d : datas){
                    System.out.println(d);
                }
            } catch (MonitorNotFoundException | MonitorNotConnectedException e) {
                System.err.println(e.getMessage());
            }catch (DateTimeException e){
                System.err.println(e.getMessage());
            }
        }
    }

    static class ListCommand extends Command{

        public ListCommand() {
            super("list");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) throws NotEnoughParameterException {
            System.out.println(modelManager);
        }
    }
}
