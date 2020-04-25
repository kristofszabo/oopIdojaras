package oophazi;
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Data implements Serializable {

    /**
     *  Sensor device that measured this Data
     */
    private Sensor sensor;

    /**
     *  Value of the sensor
     */
    private double value;

    /**
     *  The time when the value was measured
     */
    private Date measuredTime;

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
     * sensor getter
     *
     * @return sensor
     */
    public Sensor getSensor() {
        return sensor;
    }


    /**Value getter
     *
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * measuredTime getter
     *
     * @return measuredTime
     */
    public Date getMeasuredTime() {
        return measuredTime;
    }

    /**measuredTime setter
     *
     * @param measuredTime when the data was measured
     */
    public void setMeasuredTime(Date measuredTime) {
        this.measuredTime = measuredTime;
    }
}