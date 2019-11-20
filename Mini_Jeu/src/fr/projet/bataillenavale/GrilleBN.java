package fr.projet.bataillenavale;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

class GrilleBN extends Parent {
	int nbbateau = 5;
	private VBox lignes = new VBox();
	private boolean adversaire = false;
	private Properties FXMLLoader;

	GrilleBN(boolean adversaire, EventHandler<? super MouseEvent> handler) {
		this.adversaire = adversaire;
		for (int y = 0; y < 10; y++) {
			HBox ligne = new HBox();
			for (int x = 0; x < 10; x++) {
				CaseBN c = new CaseBN(x, y, this);
				c.setOnMouseClicked(handler);
				ligne.getChildren().add(c);
			}

			lignes.getChildren().add(ligne);
		}

		getChildren().add(lignes);
	}


	private boolean VerifPlacementBateau(Bateau B, int x, int y) {
		int taille = B.taille;

		if (!B.horizontal) {
			for (int i = y; i < y + taille; i++){
				if (!PointExiste(x,i))
					return false;

				CaseBN box = getCase(x,i);
				if (box.bateau != null)
					return false;

				for(CaseBN autour : getAutour(x,i)){
					if(!PointExiste(x,i))
						return false;
					if (autour.bateau != null)
						return false;
				}
			}
		} else {
			for (int i = x; i< x + taille;i++){
				if (!PointExiste(i,y))
					return false;

				CaseBN box = getCase(i,y);
				if (box.bateau != null)
					return false;

				for (CaseBN autour : getAutour(i,y)){
					if (!PointExiste(i,y))
						return false;

					if (autour.bateau != null)
						return false;
				}
			}
		}

		return true;
	}


	Boolean placerBateau(Bateau bateau, int x, int y) {
		if(VerifPlacementBateau(bateau, x,y)){
			int taille = bateau.taille;
			if (!bateau.horizontal){

				for(int i = y; i < y + taille; i++){
					CaseBN box = getCase(x,i);
					box.bateau = bateau;
					if(!adversaire){

						box.setFill(Color.GRAY);
						box.setStroke(Color.BLACK);
					}
				}
			} else {

				for (int i = x; i< x + taille; i++){
					CaseBN box = getCase(i,y);
					box.bateau = bateau;
					if(!adversaire){

						box.setFill(Color.GRAY);
						box.setStroke(Color.BLACK);
					}
				}
			}

			return true;
		}
		return false;
	}

	private boolean PointExiste (Point2D point){
		return PointExiste(point.getX(), point.getY());
	}

	private boolean PointExiste(double x, double y) {
		return x >= 0 && x < 10 && y >= 0 && y < 10;
	}



	CaseBN getCase(int x, int y) {
		return (CaseBN) ((HBox) lignes.getChildren().get(y)).getChildren().get(x);
	}

	private CaseBN[] getAutour(int x, int y) {
		Point2D[] points = new Point2D[]{
				new Point2D(x - 1, y),
				new Point2D(x + 1, y),
				new Point2D(x, y - 1),
				new Point2D(x, y + 1)
		};

		List<CaseBN> autour = new ArrayList<CaseBN>();

		for (Point2D p : points) {
			if (PointExiste(p)) {
				autour.add(getCase((int) p.getX(), (int) p.getY()));
			}
		}

		return autour.toArray(new CaseBN[0]);
	}

	class CaseBN extends Rectangle {
		int x, y;
		Bateau bateau = null;
		boolean utilise = false;
		private GrilleBN grille;

		CaseBN(int x, int y, GrilleBN grille){
			super(30, 30);
			this.x = x;
			this.y = y;
			this.grille = grille;
			setFill(Color.valueOf("#1C046B"));
			setStroke(Color.BLACK);
		}

		boolean tire(){

			utilise = true;
			setFill(Color.BLACK);
			if(bateau != null){
				bateau.toucher();
				setFill(Color.RED);
				if(!bateau.vivant()){
					grille.nbbateau--;
				}
				return true;
			}

			return false;
		}

	}
}
