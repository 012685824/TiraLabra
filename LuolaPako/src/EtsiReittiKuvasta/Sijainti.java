/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta;

/**
 *
 * @author Toni
 */
public class Sijainti {

    private int x;
    private int y;
    private double etaisyys;

    public Sijainti(int x, int y, double etaisyys) {
        this.x = x;
        this.y = y;
        this.etaisyys = etaisyys;
    }

    public double getEtaisyys() {
        return this.etaisyys;
    }

    public void setEtaisyys(double etaisyys) {
        this.etaisyys = etaisyys;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
