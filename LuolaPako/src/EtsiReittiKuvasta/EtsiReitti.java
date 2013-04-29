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

    static double[][] distance;
    static double[][] path;
    static boolean[][] kaydyt;
    static int[][] kuvaTaulu;

    public static void main(String[] args) {
    }

    public static BufferedImage haeKuva(String tiedosto) {
        BufferedImage luolaKuva = null;

        try {
            luolaKuva = ImageIO.read(new File(tiedosto));

        } catch (IOException e) {
            System.out.println(e);

        }

        return luolaKuva;
    }

    public static void dijkstra() {
        
        
        
        
        
        
        
    }

    public static void bellmanFord(int[][] g, int s, int s1) {
        /*
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
        initialiseSingleSource(g, s, s1);

        for (int u2 = 0; u2 < g.length; u2++) {
            for (int u3 = 0; u3 < g[0].length; u3++) {
                for (int u = 0; u < g.length; u++) {
                    for (int u1 = 0; u1 < g[0].length; u1++) {
                        if (u1 + 1 < g[0].length) {
                            relax(u, u1, u, u1 + 1);
                        }

                        if (u1 - 1 >= 0) {
                            relax(u, u1, u, u1 - 1);
                        }

                        if (u + 1 < g.length) {
                            relax(u, u1, u + 1, u1);
                        }

                        if (u - 1 >= 0) {
                            relax(u, u1, u - 1, u1);
                        }
                    }
                }
            }
        }



    }

    public static void piirraReitti(){
        
        
        
        
        
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

        distance = new double[korkeus][leveys];
        path = new double[korkeus][leveys];
        //käydyt = new boolean[korkeus][leveys];
        //vuoriApu = new int[korkeus][leveys];




        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                distance[i][j] = Double.MAX_VALUE;
                distance[s][s1] = 0;
            }

        }
//        käydyt[0][0] = true;




    }

    public static void relax(int u, int u1, int v, int v1) {
        // Toteuta minut

        /*Relax(u,v,w)
         1 if distance[v] > distance[u] + w(u,v)
         2  distance[v] = distance[u]+w(u,v)
         3  path[v] = u*/
        //int ero = 0;

       // ero = ero(kuvaTaulu[u][u1], kuvaTaulu[v][v1]);

        // System.out.println(ero);

        if (distance[v][v1] > distance[u][u1] + kuvaTaulu[u][u1]) {
            distance[v][v1] = distance[u][u1] + kuvaTaulu[u][u1];
            //path[v][v1] = ero;
        }
        //käydyt[v][1] = true;





    }

    public static int ero(int u, int v) {
        int ero;

        ero = u - v;

        if (ero < 0) {
            return -ero;
        }

        return ero;
    }

    public static void ratkaise(String tiedostonNimi) {
        BufferedImage kuva = null;

        kuva = haeKuva(tiedostonNimi);
        System.out.println(tiedostonNimi);
        kuvaTaulu = new int[kuva.getWidth()][kuva.getHeight()];


        for (int i = 0; i < kuvaTaulu.length; i++) {
            for (int j = 0; j < kuvaTaulu[0].length; j++) {

                kuvaTaulu[i][j] = -kuva.getRGB(i, j);
            }
        }

        bellmanFord(kuvaTaulu, 0, 0);


        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {

                System.out.print(distance[i][j] + "\t");
            }
            System.out.println("");
        }


    }
}
