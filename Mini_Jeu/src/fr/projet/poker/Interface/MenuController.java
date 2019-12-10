package fr.projet.poker.Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;


public class MenuController {

    @FXML
    private Button Quitter;

    @FXML
    Button Jouer;

    public void initMenu(Stage primaryStage) {

        Group root = new Group();

        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    @FXML
    private void MenuJoueur() {
        Stage primaryStage = (Stage) Jouer.getScene().getWindow();
        Group root = new Group(), interieurFenetre = new Group();
        AnchorPane fenetre = new AnchorPane();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("MenuJoueur.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

        double w = 0, h = 0;
        fenetre = (AnchorPane) root.getChildren().get(0);

        InputStream input = this.getClass().getResourceAsStream("/resources/Poker/Fond_JPoker.jpg");
        w = primaryStage.getWidth();
        h = primaryStage.getHeight();
        fenetre.setPrefSize(w, h);
        Image im = new Image(input, w, h, false, false);

        Font f;
        InputStream input1 = this.getClass().getResourceAsStream("/resources/Poker/SaloonExt Th.ttf");
        f = Font.loadFont(input1, 14);
        Label l = new Label();


        BackgroundImage myBI = new BackgroundImage(im,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        fenetre.setBackground(new Background(myBI));
        interieurFenetre = (Group) ((AnchorPane) root.getChildren().get(0)).getChildren().get(0);
        interieurFenetre.setLayoutX(w / 2 - 331);
        interieurFenetre.setLayoutY(h / 2 - 304.5);
        l = (Label) ((Group)((Group) fenetre.getChildren().get(0)).getChildren().get(0)).getChildren().get(1);

        l.setFont(f);


        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void Quitter() {
        // get a handle to the stage
        Stage stage = (Stage) Quitter.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
