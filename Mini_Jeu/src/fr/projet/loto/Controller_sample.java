package fr.projet.loto;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;

public class Controller_sample {
    private PartieLoto P = new PartieLoto();
    private List<JoueurLoto> temp= new ArrayList<JoueurLoto>();
    private double height = Screen.getPrimary().getVisualBounds().getHeight();
    private double width = Screen.getPrimary().getVisualBounds().getWidth();
    private GrilleLoto g_1;
    private GrilleLoto g_2;
    private GrilleLoto g_3;
    private int j=5;
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
    private Label Joueur;
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
    private void Button1(ActionEvent event) { // page après avoir sélectionné solo
        Stage primaryStage = (Stage) b1.getScene().getWindow();
        Group root = new Group();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("sample_2.fxml"));
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
        int va=0;
        JoueurLoto JL = new JoueurLoto(convertirint(getNbfavsolo().getText()), convertirint(getNbcartonssolo().getText()));
        JL.creation_joueur(getNomsolo().getText());
        P.setjoueur(JL);
        debut_game();
        P.distribution();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("sample_5.fxml"));
            if (convertirint(getNbcartonssolo().getText()) == 1) {
                unegrillesolo = (AnchorPane) root.getChildren().get(0);
                unegrillesolo.setPrefHeight(height);
                unegrillesolo.setPrefWidth(width);
                nomjoueur = (Label) unegrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - (170 / 2));
                nomjoueur.setText(getNomsolo().getText());
                G1 = (GridPane) unegrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                g_1=P.getLJ().get(0).getLGJ().get(0);
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                Pane P= (Pane) G1.getChildren().get(va);
                Label L= (Label) P.getChildren().get(0);
                L.setText(g_1.getG().getT()[i][j].getVal());
                        va++;}
                    else {
                            Pane P= (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #000000;");
                    va++;}
                    }}
               // G1.getChildren().get(0).setOnMouseClicked(handler);
                root.getChildren().add(G1);
            } else if (convertirint(getNbcartonssolo().getText()) == 2) {
                deuxgrillesolo = (AnchorPane) root.getChildren().get(1);
                deuxgrillesolo.setPrefHeight(height);
                deuxgrillesolo.setPrefWidth(width);
                nomjoueur = (Label) deuxgrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - (170 / 2));
                nomjoueur.setText(getNomsolo().getText());
                G1 = (GridPane) deuxgrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                G2 = (GridPane) deuxgrillesolo.getChildren().get(3);
                G2.setGridLinesVisible(true);
                G2.setLayoutX(height - 42);
                g_1=P.getLJ().get(0).getLGJ().get(0);
                g_2=P.getLJ().get(0).getLGJ().get(1);
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G1.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            va++;}
                        else {
                            Pane P= (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #000000;");
                            va++;}
                    }}
                va=0;
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_2.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G2.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_2.getG().getT()[i][j].getVal());
                            va++;}
                        else {
                            Pane P= (Pane) G2.getChildren().get(va);
                            P.setStyle("-fx-background-color: #000000;");
                            va++;}
                    }}
                root.getChildren().add(G1);
            } else {
                troisgrillesolo = (AnchorPane) root.getChildren().get(2);
                troisgrillesolo.setPrefHeight(height);
                troisgrillesolo.setPrefWidth(width);
                nomjoueur = (Label) troisgrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - (170 / 2));
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
                g_1=P.getLJ().get(0).getLGJ().get(0);
                g_2=P.getLJ().get(0).getLGJ().get(1);
                g_3=P.getLJ().get(0).getLGJ().get(2);
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G1.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            va++;}
                        else {
                            Pane P= (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #000000;");
                            va++;}
                    }}
                va=0;
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_2.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G2.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_2.getG().getT()[i][j].getVal());
                            va++;}
                        else {
                            Pane P= (Pane) G2.getChildren().get(va);
                            P.setStyle("-fx-background-color: #000000;");
                            va++;}
                    }}
                va=0;
                for (int i=0;i<3;i++){
                    for (int j=0;j<9;j++){
                        if (g_3.getG().getT()[i][j].getVal().compareTo(" ") != 0){
                            Pane P= (Pane) G3.getChildren().get(va);
                            Label L= (Label) P.getChildren().get(0);
                            L.setText(g_3.getG().getT()[i][j].getVal());
                            va++;}
                        else {
                            Pane P= (Pane) G3.getChildren().get(va);
                            P.setStyle("-fx-background-color: #000000;");
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
    private void Button2(ActionEvent event) { //page après avoir appuyé sur multi
        Stage primaryStage = (Stage) b2.getScene().getWindow();
        Group root = new Group();
     //   FXMLLoader loader = new FXMLLoader(getClass().getResource("sample_3.fxml"));
     //   PartieLoto controller = loader.<PartieLoto>getController();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("sample_3.fxml"));
            A3 = (AnchorPane) root.getChildren().get(0);
            A3.setPrefHeight(height);
            A3.setPrefWidth(width);
            nomj1 = (TextField) A3.getChildren().get(0);
            nomj1.setLayoutX(width / 2);
            nbfavj1 = (TextField) A3.getChildren().get(1);
            nbfavj1.setLayoutX(width / 2);
            nbcartonsj1 = (TextField) A3.getChildren().get(2);
            nbcartonsj1.setLayoutX(width / 2);
            pseu = (Label) A3.getChildren().get(3);
            pseu.setLayoutX(width / 2 - 220);
            nbf = (Label) A3.getChildren().get(4);
            nbf.setLayoutX(width / 2 - 220);
            nbc = (Label) A3.getChildren().get(5);
            nbc.setLayoutX(width / 2 - 220);
            b3 = (Button) A3.getChildren().get(6);
            b3.setLayoutX(width / 2 + 148.8);
            Joueur = (Label) A3.getChildren().get(7);
            Joueur.setLayoutX(width / 2 - 34);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void Button3(ActionEvent event) { //deuxieme page renseignement deuxieme joueur
        Stage primaryStage = (Stage) b3.getScene().getWindow();
        Group root = new Group();
        JoueurLoto JL = new JoueurLoto(convertirint(getNbfavj1().getText()), convertirint(getNbcartonsj1().getText()));
        JL.creation_joueur(getNomj1().getText());
        P.setjoueur(JL);
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("sample_4.fxml"));
            A4 = (AnchorPane) root.getChildren().get(0);
            A4.setPrefHeight(height);
            A4.setPrefWidth(width);
            nomj2 = (TextField) A4.getChildren().get(0);
            nomj2.setLayoutX(width / 2);
            nbfavj2 = (TextField) A4.getChildren().get(1);
            nbfavj2.setLayoutX(width / 2);
            nbcartonsj2 = (TextField) A4.getChildren().get(2);
            nbcartonsj2.setLayoutX(width / 2);
            pseu = (Label) A4.getChildren().get(3);
            pseu.setLayoutX(width / 2 - 220);
            nbf = (Label) A4.getChildren().get(4);
            nbf.setLayoutX(width / 2 - 220);
            nbc = (Label) A4.getChildren().get(5);
            nbc.setLayoutX(width / 2 - 220);
            b5 = (Button) A4.getChildren().get(6);
            b5.setLayoutX(width / 2 + 148.8);
            Joueur = (Label) A4.getChildren().get(7);
            Joueur.setLayoutX(width / 2 - 34);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void Button5(ActionEvent event) { // affichage info premier joueur
        Stage primaryStage = (Stage) b5.getScene().getWindow();
        Group root = new Group();
        int va=0;
        JoueurLoto JL = new JoueurLoto(convertirint(getNbfavj2().getText()), convertirint(getNbcartonsj2().getText()));
        JL.creation_joueur(getNomj2().getText());
        P.setjoueur(JL);
        debut_game();
        P.distribution();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("sample_5.fxml"));
            if ((P.getLJ().get(0).getNombredecartonsvoulus()) == 1) {
                unegrillesolo = (AnchorPane) root.getChildren().get(0);
                unegrillesolo.setPrefHeight(height);
                unegrillesolo.setPrefWidth(width);
                nomjoueur = (Label) unegrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - (170 / 2));
                nomjoueur.setText(P.getLJ().get(0).getJ().getPseudo());
                G1 = (GridPane) unegrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                g_1 = P.getLJ().get(0).getLGJ().get(0);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G1.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            va++;
                        } else {
                            Pane P = (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #000000;");
                            va++;
                        }
                    }
                }
                // G1.getChildren().get(0).setOnMouseClicked(handler);
                root.getChildren().add(G1);
            } else if ((P.getLJ().get(0).getNombredecartonsvoulus()) == 2) {
                deuxgrillesolo = (AnchorPane) root.getChildren().get(1);
                deuxgrillesolo.setPrefHeight(height);
                deuxgrillesolo.setPrefWidth(width);
                nomjoueur = (Label) deuxgrillesolo.getChildren().get(0);
                nomjoueur.setLayoutX(width / 2 - (170 / 2));
                nomjoueur.setText(P.getLJ().get(0).getJ().getPseudo());
                G1 = (GridPane) deuxgrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                G2 = (GridPane) deuxgrillesolo.getChildren().get(3);
                G2.setGridLinesVisible(true);
                G2.setLayoutX(height - 42);
                g_1 = P.getLJ().get(0).getLGJ().get(0);
                g_2 = P.getLJ().get(0).getLGJ().get(1);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G1.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            va++;
                        } else {
                            Pane P = (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #000000;");
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
                            P.setStyle("-fx-background-color: #000000;");
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
                nomjoueur.setLayoutX(width / 2 - (170 / 2));
                nomjoueur.setText(P.getLJ().get(0).getJ().getPseudo());
                G1 = (GridPane) troisgrillesolo.getChildren().get(2);
                G1.setGridLinesVisible(true);
                G2 = (GridPane) troisgrillesolo.getChildren().get(3);
                G2.setGridLinesVisible(true);
                G2.setLayoutX(height - 42);
                G3 = (GridPane) troisgrillesolo.getChildren().get(4);
                G3.setGridLinesVisible(true);
                G3.setLayoutX(height / 2);
                G3.setLayoutY(350);
                g_1 = P.getLJ().get(0).getLGJ().get(0);
                g_2 = P.getLJ().get(0).getLGJ().get(1);
                g_3 = P.getLJ().get(0).getLGJ().get(2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (g_1.getG().getT()[i][j].getVal().compareTo(" ") != 0) {
                            Pane P = (Pane) G1.getChildren().get(va);
                            Label L = (Label) P.getChildren().get(0);
                            L.setText(g_1.getG().getT()[i][j].getVal());
                            va++;
                        } else {
                            Pane P = (Pane) G1.getChildren().get(va);
                            P.setStyle("-fx-background-color: #000000;");
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
                            P.setStyle("-fx-background-color: #000000;");
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
                            P.setStyle("-fx-background-color: #000000;");
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
        P.Creationbouleloto();
        P.CreationGrille();
    }

    public int convertirint(String t) {
        int x;
        x = Integer.parseInt(t);
        return x;
    }

    public void settemp(JoueurLoto jl){
        temp.add(jl);
    }
}
