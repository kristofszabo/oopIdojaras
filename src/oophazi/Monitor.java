package oophazi;
import oophazi.exceptions.MonitorNotConnectedException;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Egy megjelenítőt jelképező osztály
 */
public class Monitor extends Device {

    /**
     * Név és alap bemenet és kimenet számok beállítása
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
            Socket socket = getInputSockets()[0];
            Cable cable = socket.getCable();
            Device storage;
            if(cable!=null) {
                storage = cable.getSocketFrom().getOwner();

                for (Data data :
                        storage.getStoredDatas()) {
                    if (data.getMeasuredTime().isAfter(dateFrom) && data.getMeasuredTime().isBefore(dateTo)) {
                        datas.add(data);
                    }
                }
            }
            return datas;
        }else{
            throw new MonitorNotConnectedException();
        }

    }


    /**
     *
     * @return csatlakoztatva van e más eszközhöz
     */
    public boolean canShowData() {
        return getInputSockets()[0].getCable()!=null;
    }

    @Override
    public String toString() {
        return "(Monitor) " + getName();
    }
}