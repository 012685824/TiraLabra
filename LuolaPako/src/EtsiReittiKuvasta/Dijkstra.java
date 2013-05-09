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
    private Reitti[][] reitti;
    private Keko K = new Keko();

    public Dijkstra(int[][] kuvaTaulu, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        this.kuvaTaulu = kuvaTaulu;
        this.xAlku = xAlku;
        this.yAlku = yAlku;
        this.xLoppu = xLoppu;
        this.yLoppu = yLoppu;
        etaisyysTaulu = new double[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
        reitti = new Reitti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }

    public void ratsaise() {
        dijkstraKeko();
        //tulostaReitti();
    }

    public void initialiseSingleSource() {
        for (int x = 0; x < this.kuvaTaulu.length; x++) {        // alustetaan etäisyys taulukko
            for (int y = 0; y < kuvaTaulu[0].length; y++) {
                etaisyysTaulu[x][y] = Double.MAX_VALUE / 2;
                etaisyysTaulu[xAlku][yAlku] = 0;

                reitti[x][y] = new Reitti(0, 0);
            }
        }

    }

    public void relax(int x, int y, int x1, int y1) {

        if (etaisyysTaulu[x1][y1] > etaisyysTaulu[x][y] + kuvaTaulu[x1][y1]) { // verrataan onko etäisyys suurempi vai pienempi uutta solmua käyttäen
            etaisyysTaulu[x1][y1] = etaisyysTaulu[x][y] + kuvaTaulu[x1][y1];
            reitti[x1][y1].setX(x);
            reitti[x1][y1].setY(y);
            K.lisaa(x1, y1, etaisyysTaulu[x1][y1]);
            //K.tulosta();
        }
    }

    public void dijkstraKeko() {
        initialiseSingleSource();
        Sijainti sijaintiApu = new Sijainti(0, 0, 0); //luodaan apu sijainti muuttuja 

        K.lisaa(0, 0, 0);

        while (!K.empty()) {
            //System.out.println("Keko ennen poistoa: ");
            //K.tulosta();
            sijaintiApu = K.poista();
            //System.out.println("apu= " + sijaintiApu.getEtaisyys() + " x=" + sijaintiApu.getX() + " y=" + sijaintiApu.getY());
            //System.out.println("Keko poiston jälkeen.");
            //K.tulosta();
            if (sijaintiApu.getX() + 1 < etaisyysTaulu.length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa

                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() + 1, sijaintiApu.getY());    // jos ei niin suoritetaan relax
                //testiTulostaEtaisyydet(etaisyysTaulu);
            }

            if (sijaintiApu.getX() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() - 1, sijaintiApu.getY());
                //testiTulostaEtaisyydet(etaisyysTaulu);
            }
            if (sijaintiApu.getY() + 1 < etaisyysTaulu[0].length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() + 1);    // jos ei niin suoritetaan relax
                //testiTulostaEtaisyydet(etaisyysTaulu);
            }

            if (sijaintiApu.getY() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() - 1);
                //testiTulostaEtaisyydet(etaisyysTaulu);
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

    public void tulostaReitti() {
        int x=xLoppu-1;
        int y=yLoppu-1;
        System.out.println("X=" + x + " Y=" + y);
        while(x !=xAlku || y!=yAlku){
                System.out.println("X=" + reitti[x][y].getX() + " Y=" + reitti[x][y].getY());
                x=reitti[x][y].getX();
                y=reitti[x][y].getY();
        }
    }
}
