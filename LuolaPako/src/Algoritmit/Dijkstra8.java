/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import EtsiReittiKuvasta.tietoRakenteet.Keko;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;

/**
 * Dijkstra8 luokka ratkaisee lyhimmän polun kahden annetun pisteet "solmun"
 * välillä käyttäen pääilmansuuntia ja väli-ilmansuuntia.
 *
 * @author Toni
 */
public class Dijkstra8 {

    private int[][] kuvaTaulu;
    private int xAlku, yAlku, xLoppu, yLoppu;
    private Sijainti[][] sijaintiTaulu;
    private Keko K = new Keko();
    private int[] testiTulostus;
    Sijainti nykyinenSijainti;
    /**
     * Luo Dijkstra8-olion, jolle annetaan alkuarvoina seuraavat
     *
     * @param kuvaTaulu sisältää tiedon pisteen värikoodista
     * @param xAlku mistä x:n koordinaatista etsintä aloitetaan
     * @param yAlku mistä y:n koordinaatista etsintä aloitetaan
     * @param xLoppu mihin x:n koordinaattiin reitti etsitään
     * @param yLoppu mihin y:n koordinaattiin reitti etsitään
     * @param sijaintiTaulu sisältää tiedot solmun arvoista x,y,etäisyys
     */
    public Dijkstra8(int[][] kuvaTaulu, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        this.kuvaTaulu = kuvaTaulu;
        this.xAlku = xAlku;
        this.yAlku = yAlku;
        this.xLoppu = xLoppu;
        this.yLoppu = yLoppu;
        sijaintiTaulu = new Sijainti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }

    /**
     * ratkaise metodi aloittaa Dijkstran8 toiminnan kutsumalla
     * dijkstraKeko-metodia.
     *
     */
    public void ratkaise() {
        dijkstra8Keko();
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
        for (int x = 0; x < this.kuvaTaulu.length; x++) {        // alustetaan etäisyys taulukko
            for (int y = 0; y < kuvaTaulu[0].length; y++) {
                sijaintiTaulu[x][y] = new Sijainti(0, 0, Double.MAX_VALUE / 2);
                sijaintiTaulu[xAlku][yAlku] = new Sijainti(0, 0, 0);
            }
        }
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
     * @param xLahde kertoo x koordinaatin mistä tullaan.
     * @param yLahde kertoo y koordinaatin mistä tullaan.
     * @param xKohde kertoo x koordinaatin mihin mennään.
     * @param yKohde kertoo y koordinaatin mihin mennään.
     */
    private void relax(int xLahde, int yLahde, int xKohde, int yKohde) {
//*1.414
        if (kordinaattiEiKelpaa(xKohde, yKohde)) {
            return;
        }
        
        final Sijainti kohde = sijaintiTaulu[xKohde][yKohde];
        final Sijainti lahde = sijaintiTaulu[xLahde][yLahde];

        double kerroin = 1;
        if ((xLahde != xKohde) && (yLahde != yKohde)) { //Tarkastetaan ollaanko 

            kerroin = 1.414;
        }

        if (kohde.getEtaisyys() > lahde.getEtaisyys() + kuvaTaulu[xKohde][yKohde] * kerroin) {

            paivitaSolmuJaLisaaKekoon(kohde, lahde, xKohde, yKohde, kerroin, xLahde, yLahde);
        }
    }

    /**
     * dijkstraKeko8Test metodi on vain dijkstraKeko metodin testaamista varten.
     */
    public void dijkstraKeko8Test() {
        dijkstra8Keko();
    }

    /**
     * dijkstra8Keko metodi kutsuu aluksi initialiseSingleSource metodia
     * alustusta varten ja luo apumuuttujan "sijaintiApu". Apumuuttujaa
     * tarvitaan keosta haettavaa tietoa varten ja relaxsin kutsua varten, jotta
     * varsinainen sijaintitaulu pysyy halutunlaisena.Jos kyseinen kuva "Verkko"
     * ei ole tyhjä, niin käydään vieruspisteet, eli "solmut" läpi (pääilman
     * suuntiin sekä välilimman suuntiin). Metodin suorittaminen lopetetaan,kun
     * keko on tyhjä, eli kaikki "solmut" on käyty loppuunasti läpi tai on
     * löydetty loppupiste.
     *
     */
    private void dijkstra8Keko() {
        initialiseSingleSource();

        K.lisaaKekoon(xAlku, yAlku, 0);

        while (!K.emptyIs()) {

            nykyinenSijainti = K.poistaKeosta();
            
            if (maali(nykyinenSijainti)) break;
          
            int nykyinenX = nykyinenSijainti.getX();
            
            relax(nykyinenX, nykyinenSijainti.getY(), nykyinenX + 1, nykyinenSijainti.getY());    // jos ei niin suoritetaan relax
            relax(nykyinenX, nykyinenSijainti.getY(), nykyinenSijainti.getX() - 1, nykyinenSijainti.getY());
            relax(nykyinenX, nykyinenSijainti.getY(), nykyinenSijainti.getX(), nykyinenSijainti.getY() + 1);    // jos ei niin suoritetaan relax
            relax(nykyinenX, nykyinenSijainti.getY(), nykyinenSijainti.getX(), nykyinenSijainti.getY() - 1);
            relax(nykyinenX, nykyinenSijainti.getY(), nykyinenSijainti.getX() + 1, nykyinenSijainti.getY() - 1);    // jos ei niin suoritetaan relax
            relax(nykyinenX, nykyinenSijainti.getY(), nykyinenSijainti.getX() - 1, nykyinenSijainti.getY() - 1);
            relax(nykyinenX, nykyinenSijainti.getY(), nykyinenSijainti.getX() + 1, nykyinenSijainti.getY() + 1);    // jos ei niin suoritetaan relax
            relax(nykyinenX, nykyinenSijainti.getY(), nykyinenSijainti.getX() - 1, nykyinenSijainti.getY() + 1);

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

    private void paivitaSolmuJaLisaaKekoon(final Sijainti kohde, final Sijainti lahde, int xKohde, int yKohde, double kerroin, int xLahde, int yLahde) {
        kohde.setEtaisyys(lahde.getEtaisyys() + kuvaTaulu[xKohde][yKohde] * kerroin);
        kohde.setX(xLahde);
        kohde.setY(yLahde);

        K.lisaaKekoon(xKohde, yKohde, kohde.getEtaisyys() * kerroin);
    }

    private boolean kordinaattiEiKelpaa(int xKohde, int yKohde) {
        if (xKohde < 0) {
            return true;
        }
        if (yKohde < 0) {
            return true;
        }
        if (xKohde >= this.sijaintiTaulu[0].length) {
            return true;
        }
        if (yKohde >= this.sijaintiTaulu.length) {
            return true;
        }

        return false;
    }

    private boolean maali(Sijainti sijaintiApu) {
        return xLoppu == sijaintiApu.getX() && yLoppu == sijaintiApu.getY();
    }
}
