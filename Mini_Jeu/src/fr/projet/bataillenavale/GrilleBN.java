package fr.projet.bataillenavale;
import fr.projet.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.projet.Case;

public class GrilleBN extends Grille {
	protected int coordonne[];
	protected HashMap<String,List<Integer>> MapCoordonnee;

	public GrilleBN(int imax, int jmax) {
		super(imax, jmax);
		MapCoordonnee = new HashMap<String, List<Integer>>();
	}
	
	public int getImax() {
		return getImax(); }
	

	public int getJmax() {
		return getJmax();
		}
	
	public void ajouterElementMap(String nom, int Coordonnee) {
		MapCoordonnee.compute(nom, (k,l) -> {if(l==null) {l = new ArrayList<Integer>();}
		; l.add(Coordonnee);return l;});
	}
	
	public void retirerElementMap(int Coordonnee) {
		for(Bateau b : Bateau.getTabBateau()) {
			if (MapCoordonnee.containsValue(Coordonnee)){
				MapCoordonnee.get(b.getNom()).remove(Coordonnee);
		}
		}
	} 
	
 	public void couler(){
		for(Bateau b : Bateau.getTabBateau())
			if (MapCoordonnee.get(b.getNom()).isEmpty()) {
				System.out.println("Bateau + " + b.getNom() + " Coule");
				MapCoordonnee.remove(b.getNom());
			}
		
	}
			
	 }
