package fr.projet;

import java.io.Serializable;

public class Joueur implements Serializable {
	
	private String pseudo;
	private int score=0;
	private int scoreSudo=0;
	private int scoreLoto=0;
	private int scoreBN=0;
	private int scorePoker=0;



	
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

	public int getScoreLoto() {
		return scoreLoto;
	}

	public int getScoreSudo() {
		return scoreSudo;
	}

	public void setScoreSudo(int scoreSudo) {
		this.scoreSudo = scoreSudo;
	}

	public void setScoreLoto(int scoreLoto) {
		this.scoreLoto = scoreLoto;
	}

	public int getScoreBN() {
		return scoreBN;
	}

	public void setScoreBN(int scoreBN) {
		this.scoreBN = scoreBN;
	}

	public int getScorePoker() {
		return scorePoker;
	}

	public void setScorePoker(int scorePoker) {
		this.scorePoker = scorePoker;
	}

	public void ajoutScoreSudo(int points){
		this.scoreSudo+=points;
		this.score+=200*points;
	}
/** Methodes d'ajout de points */
	public void ajoutScorePoker(int points){
		this.scorePoker+=points;
		this.score+=points;
	}
	public void ajoutScoreBN(int points){
		this.scoreBN+=points;
		this.score+=1000*points;
	}
	public void ajoutScoreLoto(int points){
		this.scoreLoto+=points;
		this.score+=1000*points;
	}

	public void afficherJoueur(){
		System.out.println(pseudo +"\n\t"+ scoreSudo +"\t"+ scoreLoto +"\t"+ scorePoker  +"\t"+ scoreBN +"\t"+ score);
	}

}
