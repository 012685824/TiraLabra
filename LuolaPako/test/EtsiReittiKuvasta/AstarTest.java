package EtsiReittiKuvasta;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Algoritmit.Astar;
import Algoritmit.Dijkstra;
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
public class AstarTest {

    int kuvaTaulu[][] = new int[450][450];

    public AstarTest() {
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
            Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            A.ratkaise();
            Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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


    }

    @Test
    public void selvitetaankoReittiOikeinJosReittiAlkaaVasemmaltaOikealle() {
        int xAlkuPiste = 2;
        int yAlkuPiste = 2;
        int xLoppuPiste = 200;
        int yLoppuPiste = 2;

        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        A.ratkaise();
        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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

        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        A.ratkaise();
        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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

        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        A.ratkaise();
        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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

        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        A.ratkaise();
        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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

        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        A.ratkaise();
        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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
    public void selvitetaankoReittiOikeinJosReittiAlkaaVasemmaltaYlhaaltaOikealleAlas() {
        int xAlkuPiste = 2;
        int yAlkuPiste = 2;
        int xLoppuPiste = 200;
        int yLoppuPiste = 200;

        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        A.ratkaise();
        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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
    public void selvitetaankoReittiOikeinJosReittiAlkaaOikeastaYläreunastaVasempaaAlaReunaan() {
        int xAlkuPiste = 200;
        int yAlkuPiste = 2;
        int xLoppuPiste = 2;
        int yLoppuPiste = 200;

        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        A.ratkaise();
        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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
    public void selvitetaankoReittiOikeinJosReittiAlkaaOikeastaAlaReunastaVasempaaYlaReunaan() {
        int xAlkuPiste = 200;
        int yAlkuPiste = 200;
        int xLoppuPiste = 2;
        int yLoppuPiste = 2;

        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        A.ratkaise();
        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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
    public void tulostaakoReitinOikein() {
        BufferedImage kuva = null;
        String oletusTulos = "494849474946494549444943494249414940493949384937493649354934493349324931493049294928492749264925492449234922492149204919491849174916491549144913491249114910499498497496495494493492491481471461451441431421411401391381371361351341331321311301291281271261251241231221211201191181171161151141131121111101918171615141312111";
        kuva = EtsiReitti.haeKuva("./src/Kuvat/testiKuva4.bmp");
        int[][] kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()];
        EtsiReitti.setKuvaTaulu(kuvaTaulu);
        EtsiReitti.haeVaritKuvatauluun(kuva);
        kuvaTaulu = EtsiReitti.testiGetKuvaTaulu();

        Astar A = new Astar(kuvaTaulu, 1, 1, 49, 49);
        A.ratkaise();
        int[] tulostuksenTulosApu = A.testiTulosReitti();
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
            Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

            A.ratkaise();
            Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
            int tulos = Math.abs(xAlkuPiste - xLoppuPiste) + Math.abs(yAlkuPiste - yLoppuPiste);

            assertTrue(tulos == sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys());
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
            Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
            A.initialiseSingleSourceTest();
            Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();
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
        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        A.initialiseSingleSourceTest();
        A.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }

    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaYlosOikealle() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 9;
        int yLoppuPiste = 9;
        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        A.initialiseSingleSourceTest();
        A.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }

    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaOikealle() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 11;
        int yLoppuPiste = 9;
        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        A.initialiseSingleSourceTest();
        A.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }

    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaAlasOikealle() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 11;
        int yLoppuPiste = 11;
        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        A.initialiseSingleSourceTest();
        A.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }

    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaAlas() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 10;
        int yLoppuPiste = 11;
        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        A.initialiseSingleSourceTest();
        A.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }

    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaAlasVasemmalle() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 9;
        int yLoppuPiste = 11;
        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        A.initialiseSingleSourceTest();
        A.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }

    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaVasemmalle() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 9;
        int yLoppuPiste = 10;
        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        A.initialiseSingleSourceTest();
        A.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }

    @Test
    public void toimiikoRelaxKunSilleAnnetaaSyotteenaYlosVasemmalle() {

        int xAlkuPiste = 10;
        int yAlkuPiste = 10;
        int xLoppuPiste = 9;
        int yLoppuPiste = 9;
        Astar A = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
        A.initialiseSingleSourceTest();
        A.relaxTest(xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);

        Sijainti[][] sijaintiTaulu = A.getSijaintiTaulu();

        assertTrue(sijaintiTaulu[xLoppuPiste][yLoppuPiste].getEtaisyys() == 1);
    }

    @Test
    public void toimiikoAstarKekoOikeinJosOnYhdenKokoinenSyote() {
        int[][] koeTaulu = new int[1][1];
        koeTaulu[0][0] = 1;
        Astar A = new Astar(koeTaulu, 0, 0, 0, 0);
        A.aStarKekoTest();
        assertTrue(A.getSijaintiTaulu()[0][0].getEtaisyys() == 0.0);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
