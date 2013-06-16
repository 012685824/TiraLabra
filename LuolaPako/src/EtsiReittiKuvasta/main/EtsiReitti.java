/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.main;

import Algoritmit.Astar;
import Algoritmit.BellmanFord;
import Algoritmit.Dijkstra;
import Algoritmit.Dijkstra8;
import EtsiReittiKuvasta.tietoRakenteet.Keko;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *EtsiReitti luokka toimi ohjelman pääluokkana josta ohjelmassa olevia algoritmeja kutsutaan
 * 
 * @author Toni
 */
public class EtsiReitti {

    
    static int[][] kuvaTaulu;// luodaan tarvittavat muuttujat
    static Keko K = new Keko();
    static String ratkaisuKuvanTiedostonSijainti = "src/Kuvat/ratkaisu.bmp";
    static double reitinPituus = 0;
    static String testiVirhe = "";
    static long kertojaAstariin = 0;
    static Object piirretaankoKaikkiPisteet;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
    }

    /**
     *Ratkaise metodi saa syötteenä allaluetellut muuttujat. Jotka määrittävät 
     * ohjelman toiminnan. Muuttuja tiedostonNimi kertoo minkä nimistä tiedostoa
     * aletaan ratkaisemaan. Muuttujat xAlkuPiste ja YAlkuPiste kertovat polun aloitus 
     * pisteen koordinaatit vastaavasti xLoppuPiste ja yLoppuPiste kertovat mikä on 
     * polun lopetus piste. Muuttuja valinta kertoo mitä algoritmia ratkaisuun käytetään.
     * Viimeinnen parmetri piirretaankoKaikkiPisteetA kertoo haluaako käyttäjä
     * että kaikki pisteet missä ohjelma on käynyt piirretään myös ratkaisu kuvaan.
     * Tarvittavat parametrit saatuaan ratkaise luo ensin tyhjän BufferedImage ja asettaa
     * muuttujaan piirretaankoKaikkiPisteet arvoksi muutujan piirretaankoKaikkiPisteetA arvon.
     * Sen jälkeen alustetaan muutama muutuja ja haetaan rgb arvot kuvasta. Kun tarvittavat
     * arvot on asetettu niin valitaan valinta muutujan mukainen algoritmi suorittamaan 
     * reitin etsintä.
     * 
     * @param tiedostonNimi munkä nimistä tiedostoa ratkaistaan
     * @param xAlkuPiste mistä x:n koordinaatista etsintä aloitetaan
     * @param yAlkuPiste mistä y:n koordinaatista etsintä aloitetaan
     * @param xLoppuPiste mihin x:n koordinaattiin reitti etsitään
     * @param yLoppuPiste mihin y:n koordinaattiin reitti etsitään
     * @param valinta mitä algoritmia ratkaisuun käytetään
     * @param piirretaankoKaikkiPisteetA piirretäänkö kaikki käydyt pisteet vai ei.
     */
    public static void ratkaise(String tiedostonNimi, int xAlkuPiste, int yAlkuPiste, int xLoppuPiste, int yLoppuPiste, int valinta, Object piirretaankoKaikkiPisteetA) {
        //Luodaan BufferedImage ratkaistavan kuvan käsittelyä varten ja nollataan reitin pituus joka kerta kun uuttä kuvaa aletaan ratkaisemaan.
        BufferedImage kuva = null;
        piirretaankoKaikkiPisteet = piirretaankoKaikkiPisteetA;

        reitinPituus = 0;
        testiVirhe = "";
        kuva = haeKuva(tiedostonNimi); //haetaan kuva tiedosto käsittetyyn.
        kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()]; // Luodaan oikean kokoinen taulukosta

        haeVaritKuvatauluun(kuva); //Haetaan kuvaTauluun kuvan väri koodit
        // Valitaan valinnan mukainen algoritmi
        switch (valinta) {

            case 0:
                Dijkstra D = new Dijkstra(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                D.ratkaise();
                piirraReitti(D.getSijaintiTaulu(), kuva, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                break;
            case 1:
                if (kuvaTaulu.length >= 101 || kuvaTaulu[0].length >= 101) {
                    EtsiReittiUI.virhe("Valitsit kuvan joka oli liian iso maksimi koko on 100x100 \n Joten käytetään Dijkstraa");
                    Dijkstra D1 = new Dijkstra(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                    D1.ratkaise();
                    piirraReitti(D1.getSijaintiTaulu(), kuva, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                    break;
                }
                BellmanFord B = new BellmanFord(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                B.ratkaise();
                piirraReitti(B.getSijaintiTaulu(), kuva, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                break;
            case 2:
                Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                D8.ratkaise();
                piirraReitti(D8.getSijaintiTaulu(), kuva, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                break;
            case 3:
                Astar AS = new Astar(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                AS.ratkaise();
                piirraReitti(AS.getSijaintiTaulu(), kuva, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
            //break;
        }

    }

    /**
     *Metodi haeKuva hakee valitun tiedoston kiintolevyltä.
     * 
     * @param tiedosto tiedoston sijainti
     * @return
     */
    public static BufferedImage haeKuva(String tiedosto) {
        BufferedImage ratkaistavaKuva = null; // Luodaan uusi BufferedImage kuvien käsittelyä varten

        try {
            ratkaistavaKuva = ImageIO.read(new File(tiedosto)); // Ladataan käsiteltävä kuva ratkaistavaKuva muuttujaan

        } catch (IOException e) {
            System.out.println(e); // Tulostetaan virhe jos sellainen tulee
            testiVirhe = e.toString();
        }
        return ratkaistavaKuva; // Palautetaan kuva onnistuneen latauksen jälkeen
    }

    /**
     *Metodi talletaKuva tallentaa ratkaistun kuvan kiintolevylle.
     * 
     * @param ratkaistuKuva ratkaisun sijanti kiintolevyllä.
     */
    public static void talletaKuva(BufferedImage ratkaistuKuva) {
        File ratkaisuTiedosto = new File(ratkaisuKuvanTiedostonSijainti);//Luodaan uusi File muuttuja tiedoston käsittelyä varten
        try {
            // Talletetaan ratkaistu kuva
            ImageIO.write(ratkaistuKuva, "bmp", ratkaisuTiedosto);

        } catch (IOException e) {
            System.out.println(e); // Tulostetaan virhe jos sellainen tulee
            testiVirhe = e.toString();
        }
    }

    /**
     *testiVirhe testausta varten apu metodi.
     * 
     * @return
     */
    public static String testiVirhe() {
        return testiVirhe;
    }

    /**
     *Metodi piirraReitti piirtää ratkaistun reitin. Ensin asetetaan tarvittavat 
     * muutujat kohdalleen. Asetetaan sopiva väri reitille. Luotaan BufferedImage 
     * ratkaisu kuvaa varten. Jos on valittuu että piirretään kaikki pirteet missä käyty
     * niin piirretään ne. Jos valintaa ei oli niin piirretään vain reitti. Lopuksi 
     * kutsutaan metodia talletaKuva.
     * 
     * 
     * @param sijaintiTaulu tiedot reitistä
     * @param kuva mihin piirretään ratkaistu reitti.
     * @param xAlku mihin x:n koordinaattiin reittin piirtäminen lopetetaan
     * @param yAlku mihin y:n koordinaattiin reittin piirtäminen lopetetaan
     * @param xLoppu mistä x:n koordinaattista reittin piirtäminen aloitetaan
     * @param yLoppu mistä y:n koordinaattista reittin piirtäminen aloitetaan
     */
    public static void piirraReitti(Sijainti[][] sijaintiTaulu, BufferedImage kuva, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        int x = xLoppu;     //Annetaan tulostukseen reitin alkupiste tulostus tapahtuu siis 
        int y = yLoppu;     //lopusta alkuun päin.
        int xApu = 0;
        //Ei pakollinen reitin värin valinnassa. Mutta valmiina jos päivitän ohjelmaa niin että voi valita itse välin.-->
        int r = 30;// red component 0...255
        int g = 30;// green component 0...255
        int b = 230;// blue component 0...255
        int col = (r << 16) | (g << 8) | b;
        //<--
        reitinPituus = sijaintiTaulu[xLoppu][yLoppu].getEtaisyys();
        BufferedImage kuvaRatkaisu = null;
        kuvaRatkaisu = kuva;
        kuvaRatkaisu.setRGB(xLoppu, yLoppu, col);//Lisätään ensimmäinen piste
        //Käydään reitti läpi ja "piirretään" kaikki pisteet jos valinta päällä
        if (piirretaankoKaikkiPisteet != null) {
            for (int y1 = 0; y1 < kuvaTaulu[0].length; y1++) {
                for (int x1 = 0; x1 < kuvaTaulu.length; x1++) {

                    if (sijaintiTaulu[x1][y1].getEtaisyys() != Double.MAX_VALUE / 2) {

                        kuvaRatkaisu.setRGB(x1, y1, 10000);
                        
                    }
                }
            }
        }
         //Käydään reitti läpi ja "piirretään" oikea reitti
        while (x != xAlku || y != yAlku) {

            kuvaRatkaisu.setRGB(x, y, col);
            /*kuvaRatkaisu.setRGB(x, y-1, col); //Reitti viivan paksunnus demoa varten. 
            kuvaRatkaisu.setRGB(x+1, y-1, col);
            kuvaRatkaisu.setRGB(x+1, y, col);
            kuvaRatkaisu.setRGB(x+1, y+1, col);
            kuvaRatkaisu.setRGB(x, y+1, col);
            kuvaRatkaisu.setRGB(x-1, y+1, col);
            kuvaRatkaisu.setRGB(x-1, y, col);
            kuvaRatkaisu.setRGB(x-1, y+1, col);*/
            xApu = sijaintiTaulu[x][y].getX();
            y = sijaintiTaulu[x][y].getY();
            x = xApu;
        }
        kuvaRatkaisu.setRGB(xAlku, yAlku, col);//lisätään viimeinen piste

        talletaKuva(kuvaRatkaisu);//talletetaan valmis kuva
    }

    /**
     *Metodi haeVaritKuvatauluun hakee valitun kuvan väripisteet ja
     * tallentaa ne muuttujaan kuvaTaulu.
     * 
     * @param kuva Kuva mistä värit haetaan.
     */
    public static void haeVaritKuvatauluun(BufferedImage kuva) {
        for (int y = 0; y < kuvaTaulu[0].length; y++) {
            for (int x = 0; x < kuvaTaulu.length; x++) {
                kuvaTaulu[x][y] = -kuva.getRGB(x, y); // haetaan RGB värin arvo taulukkoon.
                kertojaAstariin += -kuva.getRGB(x, y);
            }
        }
        kertojaAstariin /= ((kuvaTaulu.length * kuvaTaulu[0].length));
        //System.out.println("kertoja=" + kertojaAstariin);
    }

    /**
     *
     * @return
     */
    public static long getKertojaAstariin() {
        return kertojaAstariin;
    }

    /**
     *
     * @return
     */
    public static int[][] testiGetKuvaTaulu() {
        return kuvaTaulu;
    }

    /**
     *
     * @return
     */
    public static double reitinPituus() {
        return reitinPituus;
    }

    /**
     *
     * @param kuvaTaulu
     */
    public static void setKuvaTaulu(int[][] kuvaTaulu) {
        EtsiReitti.kuvaTaulu = kuvaTaulu;
    }
}
