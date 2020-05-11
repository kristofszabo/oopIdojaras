package oophazi;
import oophazi.exceptions.MonitorNotConnectedException;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Egy megjelenítőt jelképező osztály
 */
public class Monitor extends Device {

    /**
     * Default constructor
     */
    public Monitor(String name) {
        super(name,1,0);


    }


    /**
     * Vissza adja a monitorhoz tartozó adatokat amelyek a 2 időpont között fordultak elő
     *
     * @param dateFrom Mettől
     * @param dateTo Meddig
     * @return Adatok listája
     * @throws MonitorNotConnectedException Nincs semmilyen eszközhöz csatlakoztatva a monitor
     */
    public ArrayList<Data> getStoredDataBetweenDates(LocalDateTime dateFrom, LocalDateTime dateTo) throws MonitorNotConnectedException {
        if(canShowData()){
            ArrayList<Data> datas=new ArrayList<>();
            for (Data data:
                    getStoredDatas()) {
                if(data.getMeasuredTime().isAfter(dateFrom)&& data.getMeasuredTime().isBefore(dateTo)){
                    datas.add(data);
                }
            }
            return datas;
        }else{
            throw new MonitorNotConnectedException();
        }

    }


    public boolean canShowData() {
        return getInputSockets()[0].getCable()!=null;
    }
}