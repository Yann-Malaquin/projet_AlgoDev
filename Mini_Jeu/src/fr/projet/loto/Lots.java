package fr.projet.loto;

public class Lots {
	private String cadeau;
	private int ligne; /*1 = une ligne, 2 = deux lignes, 3 = trois lignes */
	
	public Lots(String cadeau, int ligne) {
		super();
		this.cadeau = cadeau;
		this.ligne = ligne;
	}

	public String getCadeau() {
		return cadeau;
	}

	public void setCadeau(String cadeau) {
		this.cadeau = cadeau;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	
	
	
}
