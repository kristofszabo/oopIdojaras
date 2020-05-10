package oophazi.creator;

import oophazi.DataConcentrator;
import oophazi.Device;

public class DataConcentratorCreator extends DeviceCreator {
    @Override
    public Device create(String name) {
        return new DataConcentrator(name);
    }
}
