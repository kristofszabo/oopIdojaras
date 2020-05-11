package oophazi.creator;

import oophazi.Device;

/**
 * Egy eszközt létrehozó minta osztály
 */
public abstract class DeviceCreator {
    public abstract Device create(String name);
}
