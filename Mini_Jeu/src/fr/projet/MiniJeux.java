package fr.projet;

import java.util.Scanner;

import fr.projet.loto.Partie;
import fr.projet.sudoku.Sudoku;

public class MiniJeux {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int choix=1;
		
		while(choix!=0)
		{
			System.out.println("<0> Quitter <1> Sudoku <2> Loto");
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
				Partie P = new Partie(4);
				//P.Creationbouleloto();
				//P.AffichageBoules();
				P.CreationGrille();
				break;
				
				
			case 3:
			}
		}

	}

}
