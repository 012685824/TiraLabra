/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.tietoRakenteet;

import EtsiReittiKuvasta.main.EtsiReitti;

/**
 *
 * @author Toni
 */
public class Keko {

    // luodaan tarvittavat apu muuttujat kekoa varten
    private Sijainti[] keko;
    private int keonKoko;


    /**
     *Konstruktori luo minimikeon jonka maksimi koko on 100
     *keonkoko muuttuja kertoo sen hetkisen keon koon.
     */
    public Keko() {
        keko = new Sijainti[100];
        keonKoko = 0;

    }
    //getterit ja setteri

    /**
     *
     * @return
     */
    public int getKeonKoko() {
        return keonKoko;
    }

    /**
     *
     * @return
     */
    public double palautaKekoHuippuArvo() {
        return keko[1].getEtaisyys();
    }

    /**
     *
     * @return
     */
    public int KekoTaulukonKoko() {
        return keko.length;
    }

    //Lisää kekoon uuden arvon oikealle paikalle.
    /**
     *
     * @param i
     * @return
     */
    protected int parent(int i) {
        return i / 2;
    }

    /**
     *
     * @param i
     * @return
     */
    protected int left(int i) {
        return i * 2;
    }

    /**
     *
     * @param i
     * @return
     */
    protected int right(int i) {
        return i * 2 + 1;
    }

    /**
     *
     * @return
     */
    protected boolean emptyIs() {
        if (keonKoko == 0) {
            return true;
        }
        return false;
    }

    /**
     *
     */
    protected void tuplaaKeko() {

        Sijainti[] kekoApuIso = new Sijainti[keko.length * 2];
        System.arraycopy(keko, 0, kekoApuIso, 0, keko.length);
        keko = new Sijainti[kekoApuIso.length];
        System.arraycopy(kekoApuIso, 0, keko, 0, kekoApuIso.length);

    }

    /**
     *
     */
    protected void pienennaKeko() {
        Sijainti[] kekoApu = new Sijainti[keko.length / 2];
        System.arraycopy(keko, 0, kekoApu, 0, kekoApu.length);
        keko = new Sijainti[kekoApu.length];
        System.arraycopy(kekoApu, 0, keko, 0, keko.length);

    }

    /**
     *
     * @param mikaVaihdetaan
     * @param mihinVaihdetaan
     */
    protected void vaihda(int mikaVaihdetaan, int mihinVaihdetaan) {
        Sijainti apu = keko[mikaVaihdetaan];
        Sijainti apu1 = keko[mihinVaihdetaan];

        keko[mihinVaihdetaan] = apu;
        keko[mikaVaihdetaan] = apu1;

    }

    /**
     *
     * @param x
     * @param y
     * @param uusi
     */
    public void lisaa(int x, int y, double uusi) {
        if (keko.length - 1 == keonKoko) {
            tuplaaKeko();
        }

        keko[keonKoko + 1] = new Sijainti(0, 0, 0); //Jätetään taulukon paikka 0 käyttämättä kun taulukko "alkaa" kohdasta 1

        keko[keonKoko + 1].setEtaisyys(uusi);
        keko[keonKoko + 1].setX(x);
        keko[keonKoko + 1].setY(y);

        keonKoko++;
        if (keonKoko > 1) {
            korjaaLisays();
        }

    }

    /**
     *
     */
    protected void korjaaLisays() {
        int i = keonKoko;
        while (i > 1 && keko[i].getEtaisyys() < keko[parent(i)].getEtaisyys()) {
            vaihda(i, parent(i));
            i = parent(i);
        }
    }

    /**
     *
     * @return
     */
    protected Sijainti poista() {
        if (keko.length / 4 > keonKoko && keonKoko > 25) {
            pienennaKeko();
        }
        if (keonKoko == 0) {
            return null;
        }
        if (keonKoko == 1) {
            keonKoko--;
            return keko[1];
        }
        if (keonKoko == 2) {
            vaihda(1, 2);
            keonKoko--;
            return keko[2];
        }
        Sijainti palautettavaArvo = keko[1];

        korjaaPoisto();


        return palautettavaArvo;
    }

    /**
     *
     */
    protected void korjaaPoisto() {
        Sijainti apu = keko[keonKoko];
        keko[1] = apu;
        keonKoko--;

        int i = 1;
        while (keko[i].getEtaisyys() > keko[left(i)].getEtaisyys() || keko[i].getEtaisyys() > keko[right(i)].getEtaisyys()) {
            //System.out.println("KEONKOKO= " + keonKoko + "i=" + i + " keko va=" + left(i) + " keko o=" + right(i));
            if (keko[right(i)].getEtaisyys() > keko[left(i)].getEtaisyys()) {
                vaihda(i, left(i));
                i = left(i);


            } else if (keko[right(i)].getEtaisyys() <= keko[left(i)].getEtaisyys()) {
                vaihda(i, right(i));
                i = right(i);

            }
            if (i>keonKoko/2){
       
               // System.out.println("on KEONKOKO= " + keonKoko + "i=" + i + " keko va=" + left(i) + " keko o=" + right(i));
                     break;
            }

        }
    }

    /**
     *
     * @return
     */
    protected String tulosta() {
        String apuTestiTulostusString = "";
        if (keonKoko == 0) {
            System.out.println("Keko tyhjä");
            apuTestiTulostusString = "Keko tyhjä";
        } else {
            for (int i = 1; i < keonKoko + 1; i++) {
                System.out.println("Etäisyys = " + keko[i].getEtaisyys() + " X = " + keko[i].getX() + " Y = " + keko[i].getY());
                apuTestiTulostusString = apuTestiTulostusString + " " + keko[i].getEtaisyys();
            }
            System.out.println("");
        }
        return apuTestiTulostusString;
    }
}