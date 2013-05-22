/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.tietoRakenteet;

import EtsiReittiKuvasta.main.EtsiReitti;
import EtsiReittiKuvasta.tietoRakenteet.Keko;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;
import java.util.ArrayList;

/**
 * Astar luokka ratkaisee lyhimmän polun kahden annetun pisteet "solmun" välillä
 *
 * @author Toni
 */
public class Astar {

    private int[][] kuvaTaulu;
    private int xAlku, yAlku, xLoppu, yLoppu;
    private Sijainti[][] sijaintiTaulu;
    private Keko K = new Keko();
    private long[][] arvioTaulu;
    private int[] testiTulostus;

    /**
     * Luo Astar-olion, jolle annetaan alkuarvoina seuraavat
     *
     * @param kuvaTaulu
     * @param xAlku
     * @param yAlku
     * @param xLoppu
     * @param yLoppu
     * @param sijaintiTaulu
     * @param arvioTaulu
     */
    public Astar(int[][] kuvaTaulu, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        this.kuvaTaulu = kuvaTaulu;
        this.xAlku = xAlku;
        this.yAlku = yAlku;
        this.xLoppu = xLoppu;
        this.yLoppu = yLoppu;
        sijaintiTaulu = new Sijainti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
        arvioTaulu = new long[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }

    /**
     * ratkaise metodi aloittaa Astarin toiminnan kutsumalla aStarKeko-metodia.
     *
     */
    public void ratkaise() {
        aStarKeko();
    }

    /**
     * initialiseSingleSourceTest metodi on vain initialiseSingleSource metodin
     * testaamista varten.
     */
    public void initialiseSingleSourceTest() {
        initialiseSingleSource();
    }

    /**
     * initialiseSingleSource metodi alustaa sijaintiTaulun sekä arvioTaulun
     * heurestiikka funktion arvoilla
     */
    private void initialiseSingleSource() {
        for (int x = 0; x < sijaintiTaulu.length; x++) {        // alustetaan sijaintiTaulu taulukko
            for (int y = 0; y < sijaintiTaulu[0].length; y++) {
                sijaintiTaulu[x][y] = new Sijainti(0, 0, Double.MAX_VALUE / 2); //asetetaan etäisyysarvoksi suuri arvo
            }
        }
        sijaintiTaulu[xAlku][yAlku] = new Sijainti(0, 0, 0);//aloitus kohdan etäisyysarvoksi asetetaan 0

        //long kertoja = EtsiReitti.getKertojaAstariin();


        for (int x = 0; x < arvioTaulu[0].length; x++) {
            for (int y = 0; y < arvioTaulu.length; y++) {
                arvioTaulu[x][y] = (itseisArvo(xLoppu - x) + itseisArvo(yLoppu - y));
                //System.out.println(itseisArvo(xLoppu - x) + itseisArvo(yLoppu - y));
            }
        }

        /* for (int i = 0; i < sijaintiTaulu.length; i++) {
         for (int j = 0; j < sijaintiTaulu[0].length; j++) {
         System.out.print(" "+arvioTaulu[i][j]);
         }
         System.out.println("");
         }*/
    }

    /**
     * relaxTest metodi on vain relax metodin testaamista varten.
     */
    public void relaxTest(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {
        relax(xMistaTullaan, yMistaTullaan, xMihinMennaan, yMihinMennaan);
    }

    /**
     * relax metodi vertaa, onko etäisyysarvo suurempi siinä pisteessä, mihin
     * ollaan menossa, kuin pisteen "Mistä tullaan" etäisyysarvo lisättynä
     * kuvataulusta saatavaan etäisyysarvoon. Jos näin on, niin päivitetään se
     * uudella pienemmällä arvolla ja lisätään muuttuneen pisteen tiedot kekoon.
     *
     * @param xMistaTullaan kertoo x koordinaatin mistä tullaan.
     * @param yMistaTullaan kertoo y koordinaatin mistä tullaan.
     * @param xMihinMennaan kertoo x koordinaatin mihin mennään.
     * @param yMihinMennaan kertoo y koordinaatin mihin mennään.
     */
    private void relax(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {

        if (sijaintiTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys() > sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]) {

            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setEtaisyys(sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setX(xMistaTullaan);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setY(yMistaTullaan);
            //Lisätään kekoon uusi arvo.
            K.lisaa(xMihinMennaan, yMihinMennaan, sijaintiTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys() + arvioTaulu[xMihinMennaan][yMihinMennaan]);
        }
    }

    /**
     * aStarKekoTest metodi on vain aStarKeko metodin testaamista varten.
     */
    public void aStarKekoTest() {
        aStarKeko();
    }

    /**
     * aStarKeko metodi kutsuu aluksi initialiseSingleSource metodia alustusta
     * varten ja luo apumuuttujan "sijaintiApu". Apumuuttujaa tarvitaan keosta haettavaa
     * tietoa varten ja relaxsin kutsua varten, jotta varsinainen sijaintitaulu
     * pysyy halutunlaisena.Jos kyseinen kuva "Verkko" ei ole tyhjä, niin
     * käydään vieruspisteet, eli "solmut" läpi. Metodin suorittaminen lopetetaan,
     * kun keko on tyhjä, eli kaikki "solmut" on käyty loppuunasti läpi tai on
     * löydetty loppupiste.
     */
    private void aStarKeko() {
        initialiseSingleSource();
        Sijainti sijaintiApu = new Sijainti(0, 0, 0); //luodaan apusijainti muuttuja 

        K.lisaa(xAlku, yAlku, 0);

        while (!K.emptyIs() && (xLoppu != sijaintiApu.getX() || yLoppu != sijaintiApu.getY())) {
            sijaintiApu = K.poista();
            // tarkastetaan ollaanko taulukon reunassa X tai y akselin suunnassa,
            // jos ei, niin suoritetaan relax 
            if (sijaintiApu.getX() + 1 < sijaintiTaulu.length) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() + 1, sijaintiApu.getY());
            }

            if (sijaintiApu.getX() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() - 1, sijaintiApu.getY());
            }

            if (sijaintiApu.getY() + 1 < sijaintiTaulu[0].length) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() + 1);
            }

            if (sijaintiApu.getY() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() - 1);
            }
        }
    }

    /**
     * palauttaa sijaintitaulukon.
     *
     * @return Sijainti[][]
     */
    public Sijainti[][] getSijaintiTaulu() {
        return this.sijaintiTaulu;
    }

    /**
     * testiTulosReitti() metodi on vain testiTulosReitti metodin testaamiseen.
     *
     * @return int []
     */
    public int[] testiTulosReitti() {

        tulostaReitti();
        return testiTulostus;
    }

    /**
     * testiTulosReitti metodi tulostaa kuljetun reitin alkaen lopusta ja edeten alkuun
     * päin. Aluksi luodaan muutamat apumuutujat tulostusta varten. Kun
     * muuttujat on luotu, käydään while luupin avulla kuljettu reitti läpi.
     *
     */
    public void tulostaReitti() {
        int x = xLoppu;     //Annetaan tulostukseen reitin alkupiste
        int y = yLoppu;
        int xApu = 0;
        int i = 0;
        testiTulostus = new int[48 * 4];

        while (x != xAlku || y != yAlku) {
            System.out.println("X=" + sijaintiTaulu[x][y].getX() + " Y=" + sijaintiTaulu[x][y].getY());
            testiTulostus[i] = sijaintiTaulu[x][y].getX();
            i++;
            testiTulostus[i] = sijaintiTaulu[x][y].getY();
            i++;
            xApu = sijaintiTaulu[x][y].getX();
            y = sijaintiTaulu[x][y].getY();
            x = xApu;
        }
    }

    /**
     * tulostaKaikki metodi on ainoastaa ollut testailua varten
     *
     */
    public void tulostaKaikki() {
        for (int y = 0; y < yLoppu; y++) {
            for (int x = 0; x < xLoppu; x++) {
                System.out.print("X=" + sijaintiTaulu[x][y].getX() + " Y=" + sijaintiTaulu[x][y].getY() + "\t\t");
            }
            System.out.println("");
            System.out.println("");
        }

    }

    /**
     * itseisArvo metodi saa syötteenä int tyyppisen muuttujan ja palauttaa sen
     * itseisarvon.
     *
     * @param luku on luku, josta otetaan itseisarvo.
     *
     * @return palauttaa annetun luvun itseisarvon
     */
    private static int itseisArvo(int luku) {
        if (luku < 0) {
            return -luku;
        } else {
            return luku;
        }
    }
}
