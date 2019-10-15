package fr.projet;

public class Joueur {
	
	private String pseudo;
	public int score;

	
//Constructeurs
	
	public Joueur() {
		super();
	}
	
	public Joueur(String pseudo) {
		super();
		this.pseudo = pseudo;
	}

	public Joueur(String pseudo, int score) {
		super();
		this.pseudo = pseudo;
		this.score = score;
	}
	
// Getter + setter
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
