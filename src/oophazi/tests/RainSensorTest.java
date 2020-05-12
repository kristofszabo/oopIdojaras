package oophazi.tests;

import oophazi.RainSensor;
import oophazi.Sensor;
import oophazi.exceptions.NoFreeInputSocketException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RainSensorTest {

    Sensor rain;

    /**
     * Eső érzékelő létrehozása
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        rain = new RainSensor("rain");
    }

    /**
     * Érzékelőknek nincs bemenete tehát hibát kell dobnia
     * @throws NoFreeInputSocketException
     */
    @Test(expected = NoFreeInputSocketException.class)
    public void inputSocket() throws NoFreeInputSocketException {
        rain.getFreeInputSocket();
    }
}