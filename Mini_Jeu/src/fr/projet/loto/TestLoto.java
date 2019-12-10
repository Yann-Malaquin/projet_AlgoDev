 package fr.projet.loto;

public class TestLoto {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PartieLoto P = new PartieLoto(4);
        P.Creationbouleloto();
        //P.AffichageBoules();
        P.CreationGrille();
        P.distribution();
        P.debut_partie();
    }
} 
