package fr.projet.bataillenavale;

public class Bateau {
	private String nom;
	private int taille;
	private boolean position; //true Horizontal False Vertical
	
public Bateau(String nom, int taille, boolean position) {
	this.setNom(nom);
	this.setTaille(taille);
	this.setPosition(position);
}

public Bateau(int taille, boolean position) {
	setNom("Bateau");
	this.setTaille(taille);
	this.setPosition(position);
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public int getTaille() {
	return taille;
}

public void setTaille(int taille) {
	this.taille = taille;
}

public boolean getPosition() {
	return position;
}

public void setPosition(boolean position) {
	this.position = position;
}



}