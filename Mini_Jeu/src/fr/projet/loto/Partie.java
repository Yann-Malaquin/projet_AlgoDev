package fr.projet.loto;

import java.util.ArrayList;
import java.util.List;

import fr.projet.Grille;

public class Partie {
	private List<Integer> Lint = new ArrayList<Integer>();
	private int NbJoueurs;
	private List<Grille> LG = new ArrayList<Grille>();
	private GrilleLoto G=new GrilleLoto(LG);

public Partie(int nbJoueurs) {
		super();
		NbJoueurs = nbJoueurs;
	}

public void bouleloto() {
	int i;
	for (i=0;i<99;i++) {
		Lint.add(i+1);
	}
}
	
public void testaffichageboule() {
	for(Integer i:Lint) {
		System.out.println(i);
	}
}
	
public void testaffichagegrille() {
	G.creationGrille();
}
}
