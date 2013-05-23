/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.tietoRakenteet;

/**
 *Sijainti luokka toimii pääohjelman apuna se sisältää
 * sijainti ja etäisyys tiedot.
 * 
 * @author Toni
 */
public class Sijainti {

    private int x;
    private int y;
    private double etaisyys;

    /**
     *
     * @param x x koordinaatin arvo
     * @param y y koordinaatin arvo
     * @param etaisyys etäisyys tieto koordinaatissa yx
     */
    public Sijainti(int x, int y, double etaisyys) {
        this.x = x;
        this.y = y;
        this.etaisyys = etaisyys;
    }

    /**
     *
     * @return
     */
    public double getEtaisyys() {
        return this.etaisyys;
    }

    /**
     *
     * @param etaisyys
     */
    public void setEtaisyys(double etaisyys) {
        this.etaisyys = etaisyys;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
}
