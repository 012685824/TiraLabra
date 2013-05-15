/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import EtsiReittiKuvasta.Dijkstra;
import EtsiReittiKuvasta.main.EtsiReitti;
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
public class DijstraTest {
    
    public DijstraTest() {
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
    public void tulostaakoReitinOikein() {
        BufferedImage kuva = null;
        String oletusTulos = "494849474946494549444943494249414940493949384937493649354934493349324931493049294928492749264925492449234922492149204919491849174916491549144913491249114910499498497496495494493492491481471461451441431421411401391381371361351341331321311301291281271261251241231221211201191181171161151141131121111101918171615141312111";
        kuva = EtsiReitti.haeKuva("./src/Kuvat/testiKuva4.bmp");
        int[][] kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()];
        EtsiReitti.setKuvaTaulu(kuvaTaulu);
        EtsiReitti.haeVaritKuvatauluun(kuva);
        kuvaTaulu = EtsiReitti.testiGetKuvaTaulu();
        
        Dijkstra testD = new Dijkstra(kuvaTaulu, 1, 1, 49, 49);
        testD.ratkaise();
        int[] tulostuksenTulosApu = testD.testiTulosReitti();
        String tulostuksenTulos = "";
        for (int i = 0; i < tulostuksenTulosApu.length; i++) {
            tulostuksenTulos = tulostuksenTulos+tulostuksenTulosApu[i] + "";
        }
        assertEquals(oletusTulos, tulostuksenTulos);
   
    }
    
    @Test
    
    public void onkoloppuPisteOikea(){
        BufferedImage kuva = null;
        kuva = EtsiReitti.haeKuva("./src/Kuvat/testiKuva0.bmp");
        int[][] kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()];
        /*EtsiReitti.setKuvaTaulu(kuvaTaulu);
        EtsiReitti.haeVaritKuvatauluun(kuva);
        kuvaTaulu = EtsiReitti.testiGetKuvaTaulu();
        */
        Dijkstra testD = new Dijkstra(kuvaTaulu, 1, 1, 49, 49);
        testD.ratkaise();
        kuva = EtsiReitti.haeKuva("./src/Kuvat/ratkaisu.bmp");
        System.out.println(kuva.getRGB(49, 49));
        //assertThat(kuva.getRGB(49, 49), );
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
