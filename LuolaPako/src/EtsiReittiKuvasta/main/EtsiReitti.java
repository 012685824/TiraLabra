/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.main;

import EtsiReittiKuvasta.BellmanFord;
import EtsiReittiKuvasta.Dijkstra;
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

    public static void main(String[] args) {
    }

    public static void ratkaise(String tiedostonNimi, int xAlkuPiste, int yAlkuPiste, int xLoppuPiste, int yLoppuPiste, int valinta) {

        BufferedImage kuva = null;

        kuva = haeKuva(tiedostonNimi); //haetaan kuva tiedosto käsittetyyn.
        System.out.println(tiedostonNimi);
        System.out.println("xa=" + xAlkuPiste + "ya=" + yAlkuPiste + "xl=" + xLoppuPiste + "yl=" + yLoppuPiste);
        System.out.println("valinta="+valinta);
        kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()]; // Luodaan oikean kokoinen taulukosta

        haeVaritKuvatauluun(kuva); //Haetaan kuvaTauluun kuvan väri koodit
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
        }

    }

    public static BufferedImage haeKuva(String tiedosto) {
        BufferedImage ratkaistavaKuva = null; // Luodaan uusi BufferedImage kuvien käsittelyä varten

        try {
            ratkaistavaKuva = ImageIO.read(new File(tiedosto)); // Ladataan käsiteltävä kuva luolaKuva muuttujaan

        } catch (IOException e) {
            System.out.println(e); // Tulostetaan virhe jos sellainen tulee
        }
        return ratkaistavaKuva; // Palautetaan kuva onnistuneen latauksen jälkeen
    }

    public static void talletaKuva(BufferedImage ratkaistuKuva) {
        File ratkaisuTiedosto = new File(ratkaisuKuvanTiedostonSijainti);
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
        int r = 30;// red component 0...255
        int g = 30;// green component 0...255
        int b = 230;// blue component 0...255
        int col = (r << 16) | (g << 8) | b;

        BufferedImage kuvaRatkaisu = null;
        kuvaRatkaisu = kuva;
        kuvaRatkaisu.setRGB(xLoppu, yLoppu, col);

        while (x != xAlku || y != yAlku) {
            kuvaRatkaisu.setRGB(x, y, col);
            xApu = sijaintiTaulu[x][y].getX();
            y = sijaintiTaulu[x][y].getY();
            x = xApu;
        }
        kuvaRatkaisu.setRGB(xAlku, yAlku, col);
        talletaKuva(kuvaRatkaisu);
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
}
