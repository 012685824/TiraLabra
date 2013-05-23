/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.tietoRakenteet;

import EtsiReittiKuvasta.tietoRakenteet.Sijainti;

/**
 *BellmanFord luokka ratkaisee lyhimmän polun kahden annetun pisteet "solmun" välillä
 * @author Toni
 */
public class BellmanFord {

    private int[][] kuvaTaulu;
    private int xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste;
    private Sijainti[][] sijaintiTaulu;
    private int[] testiTulostus;
    /**
     * Luo BellmanFord-olion, jolle annetaan alkuarvoina seuraavat
     *
     * @param kuvaTaulu sisältää tiedon pisteen värikoodista
     * @param xAlku misä x:n etsintä aloitetaan
     * @param yAlku misä y:n etsintä aloitetaan
     * @param xLoppu mihin x:n arvoon reitti etsitään
     * @param yLoppu mihin y:n arvoon reitti etsitään
     * @param sijaintiTaulu sisältää tiedot solmun arvoista x,y,etäisyys
     */
    public BellmanFord(int[][] kuvaTaulu, int xAlkuPiste, int yAlkuPiste, int xLoppuPiste, int yLoppuPiste) {

        this.kuvaTaulu = kuvaTaulu;
        this.xAlkuPiste = xAlkuPiste;
        this.yAlkuPiste = yAlkuPiste;
        this.xLoppuPiste = xLoppuPiste;
        this.yLoppuPiste = yLoppuPiste;
        sijaintiTaulu = new Sijainti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }
    /**
     * ratkaise metodi aloittaa kutsuu ensin initialiseSingleSource metodia.
     * ratkaise metodi käy kolmen for luupin avulla kuvaTaulua läpi ja jos ei
     * olla taulukon reunoilla niin kutsutaan relax metodia kaikiin pääilman suuntiin.
     */
    public void ratkaise() {
        initialiseSingleSource(); // Kutsutaan muutujien alustus ..
        for (int i = 0; i < kuvaTaulu.length * kuvaTaulu[0].length; i++) {
            for (int y = 0; y < kuvaTaulu[0].length; y++) {
                for (int x = 0; x < kuvaTaulu.length; x++) {

                    if (x + 1 < kuvaTaulu.length) {     // tarkastetaan ollaan taulukon reunassa
                        relax(x, y, x + 1, y);    // jos ei niin suoritetaan relax
                    }

                    if (x - 1 >= 0) {
                        relax(x, y, x - 1, y);
                    }

                    if (y + 1 < kuvaTaulu[0].length) {
                        relax(x, y, x, y + 1);
                    }

                    if (y - 1 >= 0) {
                        relax(x, y, x, y - 1);
                    }
                }
            }
        }
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

            }
        }
        sijaintiTaulu[xAlkuPiste][yAlkuPiste] = new Sijainti(0, 0, 0);
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
     * uudella pienemmällä arvolla.
     *
     * @param xMistaTullaan kertoo x koordinaatin mistä tullaan.
     * @param yMistaTullaan kertoo y koordinaatin mistä tullaan.
     * @param xMihinMennaan kertoo x koordinaatin mihin mennään.
     * @param yMihinMennaan kertoo y koordinaatin mihin mennään.
     */
    private void relax(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {

        if (sijaintiTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys() > sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]) { // verrataan onko etäisyys suurempi vai pienempi uutta solmua käyttäen

            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setEtaisyys(sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setX(xMistaTullaan);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setY(yMistaTullaan);
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
        int x = xLoppuPiste;     //Annetaan tulostukseen reitin alkupiste
        int y = yLoppuPiste;
        int xApu = 0;
        int i = 0;
        testiTulostus = new int[48 * 4];

        while (x != xAlkuPiste || y != yAlkuPiste) {
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
    /*
     public void tulostaKaikki() {
     for (int y = 0; y < yLoppuPiste; y++) {
     for (int x = 0; x < xLoppuPiste; x++) {
     System.out.print("X=" + sijaintiTaulu[x][y].getX() + " Y=" + sijaintiTaulu[x][y].getY() + "\t\t");
     }
     System.out.println("");
     System.out.println("");
     }

     }*/
}
