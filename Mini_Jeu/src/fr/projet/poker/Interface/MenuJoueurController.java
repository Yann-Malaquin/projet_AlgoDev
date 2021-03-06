package fr.projet.poker.Interface;

import fr.projet.poker.JoueurPoker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuJoueurController {


    private List<String> name = new ArrayList<String>();
    private List<String> bank = new ArrayList<String>();

    @FXML
    Button Jouer;

    @FXML
    private AnchorPane fenetre;

    @FXML
    private void sendNamePlayer() {

        Group getGroup;
        TextField getName , getDonneur, getBank, getPb;
        JoueurPoker jp;
        int i = 1;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Table.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableController controller = loader.<TableController>getController();




        //parcourt les saisies et cree de nouveau joueur et ajoute les joueurs a la liste
        while (i < ((Group) fenetre.getChildren().get(0)).getChildren().size() - 3) {

            getGroup = (Group)((Group) fenetre.getChildren().get(0)).getChildren().get(i);
            getName = (TextField) getGroup.getChildren().get(3);
            getBank = (TextField) getGroup.getChildren().get(4);
            getGroup = ((Group)((Group) fenetre.getChildren().get(0)).getChildren().get(6));
            getDonneur = (TextField) getGroup.getChildren().get(1);

            jp = new JoueurPoker(getName.getText(), Double.parseDouble(getBank.getText()));

            if(getName.getText().compareTo(getDonneur.getText())==0)
            {
                jp.setEtatJoueur("Donneur");
            }

            controller.setlJP(jp);
            i++;
        }

        getGroup = (Group)((Group) fenetre.getChildren().get(0)).getChildren().get(7);
        getPb = (TextField) getGroup.getChildren().get(1);
        controller.setPb(Double.parseDouble(getPb.getText()));

        Stage primaryStage = (Stage) Jouer.getScene().getWindow();
        controller.initTable(primaryStage);
    }


}