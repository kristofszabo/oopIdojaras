package oophazi.creator;

import oophazi.Device;
import oophazi.Sensor;

/**
 * Sensor létrehozó osztály
 */
public abstract class SensorCreator extends DeviceCreator {

    public abstract Sensor create(String name);
}
