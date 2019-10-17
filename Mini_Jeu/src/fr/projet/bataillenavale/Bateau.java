package fr.projet.bataillenavale;

import java.util.ArrayList;

public class Bateau {
	private String nom;
	private int taille;
	private boolean position; //true Horizontal False Vertical
	private static ArrayList<Bateau> TabBateau = new ArrayList<Bateau>();
	private static int totalCase;
	
public Bateau(String nom, int taille, boolean position) {
	this.setNom(nom);
	this.setTaille(taille);
	this.setPosition(position);
	totalCase = totalCase + taille;
	TabBateau.add(this);
}

public Bateau(int taille, boolean position) {
	setNom("Bateau");
	this.setTaille(taille);
	this.setPosition(position);
	totalCase = totalCase + taille;
	TabBateau.add(this);
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

public static int getTotalcase() {
	// TODO Auto-generated method stub
	return totalCase;
}

public static ArrayList<Bateau> getTabBateau() {
	return TabBateau;
}

public void setTabBateau(ArrayList<Bateau> tabBateau) {
	TabBateau = tabBateau;
}



}