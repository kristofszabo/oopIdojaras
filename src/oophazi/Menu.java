package oophazi;
import oophazi.commands.*;

import java.util.*;

/**
 * 
 */
public class Menu {

    private HashMap<String, Command> commandHashMap;
    /**
     * Default constructor
     */
    public Menu() {
        commandHashMap = new HashMap<>();
        commandHashMap.put("data",new DataCommand());
        commandHashMap.put("device", new DeviceCommand());
        commandHashMap.put("exit", new ExitCommand());
        commandHashMap.put("help", new HelpCommand());
        commandHashMap.put("load", new LoadCommand());
        commandHashMap.put("save", new SaveCommand());
        commandHashMap.put("setdatetime", new SetDateTimeCommand());
        commandHashMap.put("step", new StepCommand());
    }

    public void play(ModelManager modelManager, String[] cmd){
        commandHashMap.get(cmd[0]).action(modelManager, cmd);
    }


}