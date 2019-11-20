package fr.projet.bataillenavale;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class Partie extends Application {

	private boolean lancement = false;
	private GrilleBN grilleIA;
	private GrilleBN grilleJoueur;

	private int nbBateau = 5;

	private boolean TourIA = false;

	private Random random = new Random();


	public static void main(String[] args) {
		launch(args);
	}



	private Parent createContent() {
		Text regle = new Text();
		regle.setFont(Font.font("Verdana",15));
		regle.getLocalToSceneTransform();
		regle.setText("\t Vous placez vos bateaux sur la grille du bas\n"+
				"\t Pour placer vos bateaux horizontalement : Clique gauche\n" +
				"\t Pour placer vos bateaux verticalement : Clique droit\n" +
				"\t La grille du haut est la grille ou vous allez tirer \n");

		BorderPane page = new BorderPane();
		page.setPrefSize(600, 800);
		page.setTop(regle);


		this.grilleIA = new GrilleBN(true, event -> {
			if (!lancement)
				return;

			GrilleBN.CaseBN cell = (GrilleBN.CaseBN) event.getSource();
			if (cell.utilise)
				return;

			TourIA = !cell.tire();

			if (grilleIA.nbbateau == 0) {
				System.out.println("PERDU");
			}

			if (TourIA)
				TireIA();
		});

		grilleJoueur = new GrilleBN(false, event -> {
			if (lancement)
				return;

			GrilleBN.CaseBN cell = (GrilleBN.CaseBN) event.getSource();
			if (grilleJoueur.placerBateau(new Bateau(nbBateau, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
				if (--nbBateau == 0) {
					startGame();
				}
			}
		});

		VBox vbox = new VBox(50, grilleIA, grilleJoueur);
		vbox.setAlignment(Pos.CENTER);

		page.setCenter(vbox);

		return page;
	}

	private void TireIA() {
		while (TourIA) {
			int x = random.nextInt(10);
			int y = random.nextInt(10);

			GrilleBN.CaseBN cell = grilleJoueur.getCase(x, y);
			if (cell.utilise)
				continue;

			TourIA = cell.tire();

			if (grilleJoueur.nbbateau == 0) {
				System.out.println("PERDU");
			}
		}
	}
	
 /* Méthode qui permet de choisir aléatoirement les emplacement des bateau de l'IA) */
	private void startGame() {

		int taille = 5;

		while (taille > 0) {
			int x = random.nextInt(10);
			int y = random.nextInt(10);

			if (grilleIA.placerBateau(new Bateau(taille, Math.random() < 0.5), x, y)) {
				taille--;
			}
		}

		lancement = true;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Bataille Navale");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public void lancer() {
		launch();
	}
}