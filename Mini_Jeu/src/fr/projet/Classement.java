package fr.projet;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Classement implements Serializable{

    public ArrayList<Joueur> ClassementGlobal=new ArrayList<Joueur>();
    public String FichierSauvegarde="/resources/menuprincipal/Classement.txt";


    public Classement() {
    }

    //Ajoute un joueur dans le Classement Si non présent.
    public void ajouterJoueur(Joueur J){
        // charger fichier
        int indiceJoueur=-1;
        System.out.println("ajoutPoint" + ClassementGlobal.toString());
        for(int i=0; i<ClassementGlobal.size();i++){
            if(ClassementGlobal.get(i).getPseudo().equals(J.getPseudo()))
                indiceJoueur=i;
        }
        if(indiceJoueur==-1){
            ClassementGlobal.add(J);
        }
    }

    // jeu = 1 Sudoku / 2 Loto / 3 Poker / 4 BN (comme dansle menu du main)
    public void DonnerPointAUnJoueur(Joueur J, int points, int jeu) {
            int indiceJoueur = -1;
            if(J.getPseudo().equals(null)){ System.out.println("PSEUDO WAS NULL");J.setPseudo("Anonyme");}
        //Recherche de l'indice du Joueur J


            for (int i = 0; i < ClassementGlobal.size(); i++) {
                if (ClassementGlobal.get(i).getPseudo().equals(J.getPseudo()))
                    indiceJoueur = i;
            }
            // S'il n'existe pas alors on ajoute le joueur J au classement
            if (indiceJoueur == -1) {
                ClassementGlobal.add(J);
                indiceJoueur = ClassementGlobal.indexOf(J);
            }
            //Ajout des points selon le jeu
            if (jeu == 1) {
                ClassementGlobal.get(indiceJoueur).ajoutScoreSudo(points);
            } else if (jeu == 2) {
                ClassementGlobal.get(indiceJoueur).ajoutScoreLoto(points);
            } else if (jeu == 3) {
                ClassementGlobal.get(indiceJoueur).ajoutScorePoker(points);
            } else if (jeu == 4) {
                ClassementGlobal.get(indiceJoueur).ajoutScoreBN(points);
            }
        }

    //Methode d'affichage console du classement
    public void afficherClassement() {
        for (fr.projet.Joueur P : this.ClassementGlobal) {
            P.afficherJoueur();

        }
    }

    public File SauvegardeClassement(String Nom) {

        File SaveClassement = new File(Nom);
        FileOutputStream temp;
        try {
            temp = new FileOutputStream(SaveClassement);
            ObjectOutputStream oos = new ObjectOutputStream(temp);
            oos.writeObject(this);
            oos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return SaveClassement;
    }

    public void ChargerClassement(Classement C, String nomSave) {

        File f = new File(nomSave);

        FileInputStream inFileStream;

        try {
            inFileStream = new FileInputStream(f);
            ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);
            C = (Classement) inObjectStream.readObject();

            ClassementGlobal = C.ClassementGlobal;

            inObjectStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    // Trie selon le score du jeu donné : 1 = SUDO / 2= LOTO / 3= Poker / 4= BN / 5=Score Global
    public void TrierListeSelonScore(int indiceJeu){

        // On verifie que l'indice du jeu est compris entre 1 et 4
        if(indiceJeu>=1 && indiceJeu<=5){
            Joueur tmpi=new Joueur();
            Joueur tmpj=new Joueur();

            int scorei;
            int scorej;
            for(int i=0; i<ClassementGlobal.size()-1;i++){
                tmpi=ClassementGlobal.get(i);
                // On detecte le jeu à prendre et on associe le score du jeu
                if(indiceJeu==1){
                    scorei=tmpi.getScoreSudo();
                }
                else if(indiceJeu==2){
                    scorei=tmpi.getScoreLoto();
                }
                else if(indiceJeu==3){
                    scorei=tmpi.getScorePoker();
                }
                else if(indiceJeu==4){
                    scorei=tmpi.getScoreBN();
                }
                else{ scorei=tmpi.getScore();}


                for(int j=i+1;j<ClassementGlobal.size();j++){
                    tmpj=ClassementGlobal.get(j);
                    // On detecte le jeu à prendre et on associe le score du jeu
                    if(indiceJeu==1){
                        scorej=tmpj.getScoreSudo();
                    }
                    else if(indiceJeu==2){
                        scorej=tmpj.getScoreLoto();
                    }
                    else if(indiceJeu==3){
                        scorej=tmpj.getScorePoker();
                    }
                    else if(indiceJeu==4){
                        scorej=tmpj.getScoreBN();
                    }
                    else{
                        scorej=tmpj.getScore();
                    }
                    //On permute les deux éléments pour avoir le plus grand en tête (ordre décroissant)
                    if(scorej>=scorei){
                        Collections.swap(ClassementGlobal,i,j);
                    }

                }

            }
        }
    }

    public void TrierSelonPseudo(){
        Joueur tmpi=new Joueur();
        Joueur tmpj=new Joueur();


        for(int i=0; i<ClassementGlobal.size()-1;i++){
            tmpi=ClassementGlobal.get(i);
            for(int j=i+1; j<ClassementGlobal.size();j++){
                tmpj=ClassementGlobal.get(j);
                if( (tmpj.getPseudo().compareToIgnoreCase(tmpi.getPseudo()) <0)){
                    Collections.swap(ClassementGlobal,i,j);
                }


            }
        }

    }

    Joueur TrouverJoueur(String pseudo){
        for(Joueur J: ClassementGlobal){
            if(J.getPseudo().equals(pseudo)){
                return J;
            }
        }
        return null;
    }


}
