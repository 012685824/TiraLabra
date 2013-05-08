/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta;

/**
 *
 * @author Toni
 */
public class Keko {

    // luodaan tarvittavat apu muuttujat kekoa varten
    private double[] keko;
    private int keonKoko;

    //Konstruktori luo minimikeon jonka maksimi koko on 10 000
    //keonkoko muuttuja kertoo sen hetkisen keon koon.
    public Keko() {
        keko = new double[10000];
        keonKoko = 0;

    }

    //Lisää kekoon uuden arvon oikealle paikalle.
    public void lisaa(double uusi) {
        keko[keonKoko + 1] = uusi; //Jätetään taulukon paikka 0 käyttämättä kun taulukko "alkaa" kohdasta 1 
        keonKoko++;             //on helpompi tarkistaa keko ehto.
        korjaaLisays();


    }
    //Poistaa keon ensimmäisen arvon.

    public double poista() {
        double apu = keko[1]; // talletetaan keon huipun arvo apu muuttujaan

        keko[1] = keko[keonKoko]; // laitetaan keon viimeinen arvo keon huipulle
        keonKoko--;
        korjaaPoisto();// korjataan keko kuntoon
        
        return apu; // palautetaan keko huipun arvo eli pienin arvo
    }

    //Tarkistaa onko keko tyhjä.
    public boolean empty() {
        if (keonKoko == 0) {
            return true;
        }
        return false;
    }
    //Korjaa keko ehdon.

    public void korjaaLisays() {
        double apu = 0;
        int sijainti = keonKoko; // Luodaan uusi muuttuja joka tietää missä kohtaa kekoa ollaan tarkistuksen aikana.
        if (sijainti <= 1) {    //jos keon koko on 0 tai 1 niin eitehdä mitään
        } else {

            while (sijainti != 1) { //käydään keko läpi ja siirretään uusi arvo oikealle paikalle.
                if (keko[sijainti / 2] > keko[sijainti]) {
                    apu = keko[sijainti / 2];
                    keko[sijainti / 2] = keko[sijainti];
                    keko[sijainti] = apu;
                    sijainti = sijainti / 2;
                } else {            // kun keko kunnossa asetetaan sijainti arvo 1 niin while luuppi loppuu.
                    sijainti = 1;
                }

            }
        }
    }

    public void korjaaPoisto() {
        double apu = 0;
        int sijainti = 1; // Luodaan uusi muuttuja joka tietää missä kohtaa kekoa ollaan tarkistuksen aikana.
        // ja asetetaan se osoittamaan keon huippua.
        if (keonKoko <= 1) {    //jos keon koko on 0 tai 1 niin eitehdä mitään
        } else {

            while (sijainti <= keonKoko/2) { //käydään keko läpi ja siirretään uusi arvo oikealle paikalle.

                if (keko[sijainti * 2] < keko[sijainti * 2 + 1]) {//verrataan kumpi laspista on pienempi
                    sijainti = sijainti * 2;                      //ja asetetaan se sijainti muuttujaan
                } else {
                    sijainti = sijainti * 2 + 1;
                }

                if (keko[sijainti] < keko[sijainti / 2]) {
                    apu = keko[sijainti / 2];
                    keko[sijainti / 2] = keko[sijainti];
                    keko[sijainti] = apu;
                } else {            // kun keko kunnossa asetetaan sijainti arvo keonKoko +1 niin while luuppi loppuu.
                    sijainti = keonKoko + 1;
                }

            }
        }
    }

    public void tulosta() { //tulostetaan keko taulukko muodossa.
        for (int i = 1; i < keonKoko + 1; i++) {
            System.out.print(keko[i] + " ");
        }
    }
}
