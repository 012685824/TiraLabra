/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta;

import EtsiReittiKuvasta.main.EtsiReitti;
import EtsiReittiKuvasta.tietoRakenteet.Keko;
import EtsiReittiKuvasta.tietoRakenteet.Sijainti;
import java.util.ArrayList;

/**
 *
 * @author Toni
 */
public class Astar {

    private int[][] kuvaTaulu;
    private int xAlku, yAlku, xLoppu, yLoppu;
    private Sijainti[][] sijaintiTaulu;
    private Keko K = new Keko();
    private long[][] arvioTaulu;
    private int[] testiTulostus;

    public Astar(int[][] kuvaTaulu, int xAlku, int yAlku, int xLoppu, int yLoppu) {
        this.kuvaTaulu = kuvaTaulu;
        this.xAlku = xAlku;
        this.yAlku = yAlku;
        this.xLoppu = xLoppu;
        this.yLoppu = yLoppu;
        sijaintiTaulu = new Sijainti[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
        arvioTaulu = new long[this.kuvaTaulu.length][this.kuvaTaulu[0].length];
    }

    public void ratkaise() {
        aStarKeko();
    }
    public void initialiseSingleSourceTest() {
        initialiseSingleSource();
    }
    private void initialiseSingleSource() {
        for (int x = 0; x < sijaintiTaulu.length; x++) {        // alustetaan sijaintiTaulu taulukko
            for (int y = 0; y < sijaintiTaulu[0].length; y++) {
                sijaintiTaulu[x][y] = new Sijainti(0, 0, Double.MAX_VALUE / 2); //asetetaan etäisyys arvoksi suuri arvo
                //aloitus kohdan etäisyys arvo asetetaan 0

            }
        }
        sijaintiTaulu[xAlku][yAlku] = new Sijainti(0, 0, 0);

        long kertoja = EtsiReitti.getKertojaAstariin();


        for (int x = 0; x < arvioTaulu[0].length; x++) {
            for (int y = 0; y < arvioTaulu.length; y++) {
                arvioTaulu[x][y] = (itseisArvo(xLoppu - x) + itseisArvo(yLoppu - y));
                //System.out.println(itseisArvo(xLoppu - x) + itseisArvo(yLoppu - y));
            }
        }

        /* for (int i = 0; i < sijaintiTaulu.length; i++) {
         for (int j = 0; j < sijaintiTaulu[0].length; j++) {
         System.out.print(" "+arvioTaulu[i][j]);
         }
         System.out.println("");
         }*/
    }

    public void relaxTest(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {
        relax(xMistaTullaan, yMistaTullaan, xMihinMennaan, yMihinMennaan);
    }
    private void relax(int xMistaTullaan, int yMistaTullaan, int xMihinMennaan, int yMihinMennaan) {
        // verrataan onko etäisyys arvo suurempi ruudussa mihin ollaan menossa 
        // jos on niin päivitetään se uudella pienemmällä arvolla.
        if (sijaintiTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys() > sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]) {

            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setEtaisyys(sijaintiTaulu[xMistaTullaan][yMistaTullaan].getEtaisyys() + kuvaTaulu[xMihinMennaan][yMihinMennaan]);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setX(xMistaTullaan);
            sijaintiTaulu[xMihinMennaan][yMihinMennaan].setY(yMistaTullaan);
            //Lisätään kekoon uusi arvo.
            K.lisaa(xMihinMennaan, yMihinMennaan, sijaintiTaulu[xMihinMennaan][yMihinMennaan].getEtaisyys() + arvioTaulu[xMihinMennaan][yMihinMennaan]);
        }
    }
public void aStarKekoTest(){
    aStarKeko();
}
    private void aStarKeko() {
        initialiseSingleSource();
        Sijainti sijaintiApu = new Sijainti(0, 0, 0); //luodaan apu sijainti muuttuja 

        K.lisaa(xAlku, yAlku, 0);

        while (!K.emptyIs() && (xLoppu != sijaintiApu.getX() || yLoppu != sijaintiApu.getY())) {
            sijaintiApu = K.poista();
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

    public void tulostaKaikki() {
        for (int y = 0; y < yLoppu; y++) {
            for (int x = 0; x < xLoppu; x++) {
                System.out.print("X=" + sijaintiTaulu[x][y].getX() + " Y=" + sijaintiTaulu[x][y].getY() + "\t\t");
            }
            System.out.println("");
            System.out.println("");
        }

    }

    private static int itseisArvo(int luku) {
        if (luku < 0) {
            return -luku;
        } else {
            return luku;
        }
    }
}
