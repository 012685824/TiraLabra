/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.main;

import EtsiReittiKuvasta.BellmanFord;
import EtsiReittiKuvasta.Dijkstra;
import EtsiReittiKuvasta.Dijkstra8;
import EtsiReittiKuvasta.tietoRakenteet.Keko;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Toni
 */
public class EtsiReitti {

    static int[][] kuvaTaulu;// luodaan tarvittavat muuttujat
    static Keko K = new Keko();
    static String ratkaisuKuvanTiedostonSijainti = "C:/Users/Toni/Documents/GitHub/TiraLabra/LuolaPako/src/Kuvat/ratkaisu.bmp";
    static double reitinPituus = 0;
    
    public static void main(String[] args) {
    }

    public static void ratkaise(String tiedostonNimi, int xAlkuPiste, int yAlkuPiste, int xLoppuPiste, int yLoppuPiste, int valinta) {
        //Luodaan BufferedImage ratkaistavan kuvan käsittelyä varten ja nollataan reitin pituus joka kerta kun uuttä kuvaa aletaan ratkaisemaan.
        BufferedImage kuva = null;
        reitinPituus = 0;
        kuva = haeKuva(tiedostonNimi); //haetaan kuva tiedosto käsittetyyn.
        /*System.out.println(tiedostonNimi); //tulostuksia toiminnan tarkastelua varten
        System.out.println("xa=" + xAlkuPiste + "ya=" + yAlkuPiste + "xl=" + xLoppuPiste + "yl=" + yLoppuPiste);
        System.out.println("valinta=" + valinta);*/
        kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()]; // Luodaan oikean kokoinen taulukosta

        haeVaritKuvatauluun(kuva); //Haetaan kuvaTauluun kuvan väri koodit
        // Valitaan valinnan mukainen algoritmi
        switch (valinta) {

            case 0:
                Dijkstra D = new Dijkstra(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste); 
                D.ratkaise();
                //tulostaEtaisyyde(D.getSijaintiTaulu());
                piirraReitti(D.getSijaintiTaulu(), kuva, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                break;
            case 1:
                BellmanFord B = new BellmanFord(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                B.ratkaise();
                //tulostaEtaisyyde(B.getSijaintiTaulu());
                //B.tulostaReitti();
                piirraReitti(B.getSijaintiTaulu(), kuva, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                break;
            case 2:
                Dijkstra8 D8 = new Dijkstra8(kuvaTaulu, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                D8.ratkaise();
                piirraReitti(D8.getSijaintiTaulu(), kuva, xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste);
                break;
        }

    }

    public static BufferedImage haeKuva(String tiedosto) {
        BufferedImage ratkaistavaKuva = null; // Luodaan uusi BufferedImage kuvien käsittelyä varten

        try {
            ratkaistavaKuva = ImageIO.read(new File(tiedosto)); // Ladataan käsiteltävä kuva ratkaistavaKuva muuttujaan

        } catch (IOException e) {
            System.out.println(e); // Tulostetaan virhe jos sellainen tulee
        }
        return ratkaistavaKuva; // Palautetaan kuva onnistuneen latauksen jälkeen
    }

    public static void talletaKuva(BufferedImage ratkaistuKuva) {
        File ratkaisuTiedosto = new File(ratkaisuKuvanTiedostonSijainti);//Luodaan uusi File muuttuja tiedoston käsittelyä varten
        try {
            // Talletetaan ratkaistu kuva
            ImageIO.write(ratkaistuKuva, "bmp", ratkaisuTiedosto);

        } catch (IOException e) {
            System.out.println(e); // Tulostetaan virhe jos sellainen tulee
        }
    }

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
        BufferedImage kuvaRatkaisu = null;
        kuvaRatkaisu = kuva;
        kuvaRatkaisu.setRGB(xLoppu, yLoppu, col);//Lisätään ensimmäinen piste
        //Käydään reitti läpi ja "piirretään" reitti
        while (x != xAlku || y != yAlku) {
            reitinPituus = reitinPituus + sijaintiTaulu[x][y].getEtaisyys();
            kuvaRatkaisu.setRGB(x, y, col);
            xApu = sijaintiTaulu[x][y].getX();
            y = sijaintiTaulu[x][y].getY();
            x = xApu;
        }
        kuvaRatkaisu.setRGB(xAlku, yAlku, col);//lisätään viimeinen piste
        talletaKuva(kuvaRatkaisu);//talletetaan valmis kuva
    }

    public static void haeVaritKuvatauluun(BufferedImage kuva) {
        for (int y = 0; y < kuvaTaulu[0].length; y++) {
            for (int x = 0; x < kuvaTaulu.length; x++) {
                kuvaTaulu[x][y] = -kuva.getRGB(x, y); // haetaan RGB värin arvo taulukkoon.
            }
        }

    }

    public static void tulostaEtaisyyde(Sijainti[][] sijainti) {
        System.out.println("");
        for (int y = 0; y < sijainti[0].length; y++) { // testi tulostus toimiiko
            for (int x = 0; x < sijainti.length; x++) {
                System.out.print(sijainti[x][y].getEtaisyys() + "\t");
            }
            System.out.println("");
        }
    }
    public double getReitinPituus(){
        return this.reitinPituus;
    }
}
