package fr.projet.poker.Interface;

import javafx.application.Application;
import javafx.stage.Stage;

public class PartiePoker extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuController controller = new MenuController();
        controller.MenuJoueur(primaryStage);
    }

    public static void main() {
        launch();
    }


}