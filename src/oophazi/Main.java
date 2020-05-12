package oophazi;

import oophazi.exceptions.DeviceNotFoundException;
import oophazi.exceptions.MenuNotFoundException;
import oophazi.exceptions.NoFreeInputSocketException;
import oophazi.exceptions.NotEnoughParameterException;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ModelManager modelManager = new ModelManager();
        Menu menu = new Menu(modelManager);
        Scanner sc = new Scanner(System.in);
        String[] cmd;
        while(true){
            cmd = sc.nextLine().split(" ");
            if(cmd[0].equals("exit")){
                break;
            }
            if(cmd[0].length() == 0){
                System.out.print("A dokumentáció lekéréséhez írja be a help parancsot");
                continue;
            }
            try {
                menu.play(cmd);
            } catch (MenuNotFoundException | NotEnoughParameterException e) {
                System.err.println(e.getMessage());
            }

        }
        sc.close();

    }
}
