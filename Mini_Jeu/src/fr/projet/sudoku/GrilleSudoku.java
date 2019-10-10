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

	// Met les valeurs des cases à ' ' uniquement si elles sont modifiables (Servira
	// pour afficher la solution si abandon du joueur)
	public void resetValeurCaseModifiableGrille() {

		for (Case[] c : this.t) {
			for (Case elem : c) {
				if (elem.isModifiable())
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
				sc.close();
			}
		}

	}

	// Vérifie que la valeur d'une case de paramètre donnée soit une valeur allant
	// de 1 à 9

	public boolean valeurCaseValide(int x, int y) {
		if ((this.t[y][x].getVal() >= '1' && this.t[y][x].getVal() <= '9'))
			return true;
		return false;

	}

	// verifie qu'il n'y ait pas plusieurs doublon des char de 1 à 9 dans les sous
	// carré 3x3 du sudoku.
	public boolean verifierCarre3x3PourConstruction(char c, int i, int j) {
		// plus haute ligne du carre et colonne plus a gauche du carre
		int PHLC = i - (i % 3);
		int CPGC = j - (j % 3);

		for (int y = PHLC; y < PHLC + 3; y++) {
			for (int x = CPGC; x < CPGC + 3; x++) {
				if (this.t[y][x].getVal() == c)
					return false;
			}
		}
		return true;
	}

	// verifie si un char donné est sur la ligne
	public boolean verifLigneEtColonneCasePourConstruction(char c, int i, int j) {
		int x;

		for (x = 0; x < 9; x++) {
			if (this.t[i][x].getVal() == c)
				return false;
			if (this.t[x][j].getVal() == c)
				return false;

		}
		return true;
	}

	// resous une grille sudoku si possible, permet donc d'en generer.
	// Seul pb, s'il existe déjà une impossibilité dans la grille, le programme sera
	// incapable de trouver une grille valide associée.
	// La valeur de la position sera toujours 0 initialement, ce qui générera
	// entièrement la grille, elle permet une utilisation plus efficace de la
	// récursivité
	public boolean ResoudreSudoku(int position) {
		if (position == 9 * 9)
			return true;
		// calcul des coordonnées via la position
		int i = position / 9, j = position % 9;
		// Si la case n'est pas ' ' on laisse la valeur et on passe a la generation de
		// case suivante
		if (this.t[i][j].getVal() != ' ') {
			return ResoudreSudoku(position + 1);
		}

		// On test chaque valeur
		for (char k = '1'; k <= '9'; k++) {
			// Si l'une est plaçable, on la place et on passe à la grille suivante
			if (this.verifLigneEtColonneCasePourConstruction(k, i, j)
					&& this.verifierCarre3x3PourConstruction(k, i, j)) {

				this.t[i][j].setVal(k);

				if (ResoudreSudoku(position + 1)) {

					return true;
				}
			}
		}

		this.t[i][j].setVal(' ');
		return false;
	}

	
	public void retournerGrilleHorizontalement() {
		char tmp;
		for(int i=0;i<4;i++) {
			for(int j=0;j<9;j++) {
				 tmp=this.t[i][j].getVal();
				 this.t[i][j].setVal(this.t[8-i][j].getVal());
				 this.t[8-i][j].setVal(tmp);
			}
		}
		
	}
	
	public void retournerGrilleVerticalement() {
		char tmp;
		for(int i=0;i<9;i++) {
			for(int j=0;j<4;j++) {
				 tmp=this.t[i][j].getVal();
				 this.t[i][j].setVal(this.t[i][8-j].getVal());
				 this.t[i][8-j].setVal(tmp);
			}
		}
		
	}

	
	
	// Genere des des coordonnées aléatoires, y place 1,2 et 3 (permet la variationd
	// de grille) et genere la grille
	public void GenererGrille() {
		char cpt = '1';
		String tmp= "123456789";
		int rand=(int)(Math.random()*9);
		char randchar=tmp.charAt(rand);
		this.t[0][0].setVal(randchar);
		boolean ok=false;
		
		while (cpt < '4') {
			// On genere les coordonnées aléatoirement
			while(!ok) {
			int randi = (int) (Math.random() * 9);
			int randj = (int) (Math.random() * 9);

			if(this.verifierCarre3x3PourConstruction(cpt, randi, randj) && this.verifLigneEtColonneCasePourConstruction(cpt, randi, randj)) 
					this.t[randi][randj].setVal(cpt);
					ok=true;
}
cpt++;
ok=false;
		}

		this.ResoudreSudoku(0);
		int randRetourner=(int)(Math.random()*3);
		if(randRetourner==1) this.retournerGrilleHorizontalement();
		if(randRetourner==2)this.retournerGrilleVerticalement();
	}

	public boolean verifLignesEtColonnesEtCaseValide() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(!this.valeurCaseValide(i, j)) return false;
				for (int x = 0; x < 9; x++) {

					if (this.t[x][j] != this.t[i][j]) {
						if (this.t[x][j].getVal() == this.t[i][j].getVal())
							return false;
					}
					if (this.t[i][x] != this.t[i][j]) {
						if (this.t[i][x].getVal() == this.t[i][j].getVal())
							return false;
					}
				}

			}
		}
		return true;
	}

	public boolean verifCarre3x3() {
		int PHLC;
		int CPGC;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				PHLC = i - (i % 3);
				CPGC = j - (j % 3);
				for (int y = PHLC; y < PHLC + 3; y++) {
					for (int x = CPGC; x < CPGC + 3; x++) {
						if (this.t[y][x] != this.t[i][j]) {
							if (this.t[y][x].getVal() == this.t[i][j].getVal())
								return false;
						}
					}
				}
			}

		}
		return true;
	}

	/*
	 * Vérifie qu'il n'y a pas de doublon dans chaque ligne et colonne de chaque
	 * case et que la valeur de chaque case soit valide. Si une valeur existe en
	 * double dans un carre 3x3 retourne -2. S'il existe une valeur présente en
	 * double dans une ligne ou colonne retourne -1. S'il existe une case avec une
	 * valeur n'étant pas supposée être dans un sudoku retourne 0. retourne 1 si la
	 * grille est ok
	 */
	public int verifierGrille() {

		if(!this.verifLignesEtColonnesEtCaseValide()) return -1;
		if(!this.verifCarre3x3()) return -2;

		
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
		for(int i=0;i<20;i++) {
		g.GenererGrille();
		g.retournerGrilleVerticalement();
		System.out.println(g.verifLignesEtColonnesEtCaseValide());
		System.out.print(g.verifCarre3x3());
		System.out.println(g.verifierGrille());
		g.AfficherGrille();
		g.resetGrille();
		}

	System.out.println("FINI");	
	} 
	
		
}
