package fr.projet.bataillenavale;
import GrilleEtCase.Grille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.projet.Case;

public class GrilleBN extends Grille {
	protected int coordonne[];
	//protected HashMap<String,List<Integer>> MapCoordonnee;

	public GrilleBN(int imax, int jmax) {
		super(imax, jmax);
		//MapCoordonnee = new HashMap<String, List<Integer>>();
	}
	
	public int getImax() {
		return imax; }
	

	public int getJmax() {
		return jmax;
		}
	
	/*public void ajouterElementMap(String nom, int Coordonnee) {
		MapCoordonnee.compute(nom, (k,l) -> {if(l==null) {l = new ArrayList<Integer>();}
		; l.add(Coordonnee);return l;});
	}
	
	public void retirerElementMap(int Coordonnee) {
		for(Bateau b : Bateau.getTabBateau()) {
			if (MapCoordonnee.containsValue(Coordonnee)){
				MapCoordonnee.get(b.getNom()).remove(Coordonnee);
		}
		}
	} */
	
 /*	public void couler(){
		for(Bateau b : Bateau.getTabBateau())
			if (MapCoordonnee.get(b.getNom()).isEmpty()) {
				System.out.println("Bateau + " + b.getNom() + " Coule");
				MapCoordonnee.remove(b.getNom());
			}
		
	}
	
	public void ModifierValeur(int x, int y, char c) {
		this.grille[x][y].setVal(c);
	} */ 
	 public static void main(String args[]) {
			Joueur J1 = new Joueur("Henry", "Mhilan", "mLn <3");
			Joueur J2 = new Joueur("Moreau", "Theo", "OneT");
			IA IA = new IA();
			GrilleBN Grille = new GrilleBN(10,10);
			Bateau B1 = new Bateau("Porte-Avion",5,true);
			Bateau B2 = new Bateau("Croiseur", 4,false);
			Bateau B3 = new Bateau("Contre-Torpilleur",3,true);
			Bateau B4 = new Bateau("Sous-marin", 3, false);
			Bateau B5 = new Bateau("Torpilleur", 2,true);
			Partie P1 = new Partie(J1,J2);
			J1.AfficherGrilleBateau();
			J1.AfficherGrilleJoueur();
			P1.DemarrerPartie(J1, J2);
			//P1.DemarrerPartieIA(J1, IA, GrilleJoueur1, GrilleJoueur2);
			//GrilleJoueur2.AfficherGrilleJoueur()
			
	 }
	 }