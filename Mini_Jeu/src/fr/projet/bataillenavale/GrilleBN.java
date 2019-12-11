package fr.projet.bataillenavale;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GrilleBN extends Parent {
	private GridPane grille = new GridPane();
	private JoueurBN joueur = null;
	private int bateau = 5;

	InputStream input = this.getClass().getResourceAsStream("/resources/bataillenavale/icone_bateau.png");
	Image imgbateau = new Image(input);

	public GrilleBN(JoueurBN joueur, EventHandler<? super MouseEvent> handler){
		this.joueur = joueur;
		for (int y = 0; y < 10; y++){
			for (int x = 0 ; x < 10 ; x++){
				CaseBN c = new CaseBN(x,y,this);
				c.setOnMouseClicked(handler);
				grille.add(c, x,y);
				c.setStroke(Color.BLACK);
				if (((x+y)%2) == 0){
					c.setFill(Color.LIGHTBLUE);
				}
				else {
					c.setFill(Color.ALICEBLUE);
				}
			}
		}
		getChildren().add(grille);
	}

	public boolean placerBateau(Bateau bateau, int x, int y){
		if (VerifPlacementBateau(bateau,x,y)){
			int taille = bateau.getTaille();

			if(!bateau.isHorizontal()){
				for (int i = y; i < y + taille; i++){
					CaseBN c = getCase(x,i);
					c.setBateau(bateau);
					if (!joueur.isIA()){
						c.setFill(new ImagePattern(imgbateau));
						c.setStroke(Color.RED);
					}
				}
			} else{
				for (int i = x ; i < x + taille ; i++){
					CaseBN c = getCase(i,y);
					c.setBateau(bateau);
					if(!joueur.isIA()){
						c.setFill(new ImagePattern(imgbateau));
						c.setStroke(Color.RED);
					}
				}
			}
			return true;
		}
		return false;
	}

	public CaseBN getCase(int x, int y) {
		for (Node node : grille.getChildren()) {
			if (grille.getColumnIndex(node) == x && grille.getRowIndex(node) == y)
				return (CaseBN) node;
		}
		return null;
	}
	/* Fonction trouvée sur internet permettant de savoir si les voisin du point sont utilisée */
	private CaseBN[] getNeighbors(int x, int y){
		Point2D[] points = new Point2D[]{
				new Point2D(x-1,y),
				new Point2D(x+1,y),
				new Point2D(x,y-1),
				new Point2D(x,y+1)
		};
		List<CaseBN> neighbors = new ArrayList<>();
		for (Point2D p : points){
			if (PointExistant(p)){
				neighbors.add(getCase((int) p.getX(), (int) p.getY()));
			}
		}
		return neighbors.toArray(new CaseBN[0]);
	}
	/* Permet de renvoyer si un placement de bateau est possible
		verifie si le bateau entre dans la grille, puis on verifie si il n'est pas collé a un bateau
	 */
	private boolean VerifPlacementBateau(Bateau bateau, int x, int y){
		int taille = bateau.getTaille();

		if(!bateau.isHorizontal()){
			for (int i = y; i < y + taille; i++){
				if (!PointExistant(x,i))
					return false;

				CaseBN c = getCase(x,i);
				if(c.getBateau() != null)
					return false;

				for (CaseBN neighbor : getNeighbors(x,i)){
					if(!PointExistant(x,i))
						return false;
					if(neighbor.getBateau() != null)
						return false;
				}
			}
		} else {
			for (int i = x; i < x + taille; i++){
				if (!PointExistant(i,y))
					return false;

				CaseBN c = getCase(i,y);
				if (c.getBateau() != null)
					return false;

				for (CaseBN neighbor : getNeighbors(i,y)){
					if(!PointExistant(i,y))
						return false;
					if (neighbor.getBateau() != null)
						return false;
				}
			}
		}
		return true;
	}

	private boolean PointExistant(Point2D point) {
		return PointExistant(point.getX(), point.getY());
	}

	private boolean PointExistant(double x, double y) {
		if (x >= 0 && x < 10 && y >= 0 && y < 10)
			return true;
		return false;

	}

	int getBateau(){
		return this.bateau;
	}

	void setBateau(int i){
		this.bateau = i;
	}

}
