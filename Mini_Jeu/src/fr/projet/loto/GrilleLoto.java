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
		int j,x;
		Grille G = new Grille(3, 10);
		for (j = 0; j < 15; j++) {
			x = 0;
			while (x != 1) {
				Random rand = new Random();
				int nbAlea = rand.nextInt(99 - 1 + 1) + 1;
				if (LI.contains(nbAlea) == false) {
					LI.add(nbAlea);
					x = 1;
				}
			}
		}
		Collections.sort(LI);
		for (Integer xyz : LI) {
			System.out.println(xyz);
		}
		G = rangercartons(G, LI);
		//Affichage(G);
		LG.add(G);
	}

	public static String convertir(int a) {
		String c = Integer.toString(a);
		return c;
	}

	public Grille rangercartons(Grille G, List<Integer> LI) {
		for (Integer temp : LI) {
			if (1 <= temp && temp <= 9) {
				if (G.getT()[0][0].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][0].setVal(x);
				} else if (G.getT()[1][0].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][0].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][0].setVal(x);
				}
			}
			if (10 <= temp && temp <= 19) {
				if (G.getT()[0][1].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][1].setVal(x);
				} else if (G.getT()[1][1].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][1].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][1].setVal(x);
				}
			}
			if (20 <= temp && temp <= 29) {
				if (G.getT()[0][2].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][2].setVal(x);
				} else if (G.getT()[1][2].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][2].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][2].setVal(x);
				}
			}
			if (30 <= temp && temp <= 39) {
				if (G.getT()[0][3].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][3].setVal(x);
				} else if (G.getT()[1][3].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][3].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][3].setVal(x);
				}
			}
			if (40 <= temp && temp <= 49) {
				if (G.getT()[0][4].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][4].setVal(x);
				} else if (G.getT()[1][4].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][4].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][4].setVal(x);
				}
			}
			if (50 <= temp && temp <= 59) {
				if (G.getT()[0][5].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][5].setVal(x);
				} else if (G.getT()[1][5].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][5].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][5].setVal(x);
				}
			}
			if (60 <= temp && temp <= 69) {
				if (G.getT()[0][6].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][6].setVal(x);
				} else if (G.getT()[1][6].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][6].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][6].setVal(x);
				}
			}
			if (70 <= temp && temp <= 79) {
				if (G.getT()[0][7].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][7].setVal(x);
				} else if (G.getT()[1][7].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][7].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][7].setVal(x);
				}
			}
			if (80 <= temp && temp <= 89) {
				if (G.getT()[0][8].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][8].setVal(x);
				} else if (G.getT()[1][8].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][8].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][8].setVal(x);
				}
			}
			if (90 <= temp && temp <= 99) {
				if (G.getT()[0][9].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[0][9].setVal(x);
				} else if (G.getT()[1][9].getVal() == " ") {
					String x = convertir(temp);
					G.getT()[1][9].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][9].setVal(x);
				}
			}
		}
		return G;
	}

	public void Affichage(Grille G) {
		int i, j;
		String c=" ";
		for (i = 0; i < 3; i++) {
			System.out.println("\n______________________________");
			for (j = 0; j < 10; j++) {
				System.out.print("|");
				if (G.getT()[i][j].getVal() == " ") {
					System.out.print(" ");
				}
				if (j == 0 && G.getT()[i][j].getVal() != " ") {
					System.out.print(c + G.getT()[i][j].getVal());
				} else {
					System.out.print(G.getT()[i][j].getVal());
				}
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