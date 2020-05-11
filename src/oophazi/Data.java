package oophazi;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Egy adatot szimbolizáló osztály
 */
public class Data implements Serializable {

    /**
     *  Az adat tulaja aki mérte ezt az adatot
     */
    private Sensor sensor;

    /**
     *  A mért érték
     */
    private double value;

    /**
     *  Az idő, amikor mérve lett az adat
     */
    private LocalDateTime measuredTime;

    /**
     * Default constructor
     */
    public Data() {
    }

    public Data(Sensor sensor, double value){
        this.sensor = sensor;
        this.value = value;
    }


    /**
     * Getter a tulajdonos sensorhoz
     *
     * @return sensor
     */
    public Sensor getSensor() {
        return sensor;
    }


    /**Getter a mért értékhez
     *
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Getter a mérés időpontjához
     *
     * @return measuredTime
     */
    public LocalDateTime getMeasuredTime() {
        return measuredTime;
    }

    /**Setter a mérés időpontjához
     *
     * @param measuredTime when the data was measured
     */
    public void setMeasuredTime(LocalDateTime measuredTime) {
        this.measuredTime = measuredTime;
    }
}