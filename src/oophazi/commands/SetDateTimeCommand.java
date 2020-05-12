package oophazi.commands;

import oophazi.ModelManager;
import oophazi.exceptions.NotEnoughParameterException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Aktuális időt beállító menü command
 */
public class SetDateTimeCommand extends Command {
    public SetDateTimeCommand() {
        super("setdatetime");
    }


    /**
     * Beállítja a modell aktuális idejét a kapottra
     *
     * @param modelManager a használandó modelManager
     * @param cmd a bemenet széttördelve
     *
     *
     */
    @Override
    public void action(ModelManager modelManager, String[] cmd) throws NotEnoughParameterException {
        if(cmd.length <3){
            throw new NotEnoughParameterException();
        }
        String[] dates = cmd[1].split("\\.");
        if(dates.length<2){
            System.out.println("Rossz dátum formátum a helyes: YYYY.MM.DD.");
        }
        String[] time = cmd[2].split(":");
        if(time.length<1){
            System.out.println("Rossz idő formátum a helyes: hh:mm");
        }
        try{
            modelManager.setLocalDateTime(
                    LocalDateTime.of(
                            Integer.parseInt(dates[0]),
                            Integer.parseInt(dates[1]),
                            Integer.parseInt(dates[2]),
                            Integer.parseInt(time[0]),
                            Integer.parseInt(time[1])
                    ));
        }catch (NumberFormatException e){
            System.out.println("Csak számokat adjon meg dátumnak és időnek");
        }catch (DateTimeException e){
            System.out.println("Helyes dátumot és időt adjon meg");
        }

    }
}
