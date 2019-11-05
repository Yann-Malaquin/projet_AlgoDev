package fr.projet.loto;

import fr.projet.Grille;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrilleLoto {
	private List<Integer> LI = new ArrayList<Integer>();
	private Grille G = new Grille(3, 10);

	public GrilleLoto() {
		super();
	}

	public void creationGrille() {
		int j, x = 0, y = 0;
		while (y != 1) {
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
			if (verif2(LI)==true) {
				y++;
			}
			else {
				LI.clear();
			}
		}
		Collections.sort(LI);
		G = rangercartons(G, LI);
		// Affichage(G);
	}

	public String convertir(int a) {
		String c = Integer.toString(a);
		return c;
	}

	public Boolean verif(Grille G, int ligne) {
		int cpt = 0;
		for (int i = 0; i < 10; i++) {
			if (G.getT()[ligne][i].getVal().compareTo(" ") != 0) {
				cpt++;
			}
		}
		if (cpt < 5) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean verif2(List<Integer> LI) {
		int cpt, cpt1, cpt2, cpt3, cpt4, cpt5, cpt6, cpt7, cpt8, cpt9;
		cpt = cpt1 = cpt2 = cpt3 = cpt4 = cpt5 = cpt6 = cpt7 = cpt8 = cpt9 = 0;
		for (Integer temp : LI) {
			if (1 <= temp && temp <= 9) {
				cpt++;
			} else if (10 <= temp && temp <= 19) {
				cpt1++;
			} else if (20 <= temp && temp <= 29) {
				cpt2++;
			} else if (30 <= temp && temp <= 39) {
				cpt3++;
			} else if (40 <= temp && temp <= 49) {
				cpt4++;
			} else if (50 <= temp && temp <= 59) {
				cpt5++;
			} else if (60 <= temp && temp <= 69) {
				cpt6++;
			} else if (70 <= temp && temp <= 79) {
				cpt7++;
			} else if (80 <= temp && temp <= 89) {
				cpt8++;
			} else if (90 <= temp && temp <= 99) {
				cpt9++;
			}
		}
		if(cpt<=3 && cpt1<=3 && cpt2<=3 && cpt3<=3 && cpt4<=3 && cpt5<=3 && cpt6<=3 && cpt7<=3 && cpt8<=3 && cpt9<=3) {
			return true;
		}
		else {
			return false;
		}
	}

	public Grille rangercartons(Grille G, List<Integer> LI) {
		for (Integer temp : LI) {
			if (1 <= temp && temp <= 9) {
				if (G.getT()[0][0].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][0].setVal(x);
				} else if (G.getT()[1][0].getVal() == " " && verif(G, 1)==true) {
					String x = convertir(temp);
					G.getT()[1][0].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][0].setVal(x);
				}
			}
			if (10 <= temp && temp <= 19) {
				if (G.getT()[0][1].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][1].setVal(x);
				} else if (G.getT()[1][1].getVal() == " " && verif(G, 1)==true) {
					String x = convertir(temp);
					G.getT()[1][1].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][1].setVal(x);
				}
			}
			if (20 <= temp && temp <= 29) {
				if (G.getT()[0][2].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][2].setVal(x);
				} else if (G.getT()[1][2].getVal() == " " && verif(G, 1)==true) {
					String x = convertir(temp);
					G.getT()[1][2].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][2].setVal(x);
				}
			}
			if (30 <= temp && temp <= 39) {
				if (G.getT()[0][3].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][3].setVal(x);
				} else if (G.getT()[1][3].getVal() == " " && verif(G, 1)==true) {
					String x = convertir(temp);
					G.getT()[1][3].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][3].setVal(x);
				}
			}
			if (40 <= temp && temp <= 49) {
				if (G.getT()[0][4].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][4].setVal(x);
				} else if (G.getT()[1][4].getVal() == " " && verif(G, 1)==true) {
					String x = convertir(temp);
					G.getT()[1][4].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][4].setVal(x);
				}
			}
			if (50 <= temp && temp <= 59) {
				if (G.getT()[0][5].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][5].setVal(x);
				} else if (G.getT()[1][5].getVal() == " " && verif(G, 1)==true) {
					String x = convertir(temp);
					G.getT()[1][5].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][5].setVal(x);
				}
			}
			if (60 <= temp && temp <= 69) {
				if (G.getT()[0][6].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][6].setVal(x);
				} else if (G.getT()[1][6].getVal() == " " && verif(G, 1)==true) {
					String x = convertir(temp);
					G.getT()[1][6].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][6].setVal(x);
				}
			}
			if (70 <= temp && temp <= 79) {
				if (G.getT()[0][7].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][7].setVal(x);
				} else if (G.getT()[1][7].getVal() == " " && verif(G, 1)==true) {
					String x = convertir(temp);
					G.getT()[1][7].setVal(x);

				} else {
					String x = convertir(temp);
					G.getT()[2][7].setVal(x);

				}
			}
			if (80 <= temp && temp <= 89) {
				if (G.getT()[0][8].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][8].setVal(x);
				} else if (G.getT()[1][8].getVal() == " " && verif(G, 1)==true) {
					String x = convertir(temp);
					G.getT()[1][8].setVal(x);
				} else {
					String x = convertir(temp);
					G.getT()[2][8].setVal(x);
				}
			}
			if (90 <= temp && temp <= 99) {
				if (G.getT()[0][9].getVal() == " " && verif(G, 0)==true) {
					String x = convertir(temp);
					G.getT()[0][9].setVal(x);
				} else if (G.getT()[1][9].getVal() == " " && verif(G, 1)==true) {
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
		String c = " ";
		System.out.println("\n");
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

	public Grille getG() {
		return G;
	}

	public void setG(Grille g) {
		G = g;
	}

	public List<Integer> getLI() {
		return LI;
	}

	public void setLI(List<Integer> lI) {
		LI = lI;
	}

}