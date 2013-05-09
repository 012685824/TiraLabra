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

    public static BufferedImage haeKuva(String tiedosto) {
        BufferedImage luolaKuva = null; // Luodaan uusi BufferedImage kuvien käsittelyä varten

        try {
            luolaKuva = ImageIO.read(new File(tiedosto)); // Ladataan käsiteltävä kuva luolaKuva muuttujaan

        } catch (IOException e) {
            System.out.println(e); // Tulostetaan virhe jos sellainen tulee

        }

        return luolaKuva; // Palautetaan kuva onnistuneen latauksen jälkeen
    }

    public static void dijkstra(int[][] g, int s, int s1) {

//Dijkstra-with-heap(G,w,s)
//1 Intialise-Single-Source(G,s)
//2 S = ∅
//3 for kaikille solmuille v ∈ V
//4     heap-insert(H,v,distance[v])
//5 while not empty(H)
//6     u = heap-del-min(H)
//7     S = S ∪ {u}
//8     for jokaiselle solmulle v ∈ vierus[u] // kaikille u:n vierussolmuille v
//9         Relax(u,v,w)
//10        heap-decrease-key(H,v,distance[v])
// ei tee mitään, jos distance[v] ei ole muuttunut
        Sijainti sijaintiApu = new Sijainti(0, 0, 0); //luodaan apu sijainti muuttuja 
        initialiseSingleSource(g, s, s1);


        K.lisaa(0, 0, 0);

        while (!K.empty()) {

            sijaintiApu = K.poista();
            System.out.println("apu= " + sijaintiApu.getEtaisyys() + " x=" + sijaintiApu.getX() + " y=" + sijaintiApu.getY());
            if (sijaintiApu.getX() + 1 < g.length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa

                relaxD(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() + 1, sijaintiApu.getY());    // jos ei niin suoritetaan relax
            }

            if (sijaintiApu.getX() - 1 >= 0) {
                relaxD(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() - 1, sijaintiApu.getY());
            }
            if (sijaintiApu.getY() + 1 < g[0].length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa
                relaxD(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() + 1);    // jos ei niin suoritetaan relax
            }

            if (sijaintiApu.getY() - 1 >= 0) {
                relaxD(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getY(), sijaintiApu.getY() - 1);
            }


        }





    }

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
         */
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

    public static void piirraReitti() {
    }

    public static void initialiseSingleSource(int[][] g, int s, int s1) {
        // Toteuta minut
            /*
         1 for kaikille solmuille v ∈ V
         2   distance[v] = ∞ 
         3   path[v] = NIL
         4 distance[s] = 0*/
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

    public static void relaxD(int x, int y, int x1, int y1) {

        /*Relax(u,v,w)
         1 if distance[v] > distance[u] + w(u,v)
         2  distance[v] = distance[u]+w(u,v)
         3  path[v] = u*/
        //int ero = 0;

        // ero = ero(kuvaTaulu[u][u1], kuvaTaulu[v][v1]);

        // System.out.println(ero);
        if (distance[x1][y1] >= Double.MAX_VALUE / 2) {
            K.lisaa(x1, y1, kuvaTaulu[x1][y1]);
        }


        if (distance[x1][y1] > distance[x][y] + kuvaTaulu[x][y]) { // verrataan onko etäisyys suurempi vai pienempi uutta solmua käyttäen
            distance[x1][y1] = distance[x][y] + kuvaTaulu[x][y];
            K.lisaa(x1, y1, distance[x1][y1]);
            K.tulosta();
            //path[v][v1] = ero;
        }
        //käydyt[v][1] = true;

    }

    public static void ratkaise(String tiedostonNimi) {
        //Ratkaise .. luo ladatusta kuvasta kaksi uloitteisen numeraalisen taulukon 
        //lyhimmän polun etsimistä varten
        BufferedImage kuva = null;

        kuva = haeKuva(tiedostonNimi);
        System.out.println(tiedostonNimi);
        kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()]; // Luodaan oikean kokoinen taulukosta


        for (int x = 0; x < kuvaTaulu.length; x++) {
            for (int y = 0; y < kuvaTaulu[0].length; y++) {

                kuvaTaulu[x][y] = -kuva.getRGB(x, y); // haetaan RGB värin arvo taulukkoon.
            }
        }
        System.out.println(-kuva.getRGB(0, 0));
        System.out.println(-kuva.getRGB(2, 0));
        //bellmanFord(kuvaTaulu, 0, 0); // Ratkaistaan polku käyttäen bellman fordin algoritmiä.
        //dijkstra(kuvaTaulu, 0, 0);
        //laitaKekoonTesti(kuva);
/*
        for (int i = 0; i < kuvaTaulu.length; i++) { // testi tulostus toimiiko
            for (int j = 0; j < kuvaTaulu[0].length; j++) {

                System.out.print(kuvaTaulu[i][j] + "\t");
            }
            System.out.println("");
        }
*/
        /*
         for (int i = 0; i < distance.length; i++) { // testi tulostus toimiiko
         for (int j = 0; j < distance[0].length; j++) {

         System.out.print(distance[i][j] + "\t");
         }
         System.out.println("");
         }*/

//Keko testailua ->
       /* Keko kokeilu = new Keko();

         kokeilu.lisaa(1, 1, 3);
         kokeilu.lisaa(1, 1, 6);
         kokeilu.lisaa(1, 1, 1);
         kokeilu.lisaa(1, 1, 2);
         kokeilu.lisaa(1, 1, 7);
         kokeilu.lisaa(1, 1, 4);
         kokeilu.lisaa(1, 1, 8);
         kokeilu.poista();
         kokeilu.tulosta();*/
        //<-


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
