package oophazi.commands;

import oophazi.FileManager;
import oophazi.ModelManager;

import java.io.IOException;

public class SaveCommand extends Command {
    public SaveCommand() {
        super("save");
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        FileManager fileManager = new FileManager(modelManager);
        try {
            fileManager.save(cmd[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
