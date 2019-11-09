package fr.projet.poker.Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuJoueurController implements Initializable {

    private List<String> name = new ArrayList<String>();
    @FXML
    private Button Jouer;
    @FXML
    private AnchorPane Fenetre = new AnchorPane();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void sendData() {

        Group getGroup = new Group();
        TextField getName = new TextField();
        int i = 0;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Table.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableController controller = loader.<TableController>getController();

        while (i < Fenetre.getChildren().size() - 1) {
            getGroup = (Group) Fenetre.getChildren().get(i);
            getName = (TextField) getGroup.getChildren().get(3);
            name.add(getName.getText());
            i++;
        }
        controller.initTable(name);



        Stage primaryStage = (Stage) Jouer.getScene().getWindow();

        primaryStage.setTitle("Poker");
        primaryStage.setScene(new Scene(root, 1700.0D, 1000.0D));
        primaryStage.show();








    }
}
