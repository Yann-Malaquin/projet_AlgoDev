package fr.projet.loto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PartieLoto {
	private List<Integer> Lint = new ArrayList<Integer>();
	private int NbJoueurs;
	private List<GrilleLoto> LG = new ArrayList<GrilleLoto>();
	private GrilleLoto G;
	private List<JoueurLoto> LJ = new ArrayList<JoueurLoto>();

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
		int i, cpt = 0, x = 0;
		for (i = 0; i < 50; i++) {
			x = 0;
			while (x != 1) {
				cpt = 0;
				G = new GrilleLoto();
				G.setLI(new ArrayList<Integer>());
				G.creationGrille();
				for (int a = 0; a < 3; a++) {
					for (int b = 0; b < 10; b++) {
						if (G.getG().getT()[a][b].getVal().compareTo(" ") != 0) {
							cpt++;
						}
					}
				}
				if (cpt == 15) {
					LG.add(G);
					x = 1;
				}
			}
		}
		for (GrilleLoto temp : LG) {
			temp.Affichage(temp.getG());
		}
	}

	public void distribution() {
		int i = 0, y, z;
		Scanner sc = new Scanner(System.in);
		String nom;
		int nbfav, nbcartons;
		while (i < NbJoueurs) {
			System.out.println("\nChoisir le pr�nom du joueur: " + (i + 1));
			nom = sc.nextLine();
			System.out.println("\nChoisir le nombre favoris du joueur: ");
			nbfav = sc.nextInt();
			sc.nextLine();
			System.out.println("\nChoisir le nombre de cartons voulu par le joueur: ");
			nbcartons = sc.nextInt();
			sc.nextLine();
			JoueurLoto JL = new JoueurLoto(nbfav, nbcartons);
			JL.creation_joueur(nom);
			LJ.add(JL);
			i++;
		}
		sc.close();
		for (JoueurLoto temp : LJ) {
			y = 0;
			z = 0;
			while (y != temp.getNombredecartonsvoulus()) {
				if (z == 0) {
					for (GrilleLoto temp2 : LG) {
						if (nbfav(temp2, temp)) {
							y++;
							z++;
							LG.remove(temp2);
						}
					}
				}
				Random rand = new Random();
				int taille = rand.nextInt(LG.size());
				temp.setLGJ(LG.get(taille));
				LG.remove(LG.get(taille));
				y++;
			}
			for (GrilleLoto x : temp.getLGJ()) {
				x.Affichage(x.getG());
			}
		}
	}

	public Boolean nbfav(GrilleLoto GL, JoueurLoto JL) {
		for (int a = 0; a < 3; a++) {
			for (int j = 0; j < 10; j++) {
				if (GL.getG().getT()[a][j].getVal() == GL.convertir(JL.getNombrefav())) {
					JL.getLGJ().add(GL);
					System.out.println(JL.getJ().getPseudo());
					return true;
				}
			}
		}
		return false;
	}

	public void debut_partie() {
		int grillepleine=0;
		while (grillepleine !=1) {
		int x=0,y=0,z=0;
		Random rand = new Random();
		int index = rand.nextInt(99);
		int temp = Lint.get(index);
		for (JoueurLoto test : LJ) {
			for (GrilleLoto tempo: test.getLGJ()) {
				for (int a = 0; a < 3; a++) {
					for (int j = 0; j < 10; j++) {
						String str=tempo.convertir(temp);
						if (tempo.getG().getT()[a][j].getVal().compareTo(str)==0) {
						tempo.getG().getT()[a][j].setModifiable(false);
							}
						}
					}
				 for (int t=0;t<10;t++) {
					if (tempo.getG().getT()[0][t].isModifiable()==false) {
						x++;						
					}
					if (tempo.getG().getT()[1][t].isModifiable()==false) {
						y++;						
					}
					if (tempo.getG().getT()[2][t].isModifiable()==false) {
						z++;						
					}
				 }
				 if ((x==5 && y!=5 && z!=5) || (x!=5 && y==5 && z!=5) || (x!=5 && y!=5 && z==5)) {
					 System.out.println("\n1 ligne du carton pour:" + test.getJ().getPseudo());
				 }
				 else if((x==5 && y==5 && z!=5) || (x!=5 && y==5 && z==5) || (x==5 && y!=5 && z==5)) {
					 System.out.println("\n2 lignes du carton pour:" + test.getJ().getPseudo());
				 }
				 else if(x==5 && y==5 && z==5) {
					 System.out.println("\nCarton plein, vainqueur:" + test.getJ().getPseudo());
					 tempo.Affichage(tempo.getG());
					 grillepleine=1;
					 break;
				 	}
				}
			}
		}
	}
}