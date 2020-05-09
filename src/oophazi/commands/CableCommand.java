package oophazi.commands;

import oophazi.ModelManager;

public class CableCommand extends Command {
    public CableCommand() {
        super("cable");
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {

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
