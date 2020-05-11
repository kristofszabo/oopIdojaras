package oophazi;
import oophazi.commands.*;

import java.util.*;

/**
 * 
 */
public class Menu {

    private HashMap<String, Command> commandHashMap;
    private ModelManager modelManager;
    /**
     * Default constructor
     */
    public Menu(ModelManager manager) {
        commandHashMap = new HashMap<>();
        commandHashMap.put("data",new DataCommand());
        commandHashMap.put("device", new DeviceCommand());
        commandHashMap.put("exit", new ExitCommand());
        commandHashMap.put("help", new HelpCommand());
        commandHashMap.put("load", new LoadCommand());
        commandHashMap.put("save", new SaveCommand());
        commandHashMap.put("setdatetime", new SetDateTimeCommand());
        commandHashMap.put("step", new StepCommand());
        commandHashMap.put("cable", new CableCommand());

        this.modelManager = manager;
    }

    public void play(String[] cmd){
        if(commandHashMap.containsKey(cmd[0])){
            commandHashMap.get(cmd[0]).action(modelManager, cmd);

        }else{
            System.out.println("Az adott menüpont nem található a dokumentáció megtekintéséhez írja be a 'help' parancsot");
        }

    }


}