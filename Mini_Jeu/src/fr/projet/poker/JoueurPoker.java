package fr.projet.poker;

import java.util.ArrayList;
import java.util.List;
import fr.projet.Joueur;

public class JoueurPoker extends Joueur{
	
	private List<Carte> mainJoueur; // 2 cartes en main
	private int wallet; // porte feuille du joueur (limité par la table)
	private int mise; // mise du joueur
	private List<String> etatJoueur; 
	/*List car un joueur peuavoir plusieurs "état" fold et pblinde ...
	 * fold,call,raise,all-in,check,pblinde,gblinde,donneur
	 */
	
	public JoueurPoker(String _pseudo)
	{
		super(_pseudo);
		mainJoueur=new ArrayList<Carte>();
		int wallet=0;
		int mise=0;
		etatJoueur=new ArrayList<String>();
	}
	
	

	public List<Carte> getMainJoueur() {
		return mainJoueur;
	}

	public void setMainJoueur(Carte c) {
		this.mainJoueur.add(c);
	}

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int _wallet) {
		this.wallet = _wallet;
	}

	public int getMise() {
		return mise;
	}

	public void setMise(int _mise) {
		this.mise = _mise;
	}

	public List<String> getEtatJoueur() {
		return etatJoueur;
	}

	public void setEtatJoueur(String _etatJoueur) {
		this.etatJoueur.add(_etatJoueur);
	}

	@Override
	public String toString() {
		return this.getPseudo()+"\nmainJoueur= [" + mainJoueur + "]\nwallet=" + wallet + " €\nmise=" + mise + " €\netatJoueur=["
				+ etatJoueur + "]\n";
	}
	
	
	
	
	
	

}
