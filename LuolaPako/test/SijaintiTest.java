/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import EtsiReittiKuvasta.tietoRakenteet.Sijainti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Toni
 */
public class SijaintiTest {

    public SijaintiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void meneekoSyotteetOikeinPerille() {
        int x = 1;
        int y = 2;
        int etaisyys = 3;
        Sijainti koe = new Sijainti(x, y, etaisyys);
        assertTrue(x == koe.getX());
        assertTrue(y == koe.getY());
        assertTrue(etaisyys == koe.getEtaisyys());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
