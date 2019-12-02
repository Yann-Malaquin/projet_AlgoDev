package fr.projet.poker;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class Carte {

	@FXML
	Group label = new Group();
	@FXML
	AnchorPane carte = new AnchorPane();
	@FXML
	Label HG = new Label();
	@FXML
	Label C = new Label();
	@FXML
	Label BD = new Label();


	public Carte() {
		super();
	}

	public AnchorPane Carte(String figure, String couleur, int _valeur) {

		carte.setPrefSize(54, 85);

		if (couleur.equals("carreau")) {
			InputStream carreau = this.getClass().getResourceAsStream("/resources/Poker/Couleur_Carreau.png");
			carte.setId(_valeur + "Ca");

			HG.setId(Integer.toString(_valeur));
			C.setId("Ca");
			BD.setId("BD" + _valeur);

			ImageView iw = new ImageView(new Image(carreau));

			iw.setFitWidth(25);
			iw.setFitHeight(25);

			HG.setPrefSize(15, 15);
			HG.setLayoutX(10);
			HG.setLayoutY(10);

			C.setPrefSize(25, 25);
			C.setLayoutX(14.5);
			C.setLayoutY(30);

			BD.setPrefSize(15, 15);
			BD.setRotate(180);
			BD.setLayoutX(29);
			BD.setLayoutY(60);

			HG.setText(figure);
			HG.setTextFill(Color.RED);
			C.setGraphic(iw);
			BD.setText(figure);
			BD.setTextFill(Color.RED);

			label.setId("Carte" + _valeur + "Ca");
			label.getChildren().addAll(HG, C, BD);

			carte.getChildren().add(label);

			carte.setBorder(new Border(new BorderStroke(Color.BLACK,
					BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


		}
		if (couleur.equals("coeur")) {
			InputStream coeur = this.getClass().getResourceAsStream("/resources/Poker/Couleur_Coeur.png");

			carte.setId(_valeur + "Co");
			HG.setId(Integer.toString(_valeur));
			C.setId("Co");
			BD.setId("BD" + _valeur);

			ImageView iw = new ImageView(new Image(coeur));

			iw.setFitWidth(25);
			iw.setFitHeight(25);

			HG.setPrefSize(15, 15);
			HG.setLayoutX(10);
			HG.setLayoutY(10);

			C.setPrefSize(25, 25);
			C.setLayoutX(14.5);
			C.setLayoutY(30);

			BD.setPrefSize(15, 15);
			BD.setRotate(180);
			BD.setLayoutX(29);
			BD.setLayoutY(60);

			HG.setText(figure);
			HG.setTextFill(Color.RED);
			C.setGraphic(iw);
			BD.setText(figure);
			BD.setTextFill(Color.RED);

			label.setId("Carte" + _valeur + "Co");

			label.getChildren().addAll(HG, C, BD);
			carte.getChildren().add(label);

			carte.setBorder(new Border(new BorderStroke(Color.BLACK,
					BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


		}
		if (couleur.equals("trefle")) {
			InputStream trefle = this.getClass().getResourceAsStream("/resources/Poker/Couleur_Trefle.png");

			carte.setId(_valeur + "Tr");
			HG.setId(Integer.toString(_valeur));
			C.setId("Tr");
			BD.setId("BD" + _valeur);

			ImageView iw = new ImageView(new Image(trefle));

			iw.setFitWidth(25);
			iw.setFitHeight(25);

			HG.setPrefSize(15, 15);
			HG.setLayoutX(10);
			HG.setLayoutY(10);

			C.setPrefSize(25, 25);
			C.setLayoutX(14.5);
			C.setLayoutY(30);

			BD.setPrefSize(15, 15);
			BD.setRotate(180);
			BD.setLayoutX(29);
			BD.setLayoutY(60);

			HG.setText(figure);
			C.setGraphic(iw);
			BD.setText(figure);

			label.setId("Carte" + _valeur + "Tr");
			label.getChildren().addAll(HG, C, BD);
			carte.getChildren().add(label);
			carte.setBorder(new Border(new BorderStroke(Color.BLACK,
					BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


		}
		if (couleur.equals("pique")) {
			InputStream pique = this.getClass().getResourceAsStream("/resources/Poker/Couleur_Pique.png");

			carte.setId(_valeur + "Pi");
			HG.setId(Integer.toString(_valeur));
			C.setId("Pi");
			BD.setId("BD" + _valeur);

			ImageView iw = new ImageView(new Image(pique));

			iw.setFitWidth(25);
			iw.setFitHeight(25);

			HG.setPrefSize(15, 15);
			HG.setLayoutX(10);
			HG.setLayoutY(10);

			C.setPrefSize(25, 25);
			C.setLayoutX(14.5);
			C.setLayoutY(30);

			BD.setPrefSize(15, 15);
			BD.setRotate(180);
			BD.setLayoutX(29);
			BD.setLayoutY(60);

			HG.setText(figure);
			C.setGraphic(iw);
			BD.setText(figure);

			label.setId("Carte" + _valeur + "Pi");
			label.getChildren().addAll(HG, C, BD);
			carte.getChildren().add(label);

			carte.setBorder(new Border(new BorderStroke(Color.BLACK,
					BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


		}
		return carte;

	}
}
