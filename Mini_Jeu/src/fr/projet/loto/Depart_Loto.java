package fr.projet.loto;

import javafx.application.Application;
import javafx.stage.Stage;

public class Depart_Loto extends Application {

    public void start(Stage fenetre) throws Exception {
        Controller_sample pd = new Controller_sample();
        pd.Pagedepart(fenetre);
    }

    public void lancer() {
        launch();
    }
}
