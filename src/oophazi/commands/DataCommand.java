package oophazi.commands;

import oophazi.Data;
import oophazi.Device;
import oophazi.ModelManager;
import oophazi.Sensor;
import oophazi.exceptions.MenuNotFoundException;
import oophazi.exceptions.NotEnoughParameterException;
import oophazi.exceptions.SensorNotFoundException;

import java.util.HashMap;

/**
 * Osztály a data commandhoz
 */
public class DataCommand extends Command {
    public DataCommand() {
        super("Data");
        commandHashMap = new HashMap<>();

        commandHashMap.put("set", new SetCommand());
    }
    HashMap<String, Command> commandHashMap;

    /**
     * Kiválassza a data almenüjét
     * @param modelManager az aktuális model, amin dolgozik
     * @param cmd a bemeneti parancs
     */
    @Override
    public void action(ModelManager modelManager, String[] cmd) throws NotEnoughParameterException, MenuNotFoundException {
        if(commandHashMap.containsKey(cmd[1])){
            commandHashMap.get(cmd[1]).action(modelManager,cmd);
        }else{
            throw new MenuNotFoundException();
        }
    }

    class SetCommand extends Command {

        public SetCommand() {
            super("set");
        }


        /**
         * Adat beállítása egy szenzor számára
         * @param modelManager az aktuális modell amin dolgozik
         * @param cmd a bemeneti parancs
         */
        @Override
        public void action(ModelManager modelManager, String[] cmd) throws NotEnoughParameterException, NumberFormatException {
            if(cmd.length<4){
                throw new NotEnoughParameterException();
            }
            Sensor sensor = null;
            try {
                sensor = modelManager.findSensorByName(cmd[2]);
            } catch (SensorNotFoundException e) {
                System.err.println(e.getMessage());
                return;
            }
            try{
                sensor.receive(new Data(sensor,Double.parseDouble(cmd[3])));

            }catch (NumberFormatException e){
                System.err.println("Csak tizedes ponttal elválasztott számot adjon meg.");
            }
        }
    }
}
