package oophazi;
import java.util.*;

/**
 * Egy adat tárolót szimbolizáló eszköz
 */
public class DataStorage extends Device {

    /**
     * Alap bemenet és kimeneti socketek beállítása
     */
    public DataStorage(String name) {
        super(name,1,100);
    }

    /**
     *
     * @return true
     */
    @Override
    public boolean canStoreData() {
        return true;
    }

    @Override
    public String toString() {
        return "(DataStorage) " + super.toString();
    }
}