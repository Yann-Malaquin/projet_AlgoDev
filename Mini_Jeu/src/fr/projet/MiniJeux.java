package fr.projet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.projet.bataillenavale.JoueurBN;
import fr.projet.loto.PartieLoto;
import fr.projet.poker.Carte;
import fr.projet.poker.JoueurPoker;
import fr.projet.sudoku.Sudoku;
import fr.projet.poker.*;

public class MiniJeux {





	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int choix=1;
		
		while(choix!=0) {
			Scanner sc1 = new Scanner(System.in);
			System.out.println("<0> Quitter <1> Sudoku <2> Loto <3> Poker <4> Bataille Navale <5> Classement" );
			System.out.println("Choix: ");

			try {
				choix = sc1.nextInt();
			} catch (Exception e) {
				System.out.println(e);
			}


			switch (choix) {
				case 0:
					System.out.println("Fin programme");
					//System.exit(0);
				case 1:
					Sudoku test = new Sudoku();
					try {
						test.Jouer();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("\n");
					break;
				case 2:
					PartieLoto P = new PartieLoto(4);
					P.Creationbouleloto();
					P.CreationGrille();
					P.distribution();
					P.debut_partie();
					System.out.println("\n");
					break;

				case 3:
					PartiePoker p = new PartiePoker();
					List<Carte> lcarte = new ArrayList<Carte>();
					List<Carte> lcarte2 = new ArrayList<Carte>();
					JoueurPoker Michel = new JoueurPoker("Michel");
					JoueurPoker Claude = new JoueurPoker("Claude");
					JoueurPoker Jean = new JoueurPoker("Jean");
					JoueurPoker JC = new JoueurPoker("JC");

					Michel.setEtatJoueur("blabla");
					Claude.setEtatJoueur("nsd");
					Jean.setEtatJoueur("donneur");
					JC.setEtatJoueur("blabla");

					p.setJoueurs(Michel);
					p.setJoueurs(Claude);
					p.setJoueurs(Jean);
					p.setJoueurs(JC);

					lcarte = p.genererPaquetCarte();
					p.afficherPaquetCartes(lcarte);
					p.distribuerCartes(lcarte);
					break;
				/*case 4:
					Joueur J1 = new Joueur("mLn");
					Joueur J2 = new Joueur("OneT");
					GrilleBN Grille = new GrilleBN(10, 10);
					Partie P1 = new Partie(J1, J2);
					Bateau B1 = new Bateau("Porte-Avion", 5, true);
					Bateau B2 = new Bateau("Croiseur", 4, false);
					Bateau B3 = new Bateau("Contre-Torpilleur", 3, true);
					Bateau B4 = new Bateau("Sous-marin", 3, false);
					Bateau B5 = new Bateau("Torpilleur", 2, true);
					P1.DemarrerPartie(J1, J2);*/
					//sc.close();

                case 5:

                    Classement ClassementCharge = new Classement();
                    File FichierTMP= new File(ClassementCharge.FichierSauvegarde);
                    if(!FichierTMP.exists()) {
						FichierTMP.createNewFile();
						Joueur Anonyme = new Joueur("Anonyme");
						ClassementCharge.ajouterJoueur(Anonyme);
						ClassementCharge.SauvegardeClassement(ClassementCharge.FichierSauvegarde);
					}
                    ClassementCharge.ChargerClassement(ClassementCharge,ClassementCharge.FichierSauvegarde);
                    ClassementCharge.afficherClassement();
                    break;
			}
		}

	}

}
