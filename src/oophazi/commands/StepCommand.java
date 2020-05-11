package oophazi.commands;

import oophazi.ModelManager;

/**
 * Modellt léptető menü command
 */
public class StepCommand extends Command{
    public StepCommand() {
        super("step");
    }


    /**
     * Lépteti a megadott modellt
     * @param modelManager a model amin dolgozik
     * @param cmd a bemeneti parancs
     */
    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        modelManager.step();

    }
}
