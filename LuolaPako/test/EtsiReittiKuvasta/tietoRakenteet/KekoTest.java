package EtsiReittiKuvasta.tietoRakenteet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Comparator;
import java.util.PriorityQueue;
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
    public void keossaKahdeksanAlkiotaKunKahdeksanUuttaAlkiotaLisattyTyhjaanKekoon() {
        for (int i = 0; i < 8; i++) {
            testikeko.lisaaKekoon(i, i, i);
        }
        assertEquals(testikeko.getKeonKoko(), 8);
    }

    @Test
    public void tyhjaanKekoonLisataanPaljonAlkioita() {
        for (int i = 0; i < 50000; i++) {
            testikeko.lisaaKekoon(i, i, i);
        }
        assertEquals(testikeko.getKeonKoko(), 50000);
        assertFalse(testikeko.getKeonKoko() == 0);
    }

    @Test
    public void poistetaanKekostaJossaPaljonAlkioita() {
        for (int i = 0; i < 50000; i++) {
            testikeko.lisaaKekoon(i, i, i);
        }

        for (int i = 0; i < 50000; i++) {
            testikeko.poistaKeosta();
        }
        assertTrue(testikeko.getKeonKoko() == 0);
    }

    @Test
    public void onkoKekoTaulukonKokoOikeinKunLisataanPaljonAlkioita() {
        for (int i = 0; i < 50000; i++) {
            testikeko.lisaaKekoon(i, i, i);
        }
        assertTrue(testikeko.KekoTaulukonKoko() == 51200);
    }

    @Test
    public void onkoKekoTaulukonKokoOikeinKunLisataanPaljonAlkioitaJaPoistetaanPaljonAlkioita() {
        for (int i = 0; i < 50000; i++) {
            testikeko.lisaaKekoon(i, i, i);
        }
        for (int i = 0; i < 50000; i++) {
            testikeko.poistaKeosta();
        }
        assertTrue(testikeko.KekoTaulukonKoko() == 100);
    }

    @Test
    public void poistetaanLiikaaKekostaJossaPaljonAlkioita() {
        for (int i = 0; i < 50000; i++) {
            testikeko.lisaaKekoon(i, i, i);
        }

        for (int i = 0; i < 60000; i++) {
            testikeko.poistaKeosta();
        }
        assertTrue(testikeko.getKeonKoko() == 0);
    }

    @Test
    public void testataanPalaittaakoEmptyOikeanArvonJosTyhja() {

        assertTrue(testikeko.emptyIs() == true);

    }

    @Test
    public void testataanPalaittaakoEmptyOikeanArvonJosEiTyhja() {
        testikeko.lisaaKekoon(1, 1, 1);
        assertTrue(testikeko.emptyIs() == false);
    }

    @Test
    public void vartaillaaKeonNopeuttaPriorityQueue() {
        int testattavienAlkioidenMaara = 10000;

        for (int kertoja = 0; kertoja < 1; kertoja++) {
            testattavienAlkioidenMaara *= 2;
            PriorityQueue<Sijainti> PQ = new PriorityQueue<Sijainti>(1, new Comparator<Sijainti>() {
                @Override
                public int compare(Sijainti o1, Sijainti o2) {
                    if (o1.getEtaisyys() < o2.getEtaisyys()) {
                        return -1;
                    }
                    if (o1.getEtaisyys() > o2.getEtaisyys()) {
                        return 1;
                    }
                    return 0;
                    //throw new UnsupportedOperationException("Not supported yet.");
                }
            });


            double pqStartTime = System.currentTimeMillis();
            for (int i = 0; i < testattavienAlkioidenMaara; i++) {
                PQ.add(new Sijainti(1, 1, 1));
            }
            double pqEndTime = System.currentTimeMillis();
            double pqTotalTime = pqEndTime - pqStartTime;

            double kekoStartTime = System.currentTimeMillis();
            for (int i = 0; i < testattavienAlkioidenMaara; i++) {
                testikeko.lisaaKekoon(i, i, i);
            }

            double kekoEndTime = System.currentTimeMillis();
            double kekoTotalTime = kekoEndTime - kekoStartTime;
            System.out.println("PqTotal=" + pqTotalTime + " KekoTotal=" + kekoTotalTime + " Tulos= " + pqTotalTime / kekoTotalTime);

            assertTrue((pqTotalTime / kekoTotalTime) > 0.05 && (pqTotalTime / kekoTotalTime < 100));

        }
    }

    @Test
    public void tulostuukoKekoOikeinJosVaanLisataan() {
        testikeko.lisaaKekoon(0, 0, 9);
        testikeko.lisaaKekoon(0, 0, 2);
        testikeko.lisaaKekoon(0, 0, 7);
        testikeko.lisaaKekoon(0, 0, 1);
        testikeko.lisaaKekoon(0, 0, 8);
        testikeko.lisaaKekoon(0, 0, 3);
        testikeko.lisaaKekoon(0, 0, 5);
        assertEquals(" 1.0 2.0 3.0 9.0 8.0 7.0 5.0", testikeko.tulosta());
        System.out.println(testikeko.poistaKeosta().getEtaisyys());
        testikeko.tulosta();
        System.out.println(testikeko.poistaKeosta().getEtaisyys());
        testikeko.tulosta();


    }

    @Test
    public void tulostuukoKekoOikeinJosLisataanJaPoistetaan() {
        testikeko.lisaaKekoon(0, 0, 10);
        testikeko.lisaaKekoon(0, 0, 9);
        testikeko.lisaaKekoon(0, 0, 2);
        testikeko.lisaaKekoon(0, 0, 7);

        testikeko.poistaKeosta();
        testikeko.poistaKeosta();
        assertEquals(" 9.0 10.0", testikeko.tulosta());
        testikeko.poistaKeosta();
        testikeko.lisaaKekoon(0, 0, 4);
        testikeko.tulosta();
    }

    @Test
    public void tulostuukoKekoOikeinKunTyhja() {

        assertEquals("Keko tyhjä", testikeko.tulosta());


    }

    @Test
    public void onkoPieninLukuHuipullaKunLisätäänKekoonSatunnainenMaaraAlkioitaKunArvoiOikein() {
        Random testi = new Random();
        double sijoitettajaLuku = 0;
        double pieninLuku = Double.MAX_VALUE / 2;

        for (int i = 0; i < testi.nextInt(1000000) + 1; i++) {
            sijoitettajaLuku = new Random(1000000).nextDouble() + 1;
            testikeko.lisaaKekoon(i, i, sijoitettajaLuku);
            if (pieninLuku > sijoitettajaLuku) {
                pieninLuku = sijoitettajaLuku;
            }
        }

        assertTrue(pieninLuku == testikeko.palautaKekoHuippuArvo());

    }

    @Test
    public void onkoPieninLukuHuipullaKunLisätäänKekoonSatunnainenMaaraAlkioitaKunArvoiVaarin() {
        Random testi = new Random();
        double sijoitettajaLuku = 0;
        double pieninLuku = Double.MAX_VALUE / 2;

        for (int i = 0; i < testi.nextInt(1000000) + 1; i++) {
            sijoitettajaLuku = new Random(1000000).nextDouble() + 1;
            testikeko.lisaaKekoon(i, i, sijoitettajaLuku);
            if (pieninLuku > sijoitettajaLuku) {
                pieninLuku = sijoitettajaLuku;
            }
        }

        assertFalse(pieninLuku == testikeko.palautaKekoHuippuArvo() + 1);

    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
