package fr.projet.bataillenavale;
import fr.projet.Grille;

public class GrilleBN extends Grille {

	public GrilleBN(int imax, int jmax) {
		super(imax, jmax);
		// TODO Auto-generated constructor stub
	}
	
 public void AfficherGrille () {
		
	 int i; int j;
	 	
	 	System.out.println("\t\t\t\t\t     0    1    2    3    4    5    6    7    8    9");
	 	System.out.println("\t\t\t\t\t   -----------------------------------------------------");
	 	for( i=0;i<imax;i++) {
	 		System.out.print("\t\t\t\t\t");
	 		System.out.print(i + "  "); 
	 		for( j=0;j<jmax;j++) {
	 			
	 			System.out.print("| " + this.t[i][j].getVal() + " |");
	 		}
	 		
	 		System.out.println("\n\t\t\t\t\t   -----------------------------------------------------");
	 }
	 }

 public static void main(String args[]) {
		GrilleBN g=new GrilleBN(10,10);
		g.initialiserCaseDeLaGrille();
		//Grille Copieg=new Grille(g);
		g.AfficherGrille();
		/*System.out.print(g.t[0][0].valeur);*/
		
 }}