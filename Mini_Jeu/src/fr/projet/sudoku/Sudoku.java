package fr.projet.sudoku;

import fr.projet.*;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Sudoku {

    public static final int taille_grille =9;
    public static final int taille_sous_grille =3;
    public static final int taille_cellule =60;//px
    public static final int hauteur_max = taille_cellule * taille_grille;
    public static final int largeur_max = taille_cellule * taille_grille;
    private JTextField[][] grilleT = new JTextField[taille_grille][taille_grille];
    private JTextField ZonePseudo = new JTextField();
    private GrilleSudoku grille = new GrilleSudoku();
    private JLabel texte = new JLabel();
    private JLabel txt = new JLabel();
    private JLabel txt_score = new JLabel();
    private JButton bouton1 = new JButton();
    private JButton bouton2 = new JButton();
    private JButton bouton3 = new JButton();
    private JButton quitter = new JButton();
    private Joueur joueur = new Joueur();
    private String pseudo = "defaut";
    private JFrame fenetre = new JFrame();
    private JPanel cp = new JPanel() {
        public void paintComponent(Graphics g)
        { super.paintComponent(g);
            g.drawImage(new ImageIcon("src/Sudoku/Fond_Sudoku.jpg").getImage(), 0, 0, getWidth(), getHeight(), null); }
    };
    private JPanel cd = new JPanel();
    private JPanel votreScore =  new JPanel() {

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(new ImageIcon("src/Sudoku/Fond_VotreScore.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    };
    private int CptErreur=0;
    private boolean aCliquer = false;
    private boolean fini = false;
    private boolean retour = false;
    private boolean sudo = false;
    private boolean mp = false;
    private boolean quit = false;
    private int erreurMax = 1;
    private int difficulte; // 1=facile, 2=moyen, 3=difficile


    public Sudoku() {
        super();
    }


    /* Jouer va lancer une partie */
    public void Jouer()
    {
        InputListener listener = new InputListener();
        MouseListener mouse1 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {  aCliquer=true; difficulte = 1; }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { aCliquer=true; difficulte = 1; }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        };
        MouseListener mouse2 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { aCliquer=true; difficulte = 2; }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { aCliquer=true; difficulte = 2; }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        };
        MouseListener mouse3 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { aCliquer=true; difficulte = 3; }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { aCliquer=true; difficulte = 3; }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        };
        MouseListener mouse4 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { retour=true;fenetre.setVisible(false); }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { retour=true;fenetre.setVisible(false); }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        };



        /* Affichage du Menu Sudoku */
        fenetre.setSize(900,900);
        fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setTitle("Sudoku");



        GridBagConstraints aligne = new GridBagConstraints();
        aligne.ipady =  GridBagConstraints.CENTER;
        aligne.ipadx = GridBagConstraints.RELATIVE;
        aligne.gridx=0;
        aligne.gridy=GridBagConstraints.RELATIVE;
        aligne.fill=GridBagConstraints.HORIZONTAL;
        int espacement;
        if(fenetre.getHeight()>800  && fenetre.getWidth()>800) espacement = 80;
        else espacement = 40;
        aligne.insets = new Insets(espacement,espacement,espacement,espacement);
        ZonePseudo.setEditable(true);
        texte.setText("Votre pseudo :");
        texte.setForeground(Color.black);
        texte.setFont(new Font("Arial",Font.BOLD,18));
        texte.setVerticalAlignment(JLabel.CENTER);
        texte.setHorizontalAlignment(JLabel.CENTER);
        texte.setBackground(new Color(236,70,56,250));
        texte.setOpaque(true);
        bouton1.setText("  FACILE  ");
        bouton1.addMouseListener(mouse1);
        bouton2.setText("  MOYEN  ");
        bouton2.addMouseListener(mouse2);
        bouton3.setText("DIFFICILE");
        bouton3.addMouseListener(mouse3);
        quitter.setText("Quitter");
        quitter.setBackground(Color.LIGHT_GRAY);
        quitter.addMouseListener(mouse4);
        JLabel espace1 = new JLabel();
        espace1.setText("     ");
        JLabel espace2 = new JLabel();
        espace2.setText("     ");
        Box b = Box.createHorizontalBox();
        b.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK),
                "Difficulte",1,1,new Font("Arial",Font.BOLD,18),new Color(0,0,0)));
        b.add(bouton1);
        b.add(espace1);
        b.add(bouton2);
        b.add(espace2);
        b.add(bouton3);

        fenetre.add(cp);

        cp.setLayout(new GridBagLayout());
        aligne.gridwidth=fenetre.getWidth();
        cp.add(texte,aligne);
        aligne.gridwidth=fenetre.getWidth();
        cp.add(ZonePseudo,aligne);
        aligne.gridwidth=fenetre.getWidth();
        cp.add(b,aligne);
        cp.add(quitter,aligne);
        texte.setVisible(true);
        ZonePseudo.setVisible(true);
        bouton1.setVisible(true);
        bouton2.setVisible(true);
        bouton3.setVisible(true);
        fenetre.setVisible(true);
        //lancerMusique("src/Sudoku/Musique/Jasmin.wav");
        cd.setPreferredSize(new Dimension(hauteur_max,largeur_max));
        fenetre.repaint();
        while(!aCliquer)
        {
            if(retour) {MenuPrincipal e =new MenuPrincipal();e.affichageMenuPrincipal();}
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        grille.GenererGrille();
        grilleNonModifiable();
        gestionDifficulte(difficulte);

        /*
        Creation de la grille dans l'application
        */

            // Gestion de la redimension pour le plein ecran
        Dimension sizeWin = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        cd.setBackground(Color.DARK_GRAY);
        cd.setBorder(BorderFactory.createEmptyBorder(0,(int)((sizeWin.getWidth()-sizeWin.getHeight())/2),0,(int)((sizeWin.getWidth()-sizeWin.getHeight())/2)));
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
                if(j >= 3 && j <= 5 && (i <= 2 || i >= 6) || i >= 3 && i <= 5 && (j <= 2 || j >= 6))
                {
                    grilleT[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }

        /*
        Faire apparaitre les lignes du sudoku
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

        if(ZonePseudo.getText().length()>0 && ZonePseudo.getText()!=null) {
            pseudo = ZonePseudo.getText();
        }
        else{ pseudo="Anonyme";}
        joueur.setPseudo(pseudo);
        cd.setVisible(true);
        fenetre.setContentPane(cd);
        fenetre.repaint();
        fenetre.revalidate();

        /*
         * On lance la partie
         */
        while(!fini)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int score = 0;
        if(CptErreur<erreurMax) score += calculScore(difficulte);
        joueur.setScore(score);
        Classement C = new Classement();
        File f =new File(C.FichierSauvegarde);
        if(!f.exists()) {
            try { f.createNewFile(); } catch (IOException e) { e.printStackTrace(); } }
        C.ChargerClassement(C,C.FichierSauvegarde);
        C.DonnerPointAUnJoueur(joueur,score,1);
        C.SauvegardeClassement(C.FichierSauvegarde);

        afficherVotreScore();


    }

    // classe interne qui va rendre interactif les entrées dans la grille de l'interface
    private class InputListener implements ActionListener {

        int LigneChoisie = -1;
        int ColonneChoisie = -1;

        @Override
        public void actionPerformed(ActionEvent e) {

            JTextField source = (JTextField) e.getSource();

            boolean trouve = false;
            for (int i = 0; i < 9 && !trouve; ++i) {
                for (int j = 0; j < 9 && !trouve; ++j) {
                    if (grilleT[i][j] == source) {
                        LigneChoisie = i;
                        ColonneChoisie = j;
                        trouve = true;
                    }
                }
            }
            if (source.getText().length() > 0) {
// Recupère la valeur mise dans la case, et place le premier char dans la "vraie" grille sudoku
                String valeurInput = grilleT[LigneChoisie][ColonneChoisie].getText();
                char tmp = valeurInput.charAt(0);
                String premierChar = "";
                premierChar = premierChar + tmp;
                if (ZonePseudo.getText().length() > 0 && ZonePseudo.getText() != null) {
                    pseudo = ZonePseudo.getText();
                } else {
                    pseudo = "Anonyme";
                }

                grille.getT()[LigneChoisie][ColonneChoisie].setVal("");
                grilleT[LigneChoisie][ColonneChoisie].setText("");
                if ((!grille.verifValeurPourConstruction(premierChar, LigneChoisie, ColonneChoisie)) || (tmp<'1' || tmp >'9')) {
                    CptErreur++;

                    grilleT[LigneChoisie][ColonneChoisie].setText(premierChar);
                    grilleT[LigneChoisie][ColonneChoisie].setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, "Vous avez fais une erreur, vous êtes à " + CptErreur + " erreurs");
                    grilleT[LigneChoisie][ColonneChoisie].setText("");

                    if (CptErreur == erreurMax) {
                        JOptionPane.showMessageDialog(null, "Tu as perdu ! Tu as fais "+erreurMax+" erreurs");
                        fini=true;
                    }
                } else {
                    System.out.println(premierChar.length());

                    grille.getT()[LigneChoisie][ColonneChoisie].setVal(premierChar);
                    grilleT[LigneChoisie][ColonneChoisie].setText(premierChar);

                    if (grille.verifierGrille() == 1) {
                        JOptionPane.showMessageDialog(null, "Tu as gagné !");
                        fini=true;
                    }


                }
                grilleT[LigneChoisie][ColonneChoisie].setForeground(Color.BLACK);
            }
            else {
                grille.getT()[LigneChoisie][ColonneChoisie].setVal("");
            }
        }

    }

    public int calculScore(int difficulte) {
        return (50 - (CptErreur / difficulte));
    }


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
        int max;
        if (difficulte == 1)
            max = 3;
        else if (difficulte == 2)
            max = 5;
        else if (difficulte == 3)
            max = 6;
        else
            max = 0 ;

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


    public boolean isFini() {return fini;}
    public boolean isRetour() {return retour;}


        public void afficherVotreScore()
        {

            fenetre.setVisible(false);
            fenetre.remove(cd);
            fenetre.setContentPane(votreScore);

            MouseListener m1 = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {fenetre.dispose();fenetre.setVisible(false); sudo = true;}
                @Override
                public void mousePressed(MouseEvent e) { }
                @Override
                public void mouseReleased(MouseEvent e) { }
                @Override
                public void mouseEntered(MouseEvent e) { }
                @Override
                public void mouseExited(MouseEvent e) { }
            };
            MouseListener m2 = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {fenetre.dispose();fenetre.setVisible(false); mp = true;}
                @Override
                public void mousePressed(MouseEvent e) { }
                @Override
                public void mouseReleased(MouseEvent e) { }
                @Override
                public void mouseEntered(MouseEvent e) { }
                @Override
                public void mouseExited(MouseEvent e) { }
            };
            MouseListener m3 = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {fenetre.dispose();fenetre.setVisible(false); quit = true;}
                @Override
                public void mousePressed(MouseEvent e) { }
                @Override
                public void mouseReleased(MouseEvent e) { }
                @Override
                public void mouseEntered(MouseEvent e) { }
                @Override
                public void mouseExited(MouseEvent e) { }
            };


            GridBagConstraints gbc = new GridBagConstraints();
            votreScore.setLayout(new GridBagLayout());

            JButton espace1 = new JButton();
            espace1.setFocusPainted(false);
            espace1.setContentAreaFilled(false);
            espace1.setVisible(true);
            espace1.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
            JButton espace2 = new JButton();
            espace2.setFocusPainted(false);
            espace2.setContentAreaFilled(false);
            espace2.setVisible(true);
            espace2.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));


            JButton rejouer = new JButton();
            rejouer.setText("Rejouer");
            rejouer.setVisible(true);
            rejouer.addMouseListener(m1);
            JButton mP = new JButton();
            mP.setText("Menu Principal");
            mP.setVisible(true);
            mP.addMouseListener(m2);
            JButton quitter = new JButton();
            quitter.setText("Quitter");
            quitter.setVisible(true);
            quitter.addMouseListener(m3);

            txt.setText("Votre Score :");
            txt.setHorizontalAlignment(JLabel.CENTER);
            txt.setVerticalAlignment(JLabel.CENTER);
            txt.setForeground(Color.white);
            txt.setFont(new Font("Arial",Font.BOLD,30));
            txt.setPreferredSize(new Dimension(200,50));
            txt_score.setText(Integer.toString(joueur.getScore()));
            txt_score.setForeground(Color.black);
            txt_score.setVerticalAlignment(JLabel.CENTER);
            txt_score.setHorizontalAlignment(JLabel.CENTER);
            txt_score.setFont(new Font("Arial",Font.BOLD,50));

            gbc.ipady =  GridBagConstraints.CENTER;
            gbc.gridx=0;
            gbc.gridy=GridBagConstraints.RELATIVE;
            gbc.fill=GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(40,40,40,40);

            Box b = Box.createHorizontalBox();
            b.add(rejouer);
            b.add(espace1);
            b.add(mP);
            b.add(espace2);
            b.add(quitter);
            votreScore.add(txt,gbc);
            votreScore.add(txt_score,gbc);
            votreScore.add(b,gbc);
            txt.setVisible(true);
            txt_score.setVisible(true);
            votreScore.setVisible(true);
            fenetre.repaint();
            fenetre.setVisible(true);


            while(!sudo && !mp && !quit ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(sudo){ Sudoku s = new Sudoku(); s.Jouer(); }
            if(mp) { MenuPrincipal m = new MenuPrincipal(); m.affichageMenuPrincipal();}
            if(quit) {System.exit(0);}
        }

        public void lancerMusique(String musique) {
            File fichier = new File(musique);
            SourceDataLine line = null;
            AudioInputStream audioInputStream = null;
            try {
                AudioFileFormat format = AudioSystem.getAudioFileFormat(fichier);
            } catch (UnsupportedAudioFileException ignored) { } catch (IOException e1) { }

            try {
                audioInputStream = AudioSystem.getAudioInputStream(fichier);
            } catch (UnsupportedAudioFileException e) { } catch (IOException e) {}

            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

            try {
                line = (SourceDataLine) AudioSystem.getLine(info);
            } catch (LineUnavailableException e) {
                return;
            }

            try {
                line.open(audioFormat);
            } catch (LineUnavailableException e) {
                return;
            }
            line.start();
            try {
                byte bytes[] = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
                    line.write(bytes, 0, bytesRead);
                }
            } catch (IOException io) {
                io.printStackTrace();
                return;
            }
        }

}


