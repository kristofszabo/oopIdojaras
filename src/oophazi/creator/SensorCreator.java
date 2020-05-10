package oophazi.creator;

import oophazi.Device;
import oophazi.Sensor;

public abstract class SensorCreator extends DeviceCreator {

    public abstract Sensor create(String name);
}
