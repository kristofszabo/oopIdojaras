package oophazi.commands;

import oophazi.ModelManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Osztály a help commandhoz
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void action(ModelManager modelManager, String[] cmd) {
        try {
            Scanner sc = new Scanner(new FileReader("help.txt"));
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()){
                sb.append(sc.nextLine()+"\n");
            }
            System.out.println(sb.toString());
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("A help.txt fájlt nem tudta megnyitni a program");
        }


    }
}
