package fr.projet.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partie {

	private List<Carte> paquetCartes = new ArrayList<Carte>();
	private List<JoueurPoker> joueurs;

	public Partie() {
		super();
		joueurs=new ArrayList<JoueurPoker>();
	}

	public void setJoueurs(JoueurPoker _joueurs)
	{
		this.joueurs.add(_joueurs);
	}
	
	public List<JoueurPoker> getJoueurs()
	{
		return this.joueurs;
	}
	
	public List<Carte> genererPaquetCarte() {
		String numero[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi", "As" }; // tableau regroupant toutes les figures
		String couleur[] = { "Pique", "Trèfle", "Coeur", "Carreau" }; // tableau regroupant les couleurs
		int i;

		// parcourir les 2 tableaux
		for (String c : couleur) {
			i = 2;
			for (String n : numero) {
				Carte c1 = new Carte(c, n, i);
				paquetCartes.add(c1);
				i++;
			}
		}

		return this.paquetCartes;
	}

	public void afficherPaquetCartes(List<Carte> paquetCartes) { // on affiche le paquet de cartes
		for (Carte c : paquetCartes) {
			System.out.println(c.toString());
		}
	}

	public List<Carte> melangerPaquetCartes(List<Carte> paquetCartes) { // permet de mélanger le paquet de cartes
		List<Carte> paquetMelange = new ArrayList<Carte>();
		int indexAlea;
		int i=paquetCartes.size();
		Random rand=new Random(); // utilisation du random pour chercher parmis le paquet

		while (i > 0) { // tant que l'on a encore des cartes alors on "mélange" (ajout dans une liste tampon et suppression de la liste initiale)
			indexAlea = rand.nextInt(i);
			paquetMelange.add(paquetCartes.get(indexAlea));
			paquetCartes.remove(indexAlea);
			i--;
		}

		return paquetMelange;
	}

	public void distribuerCartes(List<Carte> paquetCartes) {
		List<Carte> paquetCartesMelangees = new ArrayList<Carte>();
		List<Carte> paquetCartesMelangeestmp = new ArrayList<Carte>();
		int pdonneur = 0; // position du donneur dans la liste
		int init; //départ du donneur pour distribution
		paquetCartes = this.genererPaquetCarte();
		boolean find=false;
		
		for (int i = 0; i < 2; i++) { // on mélange le paquet de carte
			paquetCartesMelangeestmp = this.melangerPaquetCartes(paquetCartes);
			paquetCartes = this.melangerPaquetCartes(paquetCartesMelangeestmp);
		}
		
		paquetCartesMelangees.addAll(paquetCartes);

		for (int i=0;i<joueurs.size();i++) { // on cherche le donneur parmis les joueurs
			for(int j=0;j<joueurs.get(i).getEtatJoueur().size();j++)
			{
				if (joueurs.get(i).getEtatJoueur().get(j).equals("donneur")) {
					find=true;
					break;
				}
			}
			if (find==true)
			{
				break;
			}
			else
			{
				pdonneur++;
			}	
		}
		
		init = pdonneur;
		
		for (int tour = 0; tour < 2; tour++) { // pour les tours de distribution
			
			if((pdonneur+1)==(joueurs.size())) // si on est au dernier joueur alors le 1er joueur a recevoir les cartes sera le 1er de la liste
			{
				pdonneur=0;
			}
			else
			{
				pdonneur+=1;
			}
			
		
			System.out.println("pdonneur: "+pdonneur);
			while (pdonneur < joueurs.size()) { // tant que l'on est pas supérieur au nombre de joueur
				
				joueurs.get(pdonneur).setMainJoueur(paquetCartesMelangees.get(0));
				paquetCartesMelangees.remove(0);
				System.out.println(joueurs.get(pdonneur).toString());
				
				if (pdonneur==init) // si on arrive sur le donneur alors on quitte le 1er et on entame le 2e tour
				{
					break;
				}
				else if (pdonneur==(joueurs.size()-1)) // si on arrive a la fin de la liste on retourne au 1er joueur
				{
					pdonneur=0;
				}
				else
				{
					pdonneur++;
				}				
			}
			
			pdonneur=init; // on redonne le donneur pour rédémarrer
		}
	}
}
