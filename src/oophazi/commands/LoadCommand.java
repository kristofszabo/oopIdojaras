package oophazi.commands;

import oophazi.FileManager;
import oophazi.ModelManager;

import java.io.IOException;

public class LoadCommand extends Command {
    public LoadCommand() {
        super("load");
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        try {
            modelManager = FileManager.load(cmd[1]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
