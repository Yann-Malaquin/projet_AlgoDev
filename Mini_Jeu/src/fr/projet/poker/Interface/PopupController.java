package fr.projet.poker.Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupController {
    @FXML
    private Button Valider = new Button();
    @FXML
    private TextField getDonneur = new TextField();

    public void sendDonneur() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Table.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableController controller = loader.<TableController>getController();
        //controller.getDonneur(getDonneur.getText());


        Stage popup = new Stage();
        popup = (Stage) Valider.getScene().getWindow();

        popup.close();


    }

}
