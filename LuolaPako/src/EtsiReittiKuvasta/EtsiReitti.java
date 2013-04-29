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

    public static void ratkaise(String tiedostonNimi) {
        BufferedImage luola = null;

        luola = haeKuva(tiedostonNimi);
        System.out.println(tiedostonNimi);
        int[][] luolaTaulu = new int[luola.getWidth()][luola.getHeight()];

        for (int i = 0; i < luolaTaulu.length; i++) {
            for (int j = 0; j < luolaTaulu[0].length; j++) {

                luolaTaulu[i][j] = -luola.getRGB(i, j);
            }
        }
        for (int i = 0; i < luolaTaulu.length; i++) {
            for (int j = 0; j < luolaTaulu[0].length; j++) {

                System.out.print(luolaTaulu[i][j] + "\t");
            }
            System.out.println("");
        }


    }
    
    
    
}
