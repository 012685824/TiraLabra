/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.tietoRakenteet;

import EtsiReittiKuvasta.main.EtsiReitti;

/**
 * Keko luokka luo minimikeon ja sinne voidaan lisätä Sijainti olioita.
 *
 * @author Toni
 */
public class Keko {

    // luodaan tarvittavat apu muuttujat kekoa varten
    private Sijainti[] keko;
    private int keonKoko;

    /**
     * Konstruktori luo minimikeon, jonka maksimi koko on 100. Keonkoko muuttuja
     * kertoo sen hetkisen keon koon.
     */
    public Keko() {
        keko = new Sijainti[100];
        keonKoko = 0;

    }
    //getterit ja setterit

    /**
     * Palauttaa sen hetkisen keon koon
     *
     * @return keonKoko
     */
    public int getKeonKoko() {
        return keonKoko;
    }

    /**
     * Palauttaa sen hetkisen keon huipulla olevan etäisyys arvon
     *
     * @return
     */
    public double palautaKekoHuippuArvo() {
        return keko[1].getEtaisyys();
    }

    /**
     * Palauttaa kekoa varten luodun taulukon koon
     *
     * @return
     */
    public int KekoTaulukonKoko() {
        return keko.length;
    }

    /**
     * Selvittää muuttujana annetun arvon parentin arvon ja palautta sen
     *
     * @param i keon sijainti mitä selvitetään
     * @return palautta parentin sijainnin
     */
    protected int parent(int i) {
        /*   if(i<1){
         return 0;
         }*/
        return i / 2;
    }

    /**
     * Selvittää muuttujana annetun arvon vasemman lapsen arvon ja palautta sen
     *
     * @param i keon sijainti mitä selvitetään
     * @return palautta arvon vasemman lapsen sijainnin
     */
    protected int left(int i) {
        return i * 2;
    }

    /**
     * Selvittää muuttujana annetun arvon oikean lapsen arvon ja palautta sen
     *
     * @param i keon sijainti mitä selvitetään
     * @return palautta oikean lapsen sijainnin
     */
    protected int right(int i) {
        return i * 2 + 1;
    }

    /**
     * Jos keko on tyhjä palautta true muuten false
     *
     * @return booelan
     */
    public boolean emptyIs() {
        if (keonKoko == 0) {
            return true;
        }
        return false;
    }

    /**
     * Metodi tuplaaKeko tuplaa keossa käytettävän taulukon koon ja kopioi
     * vanhat tiedot siihen.
     *
     */
    protected void tuplaaKeko() {

        Sijainti[] kekoApuIso = new Sijainti[keko.length * 2];
        System.arraycopy(keko, 0, kekoApuIso, 0, keko.length);
        keko = new Sijainti[kekoApuIso.length];
        System.arraycopy(kekoApuIso, 0, keko, 0, kekoApuIso.length);

    }

    /**
     * Metodi pienennaKeko puolittaa keossa käytettävän taulukon koon ja kopioi
     * vanhat tiedot siihen.
     *
     */
    protected void pienennaKeko() {
        Sijainti[] kekoApu = new Sijainti[keko.length / 2];
        System.arraycopy(keko, 0, kekoApu, 0, kekoApu.length);
        keko = new Sijainti[kekoApu.length];
        System.arraycopy(kekoApu, 0, keko, 0, keko.length);

    }

    /**
     * Metodi vaihda vaihtaa keossa sille annettujen parametrien osittamat
     * Sijainti olioiden paikat
     *
     * @param mikaVaihdetaan kertoo paikan keossa mikä vaihdetaan
     * @param mihinVaihdetaan kertoo paikan keossa mihin vaihdetaan
     */
    protected void vaihda(int mikaVaihdetaan, int mihinVaihdetaan) {
        Sijainti apu = keko[mikaVaihdetaan];
        Sijainti apu1 = keko[mihinVaihdetaan];

        keko[mihinVaihdetaan] = apu;
        keko[mikaVaihdetaan] = apu1;

    }

    /**
     * Metodi lisaaKekoon lisää kekoon yhden uuden arvon. Ensin tarkistetaan,
     * onko keossa käytettävä taulukon koko riittävän suuri, jos ei, niin
     * kutsutaan tuplaaKeko, joka tuplaa taulukon koon. Kun taulukon koko on
     * riittävä, niin luodaan uusi Sijainti olio saaduilla arvoilla. Lisätään
     * muuttujan keonKoko arvoa yhdellä ja jos koko on suurempi kuin 1, niin
     * kutsutaan korjaaKeko metodia
     *
     * @param x x koordinaatti Sijainti oliota varten
     * @param y y koordinaatti Sijainti oliota varten
     * @param etaisyys etäisyys tieto Sijainti oliota varten
     */
    public void lisaaKekoon(int x, int y, double etaisyys) {
        if (keko.length - 1 == keonKoko) {
            tuplaaKeko();
        }

        keko[keonKoko + 1] = new Sijainti(x, y, etaisyys); //Jätetään taulukon paikka 0 käyttämättä, kun taulukko "alkaa" kohdasta 1

        keonKoko++;
        if (keonKoko > 1) {
            korjaaLisays();
        }

    }

    /**
     * Metodi korjaaLisays suorittaa lisäyksen jälkeiset tarpeelliset keon
     * korjaus toimenpiteet, jotta keko ehto tulee taas voimaan
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
     * Metodi poistaKeosta poistaa keon huipulla olevan arvon ja palauttaa sen.
     * Ensin tarkistetaan, onko keossa käytettävä taulukko "liian iso". Jos on,
     * niin kutsutaan pienennaKeko metodia. Seuraavaksi tarkistetaan, onko keko
     * tyhjä. Jos on, niin palautetaan null. Vastaavasti, jos keossa on 1,
     * palautetaan viimeinen alkio ja jos keossa on kaksi alkiota, niin ensin
     * vaihdetaan niiden paikkaa ja pienennetään kekoa, jonka jälkeen
     * palautetaan keossa toisena oleva arvo. Vaikka keon koko on nyt 1, niin
     * voidaan keon "ulko" puolelta hakea toisena ollut alkio, koska keossa
     * käytettyä taulukkoa ei alusteta toiminnon aikana. Muuten talletetaan
     * apumuuttujaan palautettavaArvo keon ensimmäisenä ollut Sijainti olio,
     * jonka jälkeen kutsutaan motodia korjaaPoisto ja palautetaan muuttujan
     * palautettavaArvo.
     *
     * @return palauttaa keossa ylimpänä olleen alkion.
     */
    public Sijainti poistaKeosta() {
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
     * Metodi korjaaPoisto korjaa keko ehdon. Ensin luodaan apu muuttuja, johon
     * talletetaan keossa viimeisenä oleva Sijainti olio. Sitten keon kokoa
     * pienennetään yhdellä ja käydään kekoa läpi niin pitkään, vaihtaen
     * Sijainti olion paikkaa joko oikean lapsen tai vasemman lapsen kanssa
     * while luupissa, että Sijainti olio on oikealla paikalla.
     *
     *
     */
    protected void korjaaPoisto() {
        Sijainti apu = keko[keonKoko];
        keko[1] = apu;
        keonKoko--;

        int i = 1;
        while (keko[i].getEtaisyys() > keko[left(i)].getEtaisyys() || keko[i].getEtaisyys() > keko[right(i)].getEtaisyys()) {
            if (keko[right(i)].getEtaisyys() > keko[left(i)].getEtaisyys()) {
                vaihda(i, left(i));
                i = left(i);
            } else if (keko[right(i)].getEtaisyys() <= keko[left(i)].getEtaisyys()) {
                vaihda(i, right(i));
                i = right(i);

            }
            if (i > keonKoko / 2) {
                break;
            }
        }
    }

    /**
     * Metodi tulosta tulostaa ja palauttaa sen hetkisen keon sisällön.
     * Pääasiallinen tarkoitus on testien tekemisen helpottaminen.
     *
     * @return keon sisällön
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