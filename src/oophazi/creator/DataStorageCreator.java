package oophazi.creator;

import oophazi.DataStorage;
import oophazi.Device;

public class DataStorageCreator extends DeviceCreator {
    @Override
    public Device create(String name) {
        return new DataStorage(name);
    }
}
