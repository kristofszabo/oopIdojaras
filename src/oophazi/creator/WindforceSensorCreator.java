package oophazi.creator;

import oophazi.Device;
import oophazi.Sensor;
import oophazi.WindforceSensor;

/**
 * Szélerő érzékelő létrehozó osztály
 */
public class WindforceSensorCreator extends SensorCreator {
    @Override
    public Sensor create(String name) {
        return new WindforceSensor(name);
    }
}
