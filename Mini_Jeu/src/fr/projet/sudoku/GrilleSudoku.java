package fr.projet.sudoku;

import fr.projet.*;



import java.util.ArrayList;
import java.util.Scanner;



public class GrilleSudoku extends Grille {

	public GrilleSudoku() {
		super(9, 9);
	}

	// met les valeurs des cases à " ' ' " et les rends modifiables
	public void resetGrille() {

		for (Case[] c : this.getT()) {
			for (Case elem : c) {
				elem.setModifiable(true);
				elem.setVal(" ");

			}

		}
	}

	// Met les valeurs des cases à ' ' uniquement si elles sont modifiables (Servira
	// pour afficher la solution si abandon du joueur)
	public void resetValeurCaseModifiableGrille() {

		for (Case[] c : this.getT()) {
			for (Case elem : c) {
				if (elem.isModifiable())
					elem.setVal(" ");

			}

		}
	}


	

	// Vérifie que la valeur d'une case de paramètre donnée soit une valeur allant
	// de 1 à 9

	public boolean valeurCaseValide(int x, int y) {
		if (this.getT()[y][x].getVal().compareTo("1")==0  || this.getT()[y][x].getVal().compareTo("2")==0  ||this.getT()[y][x].getVal().compareTo("3")==0  ||this.getT()[y][x].getVal().compareTo("4")==0  ||this.getT()[y][x].getVal().compareTo("5")==0  ||this.getT()[y][x].getVal().compareTo("6")==0  ||this.getT()[y][x].getVal().compareTo("7")==0  ||this.getT()[y][x].getVal().compareTo("8")==0  ||this.getT()[y][x].getVal().compareTo("9")==0)
			return true;
		return false;

	}

	// verifie qu'il n'y ait pas plusieurs doublon des char de 1 à 9 dans les sous
	// carré 3x3 du sudoku.
	public boolean verifierCarre3x3PourConstruction(String c, int i, int j) {
		// plus haute ligne du carre et colonne plus a gauche du carre
		int PHLC = i - (i % 3);
		int CPGC = j - (j % 3);

		for (int y = PHLC; y < PHLC + 3; y++) {
			for (int x = CPGC; x < CPGC + 3; x++) {
				if (this.getT()[y][x].getVal().compareTo(c)==0 )
					return false;
			}
		}
		return true;
	}

	// verifie si un char donné est sur la ligne
	public boolean verifLigneEtColonneCasePourConstruction(String c, int i, int j) {
		int x;

		for (x = 0; x < 9; x++) {
			if (this.getT()[i][x].getVal().compareTo(c)==0)
				return false;
			if (this.getT()[x][j].getVal().compareTo(c)==0)
				return false;

		}
		return true;
	}

	public boolean verifValeurPourConstruction(String c, int i, int j) {
		
		if(this.verifierCarre3x3PourConstruction(c, i, j)&& this.verifLigneEtColonneCasePourConstruction(c, i, j)) return true;
		return false;
	}
	
	// resous une grille sudoku si possible, permet donc d'en generer.
	// La valeur de la position sera toujours 0 initialement, ce qui générera
	// entièrement la grille, elle permet une utilisation plus efficace de la
	// récursivité
	public boolean ResoudreSudoku(int position) {
		String tmp=" ";
		//Si on est arrivée à la 81ème case, alors c'est qu'on a généré toutes les cases
		if (position == 9 * 9)
			return true;
		// calcul des coordonnées via la position
		int i = position / 9, j = position % 9;
		// Si la case n'est pas ' ' on laisse la valeur et on passe a la generation de
		// case suivante
		if (this.getT()[i][j].getVal().compareTo(" ")!=0 ) {
			return ResoudreSudoku(position + 1);
		}

		// On test chaque valeur
		for (char k = '1'; k <= '9'; k++) {
			if(k=='1') tmp="1";if(k=='2') tmp="2";if(k=='3') tmp="3";if(k=='4') tmp="4";if(k=='5') tmp="5";if(k=='6') tmp="6";if(k=='7') tmp="7";if(k=='8') tmp="8";if(k=='9') tmp="9";
			
			
			// Si l'une est plaçable, on la place et on passe à la grille suivante
			if (this.verifLigneEtColonneCasePourConstruction(tmp, i, j)
					&& this.verifierCarre3x3PourConstruction(tmp, i, j)) {

				this.getT()[i][j].setVal(tmp);

				if (ResoudreSudoku(position + 1)) {

					return true;
				}
			}
		}

		this.getT()[i][j].setVal(" ");
		return false;
	}

	// inverse les valeurs symetriquement
	public void retournerGrilleHorizontalement() {
		String tmp;
		for(int i=0;i<4;i++) {
			for(int j=0;j<9;j++) {
				 tmp=this.getT()[i][j].getVal();
				 this.getT()[i][j].setVal(this.getT()[8-i][j].getVal());
				 this.getT()[8-i][j].setVal(tmp);
			}
		}
		
	}
	// inverse les valeurs symetriquement
	public void retournerGrilleVerticalement() {
		String tmp;
		for(int i=0;i<9;i++) {
			for(int j=0;j<4;j++) {
				 tmp=this.getT()[i][j].getVal();
				 this.getT()[i][j].setVal(this.getT()[i][8-j].getVal());
				 this.getT()[i][8-j].setVal(tmp);
			}
		}
		
	}

	
	
	// Genere des des coordonnées aléatoires, y place 1,2 et 3 (permet la variation
	// de grille) et genere la grille en resolvant celle créée
	public void GenererGrille() {
		char cpt = '1';
		String tmp= "123456789";
		String tmp2=" ";
		String tmp3=" ";
		int rand=(int)(Math.random()*9);
		char randchar=tmp.charAt(rand);
		if(randchar=='1') tmp2="1";if(randchar=='2') tmp2="2";if(randchar=='3') tmp2="3";if(randchar=='4') tmp2="4";if(randchar=='5') tmp2="5";if(randchar=='6') tmp2="6";if(randchar=='7') tmp2="7";if(randchar=='8') tmp2="8";if(randchar=='9') tmp2="9";
		this.getT()[0][0].setVal(tmp2);
		boolean ok=false;
		
		while (cpt < '4') {
			// On genere les coordonnées aléatoirement
			while(!ok) {
			int randi = (int) (Math.random() * 9);
			int randj = (int) (Math.random() * 9);
			if(cpt=='1') tmp3="1";if(cpt=='2') tmp3="2";if(cpt=='3') tmp3="3";if(cpt=='4') tmp3="4";if(cpt=='5') tmp3="5";if(cpt=='6') tmp3="6";if(cpt=='7') tmp3="7";if(cpt=='8') tmp3="8";if(cpt=='9') tmp3="9";;
			if(this.verifierCarre3x3PourConstruction(tmp3, randi, randj) && this.verifLigneEtColonneCasePourConstruction(tmp3, randi, randj)) 
					this.getT()[randi][randj].setVal(tmp3);
					ok=true;
				}
				cpt++;
				ok=false;
		}
		// On génère un random qui va potentiellement retourner la grille pour rendre la résolution moins évidente
		this.ResoudreSudoku(0);
		int randRetourner=(int)(Math.random()*3);
		if(randRetourner==1) this.retournerGrilleHorizontalement();
		if(randRetourner==2)this.retournerGrilleVerticalement();
	}

	// Verifie sur toute la grille, qu'il n'y a pas de doublon dans les lignes, colonnes, et que les valeur entrée soient valides (entre 1 et 9)
	public boolean verifLignesEtColonnesEtCaseValide() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(!this.valeurCaseValide(i, j)) return false;
				for (int x = 0; x < 9; x++) {

					if (this.getT()[x][j] != this.getT()[i][j]) {
						if (this.getT()[x][j].getVal().compareTo( this.getT()[i][j].getVal())==0)
							return false;
					}
					if (this.getT()[i][x] != this.getT()[i][j]) {
						if (this.getT()[i][x].getVal().compareTo( this.getT()[i][j].getVal())==0)
							return false;
					}
				}

			}
		}
		return true;
	}
// Verifie qu'aucune valeur n'est en double dans un carré 3x3
	public boolean verifCarre3x3() {
		int PHLC;
		int CPGC;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				PHLC = i - (i % 3);
				CPGC = j - (j % 3);
				for (int y = PHLC; y < PHLC + 3; y++) {
					for (int x = CPGC; x < CPGC + 3; x++) {
						if (this.getT()[y][x] != this.getT()[i][j]) {
							if (this.getT()[y][x].getVal().compareTo(this.getT()[i][j].getVal())==0)
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
		for (i = 0; i < this.getImax(); i++) {
			System.out.print("\t\t\t\t\t");
			System.out.print(i + "  ");
			for (j = 0; j < this.getJmax(); j++) {
				if(this.getT()[i][j].isModifiable()) {
				System.out.print("| " + this.getT()[i][j].getVal() + " |");}
				else { System.err.print("\033[31m" + "| " + this.getT()[i][j].getVal() + " |" + "\033[30m");}
				if (j == 2 || j == 5)
					System.out.print("    ");
			}

			System.out.println("\n\t\t\t\t\t   -----------------------------------------------------");
			if (i == 2 || i == 5)
				System.out.println("\t\t\t\t\t   -----------------------------------------------------");
		}
	}

}
