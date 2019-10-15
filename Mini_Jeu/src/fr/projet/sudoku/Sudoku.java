package fr.projet.sudoku;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sudoku {


	private ArrayList<Joueur> listeJoueur;

	public Sudoku() {
		super();
		this.listeJoueur = new ArrayList<Joueur>();
	}

	/*
	 * Jouer va lancer une partie selon la difficulté passée en parametre certains
	 * parametre sont modifiable comme le nombre d'erreurs maximum
	 */
	public void Jouer(int difficulte) throws IOException // 1=facile, 2=moyen, 3=difficile
	{

		Scanner sc = new Scanner(System.in);
		String nomFichier = "src/fr/sudoku/listeJoueurs.txt";

		int score = 0;
		boolean continuer = true;
		boolean pseudoInvalide = true;
		int erreur = 0;
		int erreurMax = 30;// parametre modifiable

		File f = new File(nomFichier);
		if (!f.exists()) {
			nomFichier = "listeJoueurs.txt";
			f = new File(nomFichier);
			f.createNewFile();
		}

		ChargerListe(nomFichier);

		/*
		 * On fait entrer au joueur un pseudo valide
		 */
		String pseudo = "defaut";
		while (pseudoInvalide) {
			System.out.println("Bonjour ! Entrer un pseudo s'il vous plait");
			pseudo = sc.nextLine();

			if ((pseudo != null) && (pseudo.length() > 0))
				pseudoInvalide = false;
		}
		Joueur joueur = new Joueur(pseudo);

		/*
		 * On creer une grille pleine, que l'on copie dans grille valide afin de nous
		 * servir de solution cela eviter des calculs inutiles On rend egalement la
		 * grille valide non modifiable par precaution
		 */
		GrilleSudoku grille = new GrilleSudoku();
		grille.GenererGrille();

		grilleNonModifiable(grille);
		GrilleSudoku grilleValide = new GrilleSudoku();

		for (int a = 0; a < 9; a++)
			for (int b = 0; b < 9; b++) {
				grilleValide.getT()[a][b].setVal(grille.getT()[a][b].getVal());
				grilleValide.getT()[a][b].setModifiable(false);
			}

		gestionDifficulte(difficulte, grille);

		/*
		 * On lance la partie
		 */
		System.out.println("Bienvenue " + joueur.getPseudo() + " !         Voici ta grille !\n");
		grille.AfficherGrille();

		System.out.println("Vous pouvez faire jusqu'a " + erreurMax + " erreurs.");

		int i = 0, j = 0;
		String val = " ";

		boolean identique = false;

		while (continuer) {
			while ((erreur < erreurMax) && (!identique)) {
				/*
				 * On recupere les information puis on verifie que tout est valide
				 */
				System.out.println("Vous avez actuellement " + erreur + " erreurs.");
				boolean saisieCorrect = false;
				while (!saisieCorrect) {
					try {
						sc = new Scanner(System.in);
						System.out.println("Entrer la ligne :");
						i = sc.nextInt();
						System.out.println("Entrer la colonne :");
						j = sc.nextInt();
						sc.nextLine();
						System.out.println("Entrer la valeur :");
						val = sc.nextLine();
					} catch (InputMismatchException e) {
						System.err.println("Merci d'entrer une reponse valide");
					} catch (IndexOutOfBoundsException e) {
						e.printStackTrace();
						System.err.println("N'appuyer pas sur Entrer pauvre fou !!!");
					}

					if (i >= 0 && i < 9 && j >= 0 && j < 9
							&& ((val.equals("1")) || (val.equals("2")) || (val.equals("3")) || (val.equals("4"))
									|| (val.equals("5")) || (val.equals("6")) || (val.equals("7")) || (val.equals("8"))
									|| (val.equals("9")))) {
						saisieCorrect = true;
					} else
						System.out.println("Erreur lors de la saisie d'une des 3 entrees");

				}

				/*
				 * Si la case a la position indiquee est modifiable, on verifie que la valeur
				 * choisie est la meme que celle de la grille valide pour ensuite faire une
				 * verification rapide, pour confirmer ou non que la grille est remplie sinon on
				 * demande a nouveau la valeur
				 */
				if ((grille.getT()[i][j].isModifiable())) {
					if ((grilleValide.getT()[i][j].getVal().equals(val))) {
						grille.getT()[i][j].setVal(val);
						System.out.println("Correct !!!!");
						grille.AfficherGrille();

						boolean compare = true;
						for (int x = 0; x < 9; x++) {
							for (int y = 0; y < 9; y++) {
								if (!grilleValide.getT()[x][y].getVal().equals(grille.getT()[x][y].getVal()))
									compare = false;
							}
						}
						/*
						 * Si tout est replie, on calcul le score, et on demande a l'utilisateur s'il
						 * veux continuer a jouer dans le meme mode de jeu
						 */
						if (compare) {
							identique = true;
							System.out.println("Wow ! Tu as fini ! Felicitations !");
							score += calculScore(erreur, difficulte);
							joueur.setScore(score);
							System.out.println(
									"Voulez vous rejouer pour augmenter votre score ?\nEntrer 1 pour oui et 0 pour non");
							int reponse = sc.nextInt();
							if (reponse == 1)
								continuer = true;
							else
								continuer = false;
							EnregistrerListe(nomFichier);

						}
					} else {
						System.out.println("Valeur incorrecte ! Reessaye");
						erreur++;
						if (erreur == erreurMax)
							System.out.println(
									"Malheureusement tu as fait trop d'erreurs. Tu feras mieux la prochaine fois !");
					}

				} else
					System.out.println("Erreur : la case selectionnee n'est pas remplissable");

			}

		}

	}

	/*
	 * Calcule le score d'un joueur en fonction des parametres predefini La maniere
	 * de calculer un score peut etre modifier
	 */
	public int calculScore(int erreurs, int difficulte) {
		return (50 - erreurs / difficulte);
	}

	/*
	 * Retourne un boolean qui definit si la valeur entree est valide
	 */
	public boolean valeurValide(GrilleSudoku grille, char val, int i, int j) {
		if (grille.getT()[i][j].getVal().equals(val))
			return true;
		else
			return false;
	}

	/*
	 * Rend une grille non modifiable
	 */
	public void grilleNonModifiable(GrilleSudoku grille) {
		for (int i = 0; i < grille.getImax(); i++) {
			for (int j = 0; j < grille.getJmax(); j++) {
				grille.getT()[i][j].setModifiable(false);
			}
		}
	}

	/*
	 * Faire des trous dans le grille en fonction de la difficulte selectionné
	 */
	public void gestionDifficulte(int difficulte, GrilleSudoku grille) {
		int max = 0;
		if (difficulte == 1)
			max = 3;
		else if (difficulte == 2)
			max = 5;
		else if (difficulte == 3)
			max = 6;
		else
			System.err.println("Erreur lors de la selection de la difficulté");

		for (int a = 0; a < 9; a += 3)
			for (int b = 0; b < 9; b += 3) {
				PlacerTrou3x3(a, b, max, grille);
			}
	}

	public void PlacerTrou3x3(int a, int b, int max, GrilleSudoku grille) {
		int compteur = 0;
		// prend la case en haut a gauche de chaque block 3x3
		int A = a - (a % 3);
		int B = b - (b % 3);

		while (compteur < max) {
			for (int y = A; y < A + 3; y++) {
				for (int x = B; x < B + 3; x++) {
					if (((int) (1 + Math.random() * (3)) == 2) && (!grille.getT()[y][x].getVal().contentEquals(" "))) {
						compteur++;
						grille.getT()[y][x].setVal(" ");
						grille.getT()[y][x].setModifiable(true);
					}
				}
			}
		}
	}

	/*
	 * Ajoute un Joueur a la liste
	 */
	public void addJoueur(Joueur j) {
		if (listeJoueur == null)
			this.listeJoueur = new ArrayList<Joueur>();
		listeJoueur.add(j);
	}

	/*
	 * Retire le joueur entree de la liste
	 */
	public void delJoueur(Joueur j) {
		for (Joueur x : listeJoueur) {
			if (j.equals(x)) {
				listeJoueur.remove(x);
			}
		}
	}

	/*
	 * Transforme une liste en String
	 */
	public String listeToString() {
		String liste = null;
		for (Joueur jx : this.listeJoueur) {
			liste += "" + jx.getPseudo() + "," + jx.getScore() + "\n";
		}
		return liste;
	}

	/*
	 * Transform un String en Joueur qui est ensuite ajouter a la liste
	 */
	public void toStringListe(String joueur) {
		String[] info = joueur.split(",");
		Joueur j = new Joueur();
		j.setPseudo(info[0]);
		j.setScore(Integer.parseInt(info[1]));
		addJoueur(j);
	}

	/*
	 * Getter + Setter
	 */
	public ArrayList<Joueur> getListeJoueur() {
		return listeJoueur;
	}

	public void setListeJoueur(ArrayList<Joueur> listeJoueur) {
		this.listeJoueur = listeJoueur;
	}

	/*
	 * Enregistre la liste dans un fichier
	 */
	public void EnregistrerListe(String nomFichier) {
		FileOutputStream ecrire = null;
		File f;
		String liste = null;
		liste = listeToString();

		try {
			f = new File(nomFichier);
			ecrire = new FileOutputStream(f);

			// si le fichier n'existe pas
			if (!f.exists()) {
				f.createNewFile();
			}

			// convertion en binaires
			byte[] listeBinaire = liste.getBytes();

			ecrire.write(listeBinaire);
			ecrire.flush();
			ecrire.close();

			System.out.println("...Données enregistree...");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ecrire != null)
					ecrire.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}

	/*
	 * Charge la liste a partir du fichier
	 */
	public void ChargerListe(String nomFichier) throws IOException {
		FileReader f = null;
		BufferedReader br = null;
		try {
			f = new FileReader(nomFichier);
			br = new BufferedReader(f);
			if (!f.ready()) {
				File fs = new File(nomFichier);
				fs.createNewFile();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		String ligne;

		while ((ligne = br.readLine()) != null) {
			toStringListe(ligne);
		}
		br.close();

	}

	/*
	 * Programme de test afin de mettre en valeur ce que l'on peut faire avec ce
	 * programme
	 */
	public void Demo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bonjour ! Bienvenue dans cette demo");
		System.out.println("Regardez. On peut generer une grille");
		GrilleSudoku grille = new GrilleSudoku();
		grille.GenererGrille();
		grille.AfficherGrille();

		GrilleSudoku grille1 = new GrilleSudoku();
		GrilleSudoku grille2 = new GrilleSudoku();
		GrilleSudoku grille3 = new GrilleSudoku();

		for (int a = 0; a < 9; a++)
			for (int b = 0; b < 9; b++) {
				grille1.getT()[a][b].setVal(grille.getT()[a][b].getVal());
				grille2.getT()[a][b].setVal(grille.getT()[a][b].getVal());
				grille3.getT()[a][b].setVal(grille.getT()[a][b].getVal());
				grille1.getT()[a][b].setModifiable(false);
				grille2.getT()[a][b].setModifiable(false);
				grille3.getT()[a][b].setModifiable(false);
			}

		gestionDifficulte(1, grille1);
		gestionDifficulte(1, grille2);
		gestionDifficulte(1, grille3);
		System.out.println("Voici une grille 'facile'");
		grille1.AfficherGrille();
		System.out.println("Voici une grille 'moyenne'");
		grille2.AfficherGrille();
		System.out.println("Voici une grille 'difficile'");
		grille3.AfficherGrille();
		System.out.println("On peut facilement ajouter une difficulte ou en modifier une.");
		System.out.println("De votre coté vous etes un joueur, essayer d'entrer un pseudo");

		boolean pseudoInvalide = true;
		String pseudo = "defaut";
		while (pseudoInvalide) {
			System.out.println("Bonjour ! Entrer un pseudo s'il vous plait");
			pseudo = sc.nextLine();

			if ((pseudo != null) && (pseudo.length() > 0))
				pseudoInvalide = false;
		}
		Joueur joueur = new Joueur(pseudo);
		System.out.println(
				"A la fin de votre partie le pseudo du joueur ainsi que son score sera sauvegarder dans un fichier");
		System.out.println("Essayons de remplir une case");

		grille.AfficherGrille();
		System.out.println(" ");
		grille1.AfficherGrille();

		int i = 0, j = 0;
		String val = " ";
		boolean saisieCorrect = false;
		while (!saisieCorrect) {

			try {
				sc = new Scanner(System.in);
				System.out.println("Entrer la ligne :");
				i = sc.nextInt();
				System.out.println("Entrer la colonne :");
				j = sc.nextInt();
				sc.nextLine();
				System.out.println("Entrer la valeur :");
				val = sc.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("Merci d'entrer une reponse valide");
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.err.println("N'appuyer pas sur Entrer pauvre fou !!!");
			}

			if (i >= 0 && i < 9 && j >= 0 && j < 9
					&& ((val.equals("1")) || (val.equals("2")) || (val.equals("3")) || (val.equals("4"))
							|| (val.equals("5")) || (val.equals("6")) || (val.equals("7")) || (val.equals("8"))
							|| (val.equals("9")))) {
				saisieCorrect = true;
			} else
				System.out.println("Erreur lors de la saisie d'une des 3 entrees");

		}
		if ((grille1.getT()[i][j].isModifiable())) {
			if ((grille.getT()[i][j].getVal().equals(val))) {
				grille1.getT()[i][j].setVal(val);
				System.out.println("Correct !!!!");
				grille1.AfficherGrille();

				boolean compare = true;
				int score = 0;
				if (compare) {
					System.out.println("Imaginons que vous avez fini");
					score += calculScore(0, 1);
					joueur.setScore(score);
					System.out.println("Par exemple " + joueur.getPseudo() + " a " + joueur.getScore() + " pts");
				}
			} else {
				System.out.println("Valeur incorrecte ! Reessaye");
			}
		} else
			System.out.println("Erreur : la case selectionnee n'est pas remplissable");
		System.out.println("Et ce n'est pas tout! Vous vous invite donc a participier a une partie");
	}


}

