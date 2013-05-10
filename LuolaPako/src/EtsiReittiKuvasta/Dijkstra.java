/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta;

import java.util.ArrayList;

/**
 *
 * @author Toni
 */
public class Dijkstra {

    private int[][] kuvaTaulu;
    private int xAlku, yAlku, xLoppu, yLoppu;
    private double[][] etaisyysTaulu;
    private Sijainti[][] etaisyysTaulu1;
    private Reitti[][] reitti;
    private Keko K = new Keko();
    private Keko K1 = new Keko();

    public Dijkstra(int[][] kuvaTaulu, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        this.kuvaTaulu = kuvaTaulu;
        this.xAlku = xAlku;
        this.yAlku = yAlku;
        this.xLoppu = xLoppu;
        this.yLoppu = yLoppu;
        etaisyysTaulu = new double[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
        etaisyysTaulu1 = new Sijainti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
        reitti = new Reitti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }

    public void ratkaise() {
        dijkstraKeko();

        //tulostaReitti1();
    }

    public void ratkaise1() {
        dijkstraKeko1();
    }

    private void initialiseSingleSource() {
        for (int x = 0; x < this.kuvaTaulu.length; x++) {        // alustetaan etäisyys taulukko
            for (int y = 0; y < kuvaTaulu[0].length; y++) {
                etaisyysTaulu[x][y] = Double.MAX_VALUE / 2;
                etaisyysTaulu[xAlku][yAlku] = 0;
                etaisyysTaulu1[x][y] = new Sijainti(0, 0, Double.MAX_VALUE / 2);
                etaisyysTaulu1[xAlku][yAlku] = new Sijainti(0, 0, 0);

                reitti[x][y] = new Reitti(0, 0);
            }
        }

    }

    private void relax(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {

        if (etaisyysTaulu1[xMihinMennaan][yMihinMennaan].getEtaisyys() > etaisyysTaulu1[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]) { // verrataan onko etäisyys suurempi vai pienempi uutta solmua käyttäen
            etaisyysTaulu1[xMihinMennaan][yMihinMennaan].setEtaisyys(etaisyysTaulu1[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]);
            etaisyysTaulu1[xMihinMennaan][yMihinMennaan].setX(xMistaTullaan);
            etaisyysTaulu1[xMihinMennaan][yMihinMennaan].setY(yMistaTullaan);

            K1.lisaa(xMihinMennaan, yMihinMennaan, etaisyysTaulu1[xMihinMennaan][yMihinMennaan].getEtaisyys());
            //K.tulosta();
        }




        if (etaisyysTaulu[xMihinMennaan][yMihinMennaan] > etaisyysTaulu[xMistaTullaan][yMistaTullaan] + kuvaTaulu[xMihinMennaan][yMihinMennaan]) { // verrataan onko etäisyys suurempi vai pienempi uutta solmua käyttäen
            etaisyysTaulu[xMihinMennaan][yMihinMennaan] = etaisyysTaulu[xMistaTullaan][yMistaTullaan] + kuvaTaulu[xMihinMennaan][yMihinMennaan];
            reitti[xMihinMennaan][yMihinMennaan].setX(xMistaTullaan);
            reitti[xMihinMennaan][yMihinMennaan].setY(yMistaTullaan);


            K.lisaa(xMihinMennaan, yMihinMennaan, etaisyysTaulu[xMihinMennaan][yMihinMennaan]);
            //K.tulosta();
        }
    }

    private void dijkstraKeko1() {
        initialiseSingleSource();
        Sijainti sijaintiApu = new Sijainti(0, 0, 0); //luodaan apu sijainti muuttuja 

        K1.lisaa(0, 0, 0);

        while (!K1.empty()) {
            //System.out.println("Keko ennen poistoa: ");
            //K.tulosta();
            sijaintiApu = K1.poista();
            //System.out.println("apu= " + sijaintiApu.getEtaisyys() + " x=" + sijaintiApu.getX() + " y=" + sijaintiApu.getY());
            //System.out.println("Keko poiston jälkeen.");
            //K.tulosta();
            if (sijaintiApu.getX() + 1 < etaisyysTaulu1.length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa

                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() + 1, sijaintiApu.getY());    // jos ei niin suoritetaan relax
                //testiTulostaEtaisyydet(etaisyysTaulu);
            }

            if (sijaintiApu.getX() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() - 1, sijaintiApu.getY());
                //testiTulostaEtaisyydet(etaisyysTaulu);
            }
            if (sijaintiApu.getY() + 1 < etaisyysTaulu1[0].length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() + 1);    // jos ei niin suoritetaan relax
                //testiTulostaEtaisyydet(etaisyysTaulu);
            }

            if (sijaintiApu.getY() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() - 1);
                //testiTulostaEtaisyydet(etaisyysTaulu);
            }


        }


    }

    private void dijkstraKeko() {
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

    public Sijainti[][] getSijaintiTaulu() {
        return this.etaisyysTaulu1;
    }

    public Reitti[][] getreitti() {
        return this.reitti;
    }

    private void testiTulostaEtaisyydet(double[][] etaisyydet) {
        System.out.println("");
        for (int y = 0; y < etaisyydet[0].length; y++) { // testi tulostus toimiiko
            for (int x = 0; x < etaisyydet.length; x++) {
                System.out.print(etaisyydet[x][y] + "\t");
            }
            System.out.println("");
        }


    }

    public void tulostaReitti1() {
        int x = xLoppu - 1;     //Annetaan tulostukseen reitin alkupiste
        int y = yLoppu - 1;
        int xApu = 0;
        System.out.println("X=" + x + " Y=" + y);

        while (x != xAlku || y != yAlku) {
            System.out.println("X=" + etaisyysTaulu1[x][y].getX() + " Y=" + etaisyysTaulu1[x][y].getY());
            xApu = etaisyysTaulu1[x][y].getX();
            y = etaisyysTaulu1[x][y].getY();
            x = xApu;
        }
    }

    public void tulostaKaikki() {
        for (int y = 0; y < yLoppu; y++) {
            for (int x = 0; x < xLoppu; x++) {
                System.out.print("X=" + etaisyysTaulu1[x][y].getX() + " Y=" + etaisyysTaulu1[x][y].getY() + "\t\t");
            }
            System.out.println("");
            System.out.println("");
        }

    }

    public void tulostaReitti() {
        int x = xLoppu - 1;     //Annetaan tulostukseen reitin alkupiste
        int y = yLoppu - 1;
        System.out.println("X=" + x + " Y=" + y);
        while (x != xAlku || y != yAlku) {
            System.out.println("X=" + reitti[x][y].getX() + " Y=" + reitti[x][y].getY());
            x = reitti[x][y].getX();
            y = reitti[x][y].getY();
        }
    }

    public void tulostaReittiEtaisyys() {
    }
}
