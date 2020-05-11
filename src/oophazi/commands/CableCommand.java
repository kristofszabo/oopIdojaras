package oophazi.commands;

import oophazi.ModelManager;
import oophazi.exceptions.CableNotFoundException;
import oophazi.exceptions.DeviceNotFoundException;
import oophazi.exceptions.NoFreeInputSocketException;

import java.util.HashMap;

public class CableCommand extends Command {
    HashMap<String,Command> commandHashMap;
    public CableCommand() {
        super("cable");
        commandHashMap = new HashMap<>();
        commandHashMap.put("add",new AddCommand());
        commandHashMap.put("remove", new RemoveCommand());
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        commandHashMap.get(cmd[1]).action(modelManager, cmd);
    }

    class AddCommand extends Command{

        public AddCommand() {
            super("add");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            try {
                modelManager.addCable(cmd[2],cmd[3]);
            } catch (NoFreeInputSocketException | DeviceNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    class RemoveCommand extends Command{

        public RemoveCommand() {
            super("remove");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            try {
                modelManager.removeCable(cmd[2], cmd[3]);
            } catch (CableNotFoundException | DeviceNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
