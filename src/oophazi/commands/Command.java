package oophazi.commands;

import oophazi.ModelManager;

public abstract class Command {
    private String name;

    public String getName() {
        return name;
    }

    public Command(String name) {
        this.name = name;
    }

    public abstract void action(ModelManager modelManager, String[] cmd);
}
