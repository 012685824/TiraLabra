/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta;

/**
 *
 * @author Toni
 */
public class Dijkstra {

    private int[][] kuvaTaulu;
    private int xAlku, yAlku, xLoppu, yLoppu;
    private double[][] etaisyysTaulu;
    private Keko K = new Keko();

    public Dijkstra(int[][] kuvaTaulu, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        this.kuvaTaulu = kuvaTaulu;
        this.xAlku = xAlku;
        this.yAlku = yAlku;
        this.xLoppu = xLoppu;
        this.yLoppu = yLoppu;
        etaisyysTaulu = new double[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }

    public void ratsaise() {
        dijkstraKeko();
    }

    public void initialiseSingleSource() {
        for (int x = 0; x < this.kuvaTaulu.length; x++) {        // alustetaan etäisyys taulukko
            for (int y = 0; y < kuvaTaulu[0].length; y++) {
                etaisyysTaulu[x][y] = Double.MAX_VALUE / 2;
                etaisyysTaulu[xAlku][yAlku] = 0;
            }
        }

    }

    public void relax(int x, int y, int x1, int y1) {

        /*        Relax(u, v, w)
         1 if distance[v] > distance[u] + w(u, v)
         {
         2  
         }
         distance[v] = distance[u] + w(u, v)
         3  path[v] = u //int ero = 0;
         // ero = ero(kuvaTaulu[u][u1], kuvaTaulu[v][v1]);
         // System.out.println(ero);
         
         if (distance[x1][y1] >= Double.MAX_VALUE / 2) {
         K.lisaa(x1, y1, kuvaTaulu[x1][y1]);
         }
         */

        if (etaisyysTaulu[x1][y1] > etaisyysTaulu[x][y] + kuvaTaulu[x1][y1]) { // verrataan onko etäisyys suurempi vai pienempi uutta solmua käyttäen
            etaisyysTaulu[x1][y1] = etaisyysTaulu[x][y] + kuvaTaulu[x1][y1];
            K.lisaa(x1, y1, etaisyysTaulu[x1][y1]);
            K.tulosta();
            //path[v][v1] = ero;
        }
        //käydyt[v][1] = true;

    }

    public void dijkstraKeko() {
        initialiseSingleSource();
        Sijainti sijaintiApu = new Sijainti(0, 0, 0); //luodaan apu sijainti muuttuja 

        K.lisaa(0, 0, 0);

        while (!K.empty()) {
            System.out.println("Keko ennen poistoa: ");
            K.tulosta();
            sijaintiApu = K.poista();
            System.out.println("apu= " + sijaintiApu.getEtaisyys() + " x=" + sijaintiApu.getX() + " y=" + sijaintiApu.getY());
            System.out.println("Keko poiston jälkeen.");
            K.tulosta();
            if (sijaintiApu.getX() + 1 < etaisyysTaulu.length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa

                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() + 1, sijaintiApu.getY());    // jos ei niin suoritetaan relax
                testiTulostaEtaisyydet(etaisyysTaulu);
            }

            if (sijaintiApu.getX() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() - 1, sijaintiApu.getY());
                testiTulostaEtaisyydet(etaisyysTaulu);
            }
            if (sijaintiApu.getY() + 1 < etaisyysTaulu[0].length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() + 1);    // jos ei niin suoritetaan relax
                testiTulostaEtaisyydet(etaisyysTaulu);
            }

            if (sijaintiApu.getY() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() - 1);
                testiTulostaEtaisyydet(etaisyysTaulu);
            }


        }


    }

    public double[][] getEtaisyysTaulu() {
        return this.etaisyysTaulu;
    }

    public void testiTulostaEtaisyydet(double[][] etaisyydet) {
System.out.println("");
        for (int y = 0; y < etaisyydet[0].length; y++) { // testi tulostus toimiiko
            for (int x = 0; x < etaisyydet.length; x++) {
                System.out.print(etaisyydet[x][y] + "\t");
            }
            System.out.println("");
        }


    }
}

/*
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

 public static void relaxD(int x, int y, int x1, int y1) {

 /*Relax(u,v,w)
 1 if distance[v] > distance[u] + w(u,v)
 2  distance[v] = distance[u]+w(u,v)
 3  path[v] = u
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
