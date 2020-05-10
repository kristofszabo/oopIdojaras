package oophazi.commands;

import oophazi.Device;
import oophazi.ModelManager;
import oophazi.creator.*;

import java.awt.datatransfer.SystemFlavorMap;
import java.util.HashMap;

public class DeviceCommand extends Command {
    public DeviceCommand() {
        super("device");
        commandHashMap = new HashMap<>();
        commandHashMap.put("show", new ShowCommand());
        commandHashMap.put("add", new AddCommand());
        commandHashMap.put("remove", new RemoveCommand());
        commandHashMap.put("update", new UpdateCommand());
    }
    private HashMap<String, Command> commandHashMap;
    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        commandHashMap.get(cmd[1]).action(modelManager, cmd);
    }


    class ShowCommand extends Command{

        public ShowCommand() {
            super("show");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {

        }
    }
    class AddCommand extends Command{

        HashMap<String, DeviceCreator> deviceCreatorHashMap;
        HashMap<String, SensorCreator> sensorCreatorHashMap;
        HashMap<String, MonitorCreator> monitorCreatorHashMap;
        public AddCommand() {
            super("add");
            deviceCreatorHashMap = new HashMap<>();
            sensorCreatorHashMap = new HashMap<>();

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
            if(cmd[1].contains("Sensor")){
                modelManager.addSensor(sensorCreatorHashMap.get(cmd[1]).create(cmd[2]));
            }else if(cmd[1].contains("Monitor")){
                modelManager.addMonitor(monitorCreatorHashMap.get(cmd[1]).create(cmd[2]));
            }else if(cmd[1].contains("Data")){
                modelManager.addDevice(deviceCreatorHashMap.get(cmd[1]).create(cmd[2]));
            }

        }
    }

    class RemoveCommand extends Command{


        public RemoveCommand(){
            super("remove");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            modelManager.removeDevice(cmd[2]);
        }
    }

    class UpdateCommand extends Command{

        public UpdateCommand() {
            super("update");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            Device device = modelManager.findDeviceByName(cmd[2]);
            device.setName(cmd[3]);
        }
    }
}
