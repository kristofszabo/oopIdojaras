package oophazi.creator;

import oophazi.Device;
import oophazi.HumiditySensor;
import oophazi.Sensor;

/**
 * Egy páratartalom mérőt létrehozó osztály
 */
public class HumiditySensorCreator extends SensorCreator {
    @Override
    public Sensor create(String name) {
        return new HumiditySensor(name);
    }
}
