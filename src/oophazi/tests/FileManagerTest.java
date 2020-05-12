package oophazi.tests;

import oophazi.DataStorage;
import oophazi.FileManager;
import oophazi.ModelManager;
import oophazi.Monitor;
import oophazi.exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileManagerTest {

    ModelManager model;
    FileManager fileManager;
    @Before
    public void setUp(){
        model = new ModelManager();
        fileManager=new FileManager(model);
        try {
            model.addMonitor(new Monitor("tv1"));
            model.addDevice(new DataStorage("storage"));
            model.addCable("storage","tv1");
        } catch (NameCollisionException e) {
            e.printStackTrace();
        } catch (NoFreeOutputSocketException e) {
            e.printStackTrace();
        } catch (NoFreeInputSocketException e) {
            e.printStackTrace();
        } catch (DeviceNotFoundException e) {
            e.printStackTrace();
        } catch (CableExistsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void save() {
        try {
            fileManager.save("valami.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            model = FileManager.load("valami.ser");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(2,model.getDevices().size());
    }

}