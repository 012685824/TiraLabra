/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import EtsiReittiKuvasta.main.EtsiReitti;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;
import java.awt.image.BufferedImage;
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
public class EtsiReittiTest {

    public EtsiReittiTest() {
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
    public void tulostaakoReitinPituudenOikeinKunKaytetaanDijkstraa() {
        EtsiReitti.ratkaise("./src/Kuvat/testiKuva0.bmp", 0, 0, 49, 49, 0,true);
        assertTrue(98 == EtsiReitti.reitinPituus());
    }

    @Test
    public void tulostaakoReitinPituudenOikeinKunKaytetaanDijkstraa8() {
        EtsiReitti.ratkaise("./src/Kuvat/testiKuva0.bmp", 0, 0, 49, 49, 2,true);
        assertTrue((49 * 1.414) + 0.00000000000004 == EtsiReitti.reitinPituus());
    }

    @Test
    public void tulostaakoReitinPituudenOikeinKunKaytetaanBellmanFord() {
        EtsiReitti.ratkaise("./src/Kuvat/testiKuva0.bmp", 0, 0, 49, 49, 1,true);
        assertTrue(98 == EtsiReitti.reitinPituus());
    }

    @Test
    public void haetaankoOikeatVaritKuvaTauluun() {
        EtsiReitti.ratkaise("./src/Kuvat/testiKuva0.bmp", 0, 0, 49, 49, 1,true);
        BufferedImage kuva = null;
        kuva = EtsiReitti.haeKuva("./src/Kuvat/testiKuva0.bmp");
        EtsiReitti.haeVaritKuvatauluun(kuva);
        int[][] kuvaTaulu = new int[kuva.getHeight()][kuva.getWidth()];
        kuvaTaulu = EtsiReitti.testiGetKuvaTaulu();
        for (int y = 0; y < kuvaTaulu[0].length; y++) {
            for (int x = 0; x < kuvaTaulu.length; x++) {
                assertTrue(1 == kuvaTaulu[x][y]);
            }
        }
    }

    @Test
    public void haetaankoOikeatVaritKuvaTauluun2() {
        EtsiReitti.ratkaise("./src/Kuvat/testiKuva1.bmp", 0, 0, 49, 49, 1,true);
        BufferedImage kuva = null;
        kuva = EtsiReitti.haeKuva("./src/Kuvat/testiKuva1.bmp");
        EtsiReitti.haeVaritKuvatauluun(kuva);
        int[][] kuvaTaulu = new int[kuva.getHeight()][kuva.getWidth()];
        kuvaTaulu = EtsiReitti.testiGetKuvaTaulu();
        for (int y = 0; y < kuvaTaulu[0].length; y++) {
            for (int x = 0; x < kuvaTaulu.length; x++) {
                if (1 != kuvaTaulu[x][y]) {
                    assertTrue(true);
                    return;
                }
            }
        }
        assertTrue(false);
    }

    @Test
    public void piirretäänköReittiOikeinKunKaytetaanDijkstraa8() {
        EtsiReitti.ratkaise("./src/Kuvat/testiKuva0.bmp", 0, 0, 49, 49, 2,true);
        BufferedImage kuva = null;
        BufferedImage ratkaisuKuva = null;
        kuva = EtsiReitti.haeKuva("./src/Kuvat/testiKuva2.bmp");
        ratkaisuKuva = EtsiReitti.haeKuva("./src/Kuvat/ratkaisu.bmp");

        for (int y = 0; y < kuva.getHeight(); y++) {
            for (int x = 0; x < kuva.getWidth(); x++) {
                if ((1 != kuva.getRGB(x, y)) && (1 != ratkaisuKuva.getRGB(x, y))) {
                } else {
                    assertTrue(false);
                }
            }
        }
    }

    @Test
    public void piirretäänköReittiOikeinKunKaytetaanDijkstraa8_2() {
        EtsiReitti.ratkaise("./src/Kuvat/testiKuva0.bmp", 0, 0, 49, 49, 2,true);
        BufferedImage kuva = null;
        BufferedImage ratkaisuKuva = null;
        kuva = EtsiReitti.haeKuva("./src/Kuvat/testiKuva3.bmp");
        ratkaisuKuva = EtsiReitti.haeKuva("./src/Kuvat/ratkaisu.bmp");

        for (int y = 0; y < kuva.getHeight(); y++) {
            for (int x = 0; x < kuva.getWidth(); x++) {
                if ((-1 != kuva.getRGB(x, y)) && (-1 != ratkaisuKuva.getRGB(x, y))) {
                } else {
                    assertTrue(true);
                }
            }
        }
    }
    @Test
    public void josTapahtuuVirheKunKuvaTiedostoaAvataan(){
        EtsiReitti.haeKuva("./src/Kuvat/t.bmp");
        assertEquals("javax.imageio.IIOException: Can't read input file!", EtsiReitti.testiVirhe());
    }
    /*
        @Test
    public void josTapahtuuVirheKunKuvaTiedostoaTalletetaan(){
        BufferedImage kuva = null;
        EtsiReitti.talletaKuva(kuva);
        assertTrue("javax.imageio.IIOException: Can't read input file!" == EtsiReitti.testiVirhe());
    }*/
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
