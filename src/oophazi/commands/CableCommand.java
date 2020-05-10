package oophazi.commands;

import oophazi.ModelManager;

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
            modelManager.addCable(cmd[2],cmd[3]);
        }
    }
    class RemoveCommand extends Command{

        public RemoveCommand() {
            super("remove");
        }

        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            modelManager.removeCable(cmd[2], cmd[3]);
        }
    }
}
