
package oophazi;
import java.io.*;
import java.util.*;
/**
 * 
 */
public class FileManager {

    private ModelManager modelManager;

    /**
     * Default constructor
     *
     * Beállítja a file managerhez tartozó alap ModelManager-t
     */
    public FileManager(ModelManager modelManager) {
        this.modelManager = modelManager;
    }

    /** Lementi az egész aktuális modelManagert a megadott fájlba
     *
     * @param fileName A létrehozandó fájl neve
     */
    public void save(String fileName) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(modelManager);
        oos.close();
    }

    /**Vissza ad egy ser fileból kinyert ModelManagert
     *
     *
     * @param filename A betöltendő file neve
     * @return A betöltött ModelManager példány
     */
    public static ModelManager load(String filename) throws IOException, ClassNotFoundException {

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(filename));
        ModelManager model =(ModelManager) oin.readObject();
        oin.close();
        return model;

    }

}