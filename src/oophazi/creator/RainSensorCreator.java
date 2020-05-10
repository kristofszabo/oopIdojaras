package oophazi.creator;

import oophazi.Device;
import oophazi.RainSensor;
import oophazi.Sensor;

public class RainSensorCreator extends SensorCreator {
    @Override
    public Sensor create(String name) {
        return new RainSensor(name);
    }
}
