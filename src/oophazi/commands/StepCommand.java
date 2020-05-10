package oophazi.commands;

import oophazi.ModelManager;

public class StepCommand extends Command{
    public StepCommand() {
        super("step");
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        modelManager.step();

    }
}
