package oophazi;

import oophazi.exceptions.NoFreeInputSocketException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello világ");
        ModelManager modelManager = new ModelManager();
        modelManager.addDevice(new DataConcentrator("MyDataConcentrator"));
        modelManager.addDevice(new DataStorage("MyDataStorage"));
        try {
            modelManager.addCable("MyDataConcentrator","MyDataStorage");
        } catch (NoFreeInputSocketException e) {
            e.printStackTrace();
        }

        modelManager.removeDevice("MyDataConcentrator");
        FileManager fm = new FileManager(modelManager);

        try {
            fm.save("valami.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            modelManager = FileManager.load("valami.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(modelManager);
    }
}
