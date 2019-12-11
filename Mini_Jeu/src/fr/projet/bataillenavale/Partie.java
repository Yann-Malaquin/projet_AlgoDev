package fr.projet.bataillenavale;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


public class Partie extends Application {
	private boolean lancer = false;
	private GrilleBN grilleIA;
	private GrilleBN grilleJoueur;
	private int nbBateau = 5;
	private boolean tourAdverse = false;
	private JoueurBN joueur1;
	private JoueurBN joueur2 = new JoueurBN("",true);

	private Random random = new Random();
	private boolean victoire = Boolean.parseBoolean(null);

	private Parent LancerPartie() {
		AnchorPane root = new AnchorPane();
		root.setPrefSize(800, 600);
		root.setStyle(String.valueOf(Color.valueOf("#E6E6FA")));

		grilleIA = new GrilleBN(joueur2, event -> {
			if (!lancer)
				return;

			CaseBN c = (CaseBN) event.getSource();
			if (c.isUtilise())
				return;

			tourAdverse = !c.tirer();

			if (grilleIA.getBateau() == 0) {
				victoire = true;
				Scene scene = new Scene(Popup(victoire));
				Stage popup = new Stage();
				popup.setScene(scene);
				popup.centerOnScreen();
				popup.setResizable(false);
				popup.show();
			}

			if (tourAdverse)
				CoupIA();
		});

		grilleJoueur = new GrilleBN(joueur1, event -> {
			if (lancer)
				return;

			CaseBN cell = (CaseBN) event.getSource();
			if (grilleJoueur.placerBateau(new Bateau(nbBateau, event.getButton() == MouseButton.PRIMARY), cell.get_X(), cell.get_Y())) {
				if (--nbBateau == 0) {
					demarerPartie();
				}
			}
		});

		HBox hbox = new HBox(50, grilleJoueur, grilleIA);
		root.getChildren().add(hbox);
		hbox.setLayoutX(55);
		hbox.setLayoutY(140);
		return root;
	}
	/*  Genere des coups aléatoire pour l'IA */
	private void CoupIA() {
		while (tourAdverse) {
			int x = random.nextInt(10);
			int y = random.nextInt(10);

			CaseBN c = grilleJoueur.getCase(x, y);
			if (c.isUtilise())
				continue;

			tourAdverse = c.tirer();

			if (grilleJoueur.getBateau() == 0) {
				Scene scene = new Scene(Popup(victoire));
				Stage popup = new Stage();
				popup.setScene(scene);
				popup.centerOnScreen();
				popup.setResizable(false);
				popup.show();
			}
		}
	}
	/* Place les bateau de l'IA et lance le jeu */
	private void demarerPartie() {
		int taille = 5;

		while (taille > 0) {
			int x = random.nextInt(10);
			int y = random.nextInt(10);

			if (grilleIA.placerBateau(new Bateau(taille, Math.random() < 0.5), x, y)) {
				taille--;
			}
		}

		lancer = true;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane Page = new GridPane();
		Page.setPrefSize(300,200);
		// Custom Label
		Label PseudoLabel = new Label("Pseudo du joueur");

		// Custom du TextField
		TextField PseudoTextField = new TextField();

		//Custom du bouton
		Button valider = new Button("Valider");
		valider.setOnAction(e-> {
			String pseudo = PseudoTextField.getText();
			joueur1 = new JoueurBN(pseudo,false);
			Scene scene = new Scene(LancerPartie());
			primaryStage.setScene(scene);
		});

		Page.add(PseudoLabel,0,0);
		Page.add(PseudoTextField,1, 0);
		Page.add(valider,1,1);
		Page.setHgap(20);
		Page.setVgap(10);
		Page.setAlignment(Pos.CENTER);
		Scene scene = new Scene(Page);
		primaryStage.setTitle("Bataille Navale");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private Parent Popup(boolean victoire){
		AnchorPane popup = new AnchorPane();
		if(victoire = true){
			Button Quitter = new Button("Quitter");
			Label Status = new Label("Vous avez gagner");
			Quitter.setOnAction(e -> System.exit(1));
			VBox v = new VBox();
			v.getChildren().addAll(Status,Quitter);
			v.setAlignment(Pos.CENTER);
			popup.setPrefSize(200,100);
			popup.getChildren().add(v);
		}
		if(victoire = false){
			Button Quitter = new Button("Quitter");
			Label Status = new Label("Vous avez perdu");
			Quitter.setOnAction(e -> System.exit(1));
			VBox v = new VBox();
			v.getChildren().addAll(Status,Quitter);
			v.setAlignment(Pos.CENTER);
			popup.setPrefSize(200,100);
			popup.getChildren().add(v);
		}
		return popup;
	}

	public static void main() {
		launch();
	}
}
