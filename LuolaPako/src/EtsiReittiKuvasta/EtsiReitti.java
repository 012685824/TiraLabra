/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Toni
 */
public class EtsiReitti {

    static double[][] distance; // luodaan tarvittavat muuttujat
    static double[][] path;
    static boolean[][] kaydyt;
    static int[][] kuvaTaulu;
    static Keko K = new Keko();

    public static void main(String[] args) {
    }

    public static void ratkaise(String tiedostonNimi) {
        //Ratkaise .. luo ladatusta kuvasta kaksi uloitteisen numeraalisen taulukon 
        //lyhimmän polun etsimistä varten
        int xAlku = 0;
        int yAlku = 0;

        BufferedImage kuva = null;

        kuva = haeKuva(tiedostonNimi); //haetaan kuva tiedosto käsittetyyn.
        System.out.println(tiedostonNimi);
        kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()]; // Luodaan oikean kokoinen taulukosta

        haeVaritKuvatauluun(kuva); //Haetaan kuvaTauluun kuvan väri koodit

        //bellmanFord(kuvaTaulu, 0, 0); // Ratkaistaan polku käyttäen bellman fordin algoritmiä.
        int xLoppu = kuva.getWidth();
        int yLoppu = kuva.getHeight();
        Dijkstra D = new Dijkstra(kuvaTaulu, xAlku, yAlku, xLoppu, yLoppu);
        //D.ratkaise();
        D.ratkaise1();

        //laitaKekoonTesti(kuva);
        //tulostaEtaisyydet(D.getEtaisyysTaulu());
        tulostaEtaisyyde1(D.getSijaintiTaulu());
        D.tulostaReitti1();
        D.tulostaKaikki();
        piirraReitti(D.getreitti(),kuva,xAlku,yAlku,xLoppu,yLoppu);



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
        File ratkaisuTiedosto = new File("C:/Users/Toni/Documents/GitHub/TiraLabra/LuolaPako/src/EtsiReittiKuvasta/ratkaisu.bmp");
        try {
             // Talletetaan ratkaistu kuva
            ImageIO.write(ratkaistuKuva, "bmp", ratkaisuTiedosto );

        } catch (IOException e) {
            System.out.println(e); // Tulostetaan virhe jos sellainen tulee

        }

     }
    
    /*    
     public static void bellmanFord(int[][] g, int s, int s1) {
     /*
     * Pseudo koodina Bellman Ford
     Bellman-Ford(G,w,s)
     1 Initialise-Single-Source(G,s)
     2 for i = 1 to | V | − 1
     3      for jokaiselle kaarelle (u, v) ∈ E
     4          Relax(u,v,w)
     5 for jokaiselle kaarelle (u, v) ∈ E
     6      if distance[v] > distance[u] + w(u,v)
     7          return false
     8 return true
         
     initialiseSingleSource(g, s, s1); // Kutsutaan muutujien alustus ..

     for (int u2 = 0; u2 < g.length; u2++) {             //alustettu taulukko läpi
     for (int u3 = 0; u3 < g[0].length; u3++) {
     for (int u = 0; u < g.length; u++) {
     for (int u1 = 0; u1 < g[0].length; u1++) {
     if (u1 + 1 < g[0].length) {     // tarkastetaan ollaan taulukon reunassa
     relaxB(u, u1, u, u1 + 1);    // jos ei niin suoritetaan relax
     }
                        
     if (u1 - 1 >= 0) {
     relaxB(u, u1, u, u1 - 1);
     }
                        
     if (u + 1 < g.length) {
     relaxB(u, u1, u + 1, u1);
     }
                        
     if (u - 1 >= 0) {
     relaxB(u, u1, u - 1, u1);
     }
     }
     }
     }
     }
        
        
        
     }

     */

    public static void piirraReitti(Reitti[][] reitti,BufferedImage kuva, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        int x = xLoppu - 1;     //Annetaan tulostukseen reitin alkupiste
        int y = yLoppu - 1;
        int xApu =0;
        int r = 10;// red component 0...255
        int g = 10;// green component 0...255
        int b = 10;// blue component 0...255
        int col = (r << 16) | (g << 8) | b;
        BufferedImage kuvaRatkaisu = null;
        kuvaRatkaisu = kuva;
        kuvaRatkaisu.setRGB(xLoppu-1, yLoppu-1, col);
        while (x != xAlku || y != yAlku) {
            kuvaRatkaisu.setRGB(x, y, 1000);
            xApu = reitti[x][y].getX();
            y = reitti[x][y].getY();
            x=xApu;
        }
        kuvaRatkaisu.setRGB(xAlku, yAlku, col);
        talletaKuva(kuvaRatkaisu);
    }
    /*    
     public static void initialiseSingleSource(int[][] g, int s, int s1) {
     // Toteuta minut
     /*
     1 for kaikille solmuille v ∈ V
     2   distance[v] = ∞ 
     3   path[v] = NIL
     4 distance[s] = 0
     int korkeus = g.length;
     int leveys = g[0].length;
        
     distance = new double[korkeus][leveys]; // Luodaan etäisyys taulukko käsiteltävän verkon kokeiseksi.
     //path = new double[korkeus][leveys];
     //käydyt = new boolean[korkeus][leveys];
     //vuoriApu = new int[korkeus][leveys];

     for (int i = 0; i < g.length; i++) {        // alustetaan etäisyys taulukko
     for (int j = 0; j < g[0].length; j++) {
     distance[i][j] = Double.MAX_VALUE / 2;
     distance[s][s1] = 0;
     }
            
     }
     //        käydyt[0][0] = true;

        
        
        
     }
     */

    public static void relaxB(int u, int u1, int v, int v1) {

        /*Relax(u,v,w)
         1 if distance[v] > distance[u] + w(u,v)
         2  distance[v] = distance[u]+w(u,v)
         3  path[v] = u*/
        //int ero = 0;

        // ero = ero(kuvaTaulu[u][u1], kuvaTaulu[v][v1]);

        // System.out.println(ero);

        if (distance[v][v1] > distance[u][u1] + kuvaTaulu[u][u1]) { // verrataan onko etäisyys suurempi vai pienempi uutta solmua käyttäen
            distance[v][v1] = distance[u][u1] + kuvaTaulu[u][u1];

            //path[v][v1] = ero;
        }
        //käydyt[v][1] = true;

    }

    public static void haeVaritKuvatauluun(BufferedImage kuva) {


        for (int y = 0; y < kuvaTaulu[0].length; y++) {
            for (int x = 0; x < kuvaTaulu.length; x++) {

                kuvaTaulu[x][y] = -kuva.getRGB(x, y); // haetaan RGB värin arvo taulukkoon.
            }
        }

    }

    public static void tulostaEtaisyydet(double[][] etaisyydet) {
        System.out.println("");
        for (int y = 0; y < etaisyydet[0].length; y++) { // testi tulostus toimiiko
            for (int x = 0; x < etaisyydet.length; x++) {
                System.out.print(etaisyydet[x][y] + "\t");
            }
            System.out.println("");
        }


    }

        public static void tulostaEtaisyyde1(Sijainti[][] sijainti) {
        System.out.println("");
        for (int y = 0; y < sijainti[0].length; y++) { // testi tulostus toimiiko
            for (int x = 0; x < sijainti.length; x++) {
                System.out.print(sijainti[x][y].getEtaisyys()+"\t");
            }
            System.out.println("");
        }

    }
    public static void laitaKekoonTesti(BufferedImage kuva) {

        Keko Ke = new Keko();
        for (int i = 0; i < kuvaTaulu.length; i++) {
            for (int j = 0; j < kuvaTaulu[0].length; j++) {
                Ke.lisaa(i, j, -kuva.getRGB(i, j));
                //kuvaTaulu[j][i] = -kuva.getRGB(i, j); // haetaan RGB värin arvo taulukkoon.
            }
        }/*
         for (int i = 0; i < 50; i++) {
         Ke.poista();
         }
         */
        Ke.tulosta();

    }
}
