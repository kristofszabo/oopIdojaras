package oophazi.creator;

import oophazi.DataStorage;
import oophazi.Device;

/**
 * Adat tárolót létrehozó osztály
 */
public class DataStorageCreator extends DeviceCreator {
    @Override
    public Device create(String name) {
        return new DataStorage(name);
    }
}
