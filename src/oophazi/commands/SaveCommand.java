package oophazi.commands;

import oophazi.FileManager;
import oophazi.ModelManager;

import java.io.IOException;

/**
 * Osztály a mentés commandhoz
 */
public class SaveCommand extends Command {
    public SaveCommand() {
        super("save");
    }

    /**
     * Lementi a modellt adott néven
     * @param modelManager a használatban lévő modell
     * @param cmd a bemeneti parancs
     */
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
