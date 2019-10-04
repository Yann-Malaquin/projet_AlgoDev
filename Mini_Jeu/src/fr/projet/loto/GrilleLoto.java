package fr.projet.loto;

import fr.projet.Grille;
import java.util.ArrayList;
import java.util.List;

public class GrilleLoto {
	private List<Grille> LG = new ArrayList<Grille>();
	
	public void creationGrille() {
		int i;
		for (i = 0; i < 100; i++) {
			Grille G = new Grille(3,9);
			G.initialiserCaseDeLaGrille();
			LG.add(G);
		}
	}
}