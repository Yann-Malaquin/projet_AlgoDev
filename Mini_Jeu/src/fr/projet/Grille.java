package fr.projet;


public class Grille {
	
private Case t[][];
private int imax;
private int jmax;

public Grille( int imax, int jmax) {
	super();
	
	this.imax = imax;
	this.jmax = jmax;
	this.t= new Case[imax][jmax];
	t=this.initialiserCaseDeLaGrille();
}

//Constructeur qui copie une grille g existante : Bug atm
/*
public Grille(Grille g) {
	this.imax=g.imax;
	this.jmax=g.jmax;
	this.t= new Case[imax][jmax];
	this.CopierGrille(g);
}
*/   

//initialise les cases de la grille
public Case[][] initialiserCaseDeLaGrille() {
	int i; int j;
	for(i=0;i<this.imax;i++) {
		for(j=0;j<this.jmax;j++) {
			this.t[i][j]=new Case(" ",true);
		}
	}
	return this.t;
}



public void AfficherGrille() {
	
int i; int j;
	
	System.out.println("\t\t\t\t\t     0    1    2        3    4    5        6    7    8     ");
	System.out.println("\t\t\t\t\t   -----------------------------------------------------");
	for( i=0;i<imax;i++) {
		System.out.print("\t\t\t\t\t");
		System.out.print(i + "  "); 
		for( j=0;j<jmax;j++) {
			
			System.out.print("| " + this.t[i][j].getVal() + " |"); if(j==2 || j==5 ) System.out.print("    ");
		}
		
		System.out.println("\n\t\t\t\t\t   -----------------------------------------------------");
		if(i==2 || i==5 ) 
			System.out.println("\t\t\t\t\t   -----------------------------------------------------");
}




public Case[][] getT() {
	return t;
}

public int getImax() {
	return imax;
}

public int getJmax() {
	return jmax;
}


public  static void main(String args[] ){
	
	Grille g=new Grille(9,9);
	g.t[0][0].setVal("1");
	g.t[1][1].setVal("4");
	g.t[0][1].setVal("2");
	g.t[1][0].setVal("3"); 
	
	g.AfficherGrille(); 
	System.out.print(g.t[0][0].getVal());
}
