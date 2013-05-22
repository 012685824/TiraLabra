/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.tietoRakenteet;

import EtsiReittiKuvasta.tietoRakenteet.Keko;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Toni
 */
public class Dijkstra {

    private int[][] kuvaTaulu;
    private int xAlku, yAlku, xLoppu, yLoppu;
    private Sijainti[][] sijaintiTaulu;
    private Keko K = new Keko();
    private int[] testiTulostus;

    public Dijkstra(int[][] kuvaTaulu, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        this.kuvaTaulu = kuvaTaulu;
        this.xAlku = xAlku;
        this.yAlku = yAlku;
        this.xLoppu = xLoppu;
        this.yLoppu = yLoppu;
        sijaintiTaulu = new Sijainti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }

    public void ratkaise() {
        dijkstraKeko();
    }

    public void initialiseSingleSourceTest() {
        initialiseSingleSource();
    }

    private void initialiseSingleSource() {
        for (int x = 0; x < sijaintiTaulu.length; x++) {        // alustetaan sijaintiTaulu taulukko
            for (int y = 0; y < sijaintiTaulu[0].length; y++) {
                sijaintiTaulu[x][y] = new Sijainti(0, 0, Double.MAX_VALUE / 2); //asetetaan etäisyys arvoksi suuri arvo

            }
        }
        sijaintiTaulu[xAlku][yAlku] = new Sijainti(0, 0, 0);            //aloitus kohdan etäisyys arvo asetetaan 0

    }

    public void relaxTest(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {
        relax(xMistaTullaan, yMistaTullaan, xMihinMennaan, yMihinMennaan);
    }

    protected void relax(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {
        // verrataan onko etäisyys arvo suurempi ruudussa mihin ollaan menossa 
        // jos on niin päivitetään se uudella pienemmällä arvolla.
        if (sijaintiTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys() > sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]) {

            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setEtaisyys(sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setX(xMistaTullaan);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setY(yMistaTullaan);
            //Lisätään kekoon uusi arvo.
            
            K.lisaa(xMihinMennaan, yMihinMennaan, sijaintiTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys());
            //System.out.println("jälkeen");
            //K.tulosta();
        }
    }
public void dijkstraKekoTest(){
    dijkstraKeko();
}
    private void dijkstraKeko() {
        initialiseSingleSource();
        Sijainti sijaintiApu = new Sijainti(0, 0, 0); //luodaan apu sijainti muuttuja 

        K.lisaa(xAlku, yAlku, 0);

        while (!K.emptyIs() && (xLoppu != sijaintiApu.getX() || yLoppu != sijaintiApu.getY())) {
            sijaintiApu = K.poista();
            //System.out.println("si=" +sijaintiApu.getEtaisyys());
            //K.tulosta();
            // tarkastetaan ollaan taulukon reunassa X tai y akselin suunnassa
            // jos ei niin suoritetaan relax 
            if (sijaintiApu.getX() + 1 < sijaintiTaulu.length) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() + 1, sijaintiApu.getY());
            }

            if (sijaintiApu.getX() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX() - 1, sijaintiApu.getY());
            }

            if (sijaintiApu.getY() + 1 < sijaintiTaulu[0].length) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() + 1);
            }

            if (sijaintiApu.getY() - 1 >= 0) {
                relax(sijaintiApu.getX(), sijaintiApu.getY(), sijaintiApu.getX(), sijaintiApu.getY() - 1);
            }
        }
    }

    public int[][] getKuvaTaulu() {
        return kuvaTaulu;
    }

    public void setKuvaTaulu(int[][] kuvaTaulu) {
        this.kuvaTaulu = kuvaTaulu;
    }

    public int getxAlku() {
        return xAlku;
    }

    public void setxAlku(int xAlku) {
        this.xAlku = xAlku;
    }

    public int getyAlku() {
        return yAlku;
    }

    public void setyAlku(int yAlku) {
        this.yAlku = yAlku;
    }

    public int getxLoppu() {
        return xLoppu;
    }

    public void setxLoppu(int xLoppu) {
        this.xLoppu = xLoppu;
    }

    public int getyLoppu() {
        return yLoppu;
    }

    public void setyLoppu(int yLoppu) {
        this.yLoppu = yLoppu;
    }

    public Keko getK() {
        return K;
    }

    public void setK(Keko K) {
        this.K = K;
    }

    public int[] getTestiTulostus() {
        return testiTulostus;
    }

    public void setTestiTulostus(int[] testiTulostus) {
        this.testiTulostus = testiTulostus;
    }

    public Sijainti[][] getSijaintiTaulu() {
        return this.sijaintiTaulu;
    }

    public int[] testiTulosReitti() {

        tulostaReitti();
        return testiTulostus;
    }

    public void tulostaReitti() {
        int x = xLoppu;     //Annetaan tulostukseen reitin alkupiste
        int y = yLoppu;
        int xApu = 0;
        int i = 0;
        testiTulostus = new int[48 * 4];

        while (x != xAlku || y != yAlku) {
            System.out.println("X=" + sijaintiTaulu[x][y].getX() + " Y=" + sijaintiTaulu[x][y].getY());
            testiTulostus[i] = sijaintiTaulu[x][y].getX();
            i++;
            testiTulostus[i] = sijaintiTaulu[x][y].getY();
            i++;
            xApu = sijaintiTaulu[x][y].getX();
            y = sijaintiTaulu[x][y].getY();
            x = xApu;
        }
    }
}
