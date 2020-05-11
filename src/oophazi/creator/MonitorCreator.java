package oophazi.creator;

import oophazi.Device;
import oophazi.Monitor;

/**
 * Egy megjelenítőt létrehozó osztály
 */
public class MonitorCreator extends DeviceCreator{

    @Override
    public Monitor create(String name) {
        return new Monitor(name);
    }
}
