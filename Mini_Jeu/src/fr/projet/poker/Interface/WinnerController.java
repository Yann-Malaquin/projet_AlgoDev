package fr.projet.poker.Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class WinnerController {
    private String winner;

    public void setWinner(String _winner) {
        this.winner = _winner;
    }

    public String getWinner() {
        return this.winner;
    }

    @FXML
    public void initMenu(Stage primaryStage) {

        Group root = new Group(), gr = new Group();
        AnchorPane fenetre = new AnchorPane();
        Button mp = new Button(), quitter = new Button();
        Label nameW = new Label();
        double x, y;

        try {
            root = (Group) FXMLLoader.load(this.getClass().getResource("Winner.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //nameW = (Label) ((AnchorPane) root.getChildren().get(0)).getChildren().get(3);
        //nameW.setText(this.getWinner());
        fenetre = (AnchorPane) root.getChildren().get(0);
        InputStream cup = this.getClass().getResourceAsStream("/resources/Poker/win.png");
        ImageView iw = new ImageView(new Image(cup));
        iw.setFitWidth(400);iw.setFitHeight(400);
        Label cupI = new Label();

        x = Screen.getPrimary().getVisualBounds().getWidth();
        y = Screen.getPrimary().getVisualBounds().getHeight();


        cupI.setPrefSize(400, 400);
        cupI.setGraphic(iw);
        cupI.setLayoutX((x / 2) - 200);
        cupI.setLayoutY((y / 2) - 200);

        mp.setText("Retour menu principal");
        mp.setLayoutX(x / 2 - 180);
        mp.setLayoutY(y / 2 + 300);
        mp.setPrefWidth(150);

        mp.setOnAction((event) -> {
            this.menuPrincipal(mp);
        });

        quitter.setText("Retour au bureau");
        quitter.setLayoutX(x / 2 + 25);
        quitter.setLayoutY(y / 2 + 300);
        quitter.setPrefWidth(150);

        quitter.setOnAction((event) -> {
            this.quitter(quitter);
        });

        nameW.setText(this.getWinner());
        nameW.setAlignment(Pos.CENTER);
        nameW.setFont(new Font("Arial", 20));
        nameW.setPrefWidth(150);
        nameW.setPrefHeight(50);
        nameW.setLayoutX(x/2 - 75);
        nameW.setLayoutY(y/2 + 142);

        gr.getChildren().addAll(cupI, mp, quitter,nameW);

        fenetre.getChildren().add(gr);

        fenetre.setPrefSize(x, y);

        primaryStage.setHeight(y);
        primaryStage.setWidth(x);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void menuPrincipal(Button mp) {
        Stage stage = (Stage) mp.getScene().getWindow();
        MenuController controller = new MenuController();
        controller.initMenu(stage);
    }

    @FXML
    private void quitter(Button quitter) {
        Stage stage = (Stage) quitter.getScene().getWindow();
        stage.close();
    }


}