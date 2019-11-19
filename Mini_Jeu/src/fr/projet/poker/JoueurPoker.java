package fr.projet.poker;

import fr.projet.Joueur;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class JoueurPoker extends Joueur {

    private List<AnchorPane> mainJoueur; // 2 cartes en main
    private int wallet; // porte feuille du joueur (limite par la table)
    private int mise; // mise du joueur
    private List<String> etatJoueur;
    /*List car un joueur peuavoir plusieurs "etats" fold et pblinde ...
     * fold,call,raise,all-in,check,pblinde,gblinde,donneur
     */

    public JoueurPoker(String _pseudo, int _wallet) {
        super(_pseudo);
        mainJoueur = new ArrayList<AnchorPane>();
        int wallet = _wallet;
        etatJoueur = new ArrayList<String>();
    }

    public List<AnchorPane> getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(AnchorPane c) {
        this.mainJoueur.add(c);
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int _wallet) {
        this.wallet = _wallet;
    }

    public List<String> getEtatJoueur() {
        return etatJoueur;
    }

    public void setEtatJoueur(String _etatJoueur) {
        this.etatJoueur.add(_etatJoueur);
    }

    @Override
    public String toString() {
        return this.getPseudo() + "\nmainJoueur= [" + mainJoueur + "]\nwallet=" + wallet + " �\netatJoueur=["
                + etatJoueur + "]\n";
    }
}
