package fr.projet;

/*public class MiniJeux {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int choix=1;
		
		while(choix!=0) {
			Scanner sc1 = new Scanner(System.in);
			System.out.println("<0> Quitter <1> Sudoku <2> Loto"/* <3> Poker <4> Bataille Navale);
			System.out.println("Choix: ");

			try {
				choix = sc1.nextInt();
			} catch (Exception e) {
				System.out.println(e);
			}


			switch (choix) {
				case 0:
					System.out.println("Fin programme");
					System.exit(0);
				case 1:
					Sudoku test = new Sudoku();
					try {
						test.Jouer(3);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("\n");
					break;
				case 2:
					PartieLoto P = new PartieLoto(4);
					P.Creationbouleloto();
					P.CreationGrille();
					P.distribution();
					P.debut_partie();
					System.out.println("\n");
					break;

				case 3:
					PartiePoker p = new PartiePoker();
					List<Carte> lcarte = new ArrayList<Carte>();
					List<Carte> lcarte2 = new ArrayList<Carte>();
					JoueurPoker Michel = new JoueurPoker("Michel");
					JoueurPoker Claude = new JoueurPoker("Claude");
					JoueurPoker Jean = new JoueurPoker("Jean");
					JoueurPoker JC = new JoueurPoker("JC");

					Michel.setEtatJoueur("blabla");
					Claude.setEtatJoueur("nsd");
					Jean.setEtatJoueur("donneur");
					JC.setEtatJoueur("blabla");

					p.setJoueurs(Michel);
					p.setJoueurs(Claude);
					p.setJoueurs(Jean);
					p.setJoueurs(JC);

					lcarte = p.genererPaquetCarte();
					p.afficherPaquetCartes(lcarte);
					p.distribuerCartes(lcarte);
					break;

					//sc.close();
			}
		}

	}

} */
