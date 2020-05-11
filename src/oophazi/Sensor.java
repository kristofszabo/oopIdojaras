
package oophazi;
import oophazi.Data;

import java.time.LocalDateTime;
import java.util.*;
/**
 * 
 */
public abstract class Sensor extends Device {


    /**
     * Az érzékelő mértékegysége
     */
    private String measure;

    /**
     * @param name Eszköz név
     */
    public Sensor(String name) {
        super(name,0,1);
        getStoredDatas().add(new Data());
    }

    public void setDataTime(LocalDateTime localDateTime){
        if(getStoredDatas().size()>0){
            getStoredDatas().get(0).setMeasuredTime(localDateTime);
        }
    }
    @Override
    public void receive(Data data) {
        getStoredDatas().set(0,data);

    }
}