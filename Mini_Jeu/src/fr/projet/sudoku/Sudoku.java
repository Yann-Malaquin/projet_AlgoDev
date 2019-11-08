package fr.projet.sudoku;

import fr.projet.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sudoku extends JFrame {

    public static final int taille_grille =9;
    public static final int taille_sous_grille =3;
    public static final int taille_cellule =60;//px
    public static final int hauteur_max = taille_cellule * taille_grille;
    public static final int largeur_max = taille_cellule * taille_grille;
    private JTextField[][] grilleT = new JTextField[taille_grille][taille_grille];
    private JTextArea ZonePseudo = new JTextArea();
    private GrilleSudoku grille = new GrilleSudoku();
    private JLabel texte = new JLabel();
    private JButton bouton = new JButton();
    private ArrayList<Joueur> listeJoueur;
    private String pseudo = "defaut";
    private JFrame fenetre = new JFrame();
    private JPanel cp = new JPanel();
    private JPanel cd = new JPanel();
    private int CptErreur=0;
    private boolean aCliquer = false;


    public Sudoku() {
        super();
        this.listeJoueur = new ArrayList<Joueur>();


    }

    /*
     * Jouer va lancer une partie selon la difficulté passée en parametre certains
     * parametre sont modifiable comme le nombre d'erreurs maximum
     */
    public void Jouer(int difficulte) throws IOException // 1=facile, 2=moyen, 3=difficile
    {

        Scanner sc = new Scanner(System.in);
        String nomFichier = "src/fr/sudoku/listeJoueurs.txt";

        int score = 0;
        boolean continuer = true;
        boolean pseudoInvalide = true;
        int erreur = 0;
        int erreurMax = 30;// parametre modifiable


        InputListener listener = new InputListener();
        MouseListener mouse = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                aCliquer=true;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                aCliquer=true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        File f = new File(nomFichier);
        if (!f.exists()) {
            nomFichier = "listeJoueurs.txt";
            f = new File(nomFichier);
            f.createNewFile();
        }

        ChargerListe(nomFichier);

        /*
         * On fait entrer au joueur un pseudo valide
         */

        /* TEST qui n'ont pas fonctionner*/
        GridBagConstraints aligne = new GridBagConstraints();
        aligne.gridx =0;
        aligne.gridy=GridBagConstraints.RELATIVE;
        aligne.fill=GridBagConstraints.HORIZONTAL;
        aligne.insets = new Insets(30,30,30,30);
        ZonePseudo.setEditable(true);
        ZonePseudo.setRows(1);
        ZonePseudo.setColumns(20);
        texte.setText("Votre pseudo :");
        texte.setForeground(Color.black);
        texte.setFont(new Font("Arial",Font.BOLD,16));
        texte.setVerticalAlignment(JLabel.CENTER);
        texte.setHorizontalAlignment(JLabel.CENTER);
        bouton.setText("Valider");
        bouton.addMouseListener(mouse);
        cp.setBackground(Color.yellow);
        cp.setLayout(new GridBagLayout());
        fenetre.setSize(500,500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setTitle("Sudoku");
        fenetre.setContentPane(cp);
        cp.add(texte,aligne);
        cp.add(ZonePseudo,aligne);
        cp.add(bouton,aligne);
        texte.setVisible(true);
        ZonePseudo.setVisible(true);
        bouton.setVisible(true);
        fenetre.setVisible(true);


        /*
         * On creer une grille pleine, que l'on copie dans grille valide afin de nous
         * servir de solution cela eviter des calculs inutiles On rend egalement la
         * grille valide non modifiable par precaution
         */

        grille.GenererGrille();
        grilleNonModifiable();
        gestionDifficulte(difficulte);

        /*
        Creation de la grille dans l'application
        */

        cd.setLayout(new GridLayout(taille_grille,taille_grille));

        for(int i =0;i<taille_grille;i++)
        {
            for(int j = 0; j<taille_grille;j++)
            {
                grilleT[i][j]= new JTextField();
                cd.add(grilleT[i][j]);
            }
        }

        /*
        Initialiser la grille de l'application
        */


        for(int i =0;i<taille_grille;i++)
        {
            for(int j =0;j<taille_grille;j++)
            {
                if(grille.getT()[i][j].isModifiable())
                {
                    grilleT[i][j].setText("");
                    grilleT[i][j].setEditable(true);
                    grilleT[i][j].setForeground(Color.black);
                    grilleT[i][j].addActionListener(listener);
                }
                else
                    {
                        grilleT[i][j].setText(grille.getT()[i][j].getVal());
                        grilleT[i][j].setEditable(false);
                        grilleT[i][j].setForeground(Color.BLUE);
                        grilleT[i][j].setBackground(Color.white);
                    }

                grilleT[i][j].setHorizontalAlignment(JTextField.CENTER);
                if( ((j>= 3 && j<=5) && (( i>=0 && i<=2) ||  (i>= 6 && i<=8))) ||  ((i>=3 && i<=5 ) && (( j>=0 && j<=2)  ||  (j>= 6 && j<=8))) )
                {
                    grilleT[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }

        /*
        Faire apparaitre les lignes
         */
        for(int i =0;i<taille_grille;i++)
        {
            for(int j =0;j<taille_grille;j++)
            {
                if((i==0)||(i==3)||(i==6))
                {
                    if((j==0)||(j==1)||(j==4)||(j==7))
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,1,0,0,Color.black));
                    if((j==3)||(j==6))
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.black));
                    if(j==8)
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.black));
                    if((j==2)||(j==5)||(j==8))
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,1,0,2,Color.black));
                }
                if((i==1)||(i==4)||(i==7))
                {
                    if((j==0)||(j==1)||(j==4)||(j==7))
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,1,0,0,Color.black));
                    if((j==3)||(j==6))
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.black));
                    if(j==8)
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.black));
                    if((j==2)||(j==5)||(j==8))
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,1,0,2,Color.black));
                }
                if((i==2)||(i==5)||(i==8))
                {
                    if((j==0)||(j==1)||(j==4)||(j==7))
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,1,1,0,Color.black));
                    if((j==3)||(j==6))
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.black));
                    if(j==8)
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
                    if((j==2)||(j==5)||(j==8))
                        grilleT[i][j].setBorder(BorderFactory.createMatteBorder(1,1,1,2,Color.black));
                }
            }
        }

        cd.setPreferredSize(new Dimension(hauteur_max,largeur_max)); //plein ecran ?
        while(!aCliquer)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(ZonePseudo.getText().length()>0 && ZonePseudo.getText()!=null) {
            pseudo = ZonePseudo.getText();
        }
        else{ pseudo="Anonyme";}
        Joueur joueur = new Joueur(pseudo);
        cd.setVisible(true);
        fenetre.setContentPane(cd);
        fenetre.repaint();
        fenetre.revalidate();

        //// Il faudra ensuite s'occuper de la lecture
        /*
         * On lance la partie
         */
        System.out.println("Bienvenue " + joueur.getPseudo() + " !         Voici ta grille !\n");
        grille.AfficherGrille();

        System.out.println("Vous pouvez faire jusqu'a " + erreurMax + " erreurs.");

        int i = 0, j = 0;
        String val = " ";

        boolean identique = false;

        while (continuer) {
            while ((erreur < erreurMax) && (!identique)) {
                /*
                 * On recupere les information puis on verifie que tout est valide
                 */
                System.out.println("Vous avez actuellement " + erreur + " erreurs.");
                boolean saisieCorrect = false;

                while (!saisieCorrect) {
                    try {
                        sc = new Scanner(System.in);
                        System.out.println("Entrer la ligne :");
                        i = sc.nextInt();
                        System.out.println("Entrer la colonne :");
                        j = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Entrer la valeur :");
                        val = sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Merci d'entrer une reponse valide");
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                        System.err.println("N'appuyer pas sur Entrer pauvre fou !!!");
                    }

                    if (i >= 0 && i < 9 && j >= 0 && j < 9
                            && ((val.equals("1")) || (val.equals("2")) || (val.equals("3")) || (val.equals("4"))
                            || (val.equals("5")) || (val.equals("6")) || (val.equals("7")) || (val.equals("8"))
                            || (val.equals("9")))) {
                        saisieCorrect = true;
                    } else
                        System.out.println("Erreur lors de la saisie d'une des 3 entrees");

                }

                /*
                 * Si la case a la position indiquee est modifiable, on verifie que la valeur
                 * choisie est la meme que celle de la grille valide pour ensuite faire une
                 * verification rapide, pour confirmer ou non que la grille est remplie sinon on
                 * demande a nouveau la valeur
                 */
                if ((grille.getT()[i][j].isModifiable())) {
                    if ((grille.verifValeurPourConstruction(val,i,j))) {
                        grille.getT()[i][j].setVal(val);
                        System.out.println("Correct !!!!");
                        grille.AfficherGrille();

                        boolean compare = true;
                        if (!(grille.verifierGrille()==1))
                            compare = false;


                        /*
                         * Si tout est replie, on calcul le score, et on demande a l'utilisateur s'il
                         * veux continuer a jouer dans le meme mode de jeu
                         */
                        if (compare) {
                            identique = true;
                            System.out.println("Wow ! Tu as fini ! Felicitations !");
                            score += calculScore(difficulte);
                            joueur.setScore(score);
                            System.out.println(
                                    "Voulez vous rejouer pour augmenter votre score ?\nEntrer 1 pour oui et 0 pour non");
                            int reponse = sc.nextInt();
                            if (reponse == 1)
                                continuer = true;
                            else
                                continuer = false;
                            EnregistrerListe(nomFichier);

                        }
                    } else {
                        System.out.println("Valeur incorrecte ! Reessaye");
                        erreur++;
                        if (erreur == erreurMax)
                            System.out.println(
                                    "Malheureusement tu as fait trop d'erreurs. Tu feras mieux la prochaine fois !");
                    }

                } else
                    System.out.println("Erreur : la case selectionnee n'est pas remplissable");

            }

        }
        sc.close();

    }

    // classe interne qui va rendre interactif les entrées dans la grille de l'interface
    private class InputListener implements ActionListener {

        int LigneChoisie = -1;
        int ColonneChoisie = -1;



        @Override
        public void actionPerformed(ActionEvent e) {

            JTextField source = (JTextField) e.getSource();
            boolean trouve =false;
            for(int i=0;i<9 && !trouve; ++i){
                for(int j=0;j<9 && !trouve; ++j){
                    if(grilleT[i][j]==source){
                        LigneChoisie=i;
                        ColonneChoisie=j;
                        trouve=true;
                    }
                }
            }
// Recupère la valeur mise dans la case, et place le premier char dans la "vraie" grille sudoku
            String valeurInput = grilleT[LigneChoisie][ColonneChoisie].getText();
            char tmp = valeurInput.charAt(0);
            String premierChar ="";
            premierChar = premierChar + tmp;
            if(ZonePseudo.getText().length()>0 && ZonePseudo.getText()!=null) {
                pseudo = ZonePseudo.getText();
            }
            else{ pseudo="Anonyme";}
            if(!grille.verifValeurPourConstruction(premierChar,LigneChoisie,ColonneChoisie)){
                CptErreur++;
                grilleT[LigneChoisie][ColonneChoisie].setForeground(Color.red);
                JOptionPane.showMessageDialog(null, "Vous avez fais une erreur, vous êtes à " +CptErreur+ " erreurs");
            }
            if(CptErreur==30){
                JOptionPane.showMessageDialog(null, "Tu as perdu ! Tu as fais 30 erreurs");
                fenetre.setVisible(false);
            }
            grille.getT()[LigneChoisie][ColonneChoisie].setVal(premierChar);
            grilleT[LigneChoisie][ColonneChoisie].setText(premierChar);
            grilleT[LigneChoisie][ColonneChoisie].setForeground(Color.BLACK);
            if(grille.verifierGrille()==1){
                //Faire des trucs si grille bonne
                JOptionPane.showMessageDialog(null, "Tu as gagné !");
                fenetre.setVisible(false);
            }


        }


    }


    /*
     * Calcule le score d'un joueur en fonction des parametres predefini La maniere
     * de calculer un score peut etre modifier
     */
    public int calculScore(int difficulte) {
        return (50 - CptErreur / difficulte);
    }

    /*
     * Retourne un boolean qui definit si la valeur entree est valide
     */
    public boolean valeurValide(String val, int i, int j) {
        if (grille.getT()[i][j].getVal().equals(val))
            return true;
        else
            return false;
    }

    /*
     * Rend une grille non modifiable
     */
    public void grilleNonModifiable() {
        for (int i = 0; i < grille.getImax(); i++) {
            for (int j = 0; j < grille.getJmax(); j++) {
                grille.getT()[i][j].setModifiable(false);
            }
        }
    }

    /*
     * Faire des trous dans le grille en fonction de la difficulte selectionné
     */
    public void gestionDifficulte(int difficulte) {
        int max = 0;
        if (difficulte == 1)
            max = 3;
        else if (difficulte == 2)
            max = 5;
        else if (difficulte == 3)
            max = 6;
        else
            System.err.println("Erreur lors de la selection de la difficulté");

        for (int a = 0; a < 9; a += 3)
            for (int b = 0; b < 9; b += 3) {
                PlacerTrou3x3(a, b, max);
            }
    }

    public void PlacerTrou3x3(int a, int b, int max) {
        int compteur = 0;
        // prend la case en haut a gauche de chaque block 3x3
        int A = a - (a % 3);
        int B = b - (b % 3);

        while (compteur < max) {
            for (int y = A; y < A + 3; y++) {
                for (int x = B; x < B + 3; x++) {
                    if (((int) (1 + Math.random() * (3)) == 2) && (!grille.getT()[y][x].getVal().contentEquals(" "))) {
                        compteur++;
                        grille.getT()[y][x].setVal(" ");
                        grille.getT()[y][x].setModifiable(true);
                    }
                }
            }
        }
    }

    /*
     * Ajoute un Joueur a la liste
     */
    public void addJoueur(Joueur j) {
        if (listeJoueur == null)
            this.listeJoueur = new ArrayList<Joueur>();
        listeJoueur.add(j);
    }

    /*
     * Retire le joueur entree de la liste
     */
    public void delJoueur(Joueur j) {
        for (Joueur x : listeJoueur) {
            if (j.equals(x)) {
                listeJoueur.remove(x);
            }
        }
    }

    /*
     * Transforme une liste en String
     */
    public String listeToString() {
        String liste = null;
        for (Joueur jx : this.listeJoueur) {
            liste += "" + jx.getPseudo() + "," + jx.getScore() + "\n";
        }
        return liste;
    }

    /*
     * Transforms un String en Joueur qui est ensuite ajouter a la liste
     */
    public void toStringListe(String joueur) {
        String[] info = joueur.split(",");
        Joueur j = new Joueur();
        j.setPseudo(info[0]);
        j.setScore(Integer.parseInt(info[1]));
        addJoueur(j);
    }

    /*
     * Getter + Setter
     */
    public ArrayList<Joueur> getListeJoueur() {
        return listeJoueur;
    }

    public void setListeJoueur(ArrayList<Joueur> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    /*
     * Enregistre la liste dans un fichier
     */
    public void EnregistrerListe(String nomFichier) {
        FileOutputStream ecrire = null;
        File f;
        String liste = null;
        liste = listeToString();

        try {
            f = new File(nomFichier);
            ecrire = new FileOutputStream(f);

            // si le fichier n'existe pas
            if (!f.exists()) {
                f.createNewFile();
            }

            // convertion en binaires
            byte[] listeBinaire = liste.getBytes();

            ecrire.write(listeBinaire);
            ecrire.flush();
            ecrire.close();

            System.out.println("...Données enregistree...");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ecrire != null)
                    ecrire.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    /*
     * Charge la liste a partir du fichier
     */
    public void ChargerListe(String nomFichier) throws IOException {
        FileReader f = null;
        BufferedReader br = null;
        try {
            f = new FileReader(nomFichier);
            br = new BufferedReader(f);
            if (!f.ready()) {
                File fs = new File(nomFichier);
                fs.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String ligne;

        while ((ligne = br.readLine()) != null) {
            toStringListe(ligne);
        }
        br.close();

    }
}
