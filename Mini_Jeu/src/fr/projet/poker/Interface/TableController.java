package fr.projet.poker.Interface;

import fr.projet.poker.Carte;
import fr.projet.poker.JoueurPoker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableController extends Thread {


    private List<AnchorPane> paquetCartes = new ArrayList<AnchorPane>();
    private List<AnchorPane> paquetCartesMelangees = new ArrayList<AnchorPane>();
    private List<JoueurPoker> joueurs;

    FileInputStream table;

    @FXML
    private Group root;
    @FXML
    private AnchorPane Fenetre;
    @FXML
    private AnchorPane Table;
    @FXML
    private Group AllJoueur;
    @FXML
    private AnchorPane CarteJ;
    @FXML
    private AnchorPane Carte;
    @FXML
    private Group Joueur;
    @FXML
    private Group Plateau;
    @FXML
    private Label Name;
    @FXML
    private Button afficher;
    @FXML
    private Button distribuer;

    @FXML
    private Label Role;


    public void initTable(Stage primaryStage, List<String> name, String nameDonneur) {
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("Table.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            table = new FileInputStream("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\poker\\Interface\\picture\\table_poker.png");
        } catch (Exception e) {
            System.out.println("Oups");
        }

        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

        Fenetre = (AnchorPane) root.getChildren().get(0);
        Table = (AnchorPane) Fenetre.getChildren().get(0);
        AllJoueur = (Group) Table.getChildren().get(0);
        Plateau = (Group) AllJoueur.getChildren().get(5);
        Carte = (AnchorPane) Plateau.getChildren().get(0);
        afficher = (Button) Plateau.getChildren().get(1);
        distribuer = (Button) Plateau.getChildren().get(2);

        Fenetre.setPrefWidth(primaryStage.getWidth());
        Fenetre.setPrefHeight(primaryStage.getHeight());
        distribuer.setVisible(false);

        double w, h;

        w = primaryStage.getWidth();
        h = primaryStage.getHeight();

        Table.setLayoutX(200);
        Table.setLayoutY(100);
        Image im = new Image(table, w - (w * 0.20), h - (h * 0.20), false, false);
        BackgroundImage myBI = new BackgroundImage(im,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Table.setBackground(new Background(myBI));
        System.out.println("balbalbal " + name.toString());
        this.setNamePlayer(root, name);
        this.setDonneur(root, nameDonneur);
        paquetCartes = this.genererPaquetCarte();
        afficher.setText("Afficher paquet cartes");
        afficher.setVisible(true);

        afficher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                this.afficheCarte(root, paquetCartes);

                Fenetre = (AnchorPane) root.getChildren().get(0);
                Table = (AnchorPane) Fenetre.getChildren().get(0);
                AllJoueur = (Group) Table.getChildren().get(0);
                Plateau = (Group) AllJoueur.getChildren().get(5);
            }

            public void afficheCarte(Group root, List<AnchorPane> paquetCartes) {
                int i = 1;
                double y = 300, r = -50, x = 0, cos = 0, sin = 0;
                Fenetre = (AnchorPane) root.getChildren().get(0);
                Table = (AnchorPane) Fenetre.getChildren().get(0);
                AllJoueur = (Group) Table.getChildren().get(0);
                Plateau = (Group) AllJoueur.getChildren().get(5);
                Carte = (AnchorPane) Plateau.getChildren().get(0);
                afficher = (Button) Plateau.getChildren().get(1);

                afficher.setVisible(false);

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

                if (i == 53) {
                    distribuer.setVisible(true);
                    distribuer.setText("Distribuer");
                    afficher.setVisible(false);
                }
            }
        });

        distribuer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Plateau.getChildren().remove(0);
                this.distribuerCartes(root, paquetCartes);

                Fenetre = (AnchorPane) root.getChildren().get(0);
                Table = (AnchorPane) Fenetre.getChildren().get(0);
                AllJoueur = (Group) Table.getChildren().get(0);
                Plateau = (Group) AllJoueur.getChildren().get(5);
            }

            public void distribuerCartes(Group root, List<AnchorPane> paquetCartes) {

                AnchorPane tmp = new AnchorPane();
                int i, pdonneur = 0, init;
                Label role;

                for (i = 0; i < 2; i++) { // on mélange le paquet de carte
                    List<AnchorPane> paquetCartesMelangeestmp = this.melangerPaquetCartes(paquetCartes);
                    paquetCartes = this.melangerPaquetCartes(paquetCartesMelangeestmp);
                }
                Fenetre = (AnchorPane) root.getChildren().get(0);
                Table = (AnchorPane) Fenetre.getChildren().get(0);
                AllJoueur = (Group) Table.getChildren().get(0);
                Plateau = (Group) AllJoueur.getChildren().get(5);

                for (i = 0; i < AllJoueur.getChildren().size() - 1; i++) {
                    Joueur = (Group) AllJoueur.getChildren().get(i);
                    role = (Label) Joueur.getChildren().get(3);
                    if (role.getText().compareTo("D") == 0) {

                        break;
                    } else {
                        pdonneur++;
                    }
                }

                init = pdonneur;

                for (i = 0; i < 2; i++) {

                    if ((pdonneur + 1) == AllJoueur.getChildren().size() - 1) {
                        pdonneur = 0;
                    } else {
                        pdonneur++;
                    }

                    while (pdonneur < AllJoueur.getChildren().size() - 1)
                    {

                        Joueur = (Group) AllJoueur.getChildren().get(pdonneur);
                        CarteJ = (AnchorPane) Joueur.getChildren().get(i);

                        System.out.println("while role ="+pdonneur);

                        tmp = paquetCartes.get(0);
                        paquetCartes.remove(0);
                        tmp.setLayoutX(0);
                        tmp.setLayoutY(0);
                        tmp.setRotate(0);
                        tmp.setPrefHeight(85);
                        tmp.setPrefWidth(54);
                        CarteJ.getChildren().add(tmp);

                        if (pdonneur == init) // si on arrive sur le donneur alors on quitte le 1er et on entame le 2e tour
                        {
                            break;
                        } else if (pdonneur == (AllJoueur.getChildren().size() - 2)) // si on arrive a la fin de la liste on retourne au 1er joueur
                        {
                            pdonneur = 0;
                        } else {
                            pdonneur++;
                        }
                    }
                    pdonneur = init;
                }
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


        });

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void setNamePlayer(Group root, List<String> name) {
        int i = 0;
        Fenetre = (AnchorPane) root.getChildren().get(0);
        Table = (AnchorPane) Fenetre.getChildren().get(0);
        AllJoueur = (Group) Table.getChildren().get(0);

        while (i < AllJoueur.getChildren().size() - 1) {
            Joueur = (Group) AllJoueur.getChildren().get(i);
            Name = (Label) Joueur.getChildren().get(2);
            Name.setText(name.get(i));
            i++;
        }
    }

    public void afficherPopup(Group root) {
        Stage primaryStage = new Stage();

        Group root1 = new Group();
        try {
            root1 = FXMLLoader.load(this.getClass().getResource("PopupDonneur.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root1, 400, 200));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        this.setDonneur(root, "Michel");
    }

    public void setDonneur(Group root, String name) {
        int i = 0;
        Fenetre = (AnchorPane) root.getChildren().get(0);
        Table = (AnchorPane) Fenetre.getChildren().get(0);
        AllJoueur = (Group) Table.getChildren().get(0);


        while (i < AllJoueur.getChildren().size() - 1) {
            Joueur = (Group) AllJoueur.getChildren().get(i);
            Name = (Label) Joueur.getChildren().get(2);
            System.out.println("Name : " + Name.getText());
            if (Name.getText().compareTo(name) == 0) {
                Role = (Label) Joueur.getChildren().get(3);
                Role.setText("D");
                break;
            } else {
                i++;
            }
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


}


