/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EtsiReittiKuvasta.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;

/**
 *
 * @author Toni
 */
public class EtsiReittiUI extends javax.swing.JFrame {

    /**
     * Creates new form LuolaPakoUI
     */
    static boolean piste = true;// Apu muuttuja kun koordinaatteja valitaan.
    static String tiedostojenSijainti = "src";
    static int xAlkuPiste = 1, yAlkuPiste = 1, xLoppuPiste = 1, yLoppuPiste = 1, valinta = 0;

    public EtsiReittiUI() throws FileNotFoundException, IOException {
        initComponents();
        jFileChooser1.setVisible(false);//Piillotetaan tiedosto valitsin
        frame.setVisible(false);//Piillotetaan virhe ikkuna
        valintaListanTiedot();//Päivitetään tiedostojen valinta valinta lista 
        kuvanAlkuPiste.setText("" + 1);
        kuvanLoppuPiste.setText("" + 1);
        reitinPituus.setText("" + 0);
        kulunutAika.setText("" + 0);
    }

    private void valintaListanTiedot() throws FileNotFoundException, IOException {
        //Kuvien valinta listan tiedot ovat talletettu tiedostoon mistä me pitää ensin hakea
        FileInputStream asetuksetTiedosto = new FileInputStream(tiedostojenSijainti + "/asetukset.txt");
        //Tehdään uusi Scanner että saadaan luettua tiedosto rivi kerrallaan.
        final Scanner tiedotTiedostosta = new Scanner(asetuksetTiedosto, "UTF-8");
        //Luodaan ArrayList koska ei tiedetä valinta listan kokoa
        ArrayList<String> kuvaListaAlustus = new ArrayList<String>();
        //Luetaan tiedosto listaan rivi kerrallaan.
        while (tiedotTiedostosta.hasNextLine()) {
            kuvaListaAlustus.add(tiedotTiedostosta.nextLine());
        }
        //Nyt luodaan String taulukko kun tiedetään montako kuvaa listalla on.
        final String[] kuvalistaValmis = new String[kuvaListaAlustus.size()];
        for (int i = 0; i < kuvalistaValmis.length; i++) {
            kuvalistaValmis[i] = kuvaListaAlustus.get(i);
        }
        asetuksetTiedosto.close();// Suljetaan avattu tiedosto
        //Nyt voidaan lisätä "luoda uudelleen kuvien valinta lista.
        kuvaLista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = kuvalistaValmis;

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        kuvaLista.setSelectedIndex(0);// Valitaan valmiiksi listan ensimmäinen kuva.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valintaRuudut = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        kuvaLista = new javax.swing.JList();
        Ratkaise = new javax.swing.JButton();
        kuvaKentta = new javax.swing.JLabel();
        lisaaUusiKuva = new javax.swing.JButton();
        dijkstra = new javax.swing.JCheckBox();
        bellmanFord = new javax.swing.JCheckBox();
        Dijkstra8 = new javax.swing.JCheckBox();
        kuvanAlkuPiste = new javax.swing.JTextField();
        kuvanLoppuPiste = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        ratkaisuKentta = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        poistaKuva = new javax.swing.JButton();
        jFileChooser1 = new javax.swing.JFileChooser();
        frame = new javax.swing.JInternalFrame();
        kulunutAika = new javax.swing.JTextField();
        reitinPituus = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        Astar = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(50, 50));
        setPreferredSize(new java.awt.Dimension(1000, 850));
        setResizable(false);

        kuvaLista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Kuva 1", "Kuva 2", "Kuva 3" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        kuvaLista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        kuvaLista.setToolTipText("");
        kuvaLista.setSelectedIndex(0);
        kuvaLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kuvaListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(kuvaLista);

        Ratkaise.setText("Ratkaise");
        Ratkaise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RatkaiseMouseClicked(evt);
            }
        });

        kuvaKentta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Kuvat/kuva0.jpg"))); // NOI18N
        kuvaKentta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        kuvaKentta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kuvaKenttaMouseClicked(evt);
            }
        });

        lisaaUusiKuva.setText("Lisää uusi kuva");
        lisaaUusiKuva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lisaaUusiKuvaMouseClicked(evt);
            }
        });

        valintaRuudut.add(dijkstra);
        dijkstra.setMnemonic('0');
        dijkstra.setSelected(true);
        dijkstra.setText("Dijkstra");

        valintaRuudut.add(bellmanFord);
        bellmanFord.setMnemonic('1');
        bellmanFord.setText("Bellman Ford");

        valintaRuudut.add(Dijkstra8);
        Dijkstra8.setMnemonic('2');
        Dijkstra8.setText("Dijkstra8");

        jTextField1.setEditable(false);
        jTextField1.setText(" Alku piste ");
        jTextField1.setBorder(null);

        ratkaisuKentta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Kuvat/kuva0.jpg"))); // NOI18N
        ratkaisuKentta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ratkaisuKentta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ratkaisuKenttaMouseClicked(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setText(" Loppu piste ");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        poistaKuva.setText("Poista kuva");
        poistaKuva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                poistaKuvaMouseClicked(evt);
            }
        });

        jFileChooser1.setCurrentDirectory(new java.io.File("C:\\Koulu\\2013\\Tiralabra\\Kuvat"));
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        frame.setVisible(true);

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextField3.setEditable(false);
        jTextField3.setText(" Reitin pituus ");
        jTextField3.setBorder(null);

        jTextField4.setEditable(false);
        jTextField4.setText(" Aikaa kului ms ");
        jTextField4.setBorder(null);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        valintaRuudut.add(Astar);
        Astar.setMnemonic('3');
        Astar.setText("Astar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bellmanFord)
                            .addComponent(Dijkstra8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(poistaKuva))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(402, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lisaaUusiKuva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)))
                        .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dijkstra)
                        .addGap(30, 30, 30)
                        .addComponent(Astar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(127, 127, 127))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kuvaKentta)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kuvanAlkuPiste, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kuvanLoppuPiste, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ratkaisuKentta)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(reitinPituus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kulunutAika, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(Ratkaise))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(poistaKuva)
                                .addGap(31, 31, 31)
                                .addComponent(lisaaUusiKuva))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dijkstra, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Astar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bellmanFord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Dijkstra8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ratkaise)
                        .addGap(44, 44, 44)
                        .addComponent(frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 295, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kuvaKentta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kuvanAlkuPiste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kuvanLoppuPiste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ratkaisuKentta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reitinPituus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kulunutAika, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kuvaListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kuvaListaMouseClicked

        // TODO add your handling code here:
        //Kun listasta valitaan joku kuva niin päivitetään kuva ja ratkaisu kenttä.
        try {

            kuvaKentta.setIcon(new ImageIcon(ImageIO.read(new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".jpg"))));
            ratkaisuKentta.setIcon(new ImageIcon(ImageIO.read(new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".jpg"))));

        } catch (IOException ex) {
            Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        kuvanAlkuPiste.setText("" + 0);
        kuvanLoppuPiste.setText("" + 0);
        reitinPituus.setText("" + 0);
        kulunutAika.setText("" + 0);

    }//GEN-LAST:event_kuvaListaMouseClicked

    private void RatkaiseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RatkaiseMouseClicked
        // TODO add your handling code here:
        //Kun ratkaisu painiketta painetaan niin ensin aloitetaan ajan otto ja haetaan valintaruudusta tiedo mitä algoritmiä käytetään.
        long startTime = System.currentTimeMillis();
        valinta = valintaRuudut.getSelection().getMnemonic() - 48;
        //Kutsutaan ratkaisijaa ja kutsutaan muutaRatkaisuKuvaBmpToJpg koska ikoneina ei voi olla bmp kuvia
        EtsiReitti.ratkaise(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".bmp", xAlkuPiste, yAlkuPiste, xLoppuPiste, yLoppuPiste, valinta);
        muutaRatkaisuKuvaBmpToJpg();

        String tiedostonNimi = "/Kuvat/ratkaisu.jpg";
        //päivitetään ratkaisuKenttä uudella ratkaisulla
        try {
            ratkaisuKentta.setIcon(new ImageIcon(ImageIO.read(new File(tiedostojenSijainti + tiedostonNimi))));
        } catch (IOException ex) {
            Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Lopetetaan ajan otto ja is't''n tiedot ohjelmaan samalla isätään myös reitin pituus.
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        kulunutAika.setText(totalTime + "ms");
        DecimalFormat df = new DecimalFormat("#.##");
        reitinPituus.setText(df.format(EtsiReitti.reitinPituus) + "");

    }//GEN-LAST:event_RatkaiseMouseClicked
    private void muutaRatkaisuKuvaBmpToJpg() {
        BufferedImage muunnettavaKuva = null; // Luodaan uusi BufferedImage kuvien käsittelyä varten
        File ratkaisuTiedosto = new File(tiedostojenSijainti + "/Kuvat/ratkaisu.jpg");
        try {
            muunnettavaKuva = ImageIO.read(new File(tiedostojenSijainti + "/Kuvat/ratkaisu.bmp")); // Ladataan käsiteltävä kuva luolaKuva muuttujaan
            ImageIO.write(muunnettavaKuva, "jpg", ratkaisuTiedosto);
        } catch (IOException e) {
            System.out.println(e); // Tulostetaan virhe jos sellainen tulee
        }

    }
    private void lisaaUusiKuvaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lisaaUusiKuvaMouseClicked
        // TODO add your handling code here:
        jFileChooser1.setVisible(true);
    }//GEN-LAST:event_lisaaUusiKuvaMouseClicked

    private void kuvaKenttaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kuvaKenttaMouseClicked
        //kun kuvakenttää painetaan niin ensin valitaan alku piste ja toisella painalluksella loppu piste
        //sen takia piste muuttuja joka muuttuu joka painalluksessa.

        if (piste) {
            kuvanAlkuPiste.setText(evt.getPoint().toString().substring(14));
            xAlkuPiste = evt.getX();
            yAlkuPiste = evt.getY();
            piste = false;
        } else {
            kuvanLoppuPiste.setText(evt.getPoint().toString().substring(14));
            xLoppuPiste = evt.getX();
            yLoppuPiste = evt.getY();
            piste = true;
        }



        // TODO add your handling code here:
    }//GEN-LAST:event_kuvaKenttaMouseClicked

    private void ratkaisuKenttaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratkaisuKenttaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ratkaisuKenttaMouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void poistaKuvaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_poistaKuvaMouseClicked
        // TODO add your handling code here:
        //Kun painat poista kuva niin ohjelma ei varmistele poistoa vaan poistaa valitun kuvan ja 
        //siirtää viimeisen kuvan poistetun tilalle.
        BufferedImage uusiKuva = null;
        if (kuvaLista.getAnchorSelectionIndex() == kuvaLista.getLastVisibleIndex()) {
            File poistettavaKuvaJpg = new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".jpg");
            poistettavaKuvaJpg.delete();
            File poistettavaKuvaBmp = new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".bmp");
            poistettavaKuvaBmp.delete();
            FileWriter asetuksetTiedosto = null;
            try {
                asetuksetTiedosto = new FileWriter(tiedostojenSijainti + "/asetukset.txt");
            } catch (IOException ex) {
                Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedWriter asennusTiedostonPaivitys = new BufferedWriter(asetuksetTiedosto);
            for (int i = 0; i < kuvaLista.getLastVisibleIndex(); i++) {
                try {
                    asennusTiedostonPaivitys.write(kuvaLista.getModel().getElementAt(i).toString() + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                asennusTiedostonPaivitys.close();
            } catch (IOException ex) {
                Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                valintaListanTiedot();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            File poistettavaKuvaJpg = new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".jpg");
            poistettavaKuvaJpg.delete();
            File poistettavaKuvaBmp = new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".bmp");
            poistettavaKuvaBmp.delete();
            File siirrettavaKuvaJpg = new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getLastVisibleIndex() + ".jpg");
            siirrettavaKuvaJpg.delete();
            File siirrettavaKuvaBmp = new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getLastVisibleIndex() + ".bmp");

            //Luodaan uudet kuvat poistetun kuvan tilalle viimeisestä listan kuvasta
            try {
                uusiKuva = ImageIO.read(siirrettavaKuvaBmp);
                ImageIO.write(uusiKuva, "jpg", poistettavaKuvaJpg);
                ImageIO.write(uusiKuva, "bmp", poistettavaKuvaBmp);

                //päivitetään kuvien valinta lista
                FileWriter asetuksetTiedosto = new FileWriter(tiedostojenSijainti + "/asetukset.txt");
                BufferedWriter asennusTiedostonPaivitys = new BufferedWriter(asetuksetTiedosto);
                for (int i = 0; i < kuvaLista.getLastVisibleIndex(); i++) {
                    asennusTiedostonPaivitys.write(kuvaLista.getModel().getElementAt(i).toString() + "\n");
                }
                asennusTiedostonPaivitys.close();
                valintaListanTiedot();
            } catch (IOException ex) {
                Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            siirrettavaKuvaBmp.delete();//poistetaan listan viileisenä ollut kuva
        }
        try {
            //System.out.println(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".jpg");
            ratkaisuKentta.setIcon(new ImageIcon(ImageIO.read(new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".jpg"))));
            kuvaKentta.setIcon(new ImageIcon(ImageIO.read(new File(tiedostojenSijainti + "/Kuvat/kuva" + kuvaLista.getAnchorSelectionIndex() + ".jpg"))));
            //ratkaisuKentta.setIcon(ii);
        } catch (IOException ex) {
            Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        kuvanAlkuPiste.setText("" + 0);
        kuvanLoppuPiste.setText("" + 0);
        reitinPituus.setText("" + 0);
        kulunutAika.setText("" + 0);
    }//GEN-LAST:event_poistaKuvaMouseClicked

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
        //Palautetaan tiedosto valinta ikkuna näkyväksi
        jFileChooser1.setVisible(false);
        if (evt.getActionCommand().equalsIgnoreCase("ApproveSelection")) {
            //System.out.println(evt.getActionCommand());
            File uusiTiedosto = jFileChooser1.getSelectedFile();

            //System.out.println(uusiTiedosto.getName().substring(uusiTiedosto.getName().length() - 3, uusiTiedosto.getName().length()));
            //Tarkastetaan on valittu tiedosto oikeaa muotoa..
            if (uusiTiedosto.getName().substring(uusiTiedosto.getName().length() - 3, uusiTiedosto.getName().length()).contains("jpg")
                    || uusiTiedosto.getName().substring(uusiTiedosto.getName().length() - 3, uusiTiedosto.getName().length()).contains("bmp")) {
                System.out.println(uusiTiedosto.getAbsolutePath());
                lisaaUusiTiedosto(uusiTiedosto);
            } else {
                //Jos kuva ei ollut valittua muotoa annetaan siintä virhe ilmoitus
                JOptionPane.showMessageDialog(frame, "Et valinnut tiedostoa .jpg tai .bmp");
            }
        }
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed
    private void lisaaUusiTiedosto(File uusiTiedosto) {
        BufferedImage uusiKuva = null;

        System.out.println(kuvaLista.getModel().getSize() - 1);
        //Jos valittu tiedosto oli oikeaa muotoa luodaan siintä .bmp ja .jpg tiedostot .bmp ratkaisemista varten 
        //.jpg tiedoston näyttämistä varten ja muutetaan kuvien koko 450x450 jotta kuvat asettuu ohjelmaan hyvin.
        //Muutetaan myös kuvan nimi kuva....
        File uusiKuvaTiedostoJpg = new File(tiedostojenSijainti + "/Kuvat/kuva" + ((kuvaLista.getModel().getSize() - 1) + 1) + ".jpg");
        File uusiKuvaTiedostoBmp = new File(tiedostojenSijainti + "/Kuvat/kuva" + ((kuvaLista.getModel().getSize() - 1) + 1) + ".bmp");
        try {
            uusiKuva = ImageIO.read(uusiTiedosto); // Ladataan käsiteltävä kuva uusiKuva muuttujaan
            BufferedImage uusiKokoKuvaan = new BufferedImage(450, 450, 1);
            Graphics2D g = uusiKokoKuvaan.createGraphics();
            g.drawImage(uusiKuva, 0, 0, 450, 450, null);
            g.dispose();
            uusiKuva = uusiKokoKuvaan;
            ImageIO.write(uusiKuva, "jpg", uusiKuvaTiedostoJpg);
            ImageIO.write(uusiKuva, "bmp", uusiKuvaTiedostoBmp);

        } catch (IOException e) {
            System.out.println(e); // Tulostetaan virhe jos sellainen tulee
        }
        try {
            // Päivitetään valinta listan tiedot uudella kuvalla
            FileWriter asetuksetTiedosto = new FileWriter(tiedostojenSijainti + "/asetukset.txt");
            BufferedWriter asennusTiedostonPaivitys = new BufferedWriter(asetuksetTiedosto);
            for (int i = 0; i <= (kuvaLista.getModel().getSize() - 1); i++) {
                asennusTiedostonPaivitys.write(kuvaLista.getModel().getElementAt(i).toString() + "\n");
            }
            asennusTiedostonPaivitys.write("Kuva" + ((kuvaLista.getModel().getSize() - 1) + 2));
            asennusTiedostonPaivitys.close();
            valintaListanTiedot();
        } catch (IOException ex) {
            Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        kuvanAlkuPiste.setText("" + 0);
        kuvanLoppuPiste.setText("" + 0);
        reitinPituus.setText("" + 0);
        kulunutAika.setText("" + 0);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EtsiReittiUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EtsiReittiUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EtsiReittiUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EtsiReittiUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new EtsiReittiUI().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EtsiReittiUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Astar;
    public javax.swing.JCheckBox Dijkstra8;
    private javax.swing.JButton Ratkaise;
    public javax.swing.JCheckBox bellmanFord;
    public javax.swing.JCheckBox dijkstra;
    private javax.swing.JInternalFrame frame;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField kulunutAika;
    private javax.swing.JLabel kuvaKentta;
    private javax.swing.JList kuvaLista;
    private javax.swing.JTextField kuvanAlkuPiste;
    private javax.swing.JTextField kuvanLoppuPiste;
    private javax.swing.JButton lisaaUusiKuva;
    private javax.swing.JButton poistaKuva;
    private javax.swing.JLabel ratkaisuKentta;
    private javax.swing.JTextField reitinPituus;
    public javax.swing.ButtonGroup valintaRuudut;
    // End of variables declaration//GEN-END:variables
}
