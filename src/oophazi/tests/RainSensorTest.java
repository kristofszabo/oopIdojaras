package oophazi.tests;

import oophazi.RainSensor;
import oophazi.Sensor;
import oophazi.exceptions.NoFreeInputSocketException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RainSensorTest {

    Sensor rain;
    @Before
    public void setUp() throws Exception {
        rain = new RainSensor("rain");
    }

    @Test(expected = NoFreeInputSocketException.class)
    public void inputSocket() throws NoFreeInputSocketException {
        rain.getFreeInputSocket();
    }
}