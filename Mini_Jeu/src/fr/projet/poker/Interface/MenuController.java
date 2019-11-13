package fr.projet.poker.Interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


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
        Group root = new Group();
        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("MenuJoueur.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

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
