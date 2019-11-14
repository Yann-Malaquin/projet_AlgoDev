package fr.projet.bataillenavale;


public class Bateau {
	private int taille;
	private boolean horizontal = false;
	private int pv ;


	public Bateau(int type, boolean horizontal){
	this.taille = taille;
	this.horizontal = horizontal;
	pv = taille;
	}




	public int getTaille() {
	return taille;
	}

	public void setTaille(int taille) {
	this.taille = taille;
	}

	public boolean getHorizontal() {
	return horizontal;
	}

	public void toucher(){
	pv = pv--;
	}

	public boolean vivant() {
		if (pv > 0) {
			return true;
		}
	return false;
}

}