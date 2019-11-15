package fr.projet;

import java.util.ArrayList;

public class Classement {
    public ArrayList<Joueur> ClassementGlobal=new ArrayList<Joueur>();
    public String FichierSauvegarde="Classement.txt";

    //Ajoute un joueur dans le Classement Si non présent.
    public void ajouterJoueur(Joueur J){
        // charger fichier
        int indiceJoueur=-1;
        for(int i=0; i<ClassementGlobal.size();i++){
            if(ClassementGlobal.get(i).getPseudo().equals(J.getPseudo()))
                indiceJoueur=i;
        }
        if(indiceJoueur==-1){
            ClassementGlobal.add(J);
        }
    }

    // jeu = 1 Sudoku / 2 Loto / 3 Poker / 4 BN (comme dansle menu du main)
    public void DonnerPointAUnJoueur(Joueur J, int points, int jeu){
        int indiceJoueur=-1;
        for(int i=0; i<ClassementGlobal.size();i++){
            if(ClassementGlobal.get(i).getPseudo().equals(J.getPseudo()))
                indiceJoueur=i;
        }
        if(indiceJoueur==-1){
            ClassementGlobal.add(J);
        }
        indiceJoueur=ClassementGlobal.indexOf(J);
        if(jeu==1) {
            ClassementGlobal.get(indiceJoueur).ajoutScoreSudo(points);
        }
        else if(jeu==2) {
            ClassementGlobal.get(indiceJoueur).ajoutScoreLoto(points);
        }
        else if(jeu==3) {
            ClassementGlobal.get(indiceJoueur).ajoutScorePoker(points);
        }
        else if(jeu==4) {
            ClassementGlobal.get(indiceJoueur).ajoutScoreBN(points);
        }
    }
}