package oophazi.tests;

import oophazi.DataConcentrator;
import oophazi.DataStorage;
import oophazi.ModelManager;
import oophazi.RainSensor;
import oophazi.exceptions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ModelManagerTest {
    ModelManager model;

    @Before
    public void setUp() {
        model = new ModelManager();
        try {
            model.addDevice(new DataStorage("myStorage"));
            model.addDevice(new DataConcentrator("myConcentrator"));
            model.addCable("myConcentrator","myStorage");
        } catch (NameCollisionException e) {
            System.err.println(e.getMessage());
        } catch (DeviceNotFoundException e) {
            e.printStackTrace();
        } catch (CableExistsException e) {
            e.printStackTrace();
        } catch (NoFreeInputSocketException e) {
            e.printStackTrace();
        } catch (NoFreeOutputSocketException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void addDevice()  {
        try {
            model.addDevice(new RainSensor("myStorage"));
        } catch (NameCollisionException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(1,model.getDevices().size());

        try {
            model.addSensor(new RainSensor("rain"));
        } catch (NameCollisionException e) {
            System.err.println(e.getMessage());
        }

        assertEquals(2,model.getDevices().size());
        assertEquals(1,model.getSensors().size());
    }

    @Test
    public void removeDevice(){
        try {
            model.removeDevice("garage");
        } catch (DeviceNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(1, model.getDevices().size());
        try {
            model.removeDevice("myStorage");
        } catch (DeviceNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(0, model.getDevices().size());
    }


    @Test(expected = DeviceNotFoundException.class)
    public void cableRemove() throws DeviceNotFoundException, CableNotFoundException {
        model.removeCable("sajt","kecske");

    }

    @Test(expected = CableExistsException.class)
    public void cableAdd() throws NoFreeInputSocketException, CableExistsException, DeviceNotFoundException, NoFreeOutputSocketException {
        model.addCable("myConcentrator","myStorage");
    }

}