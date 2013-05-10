/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta;

import EtsiReittiKuvasta.tietoRakenteet.Keko;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;
import java.util.ArrayList;

/**
 *
 * @author Toni
 */
public class Dijkstra {

    private int[][] kuvaTaulu;
    private int xAlku, yAlku, xLoppu, yLoppu;
    private Sijainti[][] etaisyysTaulu;
    private Keko K = new Keko();

    public Dijkstra(int[][] kuvaTaulu, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        this.kuvaTaulu = kuvaTaulu;
        this.xAlku = xAlku;
        this.yAlku = yAlku;
        this.xLoppu = xLoppu;
        this.yLoppu = yLoppu;
        etaisyysTaulu = new Sijainti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }

    public void ratkaise() {
        dijkstraKeko();
    }

    private void initialiseSingleSource() {
        for (int x = 0; x < this.kuvaTaulu.length; x++) {        // alustetaan etäisyys taulukko
            for (int y = 0; y < kuvaTaulu[0].length; y++) {
                etaisyysTaulu[x][y] = new Sijainti(0, 0, Double.MAX_VALUE / 2);
                etaisyysTaulu[xAlku][yAlku] = new Sijainti(0, 0, 0);
            }
        }

    }

    private void relax(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {

        if (etaisyysTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys() > etaisyysTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]) { // verrataan onko etäisyys suurempi vai pienempi uutta solmua käyttäen

            etaisyysTaulu[xMihinMennaan][yMihinMennaan].setEtaisyys(etaisyysTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]);
            etaisyysTaulu[xMihinMennaan][yMihinMennaan].setX(xMistaTullaan);
            etaisyysTaulu[xMihinMennaan][yMihinMennaan].setY(yMistaTullaan);

            K.lisaa(xMihinMennaan, yMihinMennaan, etaisyysTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys());
        }
    }

    private void dijkstraKeko() {
        initialiseSingleSource();
        Sijainti sijaintiApu = new Sijainti(0, 0, 0); //luodaan apu sijainti muuttuja 

        K.lisaa(0, 0, 0);

        while (!K.empty()) {
            sijaintiApu = K.poista();
            if (sijaintiApu.getX() + 1 < etaisyysTaulu.length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() + 1, sijaintiApu.getY());    // jos ei niin suoritetaan relax
            }

            if (sijaintiApu.getX() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() - 1, sijaintiApu.getY());
            }
            if (sijaintiApu.getY() + 1 < etaisyysTaulu[0].length) {     // tarkastetaan ollaan taulukon reunassa X akselin suunnassa
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() + 1);    // jos ei niin suoritetaan relax
            }

            if (sijaintiApu.getY() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() - 1);
            }
        }
    }

    public Sijainti[][] getSijaintiTaulu() {
        return this.etaisyysTaulu;
    }

    public void tulostaReitti1() {
        int x = xLoppu - 1;     //Annetaan tulostukseen reitin alkupiste
        int y = yLoppu - 1;
        int xApu = 0;

        while (x != xAlku || y != yAlku) {
            System.out.println("X=" + etaisyysTaulu[x][y].getX() + " Y=" + etaisyysTaulu[x][y].getY());
            xApu = etaisyysTaulu[x][y].getX();
            y = etaisyysTaulu[x][y].getY();
            x = xApu;
        }
    }

    public void tulostaKaikki() {
        for (int y = 0; y < yLoppu; y++) {
            for (int x = 0; x < xLoppu; x++) {
                System.out.print("X=" + etaisyysTaulu[x][y].getX() + " Y=" + etaisyysTaulu[x][y].getY() + "\t\t");
            }
            System.out.println("");
            System.out.println("");
        }

    }
}
