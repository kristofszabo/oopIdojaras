
package oophazi;
import java.util.*;
/**
 * Páratartalom mérő Sensor
 */
public class HumiditySensor extends Sensor {


    /**
     * @param name Eszköz név
     */
    public HumiditySensor(String name) {
        super(name,"g/m^3");
    }

    @Override
    public String toString() {
        return "(HumiditySensor) "+ super.toString();
    }
}