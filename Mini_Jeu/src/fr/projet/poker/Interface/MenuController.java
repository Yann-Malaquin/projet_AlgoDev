package fr.projet.poker.Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button Quitter;

    @FXML Button Jouer;

    @FXML
    private void MenuJoueur()
    {
        Stage primaryStage = (Stage) Jouer.getScene().getWindow();
        AnchorPane root = new AnchorPane();
        try {
            root = (AnchorPane) FXMLLoader.load(this.getClass().getResource("MenuJoueur.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1700.0D, 1000.0D));
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
