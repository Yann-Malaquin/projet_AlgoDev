package fr.projet.poker;

public class Carte {
	
	private String couleur;
	private String numero;
	private int valeur;
	
	public Carte(String _couleur, String _numero, int _valeur)
	{
		this.couleur=_couleur;
		this.numero=_numero;
		this.valeur=_valeur;
	}
	
	public String getCouleur()
	{
		return this.couleur;
	}
	
	public void setCouleur(String _couleur)
	{
		this.couleur=_couleur;
	}
	
	public String getNumero()
	{
		return this.numero;
	}
	
	public void setNumero(String _numero)
	{
		this.numero=_numero;
	}
	
	public int getValeur()
	{
		return this.valeur;
	}
	
	public void setValeur(int _valeur)
	{
		this.valeur=_valeur;
	}
	
	public String toString()
	{
		return (this.numero+" de "+this.couleur);
	}
}
