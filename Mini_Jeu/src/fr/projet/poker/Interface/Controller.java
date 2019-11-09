package fr.projet.poker.Interface;

import fr.projet.poker.Carte;
import fr.projet.poker.JoueurPoker;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Controller implements Initializable {
    private List<AnchorPane> paquetCartes = new ArrayList();
    private List<AnchorPane> paquetCartesMelangees = new ArrayList();
    private List<JoueurPoker> joueurs;
    FileInputStream table;
    @FXML
    private AnchorPane Table = new AnchorPane();
    @FXML
    private AnchorPane Carte = new AnchorPane();

    public Controller() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.paquetCartes = this.genererPaquetCarte();
        this.afficheCarteMelange(this.paquetCartes);

        try {
            this.table = new FileInputStream("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\poker\\Interface\\picture\\table_poker.png");
        } catch (Exception var5) {
            System.out.println("Oups");
        }

        Image i = new Image(this.table, 1500.0D, 800.0D, false, false);
        BackgroundImage myBI = new BackgroundImage(i, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.Table.setBackground(new Background(new BackgroundImage[]{myBI}));
    }

    public List<AnchorPane> getPaquetCartes() {
        return this.paquetCartes;
    }

    public List<AnchorPane> genererPaquetCarte() {
        String[] figure = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "As"};
        String[] couleur = new String[]{"carreau", "coeur", "trefle", "pique"};
        int x =0;
        int y=0;
        int i = 1;
        String[] var6 = couleur;
        int var7 = couleur.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            String c = var6[var8];
            String[] var10 = figure;
            int var11 = figure.length;

            for(int var12 = 0; var12 < var11; ++var12) {
                String n = var10[var12];
                Carte carte = new Carte();
                AnchorPane ca = carte.Carte(n, c, i);
                ca.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)}));
                this.paquetCartes.add(ca);
            }
        }

        return this.paquetCartes;
    }

    public List<AnchorPane> melangerPaquetCartes(List<AnchorPane> paquetCartes) {
        List<AnchorPane> paquetMelange = new ArrayList();
        int i = paquetCartes.size();

        for(Random rand = new Random(); i > 0; --i) {
            int indexAlea = rand.nextInt(i);
            paquetMelange.add((AnchorPane)paquetCartes.get(indexAlea));
            paquetCartes.remove(indexAlea);
        }

        return paquetMelange;
    }

    public void afficheCarteMelange(List<AnchorPane> paquetCartes) {
        int i = 1;
        double y = 300.0D;
        double r = -50.0D;
        double x = 0.0D;
        double cos = 0.0D;
        double sin = 0.0D;

        for(Iterator var13 = paquetCartes.iterator(); var13.hasNext(); r += 2.0D) {
            AnchorPane ap = (AnchorPane)var13.next();
            ap.setLayoutX(x);
            ap.setLayoutY(y);
            ap.setRotate(r);
            this.Carte.getChildren().add(ap);
            cos = Math.toRadians(r);
            sin = Math.toRadians(r);
            x += Math.cos(cos) * 15.0D;
            y += Math.sin(sin) * 15.0D;
            ++i;
        }

    }

    /*public void distribuerCartes(List<AnchorPane> paquetCartes) {
        List<AnchorPane> paquetCartesMelangees = new ArrayList();
        new ArrayList();
        int pdonneur = 0;
        paquetCartes = this.genererPaquetCarte();
        boolean find = false;

        int i;
        for(i = 0; i < 2; ++i) {
            List<AnchorPane> paquetCartesMelangeestmp = this.melangerPaquetCartes(paquetCartes);
            paquetCartes = this.melangerPaquetCartes(paquetCartesMelangeestmp);
        }

        paquetCartesMelangees.addAll(paquetCartes);

        for(i = 0; i < this.joueurs.size(); ++i) {
            for(int j = 0; j < ((JoueurPoker)this.joueurs.get(i)).getEtatJoueur().size(); ++j) {
                if (((String)((JoueurPoker)this.joueurs.get(i)).getEtatJoueur().get(j)).equals("donneur")) {
                    find = true;
                    break;
                }
            }

            if (find) {
                break;
            }

            ++pdonneur;
        }

        int init = pdonneur;

        for(i = 0; i < 2; ++i) {
            if (pdonneur + 1 == this.joueurs.size()) {
                pdonneur = 0;
            } else {
                ++pdonneur;
            }

            System.out.println("pdonneur: " + pdonneur);

            while(pdonneur < this.joueurs.size()) {
                ((JoueurPoker)this.joueurs.get(pdonneur)).setMainJoueur((AnchorPane)paquetCartesMelangees.get(0));
                paquetCartesMelangees.remove(0);
                System.out.println(((JoueurPoker)this.joueurs.get(pdonneur)).toString());
                if (pdonneur == init) {
                    break;
                }

                if (pdonneur == this.joueurs.size() - 1) {
                    pdonneur = 0;
                } else {
                    ++pdonneur;
                }
            }

            pdonneur = init;
        }

    }*/
}
