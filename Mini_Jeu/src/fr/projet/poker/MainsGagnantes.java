package fr.projet.poker;

import java.util.ArrayList;
import java.util.List;

public class MainsGagnantes {

	private List<Carte> mainGagnante;

	public MainsGagnantes() {
		mainGagnante = new ArrayList<Carte>();
	}

	public void setMainGagnante(Carte _carte) {
		mainGagnante.add(_carte);
	}

	public List<Carte> getMainGagnante() {
		return mainGagnante;
	}

	List<Carte> rechercherCarte(List<Carte> paquetCartes, int valeurCarte) {

		List<Carte> trouve = new ArrayList<Carte>();

		for (int i = 0; i < paquetCartes.size(); i++) {
			if (paquetCartes.get(i).getValeur() == 14) {
				trouve.add(paquetCartes.get(i));
			}
		}

		return trouve;
	}

	public boolean estQFR(List<Carte> carteMains) {

		List<Carte> trouve = new ArrayList<Carte>(); // permet de savoir si As il y a.
		int cpt = 1;
		int j = 0;
		trouve = this.rechercherCarte(carteMains, 14);

		if (trouve.isEmpty()) {// s'il n'y a pas d'As alors il ne peut y avoir Quinte Flush Royale
			return false;
		} else {
			for (int i = 0; i < trouve.size(); i++) { // on parcourt en fonction des as.
				j = 0;
				while (carteMains.get(j).getValeur() != 10 && j < carteMains.size() - 1) { // on cherche s'il y a un dix
																							// pour démarrer le comptage
																							// des cartes.
					j++;
				}

				while (j < carteMains.size() - 1) { // on parcourt donc de j à 6
					if ((j > 2 && cpt < 3) // si on est supérieur a 2 soit 3 cartes et que l'on a pas encore 3 de
											// correctes cartes alors on ne pourra plus avoir de Quinte Flush Royale ou
											// si la couleur est incorrecte, alors on ne poursuit pas.
							|| (carteMains.get(j).getCouleur().compareTo(trouve.get(i).getCouleur()) != 0)) {
						break;
					} else {
						if ((trouve.get(i).getCouleur().compareTo(carteMains.get(j).getCouleur()) == 0)
								&& (trouve.get(i).getCouleur().compareTo(carteMains.get(j + 1).getCouleur()) == 0)
								&& (carteMains.get(j).getValeur() + 1 == carteMains.get(j + 1).getValeur())) { // si on
																												// a 2
																												// cartes
																												// qui
																												// se
																												// suivent
																												// de
																												// même
																												// couleur
																												// alors
																												// on
																												// incrémente.

							cpt++;

							if (cpt == 4) { // dès que l'on a 4 cartes alors on sait qu'il y a Quinte Flush Royale. 4
											// cartes car nous savons qu'il y a déjà un as.
								return true;
							}
						} else { // sinon on reset le compteur.
							cpt = 1;
						}
					}
					j++;
				}
			}
		}
		return false;
	}

	public boolean estQF(List<Carte> carteMains) {
		int cpt = 1;
		for (int i = 0; i < carteMains.size() ; i++) // on parcourt le paquet de cartes.
		{
			if (carteMains.get(i).getValeur() + 1 == carteMains.get(i + 1).getValeur()
					&& (carteMains.get(i).getCouleur().compareTo(carteMains.get(i + 1).getCouleur())) == 0) // si on a 2
																											// cartes de
																											// la même
																											// couleur
																											// qui se
																											// suivent
																											// on
																											// incrémente
																											// cpt
			{
				cpt++;
				if (cpt == 5) // si on a les 5 cartes pour une main alors on return true
				{
					return true;
				}
			} else if (i > 2 && cpt < 3) // test qui coupe si on ne peut pas avoir de Quinte Flush
			{
				break;
			} else {
				cpt = 1;
			}
		}
		return false;
	}

	public boolean estCarre(List<Carte> carteMains) {
		int cpt = 1;

		for (int i = 0; i < carteMains.size(); i++) {// on parcourt la liste des cartes 1 par 1 pour tester.  
			for (int j = 0; j < carteMains.size(); j++)// on parcourt la liste pour tester avec les autres valeurs.
				if (carteMains.get(i).getValeur() == carteMains.get(j).getValeur()) {// si on a la même valeur
					cpt++;// on incrémente le compteur
					if(cpt==4)// si on a les 4 cartes on retourne true 
					{
						return true;
					}
				}
				else if(i>4 && cpt<2)// si on a déjà parcouru 5 cartes et que le compteur est = a 1 alors on ne pourra plus former de carré, donc on quitte 
				{
					break;
				}
				else
				{
					cpt=1;// sinon on reset le compteur 
				}
		}
		return false;
	}
	
	
	public boolean estFull(List<Carte> carteMains)
	{
		List<Carte> copycarteMains=new ArrayList<Carte>(carteMains);
		int cpt1=1, cpt2=1;
		
		for (int i=0;i<copycarteMains.size();i++)
		{
			for (int j=0;j<copycarteMains.size();j++)
			{
				if (copycarteMains.get(i).getValeur() == copycarteMains.get(j).getValeur()) {// si on a la même valeur
					cpt1++;// on incrémente le compteur
					copycarteMains.remove(i);
				}
			}
		}
		return false;
	}
	
	
}
