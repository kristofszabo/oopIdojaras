package oophazi.creator;

import oophazi.Device;
import oophazi.Sensor;
import oophazi.TemperatureSensor;

public class TemperatureSensorCreator extends SensorCreator {
    @Override
    public Sensor create(String name) {
        return new TemperatureSensor(name);
    }
}
