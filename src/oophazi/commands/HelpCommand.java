package oophazi.commands;

import oophazi.ModelManager;

/**
 * Osztály a help commandhoz
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        System.out.println("HELP");//TODO: Help leírás
    }
}
