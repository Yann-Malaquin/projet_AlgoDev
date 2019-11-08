package fr.projet.loto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PartieLoto {
	private List<Integer> Lint = new ArrayList<Integer>();
	private List<Integer> Lboulesortie = new ArrayList<Integer>();
	private int NbJoueurs;
	private List<GrilleLoto> LG = new ArrayList<GrilleLoto>();
	private GrilleLoto G;
	private List<JoueurLoto> LJ = new ArrayList<JoueurLoto>();
	private List<Lots> Lcadeau = new ArrayList<Lots>() ;

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
			System.out.println("\nChoisir le prénom du joueur: " + (i + 1));
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
		//sc.close();
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

/*	public void debut_partie_auto() {
		int grillepleine=0,t1=0,t2=0,t3=0,cadeau1=0,cadeau2=0,cadeau3=0;
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
				if (t1==0){ 
				 if ((x==5 && y!=5 && z!=5) || (x!=5 && y==5 && z!=5) || (x!=5 && y!=5 && z==5)) {
					 System.out.println("\n\n1 ligne du carton pour:" + test.getJ().getPseudo() + " sur sa grille:");
					 tempo.Affichage(tempo.getG());
					 t1++;
				 }}
				else if (t2==0) {
				 if((x==5 && y==5 && z!=5) || (x!=5 && y==5 && z==5) || (x==5 && y!=5 && z==5)) {
					 System.out.println("\n\n2 lignes du carton pour:" + test.getJ().getPseudo() + " sur sa grille:");
					 tempo.Affichage(tempo.getG());
					 t2++;
				 }}
				else if (t3==0) {
				 if(x==5 && y==5 && z==5) {
					 System.out.println("\n\nCarton plein, vainqueur:" + test.getJ().getPseudo() + " sur sa grille");
					 tempo.Affichage(tempo.getG());
					 grillepleine=1;
					 break;}
				 	}
				}
			}
		}
	}*/
	
	public void debut_partie() {
	int grillepleine=0,t1=0,t2=0,t3=0,cadeau1=0,cadeau2=0,cadeau3=0,max=99;
	remplissage_cadeau();
	while (grillepleine !=1) {
	int x=0,y=0,z=0;
	Random rand = new Random();
	int index = rand.nextInt(max);
	int temp = Lint.get(index);
	Lint.remove(index);
	max--;
	Lboulesortie.add(temp);
	for (JoueurLoto test : LJ) {
		System.out.println("\n\n" + test.getJ().getPseudo() + ":");
		for (GrilleLoto tempo: test.getLGJ()) {
			System.out.println("Le numéro de cette grille: " + (test.getLGJ().indexOf(tempo)+1) );
			tempo.Affichage(tempo.getG());
			System.out.println("\nCette grille contient-elle le numéro ? " + temp + "\n1=Oui, 2=Non");
			Scanner sc1 = new Scanner(System.in);
			int verif = sc1.nextInt();
			sc1.nextLine();
			if(verif==1) {
				System.out.println("Donnez le numéro de la ligne, puis de la colonne:");
			int l = sc1.nextInt();
			int c = sc1.nextInt();
			tempo.getG().getT()[l-1][c-1].setModifiable(false);
			}
			System.out.println("Rappel des numéros déjà tirées:");
			for (int c:Lboulesortie) {
				System.out.println(c + " ");
			}
			if (t1==0) {
			System.out.println("Avez-vous obtenu 1 ligne ? 1=Oui, 2=Non");
			int a = sc1.nextInt();
			a--;
				if(a==0) {
					 for (int t=0;t<10;t++) {
							if ((tempo.getG().getT()[0][t].isModifiable()==false) && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[0][t].getVal())))) {
								x++;						
							}
							if (tempo.getG().getT()[1][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[1][t].getVal())))) {
								y++;						
							}
							if (tempo.getG().getT()[2][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[2][t].getVal())))) {
								z++;						
							}
						 }
					 if ((x==5 && y!=5 && z!=5) || (x!=5 && y==5 && z!=5) || (x!=5 && y!=5 && z==5)) {
						 //vainqueur 1 ligne
						 System.out.println("\nBravo vous avez obtenu 1 ligne voici votre cadeau:");
						 while (cadeau1==0) {
							Random rand1 = new Random();
							int taille = rand1.nextInt(Lcadeau.size());
							Lots c=Lcadeau.get(taille);
							if (c.getLigne()==1) {
								cadeau1++;
								System.out.println("\nVotre cadeau:" + c.getCadeau());
								Lcadeau.remove(c);
							}
						 }
						 t1++;
					 }
					 else {
						 System.out.println("\nVous vous êtes trompés!");
						 t1=0;
					 }
				}
					
				}
			if (t2==0 && t1==1) {
			System.out.println("Avez-vous obtenu 2 lignes ? 1=Oui, 2=Non");
			int a = sc1.nextInt();
			a--;
				if(a==0) {
					 for (int t=0;t<10;t++) {
							if ((tempo.getG().getT()[0][t].isModifiable()==false) && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[0][t].getVal())))) {
								x++;						
							}
							if (tempo.getG().getT()[1][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[1][t].getVal())))) {
								y++;						
							}
							if (tempo.getG().getT()[2][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[2][t].getVal())))) {
								z++;						
							}
						 }
					 if((x==5 && y==5 && z!=5) || (x!=5 && y==5 && z==5) || (x==5 && y!=5 && z==5)) {
						 //vainqueur 2 lignes
						 System.out.println("\nBravo vous avez obtenu 2 lignes voici votre cadeau:");
						 while (cadeau2==0) {
							Random rand1 = new Random();
							int taille = rand1.nextInt(Lcadeau.size());
							Lots c=Lcadeau.get(taille);
							if (c.getLigne()==2) {
								cadeau2++;
								System.out.println("\nVotre cadeau:" + c.getCadeau());
								Lcadeau.remove(c);
							}
						 }
						 t2++;
					 }
					 else {
						 System.out.println("\nVous vous êtes trompés!");
						 t2=0;
					 }
				}
				}
			if (t3==0 && t1==1 && t2==1) {
				System.out.println("Avez-vous obtenu 1 carton plein ? 1=Oui, 2=Non");
				int a = sc1.nextInt();
				a--;
					if(a==0) {
						 for (int t=0;t<10;t++) {
								if ((tempo.getG().getT()[0][t].isModifiable()==false) && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[0][t].getVal())))) {
									x++;						
								}
								if (tempo.getG().getT()[1][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[1][t].getVal())))) {
									y++;						
								}
								if (tempo.getG().getT()[2][t].isModifiable()==false && (Lboulesortie.contains(Integer.valueOf(tempo.getG().getT()[2][t].getVal())))) {
									z++;						
								}
							 }
						 if(x==5 && y==5 && z==5) {
							 //vainqueur carton plein
							 System.out.println("\nBravo vous avez obtenu 1 carton plein voici votre cadeau:");
							 while (cadeau3==0) {
								Random rand1 = new Random();
								int taille = rand1.nextInt(Lcadeau.size());
								Lots c=Lcadeau.get(taille);
								if (c.getLigne()==3) {
									cadeau3++;
									System.out.println("\nVotre cadeau:" + c.getCadeau());
									Lcadeau.remove(c);
								}
							 }
							 t3++;
							 tempo.Affichage(tempo.getG());
							 grillepleine=1;
							 break;
						 }
						 else {
							 System.out.println("\nVous vous êtes trompés!");
							 t3=0;
						 }
					}
					}
		}
	}
	}
}
	
	public Lots remplissage_cadeau() {
		Lots L;
		L=new Lots("Blu-Ray", 1);
		Lcadeau.add(L);
		L=new Lots("Appareil à raclette", 2);
		Lcadeau.add(L);
		L=new Lots("VTT", 3);
		Lcadeau.add(L);
		L=new Lots("Télévision 4k", 3);
		Lcadeau.add(L);
		L=new Lots("Bon d'achat de 20 euros", 1);
		Lcadeau.add(L);
		L=new Lots("1 bière offerte au bar", 1);
		Lcadeau.add(L);
		L=new Lots("Roman policier", 1);
		Lcadeau.add(L);
		L=new Lots("Casquette tour de France", 1);
		Lcadeau.add(L);
		return L;
	}
}