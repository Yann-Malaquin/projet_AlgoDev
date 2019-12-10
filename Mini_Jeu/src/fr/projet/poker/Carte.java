package fr.projet.poker;

import javafx.scene.layout.AnchorPane;

public class Carte extends AnchorPane {

    private String figure;
    private String couleur;
    private int valeur;

    public Carte() {
        this.figure = null;
        this.couleur = null;
        this.valeur = 0;
    }

    public Carte(String _figure, String _couleur, int _valeur) {
        this.figure = _figure;
        this.couleur = _couleur;
        this.valeur = _valeur;
    }

    public void setFigure(String _figure) {
        this.figure = _figure;
    }

    public String getFigure() {
        return this.figure;
    }

    public void setCouleur(String _couleur) {
        this.couleur = _couleur;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public void setValeur(int _valeur) {
        this.valeur = _valeur;
    }

    public int getValeur() {
        return this.valeur;
    }

    public String toString() {
        return (this.figure + " de " + this.couleur);
    }


}
