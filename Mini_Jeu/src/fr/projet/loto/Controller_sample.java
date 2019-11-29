package fr.projet.loto;

import fr.projet.Joueur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Controller_sample {
    private List<Integer> Lint = new ArrayList<Integer>();
    private List<Integer> Lboulesortie = new ArrayList<Integer>();
    private int NbJoueurs;
    private List<GrilleLoto> LG = new ArrayList<GrilleLoto>();
    private GrilleLoto G;
    private List<JoueurLoto> LJ = new ArrayList<JoueurLoto>();
    private List<Lots> Lcadeau = new ArrayList<Lots>() ;
    private PartieLoto P = new PartieLoto();
    private double height = Screen.getPrimary().getVisualBounds().getHeight();
    private double width = Screen.getPrimary().getVisualBounds().getWidth();
    private GrilleLoto g_1;
    private GrilleLoto g_2;
    private GrilleLoto g_3;
    private int max=90;
    private EventHandler<? super MouseEvent> handler;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private TextField nbfavsolo;
    @FXML
    private TextField nbcartonssolo;
    @FXML
    private TextField nomsolo;
    @FXML
    private TextField nbfavj1;
    @FXML
    private TextField nbcartonsj1;
    @FXML
    private TextField nomj1;
    @FXML
    private TextField nbfavj2;
    @FXML
    private TextField nbcartonsj2;
    @FXML
    private TextField nomj2;
    @FXML
    private Label nomjoueur;
    @FXML
    private Label voicivos;
    @FXML
    private AnchorPane A2;
    @FXML
    private AnchorPane A3;
    @FXML
    private AnchorPane A4;
    @FXML
    private AnchorPane unegrillesolo;
    @FXML
    private AnchorPane deuxgrillesolo;
    @FXML
    private AnchorPane troisgrillesolo;
    @FXML
    private AnchorPane A6;
    @FXML
    private Label Joueur1;
    @FXML
    private Label Joueur2;
    @FXML
    private Label pseu;
    @FXML
    private Label nbf;
    @FXML
    private Label nbc;
    @FXML
    private GridPane G1;
    @FXML
    private GridPane G2;
    @FXML
    private GridPane G3;
    @FXML
    private Label nombre;
    @FXML
    private Label verif;

    @FXML
    private void Button1(ActionEvent event) { // page après avoir sélectionné solo
        Stage primaryStage = (Stage) b1.getScene().getWindow();
        Group root = new Group();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("solo_depart.fxml"));
            A2 = (AnchorPane) root.getChildren().get(0);
            A2.setPrefHeight(height);
            A2.setPrefWidth(width);
            nomsolo = (TextField) A2.getChildren().get(0);
            nomsolo.setLayoutX(width / 2);
            nbfavsolo = (TextField) A2.getChildren().get(1);
            nbfavsolo.setLayoutX(width / 2);
            nbcartonssolo = (TextField) A2.getChildren().get(2);
            nbcartonssolo.setLayoutX(width / 2);
            pseu = (Label) A2.getChildren().get(3);
            pseu.setLayoutX(width / 2 - 220);
            nbf = (Label) A2.getChildren().get(4);
            nbf.setLayoutX(width / 2 - 220);
            nbc = (Label) A2.getChildren().get(5);
            nbc.setLayoutX(width / 2 - 220);
            b4 = (Button) A2.getChildren().get(6);
            b4.setLayoutX(width / 2 + 148.8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void Button4(ActionEvent event) { //affichage des grilles du joueur solo
        Stage primaryStage = (Stage) b4.getScene().getWindow();
        Group root = new Group();
        int va=0,grillepleine=0,t1=0,t2=0,t3=0,cadeau1=0,cadeau2=0,cadeau3=0;
        JoueurLoto JL = new JoueurLoto(convertirint(getNbfavsolo().getText()), convertirint(getNbcartonssolo().getText()));
        JL.creation_joueur(getNomsolo().getText());
        LJ.add(JL);
        CreationGrille();
        ordinateur();
        distribution();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("affiche_grille.fxml"));
            verif = (Label) root.getChildren().get(5);
            if (convertirint(getNbcartonssolo().getText()) == 1) {
                unegrillesolo = (AnchorPane) root.getChildren().get(0);
                unegrillesolo.setPrefHeight(height);
                unegrillesolo.setPrefWidth(width);
                nomjoueur = (Label) unegrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(getNomsolo().getText());
                G1 = (GridPane) unegrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                g_1=LJ.get(0).getLGJ().get(0);
                if (t1==0){
                    verif.setText("Avez-vous une ligne remplie ?");
                }
                else if (t1!=0 && t2==0){
                    verif.setText("Avez-vous 2 lignes remplies ?");
                }
                else if (t1!=0 && t2!=0 && t3==0){
                    verif.setText("Avez-vous un carton plein ?");
                }
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                Pane P= (Pane) G1.getChildren().get(va);
                Label L= (Label) P.getChildren().get(0);
                L.setText(g_1.getG().getT()[i][j].getVal());
                P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (L.getTextFill()==Color.BLACK) {
                            P.setStyle("-fx-background-color: #000000;");
                            L.setTextFill(Color.WHITE);
                       }
                        else {
                            P.setStyle("-fx-background-color: #FDFFF1;");
                            L.setTextFill(Color.BLACK);
                        }
                    }
                });
                        va++;}
                    else {
                            Pane P= (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                    va++;}
                    }}
                root.getChildren().add(G1);
            } else if (convertirint(getNbcartonssolo().getText()) == 2) {
                deuxgrillesolo = (AnchorPane) root.getChildren().get(1);
                deuxgrillesolo.setPrefHeight(height);
                deuxgrillesolo.setPrefWidth(width);
                nomjoueur = (Label) deuxgrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(getNomsolo().getText());
                G1 = (GridPane) deuxgrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                G2 = (GridPane) deuxgrillesolo.getChildren().get(3);
                G2.setGridLinesVisible(true);
                G2.setLayoutX(height - 42);
                g_1=LJ.get(0).getLGJ().get(0);
                g_2=LJ.get(0).getLGJ().get(1);
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G1.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;}
                        else {
                            Pane P= (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;}
                    }}
                va=0;
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_2.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G2.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_2.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;}
                        else {
                            Pane P= (Pane) G2.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;}
                    }}
                root.getChildren().add(G1);
            } else {
                troisgrillesolo = (AnchorPane) root.getChildren().get(2);
                troisgrillesolo.setPrefHeight(height);
                troisgrillesolo.setPrefWidth(width);
                nomjoueur = (Label) troisgrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(getNomsolo().getText());
                G1 = (GridPane) troisgrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                G2 = (GridPane) troisgrillesolo.getChildren().get(3);
                G2.setGridLinesVisible(true);
                G2.setLayoutX(height - 42);
                G3 = (GridPane) troisgrillesolo.getChildren().get(4);
                G3.setGridLinesVisible(true);
                G3.setLayoutX(height / 2);
                G3.setLayoutY(350);
                g_1=LJ.get(0).getLGJ().get(0);
                g_2=LJ.get(0).getLGJ().get(1);
                g_3=LJ.get(0).getLGJ().get(2);
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G1.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;}
                        else {
                            Pane P= (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;}
                    }}
                va=0;
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_2.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G2.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_2.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;}
                        else {
                            Pane P= (Pane) G2.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;}
                    }}
                va=0;
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_3.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G3.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_3.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;}
                        else {
                            Pane P= (Pane) G3.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;}
                    }}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    public void GAME(ActionEvent event){
        Creationbouleloto();
        //nombre= (Label) root.getChildren().get(4);
        //b5.setOnAction(actionEvent -> nombre.setText(String.valueOf(index)));
        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Random rand = new Random();
                int index = rand.nextInt(getMax());
                int temp=Lint.get(index);
                nombre.setText(String.valueOf(temp));
                Lint.remove(index);
                setMax();
                Lboulesortie.add(temp);
                b5.setText("Nombre suivant");
            }
        });
    }

    @FXML
    private void Button2(ActionEvent event) { //page après avoir appuyé sur multi
        Stage primaryStage = (Stage) b2.getScene().getWindow();
        Group root = new Group();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("multi_depart.fxml"));
            A3 = (AnchorPane) root.getChildren().get(0);
            A3.setPrefHeight(height);
            A3.setPrefWidth(width);
            Group j1= new Group();
            j1 = (Group) A3.getChildren().get(0);
            Group j2= new Group();
            j2 = (Group) A3.getChildren().get(1);
            nomj1 = (TextField) j1.getChildren().get(0);
            nomj1.setLayoutX(width / 4);
            nbfavj1 = (TextField) j1.getChildren().get(1);
            nbfavj1.setLayoutX(width / 4);
            nbcartonsj1 = (TextField) j1.getChildren().get(2);
            nbcartonsj1.setLayoutX(width / 4);
            pseu = (Label) j1.getChildren().get(3);
            pseu.setLayoutX(width / 4 - 220);
            nbf = (Label) j1.getChildren().get(4);
            nbf.setLayoutX(width / 4 - 220);
            nbc = (Label) j1.getChildren().get(5);
            nbc.setLayoutX(width / 4 - 220);
            Joueur1 = (Label) j1.getChildren().get(6);
            Joueur1.setLayoutX(width / 4 + 34);
            nomj2 = (TextField) j2.getChildren().get(0);
            nomj2.setLayoutX(width / 4 + 200);
            nbfavj2 = (TextField) j2.getChildren().get(1);
            nbfavj2.setLayoutX(width / 4 + 200);
            nbcartonsj2 = (TextField) j2.getChildren().get(2);
            nbcartonsj2.setLayoutX(width / 4 + 200);
            Joueur2 = (Label) j2.getChildren().get(3);
            Joueur2.setLayoutX(width / 4 + 234);
            b3 = (Button) A3.getChildren().get(2);
            b3.setLayoutX(width / 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void Button3(ActionEvent event) { // affichage info premier joueur
        Stage primaryStage = (Stage) b3.getScene().getWindow();
        Group root = new Group();
        int va=0;
        JoueurLoto JL1 = new JoueurLoto(convertirint(getNbfavj1().getText()), convertirint(getNbcartonsj1().getText()));
        JL1.creation_joueur(getNomj1().getText());
        LJ.add(JL1);
        JoueurLoto JL2 = new JoueurLoto(convertirint(getNbfavj2().getText()), convertirint(getNbcartonsj2().getText()));
        JL2.creation_joueur(getNomj2().getText());
        LJ.add(JL2);
        debut_game();
        distribution();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("affiche_grille.fxml"));
            if ((LJ.get(0).getNombredecartonsvoulus()) == 1) {
                unegrillesolo = (AnchorPane) root.getChildren().get(0);
                unegrillesolo.setPrefHeight(height);
                unegrillesolo.setPrefWidth(width);
                nomjoueur = (Label) unegrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(LJ.get(0).getJ().getPseudo());
                G1 = (GridPane) unegrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                g_1 = LJ.get(0).getLGJ().get(0);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G1.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;
                        } else {
                            Pane P = (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                // G1.getChildren().get(0).setOnMouseClicked(handler);
                root.getChildren().add(G1);
            } else if ((LJ.get(0).getNombredecartonsvoulus()) == 2) {
                deuxgrillesolo = (AnchorPane) root.getChildren().get(1);
                deuxgrillesolo.setPrefHeight(height);
                deuxgrillesolo.setPrefWidth(width);
                nomjoueur = (Label) deuxgrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(LJ.get(0).getJ().getPseudo());
                G1 = (GridPane) deuxgrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                G2 = (GridPane) deuxgrillesolo.getChildren().get(3);
                G2.setGridLinesVisible(true);
                G2.setLayoutX(height - 42);
                g_1 = LJ.get(0).getLGJ().get(0);
                g_2 = LJ.get(0).getLGJ().get(1);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G1.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;
                        } else {
                            Pane P = (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                va = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_2.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G2.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_2.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;
                        } else {
                            Pane P = (Pane) G2.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                root.getChildren().add(G1);
            } else {
                troisgrillesolo = (AnchorPane) root.getChildren().get(2);
                troisgrillesolo.setPrefHeight(height);
                troisgrillesolo.setPrefWidth(width);
                nomjoueur = (Label) troisgrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(LJ.get(0).getJ().getPseudo());
                G1 = (GridPane) troisgrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                G2 = (GridPane) troisgrillesolo.getChildren().get(3);
                G2.setGridLinesVisible(true);
                G2.setLayoutX(height - 42);
                G3 = (GridPane) troisgrillesolo.getChildren().get(4);
                G3.setGridLinesVisible(true);
                G3.setLayoutX(height / 2);
                G3.setLayoutY(350);
                g_1 = LJ.get(0).getLGJ().get(0);
                g_2 = LJ.get(0).getLGJ().get(1);
                g_3 = LJ.get(0).getLGJ().get(2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G1.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;
                        } else {
                            Pane P = (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                va = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_2.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G2.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_2.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;
                        } else {
                            Pane P = (Pane) G2.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                va = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_3.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G3.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_3.getG().getT()[i][j].getVal());
                            P.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (L.getTextFill()==Color.BLACK) {
                                        P.setStyle("-fx-background-color: #000000;");
                                        L.setTextFill(Color.WHITE);
                                    }
                                    else {
                                        P.setStyle("-fx-background-color: #FDFFF1;");
                                        L.setTextFill(Color.BLACK);
                                    }
                                }
                            });
                            va++;
                        } else {
                            Pane P = (Pane) G3.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /*@FXML
    private void Button5(ActionEvent event) { // affichage info premier joueur
        Stage primaryStage = (Stage) b5.getScene().getWindow();
        Group root = new Group();
        int va=0;
        System.out.println(getNbcartonsj1());
        System.out.println(P.getLJ().size());
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("affiche_grille.fxml"));
            if ((LJ.get(1).getNombredecartonsvoulus()) == 1) {
                unegrillesolo = (AnchorPane) root.getChildren().get(0);
                unegrillesolo.setPrefHeight(height);
                unegrillesolo.setPrefWidth(width);
                nomjoueur = (Label) unegrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(LJ.get(1).getJ().getPseudo());
                G1 = (GridPane) unegrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                g_1 = LJ.get(1).getLGJ().get(0);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G1.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            va++;
                        } else {
                            Pane P = (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                // G1.getChildren().get(0).setOnMouseClicked(handler);
                root.getChildren().add(G1);
            } else if ((LJ.get(1).getNombredecartonsvoulus()) == 2) {
                deuxgrillesolo = (AnchorPane) root.getChildren().get(1);
                deuxgrillesolo.setPrefHeight(height);
                deuxgrillesolo.setPrefWidth(width);
                nomjoueur = (Label) deuxgrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(LJ.get(0).getJ().getPseudo());
                G1 = (GridPane) deuxgrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                G2 = (GridPane) deuxgrillesolo.getChildren().get(3);
                G2.setGridLinesVisible(true);
                G2.setLayoutX(height - 42);
                g_1 = LJ.get(1).getLGJ().get(0);
                g_2 = LJ.get(1).getLGJ().get(1);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G1.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            va++;
                        } else {
                            Pane P = (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                va = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_2.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G2.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_2.getG().getT()[i][j].getVal());
                            va++;
                        } else {
                            Pane P = (Pane) G2.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                root.getChildren().add(G1);
            } else {
                troisgrillesolo = (AnchorPane) root.getChildren().get(2);
                troisgrillesolo.setPrefHeight(height);
                troisgrillesolo.setPrefWidth(width);
                nomjoueur = (Label) troisgrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(LJ.get(0).getJ().getPseudo());
                G1 = (GridPane) troisgrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                G2 = (GridPane) troisgrillesolo.getChildren().get(3);
                G2.setGridLinesVisible(true);
                G2.setLayoutX(height - 42);
                G3 = (GridPane) troisgrillesolo.getChildren().get(4);
                G3.setGridLinesVisible(true);
                G3.setLayoutX(height / 2);
                G3.setLayoutY(350);
                g_1 = LJ.get(1).getLGJ().get(0);
                g_2 = LJ.get(1).getLGJ().get(1);
                g_3 = LJ.get(1).getLGJ().get(2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G1.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            va++;
                        } else {
                            Pane P = (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                va = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_2.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G2.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_2.getG().getT()[i][j].getVal());
                            va++;
                        } else {
                            Pane P = (Pane) G2.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
                va = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_3.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G3.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_3.getG().getT()[i][j].getVal());
                            va++;
                        } else {
                            Pane P = (Pane) G3.getChildren().get(va);
                            P.setStyle("-fx-background-color: #FD6526;");
                            va++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }*/

    public TextField getNomsolo() {
        return this.nomsolo;
    }

    public TextField getNbfavsolo() {
        return this.nbfavsolo;
    }

    public TextField getNbcartonssolo() {
        return this.nbcartonssolo;
    }

    public TextField getNomj1() {
        return this.nomj1;
    }

    public TextField getNbfavj1() {
        return this.nbfavj1;
    }

    public TextField getNbcartonsj1() {
        return this.nbcartonsj1;
    }

    public TextField getNomj2() {
        return this.nomj2;
    }

    public TextField getNbfavj2() {
        return this.nbfavj2;
    }

    public TextField getNbcartonsj2() {
        return this.nbcartonsj2;
    }

    public void debut_game() {
        Creationbouleloto();
        remplissage_cadeau();
    }

    public void setMax(){
        max--;
    }

    public int getMax(){
        return this.max;
    }

    public void ordinateur() {
        JoueurLoto newj= new JoueurLoto(54,3);
        newj.creation_joueur("David");
        LJ.add(newj);
        newj= new JoueurLoto(71,1);
        newj.creation_joueur("Martin");
        LJ.add(newj);
        newj= new JoueurLoto(4,2);
        newj.creation_joueur("Yann");
        LJ.add(newj);
        newj= new JoueurLoto(15,3);
        newj.creation_joueur("Martine");
        LJ.add(newj);
        newj= new JoueurLoto(67,3);
        newj.creation_joueur("Gaëlle");
        LJ.add(newj);
        newj= new JoueurLoto(19,1);
        newj.creation_joueur("Yannick");
        LJ.add(newj);
        newj= new JoueurLoto(81,2);
        newj.creation_joueur("Joséphine");
        LJ.add(newj);
        newj= new JoueurLoto(24,1);
        newj.creation_joueur("Mathilde");
        LJ.add(newj);
        newj= new JoueurLoto(2,3);
        newj.creation_joueur("Laura");
        LJ.add(newj);
        newj= new JoueurLoto(37,2);
        newj.creation_joueur("Raymond Domenech");
        LJ.add(newj);
    }

    public void Creationbouleloto() {
        int i;
        for (i = 0; i < 90; i++) {
            Lint.add(i + 1);
        }
    }

    public void CreationGrille() {
        int i, cpt = 0, x = 0;
        for (i = 0; i < 100; i++) {
            x = 0;
            while (x != 1) {
                cpt = 0;
                G = new GrilleLoto();
                G.setLI(new ArrayList<Integer>());
                G.creationGrille();
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 9; b++) {
                        if (G.getG().getT()[a][b].getVal().compareTo(" ") != 0) {
                            cpt++;
                        }
                    }
                }
                if (cpt == 15) {
                    LG.add(G);
                    x = 1;
                }
            }
        }
    }

    public void distribution() {
        int i = 0, y, z;
        for (JoueurLoto temp : LJ) {
            y = 0;
            z = 0;
            while (y != temp.getNombredecartonsvoulus()) {
                if (z == 0) {
                    for (GrilleLoto temp2 : LG) {
                        if (nbfav(temp2, temp)) {
                            y++;
                            z++;
                            LG.remove(temp2);
                        }
                    }
                }
                Random rand = new Random();
                int taille = rand.nextInt(LG.size());
                temp.setLGJ(LG.get(taille));
                LG.remove(LG.get(taille));
                y++;
            }
        }
    }

    public Boolean nbfav(GrilleLoto GL, JoueurLoto JL) {
        for (int a = 0; a < 3; a++) {
            for (int j = 0; j < 9; j++) {
                if (GL.getG().getT()[a][j].getVal() == GL.convertir(JL.getNombrefav())) {
                    JL.getLGJ().add(GL);
                    System.out.println(JL.getJ().getPseudo());
                    return true;
                }
            }
        }
        return false;
    }

   /* public void debut_partie() {
        int grillepleine=0,t1=0,t2=0,t3=0,cadeau1=0,cadeau2=0,cadeau3=0,max=90;
        remplissage_cadeau();
        while (grillepleine !=1) {
            int x=0,y=0,z=0;
            Random rand = new Random();
            int index = rand.nextInt(max);
            int temp = Lint.get(index);
            Lint.remove(index);
            max--;
            Lboulesortie.add(temp);
            for (JoueurLoto test : LJ) {
                System.out.println("\n\n" + test.getJ().getPseudo() + ":");
                for (GrilleLoto tempo: test.getLGJ()) {
                    System.out.println("Le num�ro de cette grille: " + (test.getLGJ().indexOf(tempo)+1) );
                    tempo.Affichage(tempo.getG());
                    System.out.println("\nCette grille contient-elle le num�ro ? " + temp + "\n1=Oui, 2=Non");
                    Scanner sc1 = new Scanner(System.in);
                    int verif = sc1.nextInt();
                    sc1.nextLine();
                    if(verif==1) {
                        System.out.println("Donnez le num�ro de la ligne, puis de la colonne:");
                        int l = sc1.nextInt();
                        int c = sc1.nextInt();
                        tempo.getG().getT()[l-1][c-1].setModifiable(false);
                    }
                    System.out.println("Rappel des num�ros d�j� tir�es:");
                    for (int c:Lboulesortie) {
                        System.out.println(c + " ");
                    }
                    if (t1==0) {
                        System.out.println("Avez-vous obtenu 1 ligne ? 1=Oui, 2=Non");
                        int a = sc1.nextInt();
                        a--;
                        if(a==0) {
                            for (int t=0;t<10;t++) {
                                if ((tempo.getG().getT()[0][t].isModifiable()==false) && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[0][t].getVal())))) {
                                    x++;
                                }
                                if (tempo.getG().getT()[1][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[1][t].getVal())))) {
                                    y++;
                                }
                                if (tempo.getG().getT()[2][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[2][t].getVal())))) {
                                    z++;
                                }
                            }
                            if ((x==5 && y!=5 && z!=5) || (x!=5 && y==5 && z!=5) || (x!=5 && y!=5 && z==5)) {
                                //vainqueur 1 ligne
                                System.out.println("\nBravo vous avez obtenu 1 ligne voici votre cadeau:");
                                while (cadeau1==0) {
                                    Random rand1 = new Random();
                                    int taille = rand1.nextInt(Lcadeau.size());
                                    Lots c=Lcadeau.get(taille);
                                    if (c.getLigne()==1) {
                                        cadeau1++;
                                        System.out.println("\nVotre cadeau:" + c.getCadeau());
                                        Lcadeau.remove(c);
                                    }
                                }
                                t1++;
                            }
                            else {
                                System.out.println("\nVous vous �tes tromp�s!");
                                t1=0;
                            }
                        }

                    }
                    if (t2==0 && t1==1) {
                        System.out.println("Avez-vous obtenu 2 lignes ? 1=Oui, 2=Non");
                        int a = sc1.nextInt();
                        a--;
                        if(a==0) {
                            for (int t=0;t<10;t++) {
                                if ((tempo.getG().getT()[0][t].isModifiable()==false) && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[0][t].getVal())))) {
                                    x++;
                                }
                                if (tempo.getG().getT()[1][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[1][t].getVal())))) {
                                    y++;
                                }
                                if (tempo.getG().getT()[2][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[2][t].getVal())))) {
                                    z++;
                                }
                            }
                            if((x==5 && y==5 && z!=5) || (x!=5 && y==5 && z==5) || (x==5 && y!=5 && z==5)) {
                                //vainqueur 2 lignes
                                System.out.println("\nBravo vous avez obtenu 2 lignes voici votre cadeau:");
                                while (cadeau2==0) {
                                    Random rand1 = new Random();
                                    int taille = rand1.nextInt(Lcadeau.size());
                                    Lots c=Lcadeau.get(taille);
                                    if (c.getLigne()==2) {
                                        cadeau2++;
                                        System.out.println("\nVotre cadeau:" + c.getCadeau());
                                        Lcadeau.remove(c);
                                    }
                                }
                                t2++;
                            }
                            else {
                                System.out.println("\nVous vous �tes tromp�s!");
                                t2=0;
                            }
                        }
                    }
                    if (t3==0 && t1==1 && t2==1) {
                        System.out.println("Avez-vous obtenu 1 carton plein ? 1=Oui, 2=Non");
                        int a = sc1.nextInt();
                        a--;
                        if(a==0) {
                            for (int t=0;t<10;t++) {
                                if ((tempo.getG().getT()[0][t].isModifiable()==false) && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[0][t].getVal())))) {
                                    x++;
                                }
                                if (tempo.getG().getT()[1][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[1][t].getVal())))) {
                                    y++;
                                }
                                if (tempo.getG().getT()[2][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[2][t].getVal())))) {
                                    z++;
                                }
                            }
                            if(x==5 && y==5 && z==5) {
                                //vainqueur carton plein
                                System.out.println("\nBravo vous avez obtenu 1 carton plein voici votre cadeau:");
                                while (cadeau3==0) {
                                    Random rand1 = new Random();
                                    int taille = rand1.nextInt(Lcadeau.size());
                                    Lots c=Lcadeau.get(taille);
                                    if (c.getLigne()==3) {
                                        cadeau3++;
                                        System.out.println("\nVotre cadeau:" + c.getCadeau());
                                        Lcadeau.remove(c);
                                    }
                                }
                                t3++;
                                tempo.Affichage(tempo.getG());
                                grillepleine=1;
                                break;
                            }
                            else {
                                System.out.println("\nVous vous �tes tromp�s!");
                                t3=0;
                            }
                        }
                    }
                }
            }
        }
    }*/

    public Lots remplissage_cadeau() {
        Lots L;
        L=new Lots("Blu-Ray", 1);
        Lcadeau.add(L);
        L=new Lots("Appareil a raclette", 2);
        Lcadeau.add(L);
        L=new Lots("VTT", 3);
        Lcadeau.add(L);
        L=new Lots("Television 4k", 3);
        Lcadeau.add(L);
        L=new Lots("Bon d'achat de 20 euros", 1);
        Lcadeau.add(L);
        L=new Lots("1 biere offerte au bar", 1);
        Lcadeau.add(L);
        L=new Lots("Roman policier", 1);
        Lcadeau.add(L);
        L=new Lots("Casquette tour de France", 1);
        Lcadeau.add(L);
        return L;
    }

    public int convertirint(String t) {
        int x;
        x = Integer.parseInt(t);
        return x;
    }
}
