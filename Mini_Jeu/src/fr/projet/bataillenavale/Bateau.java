package fr.projet.bataillenavale;

import javafx.scene.Parent;

public class Bateau extends Parent {
	private int taille;
	private boolean horizontal = true;
	private int hp;

	public Bateau(int taille, boolean horizontal){
		this.taille = taille;
		this.horizontal = horizontal;
		hp = taille;
	}

	public void touche(){
		hp--;
	}

	public boolean estVivant() {
		return hp > 0;
	}


	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
}
