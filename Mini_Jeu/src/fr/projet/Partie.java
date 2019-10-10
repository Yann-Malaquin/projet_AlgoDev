package fr.projet.bataillenavale;

import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
	Joueur j1;
	ArrayList<Bateau> ListeBateau;
	IA IA;
	GrilleBN grille;
	Scanner sc = new Scanner(System.in);
	
public Partie(Joueur j1, ArrayList<Bateau> ListeBateau, IA IA, GrilleBN grille) {
	this.j1 = j1;
	this.ListeBateau = ListeBateau;
	this.IA = IA;
	this.grille = grille;
}

/*
 * Méthode qui sert a placer le bateau selon la position (Horizontale ou
 * verticale)
 */
public void placerBateau(Bateau b, int x, int y, GrilleBN grille) {
		boolean position = b.getPosition();
		int taille = b.getTaille();
		if (b.getPosition() == true) {
			for (int i = 0; i<taille ;i++) {
				this.grille.getTBateau()[y][x+i].setVal('X');
			}
		if (b.getPosition() == false) {
			for (int i =0;i< taille; i++)
				this.grille.getTBateau()[y+i][x].setVal('X');
		}
		
		}
}		
			

public static boolean VerifCoup (int x, int y, GrilleBN grille) {
	if (grille.getTBateau()[y][x].getVal() == 'X') {
		grille.getTUser()[y][x].setVal('X');
		return true;
		}

	grille.getTUser()[y][x].setVal('O');
	return false;
	}

		
public int JouerCoup(Joueur Joueur, GrilleBN grille) {
	System.out.println("");
	System.out.println(Joueur.getNom() + " A vous de jouer");
	System.out.println("Entrez la case X");
	int CoorX = sc.nextInt() - 1;
	System.out.println("Entrez la case Y");
	int CoorY = sc.nextInt() - 1;
	boolean result = VerifCoup(CoorX, CoorY, grille);
	if (result == true) {
		System.out.println("Bien joué, cible touchée!");
		System.out.println("");
		return 1;
	} else {
		System.out.println("Arf dommmage, cible loupée");
		System.out.println("");
		return 0;
	}
}

/*
 * Méthode qui fait tourner TOUT le jeu. Alors on a un compteur (cpt) qui est
 * initialiser a un nombre aléatoire pour définir le joueur qui va commencer. On
 * a creer une boucle avec ce compteur qui va etre incrémenté à l'infini, et une
 * condition d'arret. Quand un des joueur atteint le nombre de point necessaire
 * pour gagner la partie (le nombre de case à couler) la partie s'arrete.
 * Ensuite le jeu tour à tour est geré par le compteur qui est incrémenté et qui
 * défini le joueur qui joue en fonction de si il est pair où non.
 */

public void DemarrerPartie(Joueur Joueur1, Joueur Joueur2, GrilleBN GrilleJoueur1, GrilleBN GrilleJoueur2) {
	int cpt = (int) (Math.random() * 2);
	while (Joueur1.getCoupToucher() != Bateau.getTotalcase() || Joueur2.getCoupToucher() != Bateau.getTotalcase() ) {
		if (cpt % 2 == 0) {
			if (JouerCoup(Joueur1, GrilleJoueur2) == 1) {
				Joueur1.setPoint(Joueur1.getCoupToucher() + 1);
			}
			System.out.println("");
			GrilleJoueur2.AfficherGrille();
		}
		if (cpt % 2 == 1) {
			if (JouerCoup(Joueur2, GrilleJoueur1) == 1) {
				Joueur2.setPoint(Joueur2.getCoupToucher() + 1);
			}
			System.out.println("");
			GrilleJoueur2.AfficherGrille();
		}
		cpt++;
	}

}
}
