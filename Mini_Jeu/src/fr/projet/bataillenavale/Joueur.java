/*package fr.projet.bataillenavale;

import java.util.ArrayList;

public class Joueur {
	private String nom;
	private String prenom;
	private String nickname;
	private int point;
	private int couptoucher;
	private GrilleBN tBateau = new GrilleBN(10,10);
	private GrilleBN tUser = new GrilleBN(10,10);
	private ArrayList<Integer> listeCoup;
	
	public Joueur() {
	nom = "nom";
	prenom = "prenom";
	nickname = "pseudo";
	point = 0;
	this.setListeCoup(new ArrayList<Integer>());
	}
	
	public Joueur (String nickname) {
		nom = "nom";
		prenom = "prenom";
		this.nickname = nickname;
		point =  0;
		this.setListeCoup(new ArrayList<Integer>());
	}
	
	public Joueur (String nom, String prenom, String nickname) {
		this.nom = nom;
		this.prenom = prenom;
		this.nickname = nickname;
		point =  0;
		this.setListeCoup(new ArrayList<Integer>());
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public  int getCouptoucher() {
		return couptoucher;
	}

	public void setCouptoucher(int couptoucher) {
		this.couptoucher = couptoucher;
	}

	public int getPoint() {
		return point;
	}
	
	public GrilleBN gettBateau() {
		return tBateau;
	}
	
	public GrilleBN gettUser() {
		return tUser;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public void ajouterPoint(int a) {
		int point = getPoint();
		point = point + a;
		this.setPoint(point);
	}
		
	 public void AfficherGrilleBateau () {
			
		 int i; int j;
		 	
		 	System.out.println("\t\t\t\t\t     0    1    2    3    4    5    6    7    8    9");
		 	System.out.println("\t\t\t\t\t   --------------------------------------------------");
		 	for( i=0;i<10;i++) {
		 		System.out.print("\t\t\t\t\t");
		 		System.out.print(i + "  "); 
		 		for( j=0;j<10;j++) {
		 			
		 			System.out.print("| " + tBateau.getT()[i][j].getVal() + " |");
		 		}
		 		
		 		System.out.println("\n\t\t\t\t\t   --------------------------------------------------");
		 }
		 }
	 
	 public void AfficherGrilleJoueur () {
			
		 int i; int j;
		 	
		 	System.out.println("\t\t\t\t\t     0    1    2    3    4    5    6    7    8    9");
		 	System.out.println("\t\t\t\t\t   --------------------------------------------------");
		 	for( i=0;i<10;i++) {
		 		System.out.print("\t\t\t\t\t");
		 		System.out.print(i + "  "); 
		 		for( j=0;j<10;j++) {
		 			
		 			System.out.print("| " + tUser.getT()[i][j].getVal() + " |");
		 		}
		 		
		 		System.out.println("\n\t\t\t\t\t   --------------------------------------------------");
		 }
	 }

	public ArrayList<Integer> getListeCoup() {
		return listeCoup;
	}

	public void setListeCoup(ArrayList<Integer> listeCoup) {
		this.listeCoup = listeCoup;
	}	 
} */
