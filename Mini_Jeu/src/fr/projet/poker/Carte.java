package fr.projet.poker;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.util.List;

public class Carte {
	FileInputStream carreau;
	FileInputStream coeur;
	FileInputStream pique;
	FileInputStream trefle;

	public Carte() {
		super();
	}

	public AnchorPane Carte(String figure, String couleur, int i) {
		AnchorPane carte = new AnchorPane();
		carte.setPrefSize(54,85);

		if (couleur.equals("carreau")) {
			try {
				carreau = new FileInputStream("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\poker\\Interface\\picture\\Couleur_Carreau.png");
			} catch (Exception e) {
				System.out.println("Oups");
			}


			Label HG = new Label();
			Label C = new Label();
			Label BD = new Label();

			carte.setId("Carte_" + i);
			HG.setId("HG_" + i);
			C.setId("C_" + i);
			BD.setId("BD_" + i);

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
			BD.setLayoutX(29);
			BD.setLayoutY(60);

			HG.setText(figure);
			HG.setTextFill(Color.RED);
			C.setGraphic(iw);
			BD.setText(figure);
			BD.setTextFill(Color.RED);
			Group label = new Group();

			label.getChildren().addAll(HG, C, BD);
			carte.getChildren().add(label);

			carte.setBorder(new Border(new BorderStroke(Color.BLACK,
					BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


		}
		if (couleur.equals("coeur")) {
			try {
				coeur = new FileInputStream("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\poker\\Interface\\picture\\Couleur_Coeur.png");
			} catch (Exception e) {
				System.out.println("Oups");
			}

			Label HG = new Label();
			Label C = new Label();
			Label BD = new Label();

			carte.setId("Carte_" + i);
			HG.setId("HG_" + i);
			C.setId("C_" + i);
			BD.setId("BD_" + i);


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
			BD.setLayoutX(29);
			BD.setLayoutY(60);

			HG.setText(figure);
			HG.setTextFill(Color.RED);
			C.setGraphic(iw);
			BD.setText(figure);
			BD.setTextFill(Color.RED);
			Group label = new Group();

			label.getChildren().addAll(HG, C, BD);
			carte.getChildren().add(label);

			carte.setBorder(new Border(new BorderStroke(Color.BLACK,
					BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


		}
		if (couleur.equals("trefle")) {
			try {
				trefle = new FileInputStream("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\poker\\Interface\\picture\\Couleur_Trefle.png");
			} catch (Exception e) {
				System.out.println("Oups");
			}

			Label HG = new Label();
			Label C = new Label();
			Label BD = new Label();

			carte.setId("Carte_" + i);
			HG.setId("HG_" + i);
			C.setId("C_" + i);
			BD.setId("BD_" + i);

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
			BD.setLayoutX(29);
			BD.setLayoutY(60);

			HG.setText(figure);
			C.setGraphic(iw);
			BD.setText(figure);
			Group label = new Group();

			label.getChildren().addAll(HG, C, BD);
			carte.getChildren().add(label);

			carte.setBorder(new Border(new BorderStroke(Color.BLACK,
					BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


		}
		if (couleur.equals("pique")) {
			try {
				pique = new FileInputStream("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\poker\\Interface\\picture\\Couleur_Pique.png");
			} catch (Exception e) {
				System.out.println("Oups");
			}

			Label HG = new Label();
			Label C = new Label();
			Label BD = new Label();

			carte.setId("Carte_" + i);
			HG.setId("HG_" + i);
			C.setId("C_" + i);
			BD.setId("BD_" + i);

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
			Group label = new Group();

			label.getChildren().addAll(HG, C, BD);
			carte.getChildren().add(label);

			carte.setBorder(new Border(new BorderStroke(Color.BLACK,
					BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));



		}

		return carte;

	}
}
