package fr.projet.loto;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Depart_Loto extends Application {

    public void start(Stage fenetre) throws Exception {
        Controller_sample pd = new Controller_sample();
        pd.Pagedepart(fenetre);
    }
    }
