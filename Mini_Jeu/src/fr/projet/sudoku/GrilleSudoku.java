package GrilleSudo;

import GrilleEtCase.Grille;

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
	
	
// Permet d'entrer une valeur de 1 à 9 OU ' ', repète la demande tant que c'est pas fait Marche sauf si RIEN n'entré (' ' est différent de RIEN)
	public void modifierValeur(int x, int y) {
		boolean valeurValide = false;
		if (this.t[x][y].isModifiable()) {
			while (valeurValide == false) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Valeur à entrer ? (entre 1 et 9)");
				String valeurUtilisateur = sc.nextLine();
				if (valeurUtilisateur != null) {
					char valeurEntree = valeurUtilisateur.charAt(0);
					if ((valeurEntree >= '1' && valeurEntree<='9') ||  valeurEntree == ' ') {
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
		if ((this.t[y][x].getVal() >= '1' && this.t[y][x].getVal()<='9'))
			return false;
		return true;

	}

	/*
	 * Vérifie qu'il n'y a pas de doublon dans chaque ligne et colonne de chaque
	 * case et que la valeur de chaque case soient valides retourne -1 s'il existe
	 * une valeur présente en double dans une ligne ou colonne retourne 0 s'il
	 * existe une case avec une valeur n'étant pas supposée être dans un sudoku
	 * retourne 1 si la grille est ok
	 */
	public int verifierGrille() {
		boolean grilleValide = true;
		int cptcolonne, cptligne, i;

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (!this.valeurCaseValide(x, y))
					return 0;
				char ValeurCaseEnVerif = t[y][x].getVal();
				cptcolonne = 0;
				cptligne = 0;
				i = 0;

				while (grilleValide && i < 9) {
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

	public static void main(String args[]) {

		GrilleSudoku g = new GrilleSudoku();
		g.modifierValeur(0, 0);
		g.AfficherGrille();
		System.out.println(g.verifierGrille());

	}
}
