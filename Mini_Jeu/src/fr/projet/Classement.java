package fr.projet;

import java.io.*;
import java.util.ArrayList;

public class Classement implements Serializable{
    public ArrayList<Joueur> ClassementGlobal=new ArrayList<Joueur>();
    public String FichierSauvegarde="C:\\Users\\Bitfenix\\Desktop\\Projet\\Classement.txt";

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
    public void DonnerPointAUnJoueur(Joueur J, int points, int jeu){
        int indiceJoueur=-1;
        System.out.println("DOnnerPoint" + ClassementGlobal.toString());
        for(int i=0; i<ClassementGlobal.size();i++){
            if(ClassementGlobal.get(i).getPseudo().equals(J.getPseudo()))
                indiceJoueur=i;
        }
        if(indiceJoueur==-1){
            ClassementGlobal.add(J);
            indiceJoueur=ClassementGlobal.indexOf(J);
        }

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



    }


