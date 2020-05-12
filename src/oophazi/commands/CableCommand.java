package oophazi.commands;

import oophazi.ModelManager;
import oophazi.exceptions.*;

import java.util.HashMap;

/**
 * Osztály a cable commandhoz
 */
public class CableCommand extends Command {
    HashMap<String,Command> commandHashMap;
    public CableCommand() {
        super("cable");
        commandHashMap = new HashMap<>();
        commandHashMap.put("add",new AddCommand());
        commandHashMap.put("remove", new RemoveCommand());
    }

    /**
     * Kábel almenü választás
     * @param modelManager a model, amin dolgozik
     * @param cmd a bemeneti parancs
     */
    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        commandHashMap.get(cmd[1]).action(modelManager, cmd);
    }

    /**
     * Kábel hozzá adás command
     */
    class AddCommand extends Command{

        public AddCommand() {
            super("add");
        }

        /**
         * Hozzá ad egy kábelt a modellhez
         * @param modelManager a model amin dolgozik
         * @param cmd a bemeneti parancs
         */
        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            try {
                modelManager.addCable(cmd[2],cmd[3]);
            } catch (NoFreeInputSocketException | DeviceNotFoundException | NoFreeOutputSocketException | CableExistsException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Kábel eltávolítás command
     */
    class RemoveCommand extends Command{

        public RemoveCommand() {
            super("remove");
        }

        /**
         * Kábel eltávolítás
         * @param modelManager a model amin dolgozik
         * @param cmd a bemeneti parancs
         */
        @Override
        public void action(ModelManager modelManager, String[] cmd) {
            try {
                modelManager.removeCable(cmd[2], cmd[3]);
            } catch (CableNotFoundException | DeviceNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
