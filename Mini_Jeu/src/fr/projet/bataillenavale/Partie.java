package fr.projet.bataillenavale;

import java.util.Random;
import java.util.Scanner;

public class Partie {
	Joueur j1;
	Joueur j2;
	IA IA;
	Scanner sc = new Scanner(System.in);
	
public Partie(Joueur j1, IA IA) {
	this.j1 = j1;
	this.IA = IA;
}
public Partie(Joueur j1, Joueur j2) {
	this.j1 = j1;
	this.j2 = j2;
}

/*
 * Methode qui sert a placer le bateau selon la position (Horizontale ou
 * verticale)
 */
public void placerBateau(Joueur joueur) {
	joueur.AfficherGrilleBateau();
	
	for(Bateau b : Bateau.getTabBateau()) {
		boolean verif = true;
		int Coordonnee;
		if (b.getPosition() == true) { 
			System.out.println("Votre " + b.getNom() + " de taille : " + b.getTaille() +"  va etre placer horizontalement");}
		else { 
			System.out.println("Votre " + b.getNom() + " de taille : " + b.getTaille() +"  va etre placer verticalement");}
			System.out.println("Choisissez la base de votre bateau (Colonne) ");
		int x = sc.nextInt();
		if(x < 0 || x > 9)
		{
			System.out.println("Veuillez entrer un nombre entre 0 et 9");
			x = sc.nextInt();
		}
		System.out.println("Choisissez la base de votre bateau (Ligne) ");
		
		int y = sc.nextInt();
		if(y < 0 || y > 9)
			{
				System.out.println("Veuillez entrer un nombre entre 0 et 9");
				y = sc.nextInt();
			}

		
		int taille = b.getTaille();
		if (b.getPosition() == true) {
			if(x+(taille-1) > 9) {
			System.out.println("Votre bateau a ete decaler afin de rentrer dans le tableau");
			x = 9-(taille-1);
			}
			for (int i = 0; i<taille;i++) {
				if(joueur.gettBateau().getT()[y][x+i].isModifiable() == false)
					verif = false;
				}
			if (verif = false) {
				System.out.println("Une des case des deja occupe veuillez entrez des coordonnees qui ne rentre pas en conflit\n"); 
				System.out.println("\t Entrez votre Colonne : ");
				x = sc.nextInt();
				System.out.println("\t Entrez votre Ligne : ");
				y = sc.nextInt();
				
			}
			
			for (int i = 0; i<taille ;i++) {
							joueur.gettBateau().getT()[y][x+i].setVal("X");
							joueur.gettBateau().getT()[y][x+i].setModifiable(false);
							Coordonnee = Integer.valueOf(String.valueOf(x+i) + String.valueOf(y));
							joueur.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
				}
		
		}
		
		if (b.getPosition() == false) {
			if(y+(taille-1) > 9) {
				System.out.println("Votre bateau a ete decaler afin de rentrer dans le tableau");
				y = 9-(taille-1);
							}
			
			for (int i = 0;i< taille; i++) {
					if(joueur.gettBateau().getT()[y+i][x].isModifiable() == false)
						verif = false;
									}
			if (verif = false) {
				System.out.println("Une des case des deja occupe veuillez entrez des coordonnees qui ne rentre pas en conflit\n\n"); 
				System.out.println("\t Entrez votre Colonne : ");
				x = sc.nextInt();
				System.out.println("\t Entrez votre Ligne : ");
				y = sc.nextInt();}
	
			for (int i =0;i< taille; i++){
				joueur.gettBateau().getT()[y+i][x].setVal("X");
				joueur.gettBateau().getT()[y+i][x].setModifiable(false);
				Coordonnee = Integer.valueOf(String.valueOf(x) + String.valueOf(y+i));
				joueur.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
										}
		
									}
		joueur.AfficherGrilleBateau();
	}
	
}
		



  public void PlacerBateauIA(IA IA) {
	int choixX = 0;
	int choixY = 0;
	int Coordonnee;
	Random rand = new Random();
	for (Bateau b : Bateau.getTabBateau()) {
		if (b.getPosition() == false) {
			if (b.getTaille() == 5) {
				choixX = rand.nextInt(5);
				choixY = rand.nextInt(9);
				for (int i = 0; i<b.getTaille() ;i++) {
					IA.gettBateau().getT()[choixX+i][choixY].setVal("X");
					IA.gettBateau().getT()[choixX+i][choixY].setModifiable(false);
					Coordonnee = Integer.valueOf(String.valueOf(choixX+i) + String.valueOf(choixY));
					IA.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
					}
			}
			if (b.getTaille() == 4) {
				choixX = rand.nextInt(6);
				choixY = rand.nextInt(9);
				for (int i = 0; i<b.getTaille() ;i++) {
					IA.gettBateau().getT()[choixX+i][choixY].setVal("X");
					IA.gettBateau().getT()[choixX+i][choixY].setModifiable(false);
					Coordonnee = Integer.valueOf(String.valueOf(choixX+i) + String.valueOf(choixY));
					IA.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
					}
			}
			if (b.getTaille() == 3) {
				choixX = rand.nextInt(7);
				choixY = rand.nextInt(9);
				for (int i = 0; i<b.getTaille() ;i++) {
					IA.gettBateau().getT()[choixX+i][choixY].setVal("X");
					IA.gettBateau().getT()[choixX+i][choixY].setModifiable(false);
					Coordonnee = Integer.valueOf(String.valueOf(choixX+i) + String.valueOf(choixY));
					IA.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
					}
			}
			if (b.getTaille() == 2) {
				choixX = rand.nextInt(8);
				choixY = rand.nextInt(9);
				for (int i = 0; i<b.getTaille() ;i++) {
					IA.gettBateau().getT()[choixX+i][choixY].setVal("X");
					IA.gettBateau().getT()[choixX+i][choixY].setModifiable(false);
					Coordonnee = Integer.valueOf(String.valueOf(choixX+i) + String.valueOf(choixY));
					IA.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
					}
			}

		}
		
		if (b.getPosition() == true){
			if (b.getTaille() == 5) {
				choixX = rand.nextInt(9);
				choixY = rand.nextInt(5);
				for (int i = 0; i<b.getTaille() ;i++) {
					IA.gettBateau().getT()[choixY][choixX+i].setVal("X");
					IA.gettBateau().getT()[choixY][choixY+i].setModifiable(false);
					Coordonnee = Integer.valueOf(String.valueOf(choixX+i) + String.valueOf(choixY));
					IA.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
					}
			}
			if (b.getTaille() == 4) {
				choixX = rand.nextInt(9);
				choixY = rand.nextInt(6);
				for (int i = 0; i<b.getTaille() ;i++) {
					IA.gettBateau().getT()[choixY][choixX+i].setVal("X");
					IA.gettBateau().getT()[choixY][choixY+i].setModifiable(false);
					Coordonnee = Integer.valueOf(String.valueOf(choixX+i) + String.valueOf(choixY));
					IA.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
					}
			}
			if (b.getTaille() == 3) {
				choixX = rand.nextInt(9);
				choixY = rand.nextInt(7);
				for (int i = 0; i<b.getTaille() ;i++) {
					IA.gettBateau().getT()[choixY][choixX+i].setVal("X");
					IA.gettBateau().getT()[choixY][choixY+i].setModifiable(false);
					Coordonnee = Integer.valueOf(String.valueOf(choixX+i) + String.valueOf(choixY));
					IA.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
					}
			}
			if (b.getTaille() == 2) {
				choixX = rand.nextInt(9);
				choixY = rand.nextInt(8);
				for (int i = 0; i<b.getTaille() ;i++) {
					IA.gettBateau().getT()[choixY][choixX+i].setVal("X");
					IA.gettBateau().getT()[choixY][choixY+i].setModifiable(false);
					Coordonnee = Integer.valueOf(String.valueOf(choixX+i) + String.valueOf(choixY));
					IA.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
					}
			}
			for (int i =0;i< b.getTaille(); i++){
				IA.gettBateau().getT()[choixY+i][choixX].setVal("X");
				IA.gettBateau().getT()[choixY][choixY+i].setModifiable(false);
				Coordonnee = Integer.valueOf(String.valueOf(choixX) + String.valueOf(choixX+i));
				IA.gettBateau().ajouterElementMap(b.getNom(), Coordonnee);
			}
		}
	}
	
} 

public static boolean VerifCoup (Joueur attaquant, Joueur defenseur, int x, int y) {
		boolean hyp = false;
	if (defenseur.gettBateau().getT()[y][x].getVal() == "X"){
			attaquant.gettUser().getT()[y][x].setVal("X");
			//grilleJoueur2.retirerElementMap(Integer.valueOf(String.valueOf(x) + String.valueOf(y)));
			//grilleJoueur2.couler();
			hyp = true;
		return hyp;
		}
	else if(defenseur.gettBateau().getT()[y][x].getVal() == " ") {
		attaquant.gettUser().getT()[y][x].setVal("O");
		hyp = false;
		return hyp;
			}
	return hyp;
}

		
public void JouerCoup(Joueur attaquant, Joueur defenseur) {
	System.out.println("");
	System.out.println(attaquant.getNickname() + " A vous de jouer");
	System.out.println("Entrez la case X");
	int CoorX = sc.nextInt();
	if(CoorX < 0 || CoorX > 9)
	{
		System.out.println("Veuillez entrer un nombre entre 0 et 9");
		CoorX = sc.nextInt();
	}
	System.out.println("Entrez la case Y");
	int CoorY = sc.nextInt();
	if(CoorY < 0 || CoorX > 9) {
		System.out.println("Veuillez entrer un nombre entre 0 et 9");
		CoorY = sc.nextInt();
	}
	boolean result = VerifCoup(attaquant, defenseur, CoorX, CoorY);
	if (result == true) {
		System.out.println("Bien jou�, cible touch�e!");
		System.out.println("");
		attaquant.ajouterPoint(1);
	} else {
		System.out.println("Arf dommmage, cible loup�e");
		System.out.println("");
	}
	attaquant.AfficherGrilleJoueur();
}

/*
 * M�thode qui fait tourner TOUT le jeu. Alors on a un compteur (cpt) qui est
 * initialiser a un nombre al�atoire pour d�finir le joueur qui va commencer. On
 * a creer une boucle avec ce compteur qui va etre incr�ment� � l'infini, et une
 * condition d'arret. Quand un des joueur atteint le nombre de point necessaire
 * pour gagner la partie (le nombre de case � couler) la partie s'arrete.
 * Ensuite le jeu tour � tour est ger� par le compteur qui est incr�ment� et qui
 * d�fini le joueur qui joue en fonction de si il est pair o� non.
 */

public void DemarrerPartie(Joueur Joueur1, Joueur Joueur2) {
	System.out.println(Joueur1.getNickname() + " Placez vos bateaux");
	placerBateau(Joueur1);
	System.out.println(Joueur2.getNickname() + " Placez vos bateaux");
	placerBateau(Joueur2);
	int cpt = (int) (Math.random() * 2);
	boolean PartieFini = false;
	while (PartieFini == false) {
		if (cpt % 2 == 0) {
			JouerCoup(Joueur1, Joueur2);
			if (Joueur1.getPoint() == Bateau.getTotalcase()) {
				PartieFini = true;
			}
		}
		if (cpt % 2 == 1) {
			JouerCoup(Joueur2, Joueur1);
			if (Joueur2.getPoint() == Bateau.getTotalcase()) {
				PartieFini = true;
			}
		}
		cpt++;
		System.out.println("Point " + Joueur1.getNom() + " : " + Joueur1.getPoint());
		System.out.println("Point " + Joueur2.getNom() + " : " + Joueur2.getPoint());
	}

}


public void DemarrerPartieIA(Joueur Joueur1, IA IA, GrilleBN GrilleJoueur1, GrilleBN GrilleIA) {
	System.out.println(Joueur1.getNickname() + " Placez vos bateaux");
	placerBateau(Joueur1);
	PlacerBateauIA(IA);
	int cpt = (int) (Math.random() * 2);
	boolean PartieFini = false;
	while (PartieFini == false) {
		if (cpt % 2 == 0) {
			if (cpt % 2 == 0) {
				JouerCoup(Joueur1, IA);
				if (Joueur1.getPoint() == Bateau.getTotalcase()) {
					PartieFini = true;
				}
			}
			if (cpt % 2 == 1) {
				IA.coupIA(IA, Joueur1);
				if (IA.getPoint() == Bateau.getTotalcase()) {
					PartieFini = true;
				}
			}
		cpt++;
		System.out.println("Point " + Joueur1.getNom() + " : " + Joueur1.getPoint());
		System.out.println("Point " + IA.getNom() + " : " + IA.getPoint());
						}			
					}
				}
}