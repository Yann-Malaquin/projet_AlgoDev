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
    @FXML
    AnchorPane A1;
    @FXML
    Label abc;
    @FXML
    Button b1;
    @FXML
    Button b2;

    @Override
    public void start(Stage fenetre) throws Exception {
        Group root = FXMLLoader.load(getClass().getResource("Page_depart.fxml"));
        double height=Screen.getPrimary().getVisualBounds().getHeight();
        double width=Screen.getPrimary().getVisualBounds().getWidth();
        A1=(AnchorPane) root.getChildren().get(0);
        A1.setPrefHeight(height);
        A1.setPrefWidth(width);
        abc=(Label) A1.getChildren().get(1);
        abc.setLayoutX(width/2 - 64);
        b1=(Button) A1.getChildren().get(0);
        b1.setLayoutX(width/2 - 64);
        b2=(Button) A1.getChildren().get(2);
        b2.setLayoutX(width/2 - 114.8);
        Scene scene = new Scene(root);
        fenetre.setTitle("Loto");
        fenetre.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        fenetre.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        fenetre.setScene(scene);
        fenetre.show();
    }
    }
