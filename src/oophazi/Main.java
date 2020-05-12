package oophazi;

import oophazi.exceptions.DeviceNotFoundException;
import oophazi.exceptions.MenuNotFoundException;
import oophazi.exceptions.NoFreeInputSocketException;

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
            if(cmd.length == 0){
                System.out.print("Írjon be valamit a dokumentáció lekéréséhez írja be a help parancsot");
            }
            try {
                menu.play(cmd);
            } catch (MenuNotFoundException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(modelManager);
        }
        sc.close();

    }
}
