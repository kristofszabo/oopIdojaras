package oophazi.commands;

import oophazi.ModelManager;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        System.exit(0);
    }
}
