package fr.projet.poker.Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = (AnchorPane) FXMLLoader.load(this.getClass().getResource("Menu.fxml"));
        primaryStage.setTitle("Poker");
        primaryStage.setScene(new Scene(root, 1700.0D, 1000.0D));
        primaryStage.show();

    }
}
