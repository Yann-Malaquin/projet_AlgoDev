package fr.projet.bataillenavale;


public class Bateau {
	public int taille;
	public boolean horizontal;
	private int pv ;


	public Bateau(int taille, boolean horizontal){
	this.taille = taille;
	this.horizontal = horizontal;
	pv = taille;
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