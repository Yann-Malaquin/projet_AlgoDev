package fr.projet.loto;

import fr.projet.Grille;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrilleLoto {
	private List<Grille> LG = new ArrayList<Grille>();
	private List<Integer> LI = new ArrayList<Integer>();

	public GrilleLoto(List<Grille> lG) {
		super();
		LG = lG;
	}

	public void creationGrille() {
		int i, j;
		for (i = 0; i < 2; i++) {
			Grille G = new Grille(10, 3);
			for (j = 0; j < 15; j++) {
				Random rand = new Random();
				int nbAlea = rand.nextInt(99 - 1 + 1) + 1;
				LI.add(nbAlea);
				Collections.sort(LI);
			}
			G = rangercartons(G, LI);
			Affichage(G);
			LG.add(G);
		}
	}

	public static char convertir(int a) {
		char c = (char) a;
		return c;
	}

	public static Grille rangercartons(Grille G, List<Integer> LI) {
		for (Integer temp : LI) {
			if (1 <= temp && temp <= 9) {
				if (G.getT()[0][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[0][0].setVal(x);
				} else if (G.getT()[0][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[0][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[0][2].setVal(x);
				}
			}
			if (10 <= temp && temp <= 19) {
				if (G.getT()[1][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[1][0].setVal(x);
				} else if (G.getT()[1][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[1][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[1][2].setVal(x);
				}
			}
			if (20 <= temp && temp <= 29) {
				if (G.getT()[2][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[2][0].setVal(x);
				} else if (G.getT()[2][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[2][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[2][2].setVal(x);
				}
			}
			if (30 <= temp && temp <= 39) {
				if (G.getT()[3][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[3][0].setVal(x);
				} else if (G.getT()[3][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[3][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[3][2].setVal(x);
				}
			}
			if (40 <= temp && temp <= 49) {
				if (G.getT()[4][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[4][0].setVal(x);
				} else if (G.getT()[4][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[4][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[4][2].setVal(x);
				}
			}
			if (50 <= temp && temp <= 59) {
				if (G.getT()[5][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[5][0].setVal(x);
				} else if (G.getT()[5][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[5][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[5][2].setVal(x);
				}
			}
			if (60 <= temp && temp <= 69) {
				if (G.getT()[6][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[6][0].setVal(x);
				} else if (G.getT()[6][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[6][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[6][2].setVal(x);
				}
			}
			if (70 <= temp && temp <= 79) {
				if (G.getT()[7][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[7][0].setVal(x);
				} else if (G.getT()[7][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[7][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[7][2].setVal(x);
				}
			}
			if (80 <= temp && temp <= 89) {
				if (G.getT()[8][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[8][0].setVal(x);
				} else if (G.getT()[8][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[8][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[8][2].setVal(x);
				}
			}
			if (90 <= temp && temp <= 99) {
				if (G.getT()[9][0].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[9][0].setVal(x);
				} else if (G.getT()[9][1].getVal() == ' ') {
					char x = convertir(temp);
					G.getT()[9][1].setVal(x);
				} else {
					char x = convertir(temp);
					G.getT()[9][2].setVal(x);
				}
			}
		}
		return G;
	}

	public static void Affichage(Grille G) {
		int i, j;
			System.out.println("_______________________________");
		for (i = 0; i < 3; i++) {
			System.out.println("|");
			for (j = 0; j < 10; j++) {
				System.out.println(G.getT()[j][i].getVal());
			}
		}
	}

	public List<Grille> getLG() {
		return LG;
	}

	public void setLG(List<Grille> lG) {
		LG = lG;
	}

}