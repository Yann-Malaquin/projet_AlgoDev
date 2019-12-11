package fr.projet.poker;

import fr.projet.Joueur;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class JoueurPoker extends Joueur {

    // 2 cartes en main
    private List<Carte> mainJoueur;
    // porte feuille du joueur (limite par la table)
    private double wallet;
    // mise du joueur
    private int mise;
    /*List car un joueur peu avoir plusieurs "etats" fold et pblinde ...
     * fold,call,raise,all-in,check,pblinde,gblinde,donneur
     */
    private List<String> etatJoueur;
    //s'il a une QuinteFlushRoayle, QuinteFlush ..... fait avec l'ajout des valeurs de la main
    private int typeDeMain;

    public JoueurPoker(String _pseudo, double _wallet) {
        super(_pseudo);
        mainJoueur = new ArrayList<Carte>();
        wallet = _wallet;
        etatJoueur = new ArrayList<String>();
        typeDeMain = 0;
    }

    public JoueurPoker() {
        super();
        mainJoueur = new ArrayList<Carte>();
        wallet = 0;
        etatJoueur = new ArrayList<String>();
        typeDeMain = 0;
    }

    public List<Carte> getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(Carte c) {
        this.mainJoueur.add(c);
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double _wallet) {
        this.wallet = _wallet;
    }

    public List<String> getEtatJoueur() {
        return etatJoueur;
    }

    public void setEtatJoueur(String _etatJoueur) {
        this.etatJoueur.add(_etatJoueur);
    }

    public void setTypeDeMain(int _typeDeMain){
        this.typeDeMain = _typeDeMain;
    }

    public int getTypeDeMain(){
        return typeDeMain;
    }

    public String toString(){
        return "[ "+getPseudo()+", etatJoueur"+ this.getEtatJoueur()+" ]\n";
    }
}