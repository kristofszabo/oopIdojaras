package oophazi.exceptions;

/**
 * Megjelenítő nincs csatlakoztatva kivétel
 */
public class MonitorNotConnectedException extends Exception {
    public MonitorNotConnectedException(){
        super("A monitor nincs csatlakoztatva semmilyen eszközhöz");
    }
}
