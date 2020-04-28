
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
     */
    public FileManager(ModelManager modelManager) {
        this.modelManager = modelManager;
    }

    /**
     * @param fileName A létrehozandó fájl neve
     */
    public void save(String fileName) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(modelManager);
    }

    /**
     * @param filename A betöltendő file neve
     * @return A betöltött ModelManager példány
     */
    public ModelManager load(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(filename));
        return (ModelManager) oin.readObject();
    }

}