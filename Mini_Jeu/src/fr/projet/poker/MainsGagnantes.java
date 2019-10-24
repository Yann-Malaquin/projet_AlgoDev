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
		for (int i = 0; i < carteMains.size(); i++) // on parcourt le paquet de cartes.
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
					if (cpt == 4)// si on a les 4 cartes on retourne true
					{
						return true;
					}
				} else if (i > 4 && cpt < 2)// si on a déjà parcouru 5 cartes et que le compteur est = a 1 alors on ne
											// pourra plus former de carré, donc on quitte
				{
					break;
				} else {
					cpt = 1;// sinon on reset le compteur
				}
		}
		return false;
	}

	// a reverif
	public boolean estFullHouse(List<Carte> carteMains) {
		List<Carte> copycarteMains = new ArrayList<Carte>(carteMains);
		List<Integer> indiceCartes = new ArrayList<Integer>();
		int cpt = 1, i = 0, brelan = 0, pair = 0;

		while (i < copycarteMains.size()) {
			if (copycarteMains.get(i).getValeur() == copycarteMains.get(i + 1).getValeur()) {
				indiceCartes.add(i);
				cpt++;

				if (cpt == 3) {
					copycarteMains.remove(indiceCartes.get(0));
					copycarteMains.remove(indiceCartes.get(1));
					copycarteMains.remove(indiceCartes.get(2));
					brelan = 1;

					break;
				}
				i++;

			} else if (cpt == 0 && i > 4) {
				break;
			} else {
				cpt = 0;
				i++;
			}
		}

		cpt = 0;
		i = 0;
		indiceCartes.clear();

		while (i < copycarteMains.size()) {
			if (copycarteMains.get(i).getValeur() == copycarteMains.get(i + 1).getValeur()) {
				indiceCartes.add(i);
				cpt++;

				if (cpt == 2) {
					copycarteMains.remove(indiceCartes.get(0));
					copycarteMains.remove(indiceCartes.get(1));
					pair = 1;
					break;
				}
				i++;

			} else if (cpt == 0 && i >= 3) {
				break;
			} else {
				cpt = 0;
				i++;
			}
		}

		if (brelan == 1 && pair == 1) {
			return true;
		}

		return false;
	}

	public boolean estFlush(List<Carte> carteMains) {
		int cpt = 1;

		for (int i = 0; i < carteMains.size() - 1; i++) {

			if ((i + 1) == 7 && cpt == 3
					&& (carteMains.get(6).getCouleur().compareTo(carteMains.get(5).getCouleur()) == 0)) {

				return true;
			}
			if ((carteMains.get(i).getCouleur().compareTo(carteMains.get(i + 1).getCouleur())) == 0) {
				cpt++;
				if ((carteMains.get(i + 1).getCouleur().compareTo(carteMains.get(i + 2).getCouleur()) != 0)) {
					cpt++;
				}
				System.out.println("cpt =" + cpt);
				if (cpt == 4) {
					return true;
				}
			} else if (i > 3 && cpt == 0) {
				break;
			} else {
				cpt = 0;
			}
		}
		return false;
	}

	public boolean estQuinte(List<Carte> carteMains) {
		int cpt = 1;

		for (int i = 0; i < carteMains.size(); i++) {
			if (i + 1 >= carteMains.size() && cpt <= 4) {
				return false;
			}
			if (carteMains.get(i).getValeur() + 1 == carteMains.get(i + 1).getValeur()) {
				cpt++;
				System.out.println("cpt= " + cpt);
				if (cpt == 5) {
					return true;
				} else if (i > 3 && cpt < 2) {
					break;
				}
			} else {
				cpt = 1;
			}
		}

		return false;
	}

	public boolean estBrelan(List<Carte> carteMains) {
		List<Carte> copycarteMains = new ArrayList<Carte>(carteMains);

		int cpt = 1;

		for (int i = 0; i < copycarteMains.size(); i++) {
			if (i + 1 >= carteMains.size() && cpt <= 2) {
				return false;
			}
			if (carteMains.get(i).getValeur() == carteMains.get(i + 1).getValeur()) {
				cpt++;
				System.out.println("cpt = " + cpt);
				if (cpt == 3) {
					return true;
				}

			} else if (cpt == 1 && i > 5) {
				break;
			} else {
				cpt = 1;
			}
		}
		return false;
	}

	public boolean estDoublePaire(List<Carte> carteMains) {

		List<Carte> copyCartesMain = new ArrayList<Carte>(carteMains);
		List<Integer> indiceCarte = new ArrayList<Integer>();
		int i, cpt = 1;
		boolean pair1 = false, pair2 = false;

		for (i = 0; i < copyCartesMain.size(); i++) {
			if (i + 1 >= copyCartesMain.size() && cpt == 1) {
				return false;
			}
			if (copyCartesMain.get(i).getValeur() == copyCartesMain.get(i + 1).getValeur()) {
				cpt++;
				indiceCarte.add(i);
				indiceCarte.add(i + 1);

				if (cpt == 2) {
					pair1 = true;
					copyCartesMain.remove(indiceCarte.get(0));
					copyCartesMain.remove(indiceCarte.get(1));
					break;
				}

			} else if (cpt == 1 && i > 5) {
				break;
			} else {
				cpt = 1;
			}
		}

		indiceCarte.clear();

		for (i = 0; i < copyCartesMain.size(); i++) {
			if (i + 1 >= carteMains.size() && cpt == 1) {
				return false;
			}
			if (carteMains.get(i).getValeur() == carteMains.get(i + 1).getValeur()) {
				cpt++;
				indiceCarte.add(i);
				indiceCarte.add(i + 1);

				if (cpt == 2) {
					pair2 = true;
					copyCartesMain.remove(indiceCarte.get(0));
					copyCartesMain.remove(indiceCarte.get(1));
					break;
				} else if (cpt == 1 && i > 5) {
					break;
				} else {
					cpt = 1;
				}
			}
		}

		if (pair1 && pair2) {
			return true;
		}

		return false;
	}

	public boolean estPaire(List<Carte> carteMains) {
		int cpt = 1;
		for (int i = 0; i < carteMains.size(); i++) {
			if (i + 1 >= carteMains.size() && cpt == 1) {
				return false;
			}
			if (carteMains.get(i).getValeur() == carteMains.get(i + 1).getValeur()) {
				cpt++;
				if (cpt == 2) {
					return true;
				} else if (cpt == 1 && i > 5) {
					break;
				} else {
					cpt = 1;
				}
			}
		}
		return false;
	}
}
