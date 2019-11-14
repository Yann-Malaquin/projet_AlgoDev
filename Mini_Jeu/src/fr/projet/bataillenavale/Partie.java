package fr.projet.bataillenavale;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
		BorderPane root = new BorderPane();
		root.setPrefSize(600, 800);

		root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));

		grilleIA = new GrilleBN(true, event -> {
			if (!lancement)
				return;

			GrilleBN.CaseBN cell = (GrilleBN.CaseBN) event.getSource();
			if (cell.utilise)
				return;

			TourIA = !cell.tire();

			if (grilleIA.getBateau() == 0) {
				System.out.println("YOU WIN");
				System.exit(0);
			}

			if (TourIA)
				TireIA();
		});

		grilleJoueur = new GrilleBN(false, event -> {
			if (lancement)
				return;

			GrilleBN.CaseBN box = (GrilleBN.CaseBN) event.getSource();
			if (grilleJoueur.placerBateau(new Bateau(nbBateau, event.getButton() == MouseButton.PRIMARY), box.x, box.y)) {
				if (--nbBateau == 0) {
					startGame();
				}
			}
		});

		VBox vbox = new VBox(50, grilleIA, grilleJoueur);
		vbox.setAlignment(Pos.CENTER);

		root.setCenter(vbox);

		return root;
	}

	private void TireIA() {
		while (TourIA) {
			int x = random.nextInt(10);
			int y = random.nextInt(10);

			GrilleBN.CaseBN box = grilleJoueur.getCase(x, y);
			if (box.utilise)
				continue;

			TourIA = box.tire();

			if (grilleJoueur.getBateau() == 0) {
				System.out.println("YOU LOSE");
				System.exit(0);
			}
		}
	}

	private void startGame() {
		// place enemy ships
		int type = 5;

		while (type > 0) {
			int x = random.nextInt(10);
			int y = random.nextInt(10);

			if (grilleIA.placerBateau(new Bateau(type, Math.random() < 0.5), x, y)) {
				type--;
			}
		}

		lancement = true;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Battleship");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}