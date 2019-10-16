package fr.projet.loto;

import java.util.ArrayList;
import java.util.List;

import fr.projet.Grille;

public class PartieLoto {
	private List<Integer> Lint = new ArrayList<Integer>();
	private int NbJoueurs;
	private List<GrilleLoto> LG = new ArrayList<GrilleLoto>();
	private GrilleLoto G;

	public PartieLoto(int nbJoueurs) {
		super();
		NbJoueurs = nbJoueurs;
	}

	public void Creationbouleloto() {
		int i;
		for (i = 0; i < 99; i++) {
			Lint.add(i + 1);
		}
	}

	public void AffichageBoules() {
		for (Integer i : Lint) {
			System.out.println(i);
		}
	}

	public void CreationGrille() {
		int i;
		for (i = 0; i < 20; i++) {
			G=new GrilleLoto();
			G.creationGrille();
			LG.add(G);
		}
	}
}
