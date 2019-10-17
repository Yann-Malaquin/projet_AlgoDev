package fr.projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.projet.bataillenavale.Bateau;
import fr.projet.bataillenavale.GrilleBN;
import fr.projet.bataillenavale.Joueur;
import fr.projet.bataillenavale.Partie;
import fr.projet.loto.PartieLoto;
import fr.projet.poker.Carte;
import fr.projet.poker.JoueurPoker;
import fr.projet.sudoku.Sudoku;
import fr.projet.poker.*;

public class MiniJeux {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int choix=1;
		
		while(choix!=0)
		{
			System.out.println("<0> Quitter <1> Sudoku <2> Loto <3> Poker <4> Bataille Navale");
			System.out.println("Choix: ");
		
			choix = sc.nextInt();
			
			switch(choix)
			{
			case 0:
				System.out.println("Fin programme");
				System.exit(0);
			case 1:
				Sudoku sudoku = new Sudoku();
				sudoku.Demo();
				System.out.println("");
				break;
			case 2:
				PartieLoto P = new PartieLoto(4);
				//P.Creationbouleloto();
				//P.AffichageBoules();
				P.CreationGrille();
				break;
			case 3:
				PartiePoker p=new PartiePoker();
				List<Carte> lcarte=new ArrayList<Carte>();
				List<Carte> lcarte2=new ArrayList<Carte>();
				JoueurPoker Michel=new JoueurPoker("Michel");
				JoueurPoker Claude=new JoueurPoker("Claude");
				JoueurPoker Jean=new JoueurPoker("Jean");
				JoueurPoker JC=new JoueurPoker("JC");
				
				Michel.setEtatJoueur("blabla");
				Claude.setEtatJoueur("nsd");
				Jean.setEtatJoueur("donneur");
				JC.setEtatJoueur("blabla");
				
				p.setJoueurs(Michel);
				p.setJoueurs(Claude);
				p.setJoueurs(Jean);
				p.setJoueurs(JC);
				
				lcarte=p.genererPaquetCarte();
				p.afficherPaquetCartes(lcarte);
				p.distribuerCartes(lcarte);
				break;
			case 4:
				Joueur J1 = new Joueur("mLn");
				Joueur J2 = new Joueur("OneT");
				GrilleBN Grille = new GrilleBN(10,10);
				Partie P1 = new Partie(J1,J2);
				Bateau B1 = new Bateau("Porte-Avion",5,true);
				Bateau B2 = new Bateau("Croiseur", 4,false);
				Bateau B3 = new Bateau("Contre-Torpilleur",3,true);
				Bateau B4 = new Bateau("Sous-marin", 3, false);
				Bateau B5 = new Bateau("Torpilleur", 2,true);
				P1.DemarrerPartie(J1, J2);
			}
		}

	}

}
