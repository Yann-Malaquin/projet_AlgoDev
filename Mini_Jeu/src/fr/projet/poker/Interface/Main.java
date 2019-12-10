package fr.projet.poker.Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuController controller = new MenuController();
        controller.initMenu(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void lancer(){
        launch();
    }


}
