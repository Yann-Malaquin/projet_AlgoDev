package fr.projet;


import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

        import fr.projet.loto.PartieLoto;
        import fr.projet.poker.Carte;
        import fr.projet.poker.JoueurPoker;
        import fr.projet.sudoku.Sudoku;
        import fr.projet.poker.*;

public class MiniJeux {

    public static void main(String[] args) throws IOException {
        MenuPrincipal e = new MenuPrincipal();
        e.affichageMenuPrincipal();
    }
}
