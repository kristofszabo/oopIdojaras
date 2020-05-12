package oophazi.tests;

import oophazi.DataConcentrator;
import oophazi.DataStorage;
import oophazi.ModelManager;
import oophazi.RainSensor;
import oophazi.exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertEquals;


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

    /**
     * Ha hozzá adunk egy sensort az a device listában és a sensor listában is szerepelnie kell
     */
    @Test
    public void addDevice()  {
        try {
            model.addDevice(new RainSensor("myStorage"));
        } catch (NameCollisionException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(2,model.getDevices().size());

        try {
            model.addSensor(new RainSensor("rain"));
        } catch (NameCollisionException e) {
            System.err.println(e.getMessage());
        }

        assertEquals(3,model.getDevices().size());
        assertEquals(1,model.getSensors().size());
    }


    /**
     * Eszköz hozzá adás és másik eltávolítás név alapján
     */
    @Test
    public void removeDevice(){
        try {
            model.removeDevice("garage");
        } catch (DeviceNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(2, model.getDevices().size());
        try {
            model.removeDevice("myStorage");
        } catch (DeviceNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(1, model.getDevices().size());
    }


    /**
     * Ha olyan kábelt akarunk találni aminek legalább egyik oldalán lévő eszköz hiányzik hibát kapunk
     * @throws DeviceNotFoundException
     * @throws CableNotFoundException
     */
    @Test(expected = DeviceNotFoundException.class)
    public void cableRemove() throws DeviceNotFoundException, CableNotFoundException {
        model.removeCable("garage","kecske");

    }

    @Test(expected = CableExistsException.class)
    public void cableAdd() throws NoFreeInputSocketException, CableExistsException, DeviceNotFoundException, NoFreeOutputSocketException {
        model.addCable("myConcentrator","myStorage");
    }

}