package fr.projet.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestPoker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PartiePoker p=new PartiePoker();
		List<Carte> lcarte=new ArrayList<Carte>();
		List<Carte> lcarte2=new ArrayList<Carte>();
		JoueurPoker Michel=new JoueurPoker("Michel");
		JoueurPoker Claude=new JoueurPoker("Claude");
		JoueurPoker Jean=new JoueurPoker("Jean");
		JoueurPoker JC=new JoueurPoker("JC");
		
		/*Michel.setEtatJoueur("blabla");
		Claude.setEtatJoueur("nsd");
		Jean.setEtatJoueur("donneur");
		JC.setEtatJoueur("blabla");
		
		p.setJoueurs(Michel);
		p.setJoueurs(Claude);
		p.setJoueurs(Jean);
		p.setJoueurs(JC);
		
		
		p.distribuerCartes(lcarte,2);*/
		
		
		Carte troisT = new Carte("Carreau","9",9);
		Carte deuxCa = new Carte("Pique","10",10);
		Carte deuxCo = new Carte("Trèfle","2",2);
		Carte troisC = new Carte("Trèfle","5",5);
		Carte troisP = new Carte("Carreau","6",6);
		Carte As = new Carte("Trèfle","7",7);
		Carte Roi = new Carte("Carreau","4",4);
		
		p.setCarteMilieu(troisT);
		p.setCarteMilieu(deuxCa);
		p.setCarteMilieu(deuxCo);
		p.setCarteMilieu(troisC);
		p.setCarteMilieu(troisP);
		
		p.setCarteMilieu(As);
		p.setCarteMilieu(Roi);
		
		System.out.println(p.getCarteMilieu());

		MainsGagnantes mg = new MainsGagnantes();
		
		/*if(mg.estPaire(p.getCarteMilieu()))
				{
			System.out.println("OK");
				}
		else {
			System.out.println("Pas OK");
		}*/
		lcarte = p.genererPaquetCarte();
		//p.afficherPaquetCartes(p.trieCarteNbreCouleur(p.getCarteMilieu()));
		p.afficherPaquetCartes(p.trieCarteNbre(p.getCarteMilieu()));
			

	}

}