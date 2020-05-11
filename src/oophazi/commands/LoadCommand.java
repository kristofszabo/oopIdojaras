package oophazi.commands;

import oophazi.FileManager;
import oophazi.ModelManager;

import java.io.IOException;

/**
 * Osztály a betöltés commandhoz
 */
public class LoadCommand extends Command {
    public LoadCommand() {
        super("load");
    }

    /**
     * Betölti a modelbe a fájlt
     * @param modelManager
     * @param cmd a bemeneti paraméterek
     */
    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        FileManager fm = new FileManager(modelManager);
        String filename = cmd[1];
        if(!filename.endsWith(".ser")){
            filename+=".ser";
        }
        try {
            modelManager.load(FileManager.load(cmd[1]));
        } catch (IOException e) {
            System.out.println("N");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
