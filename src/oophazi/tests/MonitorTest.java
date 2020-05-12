package oophazi.tests;

import oophazi.Data;
import oophazi.Device;
import oophazi.Monitor;
import oophazi.exceptions.MonitorNotConnectedException;
import oophazi.exceptions.NoFreeInputSocketException;
import oophazi.exceptions.NoFreeOutputSocketException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MonitorTest {

    Monitor monitor;

    /**
     * Monitor létrehozása
     */
    @Before
    public void setUp(){
        monitor = new Monitor("megjelenito");
    }

    /**
     * Próbálkozás kimeneti socket lekéréssel monitoron, amin nincs
     * @throws NoFreeOutputSocketException
     */
    @Test(expected = NoFreeOutputSocketException.class)
    public void outputTest() throws NoFreeOutputSocketException {
        monitor.getFreeOutputSocket();

    }

    /**
     * Próbálkozás bemeneti aljzat lekéréssel amiből csak 1 van
     * @throws NoFreeInputSocketException
     */
    @Test
    public void freeInputTest() throws NoFreeInputSocketException {
        assertEquals(monitor.getFreeInputSocket(),monitor.getInputSockets()[0]);

    }

    /**
     * Nem bekábelezett monitor adatainak lekérése a várt eredmény hiba, mert nincs semmi amitől lekérhetné az adatot
     * @throws MonitorNotConnectedException
     */
    @Test(expected = MonitorNotConnectedException.class)
    public void getStoredDataBetweenDates() throws MonitorNotConnectedException {
        var list = monitor.getStoredDataBetweenDates(LocalDateTime.of(2020,02,15,12,11),LocalDateTime.of(2014,12,12,12,12));

    }
}