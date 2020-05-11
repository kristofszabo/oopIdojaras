package oophazi;

import oophazi.exceptions.DeviceNotFoundException;
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
            if(cmd.equals("exit")){
                break;
            }
            if(cmd.length == 0){
                System.out.print("Írjon be valamit a dokumentáció lekéréséhez írja be a help parancsot");
            }
            menu.play(cmd);

            System.out.println(modelManager);
        }
        sc.close();

    }
}
