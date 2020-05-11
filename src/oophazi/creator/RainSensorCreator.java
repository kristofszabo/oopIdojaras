package oophazi.creator;

import oophazi.Device;
import oophazi.RainSensor;
import oophazi.Sensor;

/**
 * Egy eső mérőt létrehozó osztály
 */
public class RainSensorCreator extends SensorCreator {
    @Override
    public Sensor create(String name) {
        return new RainSensor(name);
    }
}
