package oophazi;
import oophazi.commands.*;
import oophazi.exceptions.MenuNotFoundException;
import oophazi.exceptions.NotEnoughParameterException;

import java.util.*;

/**
 * A menüt megvalósító osztály
 */
public class Menu {

    /**
     * A menü alap parancsait tároló map
     */
    private HashMap<String, Command> commandHashMap;
    /**
     * A menühöz tartozó model
     */
    private ModelManager modelManager;
    /**
     * Inicializálja a hash map-et amiben a parancsok vannak és beállítja az aktuálisan használt modellt
     */
    public Menu(ModelManager manager) {
        commandHashMap = new HashMap<>();
        commandHashMap.put("data",new DataCommand());
        commandHashMap.put("device", new DeviceCommand());
        commandHashMap.put("help", new HelpCommand());
        commandHashMap.put("load", new LoadCommand());
        commandHashMap.put("save", new SaveCommand());
        commandHashMap.put("setdatetime", new SetDateTimeCommand());
        commandHashMap.put("step", new StepCommand());
        commandHashMap.put("cable", new CableCommand());

        this.modelManager = manager;
    }

    /**
     * Meghívja a megfelelő commandhoz tartozó tevékenységet
     * @param cmd bemeneti parancs
     * @throws MenuNotFoundException ha a menü nem található
     * @throws NotEnoughParameterException ha nincs elég paraméter megadva a bemeneti parancsban
     */
    public void play(String[] cmd) throws MenuNotFoundException, NotEnoughParameterException {
        if(commandHashMap.containsKey(cmd[0])){
            commandHashMap.get(cmd[0]).action(modelManager, cmd);

        }else{
            throw new MenuNotFoundException();
        }

    }


}