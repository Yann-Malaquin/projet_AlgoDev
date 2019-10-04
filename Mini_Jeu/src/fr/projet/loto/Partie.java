package fr.projet.loto;

import java.util.ArrayList;
import java.util.List;

public class Partie {
	private List<Integer> Lint = new ArrayList<Integer>();
	private int NbJoueurs;

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
}
