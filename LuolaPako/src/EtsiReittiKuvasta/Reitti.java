/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta;

/**
 *
 * @author Toni
 */
public class Reitti {
    private int x;
    private int y;
    

    public Reitti(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String tulostaReitti(){
        String tulostus ="";
        tulostus = "X="+this.x+" Y="+this.y;
        return tulostus;
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
