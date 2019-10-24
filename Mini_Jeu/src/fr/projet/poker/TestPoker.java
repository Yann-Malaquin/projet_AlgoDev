package fr.projet.poker;

import java.util.ArrayList;
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
		
		Michel.setEtatJoueur("blabla");
		Claude.setEtatJoueur("nsd");
		Jean.setEtatJoueur("donneur");
		JC.setEtatJoueur("blabla");
		
		p.setJoueurs(Michel);
		p.setJoueurs(Claude);
		p.setJoueurs(Jean);
		p.setJoueurs(JC);
		
		lcarte = p.genererPaquetCarte();
		p.distribuerCartes(lcarte,2);
		
		
		Carte troisT = new Carte("Carreau","0",7);
		Carte deuxCa = new Carte("Pique","1",5);
		Carte deuxCo = new Carte("Trèfle","2",4);
		Carte troisC = new Carte("Carreau","5",0);
		Carte troisP = new Carte("Carreau","6",8);
		Carte As = new Carte("Carreau","7",5);
		Carte Roi = new Carte("Carreau","8",5);
		
		p.setCarteMilieu(troisT);
		p.setCarteMilieu(deuxCa);
		p.setCarteMilieu(deuxCo);
		p.setCarteMilieu(troisC);
		p.setCarteMilieu(troisP);
		
		p.setCarteMilieu(As);
		p.setCarteMilieu(Roi);
		
		System.out.println(p.getCarteMilieu());

		MainsGagnantes mg = new MainsGagnantes();
		
		if(mg.estPaire(p.getCarteMilieu()))
				{
			System.out.println("OK");
				}
		else {
			System.out.println("Pas OK");
		}
	

	}

}