package oophazi.commands;

import oophazi.ModelManager;

/**
 * Osztály az exit commandhoz
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {

    }
}
