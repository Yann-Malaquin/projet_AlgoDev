package GrilleSudo;

import GrilleEtCase.Grille;

import java.util.ArrayList;
import java.util.Scanner;

import GrilleEtCase.Case;

public class GrilleSudoku extends Grille {

	public GrilleSudoku() {
		super(9, 9);
		this.t = new Case[imax][jmax];
		t = this.initialiserCaseDeLaGrille();

	}

	// met les valeurs des cases à " ' ' " et les rends modifiables
	public void resetGrille() {

		for (Case[] c : this.t) {
			for (Case elem : c) {
				elem.setModifiable(true);
				elem.setVal(' ');

			}

		}
	}

	// Met les valeurs des cases à ' ' uniquement si elles sont modifiables (Servira pour afficher la solution si abandon du joueur)
	public void resetValeurCaseModifiableGrille() {

		for (Case[] c : this.t) {
			for (Case elem : c) {
				if(elem.isModifiable())
				elem.setVal(' ');

			}

		}
	}
	
	
// Permet d'entrer une valeur de 1 à 9 OU ' ', repète la demande tant que c'est pas fait Marche sauf si RIEN n'entré (' ' est différent de RIEN)
	// -> Faire le try-catch
	public void modifierValeur(int x, int y) {
		boolean valeurValide = false;
		if (this.t[x][y].isModifiable()) {
			while (valeurValide == false) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Valeur à entrer ? (entre 1 et 9)");
				String valeurUtilisateur = sc.nextLine();
				if (valeurUtilisateur != null) {
					char valeurEntree = valeurUtilisateur.charAt(0);
					if ((valeurEntree >= '1' && valeurEntree <= '9') || valeurEntree == ' ') {
						this.t[y][x].setVal(valeurEntree);
						valeurValide = true;

					}

				}
			}
		}

	}

	// Vérifie que la valeur d'une case de paramètre donnée soit une valeur allant
	// de 1 à 9

	public boolean valeurCaseValide(int x, int y) {
		if ((this.t[y][x].getVal() >= '1' && this.t[y][x].getVal() <= '9'))
			return false;
		return true;

	}

	// verifie qu'il n'y ait pas plusieurs doublon des char de 1 à 9 dans les sous
	// carré 3x3 du sudoku. 
	public boolean verifierCarre3x3(char c,int i, int j) {
		//plus haute ligne du carre    et colonne plus a gauche du carre
		int PHLC=i-(i%3);
		int CPGC=j-(j%3);
		
		for(int y=PHLC;y<PHLC+3;y++) {
			for(int x=CPGC;x<CPGC+3;x++) {
				if(this.t[y][x].getVal()==c) return false;
			}
		}
		return true;	
	}

	// verifie si un char donné est sur la ligne
	public boolean verifLigneEtColonneCase(char c, int i, int j) {
		int x;
		
		for (x = 0; x < 9; x++) {
			if (this.t[i][x].getVal() ==c)
				return false;
			if (this.t[x][j].getVal() ==c)
				return false;
				
		}
		return true;
	}
	
	  //resous une grille sudoku si possible, permet donc d'en generer.
	// Seul pb, s'il existe déjà une impossibilité dans la grille, le programme sera incapable de trouver une grille valide associée.
	// La valeur de la position sera toujours 0 initialement, ce qui générera entièrement la grille, elle permet une utilisation plus efficace de la récursivité
	public boolean GenererOuResoudre(int position)
	{
	    if (position == 9*9)
	        return true;
// calcul des coordonnées via la position
	    int i = position/9, j = position%9;
// Si la case n'est pas ' ' on laisse la valeur et on passe a la generation de case suivante
	    if (this.t[i][j].getVal() != ' ') {
	    	return GenererOuResoudre(position+1);
	    }
	    
	    // On test chaque valeur
	    for (char k='1'; k <= '9'; k++)
	    {
	    	// Si l'une est plaçable, on la place et on passe à la grille suivante
	        if (this.verifLigneEtColonneCase(k,i,j) && this.verifierCarre3x3(k, i, j))
	        {
	        	
	           this.t[i][j].setVal(k);

	            if ( GenererOuResoudre( position+1) ) {
	            	
	            	
	                return true;
	            }
	            }
	    }
	    
	    this.t[i][j].setVal(' ');
	    return false;
	}  
	
	/*
	 * Vérifie qu'il n'y a pas de doublon dans chaque ligne et colonne de chaque
	 * case et que la valeur de chaque case soit valide. 
	 * Si une valeur existe en double dans un carre 3x3 retourne -2.
	 * S'il existe une valeur présente en double dans une ligne ou colonne retourne -1. 
	 * S'il existe une case avec une valeur n'étant pas supposée être dans un sudoku retourne 0.
	 * retourne 1 si la grille est ok
	 */
	public int verifierGrille() {

		int cptcolonne, cptligne, i;
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (!this.valeurCaseValide(x, y))
					return 0;
				if(!this.verifierCarre3x3(this.t[y][x].getVal(), y, x)) return -2;
				char ValeurCaseEnVerif = t[y][x].getVal();
				cptcolonne = 0;
				cptligne = 0;
				i = 0;
				

				while (i < 9) {
					// verif ligne on garde la ligne y
					if (t[y][i].getVal() == ValeurCaseEnVerif)
						cptligne++;
					// verif colonne, on garde la colonne x
					if (t[i][x].getVal() == ValeurCaseEnVerif)
						cptcolonne++;
			
					i++;
				}
				if (cptcolonne != 1 || cptligne != 1)
					return -1;

			}
		}
		return 1;
	}

	


	

	

//affiche la grille d'une bonne manière pour le sudoku, avec les indices des cases en bordure 
	public void AfficherGrille() {

		int i;
		int j;

		System.out.println("\t\t\t\t\t     0    1    2        3    4    5        6    7    8     ");
		System.out.println("\t\t\t\t\t   -----------------------------------------------------");
		for (i = 0; i < imax; i++) {
			System.out.print("\t\t\t\t\t");
			System.out.print(i + "  ");
			for (j = 0; j < jmax; j++) {

				System.out.print("| " + this.t[i][j].getVal() + " |");
				if (j == 2 || j == 5)
					System.out.print("    ");
			}

			System.out.println("\n\t\t\t\t\t   -----------------------------------------------------");
			if (i == 2 || i == 5)
				System.out.println("\t\t\t\t\t   -----------------------------------------------------");
		}
	}


	public static void main(String args[]) {

		GrilleSudoku g = new GrilleSudoku();
		// g.GenererGrilleValide();
		
		
		g.t[0][0].setVal('9');
		g.t[0][1].setVal('9');
		//System.out.println(g.verifierCarre3x3('4', 0, 0));
   
		System.out.println(g.GenererOuResoudre(0));
       // System.out.println(g.verifierGrille());
		g.AfficherGrille();
		/*
		 * g.modifierValeur(0, 0); g.AfficherGrille();
		 * System.out.println(g.verifierGrille());
		 */

	}
}
