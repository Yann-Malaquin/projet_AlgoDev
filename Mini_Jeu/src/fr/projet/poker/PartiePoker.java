package fr.projet.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PartiePoker {

	private List<Carte> paquetCartes = new ArrayList<Carte>();
	private List<JoueurPoker> joueurs = new ArrayList<JoueurPoker>();
	private List<Jeton> jetons = new ArrayList<Jeton>();
	private List<Carte> carteMilieu=new ArrayList<Carte>();
	List<Carte> paquetCartesMelangees = new ArrayList<Carte>();

	public PartiePoker() {
		super();
	}

	public void setJoueurs(JoueurPoker _joueurs)// ajouter joueur à la partie
	{
		this.joueurs.add(_joueurs);
	}

	public List<JoueurPoker> getJoueurs()// liste des joueurs présents dans la partie
	{
		return this.joueurs;
	}
	
	public void setCarteMilieu(Carte _carte)// ajouter carte à la liste du milieu
	{
		this.carteMilieu.add(_carte);
	}
	
	public List<Carte> getCarteMilieu()// liste des cartes au milieu
	{
		return this.carteMilieu;
	}
	
	public void setpaquetCartesMelangees(Carte _carte)// ajouter carte à la liste du milieu
	{
		this.paquetCartesMelangees.add(_carte);
	}
	
	public List<Carte> getpaquetCartesMelangees()// liste des cartes au milieu
	{
		return this.paquetCartesMelangees;
	}
	

	
	
	public List<Carte> genererPaquetCarte() // créer un paquet de cartes
	{
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

	public void afficherPaquetCartes(List<Carte> paquetCartes)// afficher le paquet de cartes
	{
		for (Carte c : paquetCartes) {
			System.out.println(c.toString());
		}
	}

	public List<Carte> melangerPaquetCartes(List<Carte> paquetCartes)// melanger le paquet de cartes 1 fois
	{
		List<Carte> paquetMelange = new ArrayList<Carte>();
		int indexAlea;
		int i = paquetCartes.size();
		Random rand = new Random();

		while (i > 0) {
			indexAlea = rand.nextInt(i);
			paquetMelange.add(paquetCartes.get(indexAlea));
			paquetCartes.remove(indexAlea);
			i--;
		}

		return paquetMelange;
	}

	public void distribuerCartes(List<Carte> paquetCartes,int nombreCarte) {//on passe la liste de carte préalablement créer, et on précise le nombre de carte que l'on veut distribuer par joueur
		List<Carte> paquetCartesMelangeestmp = new ArrayList<Carte>();
		int pdonneur = 0; // position du donneur dans la liste
		int init;
		paquetCartes = this.genererPaquetCarte();
		boolean find = false;

		for (int i = 0; i < 1; i++) { // on mélange 4 fois le paquet de cartes
			paquetCartesMelangeestmp = this.melangerPaquetCartes(paquetCartes);
			paquetCartes = this.melangerPaquetCartes(paquetCartesMelangeestmp);
		}
		
		paquetCartesMelangees.addAll(paquetCartes); // on affecte la le paquets de carte a la liste melangees
		

		// on recherche le donneur
		for (int i = 0; i < joueurs.size(); i++) {
			for (int j = 0; j < joueurs.get(i).getEtatJoueur().size(); j++) {
				if (joueurs.get(i).getEtatJoueur().get(j).equals("donneur")) {
					find = true;
					break;
				}
			}
			if (find == true) {
				break;
			} else {
				pdonneur++;
			}
		}

		init = pdonneur; // on sauvagrde la position du donneur pour le 2e tour de distribution

		for (int tour = 0; tour < nombreCarte; tour++) { // on distribue 2 cartes donc 2 itérations

			// on test si le donneur est le dernier joueur de la liste alors on démarre au 1er joueur de la liste, sinon on incrémente d'1 pour commencer à sa gauche
			if ((pdonneur + 1) == (joueurs.size())) {
				pdonneur = 0;
			} else {
				pdonneur += 1;
			}

			/*Tant que l'on est dans la liste
			 * on distribue la 1ère carte du paquet et on la supprime du paquet
			 * si on arrive sur le donneur, on quitter le while et on fait le 2e tour 
			 * si on arrive au dernier joueur on reprend au 1er de la liste
			 * sinon on incrémente le joueur
			 */
			while (pdonneur < joueurs.size()) {

				joueurs.get(pdonneur).setMainJoueur(paquetCartesMelangees.get(0));
				paquetCartesMelangees.remove(0);
				System.out.println(joueurs.get(pdonneur).toString());

				if (pdonneur == init) {
					break;
				} else if (pdonneur == (joueurs.size() - 1)) {
					pdonneur = 0;
				} else {
					pdonneur++;
				}
			}
		
			pdonneur = init; // on réinit le donneur pour pouvoir reprendre le 2e tour
		}
	}
	
	public void afficherCartesMilieu(List<Carte> paquetCartes,int tour)
	{
		if (tour==1)
		{
			for (int i=0;i<3;i++)
			{
				this.setCarteMilieu(paquetCartes.get(0));
				paquetCartes.remove(0);
			}
			System.out.println(this.getCarteMilieu());
		}
		
		if (tour==2)
		{
			this.setCarteMilieu(paquetCartes.get(0));
			paquetCartes.remove(0);
			System.out.println(this.getCarteMilieu());
		}
		
		if (tour==3)
		{
			this.setCarteMilieu(paquetCartes.get(0));
			paquetCartes.remove(0);
			System.out.println(this.getCarteMilieu());
		}
	}
	
	
	

	
	

}
