/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta;

import Algoritmit.Dijkstra;
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
public class SuoritusKykyMittausTest {

    public SuoritusKykyMittausTest() {
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
    public void aika() {

        double startTime = 0;
        double endTime = 0;
        double totalTime = 0;
        double kertoja = 0;
        int koko = 10;
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 6; b++) {
                int[][] koeTaulu = new int[koko][koko];

                for (int i = 0; i < koko; i++) {
                    for (int j = 0; j < koko; j++) {
                        koeTaulu[i][j] = 1;
                    }
                }
                System.gc();
                     
                Dijkstra D = new Dijkstra(koeTaulu, 0, 0, koko - 1, koko - 1);
                startTime = System.currentTimeMillis();
                D.ratkaise();
                endTime = System.currentTimeMillis();
                totalTime += endTime - startTime;
            }
            kertoja = koko*koko / (totalTime / 5);
            System.out.println("Taulukon koko=" + koko + " Aika=" + totalTime / 5 + " kertoja=" + kertoja);
            koko = koko * 2;
        }
    }
 /*       @Test
    public void aika1() {

        double startTime = 0;
        double endTime = 0;
        double totalTime = 0;
        double kertoja = 0;
        int koko = 200;
        for (int a = 0; a < 1; a++) {
            for (int b = 0; b < 5; b++) {
                int[][] koeTaulu = new int[koko][koko];

                for (int i = 0; i < koko; i++) {
                    for (int j = 0; j < koko; j++) {
                        koeTaulu[i][j] = 1;
                    }
                }
                System.gc();
                     
                Dijkstra D = new Dijkstra(koeTaulu, 0, 0, koko - 1, koko - 1);
                startTime = System.currentTimeMillis();
                D.ratkaise();
                endTime = System.currentTimeMillis();
                totalTime += endTime - startTime;
            }
            kertoja = koko / (totalTime / 5);
            System.out.println("Taulukon koko=" + koko + " Aika=" + totalTime / 5 + " kertoja=" + kertoja);
            koko = koko * 2;
        }
    }
            @Test
    public void aika2() {

        double startTime = 0;
        double endTime = 0;
        double totalTime = 0;
        double kertoja = 0;
        int koko = 400;
        for (int a = 0; a < 1; a++) {
            for (int b = 0; b < 5; b++) {
                int[][] koeTaulu = new int[koko][koko];

                for (int i = 0; i < koko; i++) {
                    for (int j = 0; j < koko; j++) {
                        koeTaulu[i][j] = 1;
                    }
                }
                System.gc();
                     
                Dijkstra D = new Dijkstra(koeTaulu, 0, 0, koko - 1, koko - 1);
                startTime = System.currentTimeMillis();
                D.ratkaise();
                endTime = System.currentTimeMillis();
                totalTime += endTime - startTime;
            }
            kertoja = koko / (totalTime / 5);
            System.out.println("Taulukon koko=" + koko + " Aika=" + totalTime / 5 + " kertoja=" + kertoja);
            koko = koko * 2;
        }
    }*/
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
