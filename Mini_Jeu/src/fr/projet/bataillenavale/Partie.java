package fr.projet.bataillenavale;

import java.util.ArrayList;

public class Partie {
	Joueur j1;
	ArrayList<Bateau> ListeBateau;
	IA IA;
	GrilleBN grille;
	
public Partie(Joueur j1, ArrayList<Bateau> ListeBateau, IA IA, GrilleBN grille) {
	this.j1 = j1;
	this.ListeBateau = ListeBateau;
	this.IA = IA;
	this.grille = grille;
}

public void placerBateau(Bateau b, int x, int y, GrilleBN grille) {
		boolean position = b.getPosition();
		int taille = b.getTaille();
		if (b.getPosition() == true) {
			this.grille.
		}
			
			
	}
}