package oophazi.commands;

import oophazi.ModelManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SetDateTimeCommand extends Command {
    public SetDateTimeCommand() {
        super("setdateime");
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        String[] dates = cmd[1].split(".");
        String[] time = cmd[2].split(":");
        modelManager.setLocalDateTime(
                LocalDateTime.of(
                        Integer.parseInt(dates[0]),
                        Integer.parseInt(dates[1]),
                        Integer.parseInt(dates[2]),
                        Integer.parseInt(time[0]),
                        Integer.parseInt(time[1])
                ));
    }
}
