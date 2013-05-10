/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import EtsiReittiKuvasta.Keko;
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
public class KekoTest {
    Keko testikeko;
    
    public KekoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testikeko = new Keko();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void keonKokoOikeinKunLuodaanUusiKeko() {
        
        assertTrue(testikeko.getKeonKoko() == 0);
    }
    
    @Test
    public void keossaKahdeksanAlkiotaKunKahdeksanUuttaAlkiotaLisattyTyhjaanKekoon(){
        for (int i = 0; i < 8; i++) {
            testikeko.lisaa(i, i, i);
        }
        assertEquals(testikeko.getKeonKoko(),8);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
