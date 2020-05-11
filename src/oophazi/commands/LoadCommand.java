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
        FileManager fm = new FileManager(modelManager);
        try {
            modelManager.load(FileManager.load(cmd[1]));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
