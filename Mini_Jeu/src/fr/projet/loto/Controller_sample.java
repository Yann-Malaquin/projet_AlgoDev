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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller_sample {
    private List<Integer> Lint = new ArrayList<Integer>();
    private List<Integer> Lboulesortie = new ArrayList<Integer>();
    private int NbJoueurs;
    private int grillepleine=0,t1=0,t2=0,t3=0,cadeau1=0,cadeau2=0,cadeau3=0;
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
    private int cad1=0,cad2=0,cad3=0;
    private EventHandler<? super MouseEvent> handler;
    @FXML
    private AnchorPane first;
    @FXML
    private Label l1;
    @FXML
    private Button Solo;
    @FXML
    private Button Conseils;
    @FXML
    private Button sol;
    @FXML
    private Button c;
    @FXML
    private Button Retour;
    @FXML
    private Button Save;
    @FXML
    private Button b5;
    @FXML
    private TextField nbfavsolo;
    @FXML
    private TextField nbcartonssolo;
    @FXML
    private TextField nomsolo;
    @FXML
    private Label nomjoueur;
    @FXML
    private AnchorPane A2;
    @FXML
    private AnchorPane A3;
    @FXML
    private AnchorPane unegrillesolo;
    @FXML
    private AnchorPane deuxgrillesolo;
    @FXML
    private AnchorPane troisgrillesolo;
    @FXML
    private Label conseil1;
    @FXML
    private Label conseil2;
    @FXML
    private Label conseil;
    @FXML
    private Label nbf;
    @FXML
    private Label nbc;
    @FXML
    private Label pseu;
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
    private Button jeu;
    @FXML
    private Label cadeau;
    @FXML
    private Button Quitter;

    @FXML
    private void Solo(ActionEvent event) { // page après avoir sélectionné le mode solo, elle sert à indiquer nos renseignements pour le jeu
        Stage primaryStage = (Stage) Solo.getScene().getWindow();
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
            Save = (Button) A2.getChildren().get(6);
            Save.setLayoutX(width / 2 + 148.8);
            InputStream input = this.getClass().getResourceAsStream("/resources/Lotto2-1080x675.jpg");
            Image im = new Image(input,width,height,false,false);
            BackgroundImage myBI = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            A2.setBackground(new Background(myBI));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void Save(ActionEvent event) { //après avoir enregistré les infos du joueur, nous lui présentons ces grilles et le jeu peut commencer
        Stage primaryStage = (Stage) Save.getScene().getWindow();
        Group root = new Group();
        int va=0;
        JoueurLoto JL = new JoueurLoto(convertirint(getNbfavsolo().getText()), convertirint(getNbcartonssolo().getText()));
        JL.creation_joueur(getNomsolo().getText());
        LJ.add(JL);
        Joueur joueur = new Joueur(JL.getJ().getPseudo());
        CreationGrille();
        ordinateur();
        distribution();
        remplissage_cadeau();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("affiche_grille.fxml"));
            verif = (Label) root.getChildren().get(5);
            verif.setText("Avez-vous une ligne remplie ?");
            if (convertirint(getNbcartonssolo().getText()) == 1) {
                unegrillesolo = (AnchorPane) root.getChildren().get(0);
                unegrillesolo.setPrefHeight(height);
                unegrillesolo.setPrefWidth(width);
                InputStream input = this.getClass().getResourceAsStream("/resources/Lotto2-1080x675.jpg");
                Image im = new Image(input,width,height,false,false);
                BackgroundImage myBI = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                unegrillesolo.setBackground(new Background(myBI));
                nomjoueur = (Label) unegrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - 121.5);
                nomjoueur.setText(getNomsolo().getText());
                G1 = (GridPane) unegrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                g_1=LJ.get(0).getLGJ().get(0);
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                Pane P= (Pane) G1.getChildren().get(va);
                P.setStyle("-fx-background-color: WHITE");
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
                            P.setStyle("-fx-background-color: WHITE;");
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
                InputStream input = this.getClass().getResourceAsStream("/resources/Lotto2-1080x675.jpg");
                Image im = new Image(input,width,height,false,false);
                BackgroundImage myBI = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                deuxgrillesolo.setBackground(new Background(myBI));
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
                            P.setStyle("-fx-background-color: WHITE");
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
                                        P.setStyle("-fx-background-color: WHITE;");
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
                            P.setStyle("-fx-background-color: WHITE");
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
                                        P.setStyle("-fx-background-color: WHITE;");
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
                InputStream input = this.getClass().getResourceAsStream("/resources/Lotto2-1080x675.jpg");
                Image im = new Image(input,width,height,false,false);
                BackgroundImage myBI = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                troisgrillesolo.setBackground(new Background(myBI));
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
                            P.setStyle("-fx-background-color: WHITE");
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
                                        P.setStyle("-fx-background-color: WHITE;");
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
                            P.setStyle("-fx-background-color: WHITE");
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
                                        P.setStyle("-fx-background-color: WHITE;");
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
                            P.setStyle("-fx-background-color: WHITE");
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
                                        P.setStyle("-fx-background-color: WHITE;");
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
            Creationbouleloto();
            b5=(Button) root.getChildren().get(3);
            Button b= (Button) root.getChildren().get(8);
            b.setOnAction((event1) -> {
                this.Pagedepart(primaryStage);
            });
            nombre=(Label) root.getChildren().get(4);
            cadeau=(Label) root.getChildren().get(7);
            b5.setOnAction(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent actionEvent) { // a chaque appui sur le bouton nombre suivant on verifie si un autre des joueurs a la ligne et on affiche un nouveau nombre
                                   Random rand = new Random();
                                   int index = rand.nextInt(getMax());
                                   int temp = Lint.get(index);
                                   nombre.setText(String.valueOf(temp));
                                   Lint.remove(index);
                                   setMax();
                                   Lboulesortie.add(temp);
                                   for (JoueurLoto lj : LJ) {
                                       if (t1 == 0) {
                                           for (GrilleLoto lg : lj.getLGJ()) {
                                               int l1 = 0, l2 = 0, l3 = 0;
                                               for (int i = 0; i < 3; i++) {
                                                   for (int j = 0; j < 9; j++) {
                                                       if (lg.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                                                           if (i == 0 && Lboulesortie.contains(convertirint(lg.getG().getT()[i][j].getVal()))) {
                                                               l1++;
                                                           } else if (i == 1 && Lboulesortie.contains(convertirint(lg.getG().getT()[i][j].getVal()))) {
                                                               l2++;
                                                           } else if (i == 2 && Lboulesortie.contains(convertirint(lg.getG().getT()[i][j].getVal()))) {
                                                               l3++;
                                                           }
                                                       }
                                                   }
                                               }
                                               if ((l1 == 5 || l2 == 5 || l3 == 5) && t1 != 1) {
                                                   t1 = 1;
                                                   while (cad1 == 0) {
                                                       Random rand1 = new Random();
                                                       int cad = rand1.nextInt(Lcadeau.size());
                                                       Lots c = Lcadeau.get(cad);
                                                       if (c.getLigne() == 1) {
                                                           cad1++;
                                                           cadeau.setLayoutX(0);
                                                           cadeau.setLayoutY(700);
                                                           cadeau.setText("Félicitation " + lj.getJ().getPseudo() + " a gagné: " + c.getCadeau());
                                                           Lcadeau.remove(c);
                                                       }
                                                   }
                                                   verif.setText("Avez-vous 2 lignes remplies ?");
                                                   break;
                                               }
                                           }
                                       } else if (t1 != 0 && t2 == 0) {
                                           for (GrilleLoto lg : lj.getLGJ()) {
                                               int l1 = 0, l2 = 0, l3 = 0;
                                               for (int i = 0; i < 3; i++) {
                                                   for (int j = 0; j < 9; j++) {
                                                       if (lg.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                                                           if (i == 0 && Lboulesortie.contains(convertirint(lg.getG().getT()[i][j].getVal()))) {
                                                               l1++;
                                                           } else if (i == 1 && Lboulesortie.contains(convertirint(lg.getG().getT()[i][j].getVal()))) {
                                                               l2++;
                                                           } else if (i == 2 && Lboulesortie.contains(convertirint(lg.getG().getT()[i][j].getVal()))) {
                                                               l3++;
                                                           }
                                                       }
                                                   }
                                               }
                                               if (((l1 == 5 && l2 == 5) || (l2 == 5 && l3 == 5) || (l3 == 5 && l1 == 5)) && t2 != 1) {
                                                   t2 = 1;
                                                   while (cad2 == 0) {
                                                       Random rand1 = new Random();
                                                       int cad = rand1.nextInt(Lcadeau.size());
                                                       Lots c = Lcadeau.get(cad);
                                                       if (c.getLigne() == 2) {
                                                           cad2++;
                                                           cadeau.setLayoutX(0);
                                                           cadeau.setLayoutY(700);
                                                           cadeau.setText("Félicitation " + lj.getJ().getPseudo() + " a gagné: " + c.getCadeau());
                                                           Lcadeau.remove(c);
                                                       }
                                                   }
                                                   verif.setText("Avez-vous un carton plein ?");
                                                   break;
                                               }
                                           }
                                       } else if (t1 != 0 && t2 != 0 && t3 == 0) {
                                           for (GrilleLoto lg : lj.getLGJ()) {
                                               int l1 = 0, l2 = 0, l3 = 0;
                                               for (int i = 0; i < 3; i++) {
                                                   for (int j = 0; j < 9; j++) {
                                                       if (lg.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                                                           if (i == 0 && Lboulesortie.contains(convertirint(lg.getG().getT()[i][j].getVal()))) {
                                                               l1++;
                                                           } else if (i == 1 && Lboulesortie.contains(convertirint(lg.getG().getT()[i][j].getVal()))) {
                                                               l2++;
                                                           } else if (i == 2 && Lboulesortie.contains(convertirint(lg.getG().getT()[i][j].getVal()))) {
                                                               l3++;
                                                           }
                                                       }
                                                   }
                                               }
                                               if ((l1 == 5 && l2 == 5 && l3 == 5) && t3 != 1) {
                                                   t3 = 1;
                                                   while (cad3 == 0) {
                                                       Random rand1 = new Random();
                                                       int cad = rand1.nextInt(Lcadeau.size());
                                                       Lots c = Lcadeau.get(cad);
                                                       if (c.getLigne() == 3) {
                                                           cad3++;
                                                           cadeau.setLayoutX(0);
                                                           cadeau.setLayoutY(700);
                                                           cadeau.setText("Félicitation " + lj.getJ().getPseudo() + " a gagné: " + c.getCadeau());
                                                           Lcadeau.remove(c);
                                                       }
                                                   }
                                                   verif.setText("Fin du jeu, merci d'avoir joué");
                                                   jeu.setVisible(false);
                                                   b.setVisible(true);
                                                   b.setText("Quitter");
                                                   b.setLayoutX(630);
                                                   b.setLayoutY(610);
                                                   break;
                                               }
                                           }
                                       }
                                   }
                               }
                           });
            jeu=(Button) root.getChildren().get(6);
            jeu.setOnAction(new EventHandler<ActionEvent>() { //vérification si le joueur a menti ou non s'il n'a pas menti il remporte le lot sinon on lui affiche les boules deja sorties pour qu'il se corrige
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (t1 == 0) {
                        JoueurLoto test = LJ.get(0);
                        for (GrilleLoto tempo : test.getLGJ()) {
                            int l1 = 0, l2 = 0, l3 = 0;
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (tempo.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                                        if (i == 0 && Lboulesortie.contains(convertirint(tempo.getG().getT()[i][j].getVal()))) {
                                            l1++;
                                        } else if (i == 1 && Lboulesortie.contains(convertirint(tempo.getG().getT()[i][j].getVal()))) {
                                            l2++;
                                        } else if (i == 2 && Lboulesortie.contains(convertirint(tempo.getG().getT()[i][j].getVal()))) {
                                            l3++;
                                        }
                                    }
                                }
                            }
                            if ((l1 == 5 || l2 == 5 || l3 == 5) && t1!=1) {
                                t1 = 1;
                                while (cad1==0) {
                                    Random rand1 = new Random();
                                    int cad = rand1.nextInt(Lcadeau.size());
                                    Lots c=Lcadeau.get(cad);
                                    if (c.getLigne()==1) {
                                        cad1++;
                                        cadeau.setLayoutX(0);
                                        cadeau.setLayoutY(700);
                                        cadeau.setText("Félicitation vous avez gagné: " + c.getCadeau());
                                        Lcadeau.remove(c);
                                        Classement C = new Classement();
                                                           C.ChargerClassement(C,C.FichierSauvegarde);
                                                           C.DonnerPointAUnJoueur(joueur,1,2);
                                                           C.SauvegardeClassement(C.FichierSauvegarde);
                                    }
                                }
                                verif.setText("Avez-vous 2 lignes remplies ?");
                                break;
                            } else {
                                String s = " ";
                                int nb=0;
                                for (Integer i : Lboulesortie) {
                                    nb++;
                                    s= s + i;
                                    s= s + " ";
                                    if (nb==30){
                                        s=s+"\n";
                                    }
                                }
                                cadeau.setLayoutX(0);
                                cadeau.setLayoutY(650);
                                cadeau.setText("Vous vous êtes trompés, cochez les boules que vous avez oublié, voici les boules déjà sortiees:");
                                cadeau.setText(cadeau.getText() + s);
                            }
                        }
                    } else if (t1 != 0 && t2 == 0) {
                        JoueurLoto test = LJ.get(0);
                        for (GrilleLoto tempo : test.getLGJ()) {
                            int l1 = 0, l2 = 0, l3 = 0;
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (tempo.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                                        if (i == 0 && Lboulesortie.contains(convertirint(tempo.getG().getT()[i][j].getVal()))) {
                                            l1++;
                                        } else if (i == 1 && Lboulesortie.contains(convertirint(tempo.getG().getT()[i][j].getVal()))) {
                                            l2++;
                                        } else if (i == 2 && Lboulesortie.contains(convertirint(tempo.getG().getT()[i][j].getVal()))) {
                                            l3++;
                                        }
                                    }
                                }
                            }
                            if (((l1 == 5 && l2 == 5) || (l2 == 5 && l3 == 5) || (l3 == 5 && l1 == 5)) && t2!=1) {
                                t2 = 1;
                                while (cad2==0) {
                                    Random rand1 = new Random();
                                    int cad = rand1.nextInt(Lcadeau.size());
                                    Lots c=Lcadeau.get(cad);
                                    if (c.getLigne()==2) {
                                        cad2++;
                                        cadeau.setLayoutX(0);
                                        cadeau.setLayoutY(700);
                                        cadeau.setText("Félicitation vous avez gagné: " + c.getCadeau());
                                        Lcadeau.remove(c);
                                        Classement C = new Classement();
                                                           C.ChargerClassement(C,C.FichierSauvegarde);
                                                           C.DonnerPointAUnJoueur(joueur,1,2);
                                                           C.SauvegardeClassement(C.FichierSauvegarde);
                                    }
                                }
                                verif.setText("Avez-vous un carton plein ?");
                                break;
                            } else {
                                String s = " ";
                                int nb=0;
                                for (Integer i : Lboulesortie) {
                                    nb++;
                                    s= s + i;
                                    s= s + " ";
                                    if (nb==30){
                                        s=s+"\n";
                                    }
                                }
                                cadeau.setLayoutX(0);
                                cadeau.setLayoutY(650);
                                cadeau.setText("Vous vous êtes trompés, cochez les boules que vous avez oublié, voici les boules déjà sortiees:");
                                cadeau.setText(cadeau.getText() + s);

                            }
                        }
                    } else if (t1 != 0 && t2 != 0 && t3 == 0) {
                        JoueurLoto test = LJ.get(0);
                        for (GrilleLoto tempo : test.getLGJ()) {
                            int l1 = 0, l2 = 0, l3 = 0;
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (tempo.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                                        if (i == 0 && Lboulesortie.contains(convertirint(tempo.getG().getT()[i][j].getVal()))) {
                                            l1++;
                                        } else if (i == 1 && Lboulesortie.contains(convertirint(tempo.getG().getT()[i][j].getVal()))) {
                                            l2++;
                                        } else if (i == 2 && Lboulesortie.contains(convertirint(tempo.getG().getT()[i][j].getVal()))) {
                                            l3++;
                                        }
                                    }
                                }
                            }
                            if ((l1 == 5 && l2 == 5 && l3 == 5) && t3!=1) {
                                t3 = 1;
                                while (cad3==0) {
                                    Random rand1 = new Random();
                                    int cad = rand1.nextInt(Lcadeau.size());
                                    Lots c=Lcadeau.get(cad);
                                    if (c.getLigne()==3) {
                                        cad3++;
                                        cadeau.setLayoutX(0);
                                        cadeau.setLayoutY(700);
                                        cadeau.setText("Félicitation vous avez gagné: " + c.getCadeau());
                                        Lcadeau.remove(c);
                                        Classement C = new Classement();
                                                           C.ChargerClassement(C,C.FichierSauvegarde);
                                                           C.DonnerPointAUnJoueur(joueur,1,2);
                                                           C.SauvegardeClassement(C.FichierSauvegarde);
                                    }
                                }
                                verif.setText("Fin du jeu, merci d'avoir joué");
                                jeu.setVisible(false);
                                b.setVisible(true);
                                b.setText("Quitter");
                                b.setLayoutX(630);
                                b.setLayoutY(610);
                                break;
                            } else {
                                String s = " ";
                                int nb=0;
                                for (Integer i : Lboulesortie) {
                                    nb++;
                                    s= s + i;
                                    s= s + " ";
                                    if (nb==30){
                                        s=s+"\n";
                                    }
                                }
                                cadeau.setLayoutX(0);
                                cadeau.setLayoutY(650);
                                cadeau.setText("Vous vous êtes trompés, cochez les boules que vous avez oublié, voici les boules déjà sortiees:");
                                cadeau.setText(cadeau.getText() + s);
                            }
                        }
                    }
                } });
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void Conseils(ActionEvent event) { //page qui indique des petits conseils après avoir appuyé sur le bouton conseils
        Stage primaryStage = (Stage) Conseils.getScene().getWindow();
        Group root = new Group();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("Aide.fxml"));
            A3 = (AnchorPane) root.getChildren().get(0);
            A3.setPrefHeight(height);
            A3.setPrefWidth(width);
            conseil= (Label) A3.getChildren().get(0);
            conseil.setLayoutX(width/2 - 65.6);
            conseil1= (Label) A3.getChildren().get(1);
            conseil1.setLayoutX(width/2 -453.2);
            conseil2= (Label) A3.getChildren().get(2);
            conseil2.setLayoutX(width/2 -154.4);
            Retour= (Button) A3.getChildren().get(3);
            Retour.setLayoutX(width/2 + 300);
            Retour.setOnAction((event1) -> {
                this.Pagedepart(primaryStage);
            });
            InputStream input = this.getClass().getResourceAsStream("/resources/images-gratuites-libres-de-droits-sans-droits-d-auteur-82-1560x832.jpg");
            Image im = new Image(input,width,height,false,false);
            BackgroundImage myBI = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            A3.setBackground(new Background(myBI));
            }
        catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

@FXML
    public void Pagedepart(Stage fenetre){ //la première page qui s'affiche à l'ouverture du jeu avec trois options jeu, conseils et quitter
        Group root =new Group();
        try{
        root = FXMLLoader.load(this.getClass().getResource("Page_depart.fxml"));
        double height=Screen.getPrimary().getVisualBounds().getHeight();
        double width=Screen.getPrimary().getVisualBounds().getWidth();
        first=(AnchorPane) root.getChildren().get(0);
        first.setPrefHeight(height);
        first.setPrefWidth(width);
        l1=(Label) first.getChildren().get(1);
        l1.setLayoutX(width/2 - 64);
        sol=(Button) first.getChildren().get(0);
        sol.setLayoutX(width/2 - 64);
        c=(Button) first.getChildren().get(2);
        c.setLayoutX(width/2 - 53);
        Quitter=(Button) first.getChildren().get(3);
        Quitter.setLayoutX(width/2 -45.2);
        Quitter.setOnAction((event) -> {
            fenetre.close();
        });
            InputStream input = this.getClass().getResourceAsStream("/resources/Lotto2-1080x675.jpg");
            Image im = new Image(input,width,height,false,false);
            BackgroundImage myBI = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            first.setBackground(new Background(myBI));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        fenetre.setTitle("Loto");
        fenetre.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        fenetre.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        fenetre.setScene(scene);
        fenetre.show();

    }

    public TextField getNomsolo() {
        return this.nomsolo;
    }

    public TextField getNbfavsolo() {
        return this.nbfavsolo;
    }

    public TextField getNbcartonssolo() {
        return this.nbcartonssolo;
    }

    public void debut_game() {
        Creationbouleloto();
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

    public void remplissage_cadeau() {
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
    }

    public int convertirint(String t) {
        int x;
        x = Integer.parseInt(t);
        return x;
    }
}
