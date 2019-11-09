package fr.projet.poker.Interface;

import fr.projet.poker.Carte;
import fr.projet.poker.JoueurPoker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.lang.String;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class TableController implements Initializable {


    private List<AnchorPane> paquetCartes = new ArrayList<AnchorPane>();
    private List<AnchorPane> paquetCartesMelangees = new ArrayList<AnchorPane>();
    private List<JoueurPoker> joueurs;

    FileInputStream table;

    @FXML
    private AnchorPane Table = new AnchorPane();
    @FXML
    private AnchorPane Carte = new AnchorPane();
    @FXML
    private Group AllJoueur = new Group();
    @FXML
    private Group Joueur = new Group();
    @FXML
    private AnchorPane CarteJ = new AnchorPane();
    @FXML
    private Label Name = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        paquetCartes = this.genererPaquetCarte();
        //this.afficheCarteMelange(paquetCartes);
        this.distribuerCartes(paquetCartes);

    }

    public List<AnchorPane> getPaquetCartes() {
        return this.paquetCartes;
    }

    public void initTable(List<String> name) {
        try {
            table = new FileInputStream("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\poker\\Interface\\picture\\table_poker.png");
        } catch (Exception e) {
            System.out.println("Oups");
        }
        Image im = new Image(table, 1500, 800, false, false);

        BackgroundImage myBI = new BackgroundImage(im,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Table.setBackground(new Background(myBI));
        int i = 0;

        while(i < AllJoueur.getChildren().size()-1) {
            Joueur = (Group) AllJoueur.getChildren().get(i);
            Name = (Label) Joueur.getChildren().get(2);
            Name.setText(name.get(i));
            i++;
        }

    }

    public List<AnchorPane> genererPaquetCarte() {

        // tableau regroupant toutes les figures
        String figure[] = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "As"};

        // tableau regroupant les couleurs
        String couleur[] = {"carreau", "coeur", "trefle", "pique"};
        int x = 0, y = 0;
        int i = 1;
        // parcourir les 2 tableaux

        for (String c : couleur) {
            for (String n : figure) {

                Carte carte = new Carte();
                AnchorPane ca;

                ca = carte.Carte(n, c, i);
                ca.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                paquetCartes.add(ca);
            }
        }
        return paquetCartes;
    }


    public List<AnchorPane> melangerPaquetCartes(List<AnchorPane> paquetCartes)// melanger le paquet de cartes 1 fois
    {
        List<AnchorPane> paquetMelange = new ArrayList<AnchorPane>();
        int indexAlea;
        int i = paquetCartes.size();
        Random rand = new Random();

        while (i > 0) {
            indexAlea = rand.nextInt(i);
            paquetMelange.add(paquetCartes.get(indexAlea));
            paquetCartes.remove(indexAlea);
            i--;
        }

        return paquetMelange;
    }

    public void afficheCarteMelange(List<AnchorPane> paquetCartes) {
        int i = 1;
        double y = 300, r = -50, x = 0, cos = 0, sin = 0;
        for (AnchorPane ap : paquetCartes) {
            ap.setLayoutX(x);
            ap.setLayoutY(y);
            ap.setRotate(r);

            Carte.getChildren().add(ap);

            cos = Math.toRadians(r);
            sin = Math.toRadians(r);

            x += Math.cos(cos) * 15;

            y += Math.sin(sin) * 15;

            i++;
            r += (2 * (90 / 52));
        }
    }


    public void distribuerCartes(List<AnchorPane> paquetCartes){

            AnchorPane tmp = new AnchorPane();

        for (int i = 0; i < 2; i++) { // on mélange le paquet de carte
            List<AnchorPane> paquetCartesMelangeestmp = this.melangerPaquetCartes(paquetCartes);
            paquetCartes = this.melangerPaquetCartes(paquetCartesMelangeestmp);
        }

            for (int i = 0; i <2;i++) {
                for (int j = 0; j < 5; j++) {
                    Joueur = (Group) AllJoueur.getChildren().get(j);
                    CarteJ = (AnchorPane) Joueur.getChildren().get(i);

                    System.out.println(Carte.getLayoutX());
                    System.out.println(Carte.getLayoutY());

                    tmp = paquetCartes.get(0);
                    paquetCartes.remove(0);
                    tmp.setLayoutX(0);
                    tmp.setLayoutY(0);
                    tmp.setRotate(0);
                    tmp.setPrefHeight(85);
                    tmp.setPrefWidth(54);
                    CarteJ.getChildren().add(tmp);
                   

                }
            }
        }


    /*public void distribuerCartes(List<AnchorPane> paquetCartes) {
        List<AnchorPane> paquetCartesMelangees = new ArrayList<AnchorPane>();
        List<AnchorPane> paquetCartesMelangeestmp = new ArrayList<AnchorPane>();
        int pdonneur = 0; // position du donneur dans la liste
        int init; //départ du donneur pour distribution
        paquetCartes = this.genererPaquetCarte();
        boolean find = false;

        for (int i = 0; i < 2; i++) { // on mélange le paquet de carte
            paquetCartesMelangeestmp = this.melangerPaquetCartes(paquetCartes);
            paquetCartes = this.melangerPaquetCartes(paquetCartesMelangeestmp);
        }

        paquetCartesMelangees.addAll(paquetCartes);

        for (int i = 0; i < joueurs.size(); i++) { // on cherche le donneur parmis les joueurs
            for (int j = 0; j < joueurs.get(i).getEtatJoueur().size(); j++) {
                if (joueurs.get(i).getEtatJoueur().get(j).equals("donneur")) {
                    find = true;
                    break;
                }


            }
            if (find == true) {
                break;
            } else {
                pdonneur++;
            }
        }

        init = pdonneur;

        for (int tour = 0; tour < 2; tour++) { // pour les tours de distribution

            if ((pdonneur + 1) == (joueurs.size())) // si on est au dernier joueur alors le 1er joueur a recevoir les cartes sera le 1er de la liste
            {
                pdonneur = 0;
            } else {
                pdonneur += 1;
            }


            System.out.println("pdonneur: " + pdonneur);
            while (pdonneur < joueurs.size()) { // tant que l'on est pas supérieur au nombre de joueur

                joueurs.get(pdonneur).setMainJoueur(paquetCartesMelangees.get(0));
                paquetCartesMelangees.remove(0);
                System.out.println(joueurs.get(pdonneur).toString());

                if (pdonneur == init) // si on arrive sur le donneur alors on quitte le 1er et on entame le 2e tour
                {
                    break;
                } else if (pdonneur == (joueurs.size() - 1)) // si on arrive a la fin de la liste on retourne au 1er joueur
                {
                    pdonneur = 0;
                } else {
                    pdonneur++;
                }
            }

            pdonneur = init; // on redonne le donneur pour rédémarrer
        }
    }*/

}


