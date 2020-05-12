package oophazi;
import java.util.*;

/**
 * Egy adat tárolót szimbolizáló eszköz
 */
public class DataStorage extends Device {

    /**
     * Default constructor
     */
    public DataStorage(String name) {
        super(name,1,8);
    }

    @Override
    public boolean canStoreData() {
        return true;
    }

    @Override
    public String toString() {
        return "(DataStorage) " + getName();
    }
}