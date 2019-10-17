package fr.projet.bataillenavale;

import java.util.Random;

public class IA extends Joueur {
	private int tableauResult[] = new int[201];
	private int memoireCoups[] = new int[100];
	private GrilleBN tBateau;
	private int cpt;
	private int point;
	
	public IA() {
		super();
		this.tBateau = new GrilleBN(10,10);
		this.cpt = 1;
	}	
	public int getTableauCoup(int numcase) {
		return tableauResult[numcase];
	}

	public void setTableauCoup(int[] tableauCoup) {
		this.tableauResult = tableauCoup;
	}

	public int getCpt() {
		return cpt;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	public GrilleBN gettBateau() {
		return tBateau;
	}

	public boolean RechercheTableauEntier(int[] Tableau, int valeurs) {
		for (int n : Tableau)
			if (Tableau[n] == valeurs)
				return true;
		return false;
	} 
	
	public int coupIA(IA IA, Joueur defenseur) {
		Random rand = new Random();
		int CoorX = 0, CoorY = 0;
		if (tableauResult[cpt - 1] == 0) { // Si le coup précédent est loupé les coordonnées sont choisis aléatoirement entre 1 et 10;
			CoorX = rand.nextInt(9);
			CoorY = rand.nextInt(9);
			while(RechercheTableauEntier(memoireCoups, Integer.parseInt(Integer.toString(CoorX)+Integer.toString(CoorY)))) // On verifie dans le tableau que le coup n'a pas été déjà jouer
			{	
				CoorX = rand.nextInt(9);
				CoorY = rand.nextInt(9);
				
			}
		}
		if (tableauResult[cpt - 1] == 1) { // Si le coup précédent à toucher alors l'IA choisis une case proche de celle qu'elle a touché
			IA.ChoixPossible(CoorX, CoorY);
			while(RechercheTableauEntier(memoireCoups,Integer.parseInt(Integer.toString(CoorX)+Integer.toString(CoorY))))
			{ 	
				CoorX = rand.nextInt(9);
				CoorY = rand.nextInt(9);
			}
		}
		
		System.out.println("");
		System.out.println("A L'IA de jouer.");
		System.out.println("L'IA à choisis la case " + (CoorX) + " " +(CoorY));

		boolean result = Partie.VerifCoup(IA, defenseur, CoorY, CoorY);
		if (result == true) {
			System.out.println("L'IA a touché la cible");
			System.out.println("");
			tableauResult[cpt] = 1;
			cpt++;
			return 1;
		} else {
			System.out.println("L'IA a loupé la cible");
			System.out.println("");
			return 0;

		}
	}
	
	public void ChoixPossible(int CoorX, int CoorY){
		Random rand = new Random();
		if (CoorX == 0) { // Choix si on est sur la premiere ligne 
			if(CoorY == 0) { // Et dans la premiere colonne (0,0) (la case en haut à gauche)
				int choix = rand.nextInt(1);
					if (choix == 0) CoorX++; // On a le droit d'aller soit dans la case du dessous
					if (choix == 1) CoorY--; // Soit dans la case de droite
				
			}
			
			if(CoorY == 9) { // Et dans la derniere colonne (La case en haut à droite)
				int choix = rand.nextInt(1);
				if (choix == 0) CoorX++; // On a le droit d'aller soit dans la case du dessous
				if (choix == 1) CoorY--; // Soit dans la case de gauche
			}
			
			int choix = rand.nextInt(2);
				if(choix == 0) CoorY--; // On peut aller soit dans la case de gauche,
				if(choix == 1) CoorY++; // De droite,
				if(choix == 2) CoorX++; // Ou du dessous?
		}
		
		if (CoorX == 9) { // Choix si on est sur la derniere ligne 
			if(CoorY == 0) { // Et dans la premiere colonne (la case en bas à gauche)
				int choix = rand.nextInt(1);
					if (choix == 0) CoorX--; // On a le droit d'aller dans la case du dessus
					if (choix == 1) CoorY++; // Ou dans la case de droite
				
			}
			
			if(CoorY == 9) { // Et dans la dernière colonne (la case en bas à droite)
				int choix = rand.nextInt(1);
				if (choix == 0) CoorX--; // On a le droit d'aller soit dans la case du dessus
				if (choix == 1) CoorY--; // Soit dans la case de gauche
			}
			
			int choix = rand.nextInt(2);
				if(choix == 0) CoorY--; // On peut aller dans la case de gauche,
				if(choix == 1) CoorY++; // De droite,
				if(choix == 2) CoorX--; // Ou du dessus.
		}
		
		if (CoorY == 0) { // Si on est dans la première colonne
			int choix = rand.nextInt(2);
			if (choix == 0) CoorX++; // On peut choisir la case du dessous,
			if (choix == 1) CoorX--; // Du dessous,
			if (choix == 2) CoorY++; // Ou bien la case de droite
					
		}
		
		if (CoorY == 9) { // Si on est dans la derniere colonne
			int choix = rand.nextInt(2);
			if (choix == 0) CoorX++; // On peut choisir la case du dessous,
			if (choix == 1) CoorX--; // Du dessous,
			if (choix == 2) CoorY--; // Ou bien la case de gauche
		
		}
		
		int choix = rand.nextInt(3); // Si on est dans tout les autres cas
		if (choix == 0) CoorX--;
		if (choix == 1) CoorY++;
		if (choix == 2)	CoorX++;
		if (choix == 3) CoorY--;
} 
} 
