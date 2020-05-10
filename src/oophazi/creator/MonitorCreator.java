package oophazi.creator;

import oophazi.Device;
import oophazi.Monitor;

public class MonitorCreator extends DeviceCreator{

    @Override
    public Monitor create(String name) {
        return new Monitor(name);
    }
}
