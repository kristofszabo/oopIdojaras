package oophazi.tests;

import oophazi.Menu;
import oophazi.ModelManager;
import oophazi.exceptions.MenuNotFoundException;
import oophazi.exceptions.NotEnoughParameterException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuTest {

    Menu menu;
    @Before
    public void setUp() throws Exception {
        menu=new Menu(new ModelManager());
    }

    @Test(expected = MenuNotFoundException.class)
    public void play() throws MenuNotFoundException, NotEnoughParameterException {
        String[] failCmd= {"kovász"};
        menu.play(failCmd);
    }
}