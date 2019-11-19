package fr.projet.poker.Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuJoueurController {


    private List<String> name = new ArrayList<String>();
    private List<String> bank = new ArrayList<String>();

    @FXML
    Button Jouer;

    @FXML
    private AnchorPane Fenetre;

    @FXML
    private void sendNamePlayer() {

        Group getGroup = new Group();
        TextField getName = new TextField();
        TextField getDonneur = new TextField();
        TextField getBank = new TextField();
        String donneur = null;
        int i = 0;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Table.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableController controller = loader.<TableController>getController();

        while (i < Fenetre.getChildren().size() - 2) {
            getGroup = (Group) Fenetre.getChildren().get(i);
            getName = (TextField) getGroup.getChildren().get(3);
            name.add(getName.getText());
            i++;
        }
        getGroup = (Group) Fenetre.getChildren().get(5);
        getDonneur = (TextField) getGroup.getChildren().get(1);
        donneur = getDonneur.getText();

        i = 0;

        while (i < Fenetre.getChildren().size() - 2) {

            getGroup = (Group) Fenetre.getChildren().get(i);
            getBank = (TextField) getGroup.getChildren().get(4);
            bank.add(getBank.getText());
            i++;
        }
        Stage primaryStage = (Stage) Jouer.getScene().getWindow();
        controller.initTable(primaryStage,name,donneur,bank);
    }


}
