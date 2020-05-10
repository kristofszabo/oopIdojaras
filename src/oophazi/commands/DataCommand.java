package oophazi.commands;

import oophazi.Data;
import oophazi.Device;
import oophazi.ModelManager;
import oophazi.Sensor;

import java.util.HashMap;

public class DataCommand extends Command {
    public DataCommand() {
        super("Data");
        commandHashMap = new HashMap<>();

        commandHashMap.put("set", new SetCommand());
    }
    HashMap<String, Command> commandHashMap;

    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        commandHashMap.get(cmd[1]).action(modelManager,cmd);
    }

    class SetCommand extends Command {

        public SetCommand() {
            super("set");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            Device device = modelManager.findDeviceByName(cmd[2]);
            device.receive(new Data((Sensor)device,Double.parseDouble(cmd[3])));
        }
    }
}
