/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import EtsiReittiKuvasta.tietoRakenteet.Keko;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;


/**
 * Dijkstra luokka ratkaisee lyhimmän polun kahden annetun pisteet "solmun"
 * välillä liikkuen vain pää ilmansuuntiin.
 *
 * @author Toni
 */
public class Dijkstra {

    private int[][] kuvaTaulu;
    private int xAlku, yAlku, xLoppu, yLoppu;
    private Sijainti[][] sijaintiTaulu;
    private Keko K = new Keko();
    private int[] testiTulostus;

    /**
     * Luo Dijkstra-olion, jolle annetaan alkuarvoina seuraavat
     *
     * @param kuvaTaulu sisältää tiedon pisteen värikoodista
     * @param xAlku misä x:n etsintä aloitetaan
     * @param yAlku misä y:n etsintä aloitetaan
     * @param xLoppu mihin x:n arvoon reitti etsitään
     * @param yLoppu mihin y:n arvoon reitti etsitään
     * @param sijaintiTaulu sisältää tiedot solmun arvoista x,y,etäisyys
     */
    public Dijkstra(int[][] kuvaTaulu, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        this.kuvaTaulu = kuvaTaulu;
        this.xAlku = xAlku;
        this.yAlku = yAlku;
        this.xLoppu = xLoppu;
        this.yLoppu = yLoppu;
        sijaintiTaulu = new Sijainti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }

    /**
     * ratkaise metodi aloittaa Dijkstran toiminnan kutsumalla
     * dijkstraKeko-metodia.
     *
     */
    public void ratkaise() {
        dijkstraKeko();
    }

    /**
     * initialiseSingleSourceTest metodi on vain initialiseSingleSource metodin
     * testaamista varten.
     */
    public void initialiseSingleSourceTest() {
        initialiseSingleSource();
    }

    /**
     * initialiseSingleSource metodi alustaa sijaintiTaulun.
     */
    private void initialiseSingleSource() {
        for (int x = 0; x < sijaintiTaulu.length; x++) {        // alustetaan sijaintiTaulu taulukko
            for (int y = 0; y < sijaintiTaulu[0].length; y++) {
                sijaintiTaulu[x][y] = new Sijainti(0, 0, Double.MAX_VALUE / 2); //asetetaan etäisyys arvoksi suuri arvo

            }
        }
        sijaintiTaulu[xAlku][yAlku] = new Sijainti(0, 0, 0);            //aloitus kohdan etäisyys arvo asetetaan 0

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
    protected void relax(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {

        if (sijaintiTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys() > sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]) {

            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setEtaisyys(sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setX(xMistaTullaan);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setY(yMistaTullaan);
            //Lisätään kekoon uusi arvo.

            K.lisaaKekoon(xMihinMennaan, yMihinMennaan, sijaintiTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys());
            //System.out.println("jälkeen");
            //K.tulosta();
        }
    }

    /**
     * dijkstraKekoTest metodi on vain dijkstraKeko metodin testaamista varten.
     */
    public void dijkstraKekoTest() {
        dijkstraKeko();
    }

    /**
     * dijkstraKeko metodi kutsuu aluksi initialiseSingleSource metodia
     * alustusta varten ja luo apumuuttujan "sijaintiApu". Apumuuttujaa
     * tarvitaan keosta haettavaa tietoa varten ja relaxsin kutsua varten, jotta
     * varsinainen sijaintitaulu pysyy halutunlaisena.Jos kyseinen kuva "Verkko"
     * ei ole tyhjä, niin käydään vieruspisteet, eli "solmut" läpi (pääilman
     * suunnista). Metodin suorittaminen lopetetaan, kun keko on tyhjä, eli
     * kaikki "solmut" on käyty loppuunasti läpi tai on löydetty loppupiste.
     */
    private void dijkstraKeko() {
        initialiseSingleSource();
        Sijainti sijaintiApu = new Sijainti(0, 0, 0); //luodaan apu sijainti muuttuja 

        K.lisaaKekoon(xAlku, yAlku, 0);

        while (!K.emptyIs() && (xLoppu != sijaintiApu.getX() || yLoppu != sijaintiApu.getY())) {
            sijaintiApu = K.poistaKeosta();
            // tarkastetaan ollaan taulukon reunassa X tai y akselin suunnassa
            // jos ei niin suoritetaan relax 
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

    public int[][] getKuvaTaulu() {
        return kuvaTaulu;
    }

    public void setKuvaTaulu(int[][] kuvaTaulu) {
        this.kuvaTaulu = kuvaTaulu;
    }

    public int getxAlku() {
        return xAlku;
    }

    public void setxAlku(int xAlku) {
        this.xAlku = xAlku;
    }

    public int getyAlku() {
        return yAlku;
    }

    public void setyAlku(int yAlku) {
        this.yAlku = yAlku;
    }

    public int getxLoppu() {
        return xLoppu;
    }

    public void setxLoppu(int xLoppu) {
        this.xLoppu = xLoppu;
    }

    public int getyLoppu() {
        return yLoppu;
    }

    public void setyLoppu(int yLoppu) {
        this.yLoppu = yLoppu;
    }

    public Keko getK() {
        return K;
    }

    public void setK(Keko K) {
        this.K = K;
    }

    public int[] getTestiTulostus() {
        return testiTulostus;
    }

    public void setTestiTulostus(int[] testiTulostus) {
        this.testiTulostus = testiTulostus;
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
     * testiTulosReitti metodi tulostaa kuljetun reitin alkaen lopusta ja edeten
     * alkuun päin. Aluksi luodaan muutamat apumuutujat tulostusta varten. Kun
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
}
