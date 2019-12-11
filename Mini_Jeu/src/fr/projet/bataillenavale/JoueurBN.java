package fr.projet.bataillenavale;

import fr.projet.Joueur;

public class JoueurBN extends Joueur {
    private boolean IA;

    public JoueurBN(String pseudo, boolean IA){
        super(pseudo);
        this.IA = IA;

    }

    public boolean isIA(){
        return IA;
    }

    public void setIA(boolean IA){
        this.IA = IA;
    }
}
