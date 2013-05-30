package EtsiReittiKuvasta;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Algoritmit.Dijkstra;
import Algoritmit.Dijkstra8;
import EtsiReittiKuvasta.main.EtsiReitti;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;
import java.awt.image.BufferedImage;
import java.util.Random;
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
public class Dijkstra8Test {
    int kuvaTaulu[][] = new int[450][450];
    public Dijkstra8Test() {
    }
 
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        for (int i = 0; i < kuvaTaulu.length; i++) {
            for (int j = 0; j < kuvaTaulu[0].length; j++) {
                kuvaTaulu[i][j] = 1;
            }
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void selvitetaankoReittiOikeinSatunnaisillaSyotteilla() {
        for (int i = 0; i < 30; i++) {


            Random random = new Random();
            int xAlkuPiste = random.nextInt(450);
            int yAlkuPiste = random.nextInt(450);
            int xLoppuPiste = random.nextInt(450);
            int yLoppuPiste = random.nextInt(450);
            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
            Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
            int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);
            int maara = 0;
            int xApu = 0;
            while (xLoppuPiste != xAlkuPiste || yLoppuPiste != yAlkuPiste) {
                xApu = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getX();
                yLoppuPiste = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getY();
                xLoppuPiste = xApu;
                maara++;
            }

            assertTrue(tulos <= maara || tulos >= (maara/2)*1.414);
        }
    }


    @Test
    public void selvitetaankoReittiOikeinJosReittiAlkaaVasemmaltaOikealle() {
        int xAlkuPiste = 2;
        int yAlkuPiste = 2;
        int xLoppuPiste = 200;
        int yLoppuPiste = 2;

            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
        int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);
        int maara = 0;
        int xApu = 0;
        while (xLoppuPiste != xAlkuPiste || yLoppuPiste != yAlkuPiste) {
            xApu = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getX();
            yLoppuPiste = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getY();
            xLoppuPiste = xApu;
            maara++;
        }
        assertTrue(tulos == maara);
    }

    @Test
    public void selvitetaankoReittiOikeinJosReittiAlkaaOikealtaVasemmalle() {
        int xAlkuPiste = 200;
        int yAlkuPiste = 2;
        int xLoppuPiste = 2;
        int yLoppuPiste = 2;

            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
        int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);
        int maara = 0;
        int xApu = 0;
        while (xLoppuPiste != xAlkuPiste || yLoppuPiste != yAlkuPiste) {
            xApu = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getX();
            yLoppuPiste = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getY();
            xLoppuPiste = xApu;
            maara++;
        }
        assertTrue(tulos == maara);
    }
        @Test
    public void selvitetaankoReittiOikeinJosReittiAlkaaYlhaaltaAlas() {
        int xAlkuPiste = 2;
        int yAlkuPiste = 2;
        int xLoppuPiste = 2;
        int yLoppuPiste = 200;

            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
        int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);
        int maara = 0;
        int xApu = 0;
        while (xLoppuPiste != xAlkuPiste || yLoppuPiste != yAlkuPiste) {
            xApu = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getX();
            yLoppuPiste = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getY();
            xLoppuPiste = xApu;
            maara++;
        }
        assertTrue(tulos == maara);
    }
        @Test
    public void selvitetaankoReittiOikeinJosReittiAlkaaAlhaaltaYlos() {
        int xAlkuPiste = 2;
        int yAlkuPiste = 200;
        int xLoppuPiste = 2;
        int yLoppuPiste = 2;

            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
        int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);
        int maara = 0;
        int xApu = 0;
        while (xLoppuPiste != xAlkuPiste || yLoppuPiste != yAlkuPiste) {
            xApu = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getX();
            yLoppuPiste = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getY();
            xLoppuPiste = xApu;
            maara++;
        }
        assertTrue(tulos == maara);
    }
        
    @Test
    public void selvitetaankoReittiOikeinJosReittiAlkaaVasemmastaAlaReunastaOikeaanYläReunaan() {
        int xAlkuPiste = 2;
        int yAlkuPiste = 200;
        int xLoppuPiste = 200;
        int yLoppuPiste = 2;

            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
        int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);
        int maara = 0;
        int xApu = 0;
        while (xLoppuPiste != xAlkuPiste || yLoppuPiste != yAlkuPiste) {
            xApu = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getX();
            yLoppuPiste = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getY();
            xLoppuPiste = xApu;
            maara++;
        }

        assertTrue(tulos == maara*2);
    }        
    @Test
    public void selvitetaankoReittiOikeinJosReittiAlkaaVasemmaltaYlhaaltaOikealleAlas() {
        int xAlkuPiste = 2;
        int yAlkuPiste = 2;
        int xLoppuPiste = 200;
        int yLoppuPiste = 200;

            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
        int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);
        int maara = 0;
        int xApu = 0;
        while (xLoppuPiste != xAlkuPiste || yLoppuPiste != yAlkuPiste) {
            xApu = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getX();
            yLoppuPiste = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getY();
            xLoppuPiste = xApu;
            maara++;
        }
        assertTrue(tulos == maara*2);
    }
        @Test
    public void selvitetaankoReittiOikeinJosReittiAlkaaOikeastaYläreunastaVasempaaAlaReunaan() {
        int xAlkuPiste = 200;
        int yAlkuPiste = 2;
        int xLoppuPiste = 2;
        int yLoppuPiste = 200;

            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
        int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);
        int maara = 0;
        int xApu = 0;
        while (xLoppuPiste != xAlkuPiste || yLoppuPiste != yAlkuPiste) {
            xApu = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getX();
            yLoppuPiste = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getY();
            xLoppuPiste = xApu;
            maara++;
        }
        assertTrue(tulos == maara*2);
    }
            @Test
    public void selvitetaankoReittiOikeinJosReittiAlkaaOikeastaAlaReunastaVasempaaYlaReunaan() {
        int xAlkuPiste = 200;
        int yAlkuPiste = 200;
        int xLoppuPiste = 2;
        int yLoppuPiste = 2;

            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
        int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);
        int maara = 0;
        int xApu = 0;
        while (xLoppuPiste != xAlkuPiste || yLoppuPiste != yAlkuPiste) {
            xApu = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getX();
            yLoppuPiste = sijaintiTaulu[xLoppuPiste][yLoppuPiste].getY();
            xLoppuPiste = xApu;
            maara++;
        }
        assertTrue(tulos == maara*2);
    }    
    
    @Test
    public void tulostaakoReitinOikein() {
        BufferedImage kuva = null;
        String oletusTulos = "49484947494649454944494349424941494049394938493749364935493449334932493149304929492849274926492549244923492249214920491949184917491649154914491349124911491049949849749649549449349248147146145144143142141140139138137136135134133132131130129128127126125124123122121120119118117116115114113112111110191817161514131211100";
        kuva = EtsiReitti.haeKuva("./src/Kuvat/testiKuva4.bmp");
        int[][] kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()];
        EtsiReitti.setKuvaTaulu(kuvaTaulu);
        EtsiReitti.haeVaritKuvatauluun(kuva);
        kuvaTaulu = EtsiReitti.testiGetKuvaTaulu();

        Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, 1, 1, 49, 49);
        D8.ratkaise();
        int[] tulostuksenTulosApu = D8.testiTulosReitti();
        String tulostuksenTulos = "";
        for (int i = 0; i < tulostuksenTulosApu.length; i++) {
            tulostuksenTulos = tulostuksenTulos + tulostuksenTulosApu[i] + "";
        }

        assertEquals(oletusTulos, tulostuksenTulos);

    }

    @Test
    public void onkoloppuPisteOikea() {
        for (int i = 0; i < 30; i++) {


            Random random = new Random();
            int xAlkuPiste = random.nextInt(450);
            int yAlkuPiste = random.nextInt(450);
            int xLoppuPiste = random.nextInt(450);
            int yLoppuPiste = random.nextInt(450);
            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            D8.ratkaise();
            Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
            int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);

            assertTrue(tulos <= sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() || tulos >= (sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys()/2)*1.414);
        }
    }

    @Test
    public void toimiikoInitialiseSingleSourceKunSilleAnnetaanSatunnaisiaSyotteita() {
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            int xAlkuPiste = 0;
            int yAlkuPiste = 0;
            int xLoppuPiste = random.nextInt(450);
            int yLoppuPiste = random.nextInt(450);
            Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
            D8.initialiseSingleSourceTest();
            Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
            for (int x = 1; x < sijaintiTaulu[0].length; x++) {
                for (int y = 1; y < sijaintiTaulu.length; y++) {
                    assertTrue(sijaintiTaulu[x][y].getEtaisyys() == Double.MAX_VALUE / 2);
                    assertTrue(sijaintiTaulu[x][y].getX() == 0);
                    assertTrue(sijaintiTaulu[x][y].getY() == 0);
                }
            }
        }
    }

    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaYlos() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 10;
        int yLoppuPiste = 9;
        Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        D8.initialiseSingleSourceTest();
        D8.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }

    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaYlosOikealle() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 9;
        int yLoppuPiste = 9;
        Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        D8.initialiseSingleSourceTest();
        D8.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();
        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1.414);
    }
        @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaOikealle() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 11;
        int yLoppuPiste = 10;
        Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        D8.initialiseSingleSourceTest();
        D8.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }
    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaAlasOikealle() {

            int xAlkuPiste = 10;
            int yAlkuPiste = 10;
            int xLoppuPiste = 11;
            int yLoppuPiste = 11;
         Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        D8.initialiseSingleSourceTest();
        D8.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();

            assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1.414);
      }
        @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaAlas() {

            int xAlkuPiste = 10;
            int yAlkuPiste = 10;
            int xLoppuPiste = 10;
            int yLoppuPiste = 11;
        Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        D8.initialiseSingleSourceTest();
        D8.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();


            assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
      }
            @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaAlasVasemmalle() {

            int xAlkuPiste = 10;
            int yAlkuPiste = 10;
            int xLoppuPiste = 9;
            int yLoppuPiste = 11;
        Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        D8.initialiseSingleSourceTest();
        D8.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();


            assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1.414);
      }
                @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaVasemmalle() {

            int xAlkuPiste = 10;
            int yAlkuPiste = 10;
            int xLoppuPiste = 9;
            int yLoppuPiste = 10;
        Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        D8.initialiseSingleSourceTest();
        D8.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();


            assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
      }
                    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaYlosVasemmalle() {

            int xAlkuPiste = 10;
            int yAlkuPiste = 10;
            int xLoppuPiste = 9;
            int yLoppuPiste = 9;
        Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        D8.initialiseSingleSourceTest();
        D8.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = D8.getSijaintiTaulu();


            assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1.414);
      }
    @Test
    public void toimiikoDijkstra8KekoOikeinJosOnYhdenKokoinenSyote(){
        int[][] koeTaulu = new int[1][1];
        koeTaulu[0][0] = 1;
        Dijkstra8 D8 = new Dijkstra8(koeTaulu, 0, 0, 0, 0);
        D8.dijkstraKeko8Test();
        assertTrue(D8.getSijaintiTaulu()[0][0].getEtaisyys() == 0.0);
    }        
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}