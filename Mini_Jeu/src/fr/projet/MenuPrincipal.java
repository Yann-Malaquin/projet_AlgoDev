package fr.projet;

import fr.projet.bataillenavale.Partie;
import fr.projet.loto.Depart_Loto;
import fr.projet.poker.Interface.PartiePoker;
import fr.projet.sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class MenuPrincipal implements ActionListener {

    private JFrame fenetre1 = new JFrame();
    /*
            Boutons du Classement
     */
    private JButton quitter = new JButton("Quitter");
    private JButton pseudo = new JButton("Pseudo");
    private JButton sudo = new JButton("Sudoku");
    private JButton lot = new JButton("Loto");
    private JButton poke = new JButton("Poker");
    private JButton bat = new JButton("Bataille Navale");
    private JButton sco = new JButton("Score");
    /*
            Boutons du Menu Principal
     */
    private JButton bJeux = new JButton();
    private JButton bClassement = new JButton();
    private JButton bCredits = new JButton();
    private JButton bQuitter = new JButton();
    /*
            Boutons du Menu Jeux
     */
    private JButton Sudo = new JButton();
    private JButton Loto = new JButton();
    private JButton Poker = new JButton();
    private JButton BN = new JButton();
    /*
            Boutons des Règles
     */
    private JButton suivantS = new JButton("Suivant");
    private JButton suivantL = new JButton("Suivant");
    private JButton suivantP = new JButton("Suivant");
    private JButton suivantB = new JButton("Suivant");
    /*
            Panel
     */
    private JPanel panel = new JPanel(){
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            URL imageURL = this.getClass().getResource("/resources/menuprincipal/Fond_Menu.jpg");
            g.drawImage(new ImageIcon(imageURL).getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    };
    private JPanel pan = new JPanel(){
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            URL imageURL = this.getClass().getResource("/resources/menuprincipal/Fond_Jeux.jpg");
            g.drawImage(new ImageIcon(imageURL).getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    };
    /*
        Divers
     */
    private JButton retour = new JButton();
    private JButton menuP = new JButton("retour");
    Classement c = new Classement();

    public MenuPrincipal(){}

    /*
        Affichage du Menu principal
     */
    public void affichageMenuPrincipal() {

        MouseListener mouse1 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { bJeux.setBackground(new Color(134,186,216)); }
            @Override
            public void mouseExited(MouseEvent e) { bJeux.setBackground(Color.white);}
        };
        MouseListener mouse2 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { bClassement.setBackground(new Color(134,186,216)); }
            @Override
            public void mouseExited(MouseEvent e) { bClassement.setBackground(Color.white);}
        };
        MouseListener mouse3 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { bCredits.setBackground(new Color(134,186,216));}
            @Override
            public void mouseExited(MouseEvent e) { bCredits.setBackground(Color.white);}
        };
        MouseListener mouse4 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { bQuitter.setBackground(new Color(134,186,216));}
            @Override
            public void mouseExited(MouseEvent e) { bQuitter.setBackground(Color.white);}
        };

        fenetre1.setSize(900,900);
        fenetre1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fenetre1.setLocationRelativeTo(null);
        fenetre1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre1.setTitle("Mini Jeux");

        panel.setLayout(new GridBagLayout());

        GridBagConstraints aligne = new GridBagConstraints();
        aligne.ipady =  GridBagConstraints.CENTER;
        aligne.ipadx = GridBagConstraints.RELATIVE;
        aligne.gridx=0;
        aligne.gridy=GridBagConstraints.RELATIVE;
        aligne.fill=GridBagConstraints.HORIZONTAL;
        aligne.insets = new Insets(40,40,40,40);
        bJeux.setBackground(Color.white);
        bJeux.setFont(new Font("Arial",Font.BOLD,20));
        bJeux.setForeground(Color.black);
        bJeux.setPreferredSize(new Dimension(200,30));
        bJeux.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        bJeux.addActionListener(this);
        bJeux.addMouseListener(mouse1);
        bClassement.setBackground(Color.white);
        bClassement.setPreferredSize(new Dimension(200,30));
        bClassement.setFont(new Font("Arial",Font.BOLD,20));
        bClassement.setForeground(Color.black);
        bClassement.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        bClassement.addActionListener(this);
        bClassement.addMouseListener(mouse2);
        bCredits.setBackground(Color.white);
        bCredits.setFont(new Font("Arial",Font.BOLD,20));
        bCredits.setForeground(Color.black);
        bCredits.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        bCredits.addActionListener(this);
        bCredits.addMouseListener(mouse3);
        bQuitter.setBackground(Color.white);
        bQuitter.setFont(new Font("Arial",Font.BOLD,20));
        bQuitter.setForeground(Color.black);
        bQuitter.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        bQuitter.addActionListener(this);
        bQuitter.addMouseListener(mouse4);
        panel.add(bJeux,aligne);
        panel.add(bClassement,aligne);
        panel.add(bCredits,aligne);
        panel.add(bQuitter,aligne);
        fenetre1.setContentPane(panel);

        bJeux.setText("   Jeux  ");
        bClassement.setText("  Classement  ");
        bCredits.setText("  Credit  ");
        bQuitter.setText("  Quitter  ");


        bJeux.setVisible(true);
        bClassement.setVisible(true);
        bCredits.setVisible(true);
        bQuitter.setVisible(true);
        panel.setVisible(true);
        fenetre1.setVisible(true);
        fenetre1.repaint();

    }
    /*
        Affichage du Menu de choix du Jeu
     */
    private void afficherMenuJeux() {

        fenetre1.setContentPane(pan);
        pan.setLayout(new GridLayout(3,4,20,5));
        pan.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        fenetre1.setTitle("Jeux");

        Font font = new Font("Arial",Font.BOLD,20);

        JLabel TSudo = new JLabel();
        TSudo.setHorizontalAlignment(SwingConstants.CENTER);
        TSudo.setText("Sudoku");
        TSudo.setForeground(Color.black);
        TSudo.setFont(font);
        TSudo.setOpaque(false);
        pan.add(TSudo);

        JLabel TLoto = new JLabel();
        TLoto.setHorizontalAlignment(SwingConstants.CENTER);
        TLoto.setText("Loto");
        TLoto.setForeground(Color.black);
        TLoto.setFont(font);
        TLoto.setOpaque(false);
        pan.add(TLoto);

        JLabel TPoker = new JLabel();
        TPoker.setHorizontalAlignment(SwingConstants.CENTER);
        TPoker.setText("Poker");
        TPoker.setForeground(Color.black);
        TPoker.setFont(font);
        TPoker.setOpaque(false);
        pan.add(TPoker);

        JLabel TBN = new JLabel();
        TBN.setHorizontalAlignment(SwingConstants.CENTER);
        TBN.setText("Bataille  Navale");
        TBN.setForeground(Color.black);
        TBN.setFont(font);
        TBN.setOpaque(false);
        pan.add(TBN);


        URL imageURL1 = this.getClass().getResource("/resources/menuprincipal/Sudoku.jpg");
        Sudo.setIcon(new ImageIcon(imageURL1));
        Sudo.setText("Sudoku");
        Sudo.addActionListener(this);
        pan.add(Sudo);

        URL imageURL2 = this.getClass().getResource("/resources/menuprincipal/Loto.jpg");
        Loto.setIcon(new ImageIcon(imageURL2));
        Loto.setText("Loto");
        Loto.addActionListener(this);
        pan.add(Loto);

        URL imageURL3 = this.getClass().getResource("/resources/menuprincipal/Poker.jpg");
        Poker.setIcon(new ImageIcon(imageURL3));
        Poker.setText("Poker");
        Poker.addActionListener(this);
        pan.add(Poker);


        URL imageURL4 = this.getClass().getResource("/resources/menuprincipal/BN.jpg");
        BN.setIcon(new ImageIcon(imageURL4));
        BN.setText("BN");
        BN.addActionListener(this);
        pan.add(BN);


        retour.addActionListener(this);
        retour.setText("Retour");
        retour.setBackground(Color.white);
        retour.setBorder(BorderFactory.createEmptyBorder());

        pan.add(new JLabel());
        pan.add(new JLabel());
        pan.add(new JLabel());
        pan.add(retour);



        pan.setVisible(true);
        fenetre1.validate();
        fenetre1.repaint();

    }
    /*
        Affichage du classement
     */
    private void afficherClassement(int tri) { // tri peut valeur 0,..6
        JPanel classement = new JPanel();

        fenetre1.getContentPane().setLayout(null);

        JPanel global = new JPanel();
        global.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(global,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setWheelScrollingEnabled(true); // accepter le scroll de la souris
        fenetre1.setContentPane(scroll);

        global.add(classement,BorderLayout.CENTER);

        MouseListener m1 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {pseudo.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));}
            @Override
            public void mouseExited(MouseEvent e) {pseudo.setBorder(BorderFactory.createEtchedBorder());}
        };
        MouseListener m2 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {sudo.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));}
            @Override
            public void mouseExited(MouseEvent e) {sudo.setBorder(BorderFactory.createEtchedBorder());}
        };
        MouseListener m3 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {lot.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));}
            @Override
            public void mouseExited(MouseEvent e) {lot.setBorder(BorderFactory.createEtchedBorder());}
        };
        MouseListener m4 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {poke.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));}
            @Override
            public void mouseExited(MouseEvent e) {poke.setBorder(BorderFactory.createEtchedBorder());}
        };
        MouseListener m5 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {bat.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));}
            @Override
            public void mouseExited(MouseEvent e) {bat.setBorder(BorderFactory.createEtchedBorder());}
        };
        MouseListener m6 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {sco.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));}
            @Override
            public void mouseExited(MouseEvent e) {sco.setBorder(BorderFactory.createEtchedBorder());}
        };
        MouseListener mq = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {quitter.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));}
            @Override
            public void mouseExited(MouseEvent e) {quitter.setBorder(BorderFactory.createRaisedBevelBorder());}
        };


        quitter.setFont(new Font("Arial",Font.PLAIN,18));
        quitter.setBorder(BorderFactory.createRaisedBevelBorder());
        quitter.setBackground(new Color(0,128,128));
        quitter.setForeground(Color.white);
        quitter.addActionListener(this);
        quitter.addMouseListener(mq);


        global.add(quitter,BorderLayout.NORTH);

        c.ChargerClassement(c,c.FichierSauvegarde);
        if(tri==1) c.TrierSelonPseudo();
        if(tri==2 || tri==3 || tri==4|| tri==5 || tri==6) c.TrierListeSelonScore(tri-1);

        int nbligne = c.ClassementGlobal.size();
        Dimension d = new Dimension(fenetre1.getWidth()/6-20,50);
        classement.setLayout(new GridLayout(0,6,1,0));


        pseudo.setPreferredSize(d);
        pseudo.setFont(new Font("Arial",Font.BOLD,18));
        pseudo.setBackground(new Color(244,208,23));
        pseudo.setBorder(BorderFactory.createEtchedBorder());
        pseudo.addActionListener(this);
        pseudo.addMouseListener(m1);
        sudo.setPreferredSize(d);
        sudo.setFont(new Font("Arial",Font.BOLD,18));
        sudo.setBackground(new Color(244,208,23));
        sudo.setBorder(BorderFactory.createEtchedBorder());
        sudo.addActionListener(this);
        sudo.addMouseListener(m2);
        lot.setPreferredSize(d);
        lot.setFont(new Font("Arial",Font.BOLD,18));
        lot.setBackground(new Color(244,208,23));
        lot.setBorder(BorderFactory.createEtchedBorder());
        lot.addActionListener(this);
        lot.addMouseListener(m3);
        poke.setPreferredSize(d);
        poke.setFont(new Font("Arial",Font.BOLD,18));
        poke.setBackground(new Color(244,208,23));
        poke.setBorder(BorderFactory.createEtchedBorder());
        poke.addActionListener(this);
        poke.addMouseListener(m4);
        bat.setPreferredSize(d);
        bat.setFont(new Font("Arial",Font.BOLD,18));
        bat.setBackground(new Color(244,208,23));
        bat.setBorder(BorderFactory.createEtchedBorder());
        bat.addActionListener(this);
        bat.addMouseListener(m5);
        sco.setPreferredSize(d);
        sco.setFont(new Font("Arial",Font.BOLD,18));
        sco.setBackground(new Color(244,208,23));
        sco.setBorder(BorderFactory.createEtchedBorder());
        sco.addActionListener(this);
        sco.addMouseListener(m6);


        classement.add(pseudo);
        classement.add(sudo);
        classement.add(lot);
        classement.add(poke);
        classement.add(bat);
        classement.add(sco);



        for(int lign=0;lign<nbligne;lign++)
        {

            JLabel nom = new JLabel(c.ClassementGlobal.get(lign).getPseudo(),JLabel.CENTER);
            nom.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            nom.setPreferredSize(d);
            JLabel scoreS = new JLabel(String.valueOf(c.ClassementGlobal.get(lign).getScoreSudo()),JLabel.CENTER);
            scoreS.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            scoreS.setPreferredSize(d);
            JLabel scoreL = new JLabel(String.valueOf(c.ClassementGlobal.get(lign).getScoreLoto()),JLabel.CENTER);
            scoreL.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            scoreL.setPreferredSize(d);
            JLabel scoreP = new JLabel(String.valueOf(c.ClassementGlobal.get(lign).getScorePoker()),JLabel.CENTER);
            scoreP.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            scoreP.setPreferredSize(d);
            JLabel scoreB = new JLabel(String.valueOf(c.ClassementGlobal.get(lign).getScoreBN()),JLabel.CENTER);
            scoreB.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            scoreB.setPreferredSize(d);
            JLabel score = new JLabel(String.valueOf(c.ClassementGlobal.get(lign).getScore()),JLabel.CENTER);
            score.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            score.setPreferredSize(d);
            classement.add(nom);
            classement.add(scoreS);
            classement.add(scoreL);
            classement.add(scoreP);
            classement.add(scoreB);
            classement.add(score);
        }
        for(int i=0;i<6-nbligne;i++) // cas ou le classement contient trop peu de valeurs
        {
            JLabel vide1 = new JLabel();
            JLabel vide2 = new JLabel();
            JLabel vide3 = new JLabel();
            JLabel vide4 = new JLabel();
            JLabel vide5 = new JLabel();
            JLabel vide6 = new JLabel();
            classement.add(vide1);
            classement.add(vide2);
            classement.add(vide3);
            classement.add(vide4);
            classement.add(vide5);
            classement.add(vide6);
        }


        global.setVisible(true);
        fenetre1.setTitle("Classements");
        fenetre1.validate();
        fenetre1.repaint();

    }
    /*
        Affichage des crédits
     */
    private void afficherCredits() {
        JPanel cred = new JPanel();
        cred.setBackground(Color.black);
        JLabel texte = new JLabel("<HTML><center><U>Développé par :</U>" +
                "<BR><BR><BR>Malaquin Yann" +
                "<BR>Henry Mhilan" +
                "<BR>Mortreux Billy" +
                "<BR>Moreau Théo" +
                "<BR>Lesur Mathilde" +
                "<BR><BR><BR> Merci d'avoir choisi notre jeu !</center></HTML>",JLabel.CENTER);
        texte.setFont(new Font("Arial", Font.ITALIC,28));
        texte.setForeground(Color.white);
        cred.setLayout(new BorderLayout());
        menuP.addActionListener(this);
        menuP.setBackground(Color.black);
        menuP.setForeground(Color.white);
        cred.add(texte);
        cred.add(menuP,BorderLayout.SOUTH);


        fenetre1.setContentPane(cred);
        fenetre1.setTitle("Credits");
        cred.setVisible(true);
        texte.setVisible(true);
        fenetre1.validate();
        fenetre1.repaint();
    }
    /*
        Affiche les règles du Sudoku
     */
    private void afficherRSudoku() {
        JPanel RSudo = new JPanel();
        RSudo.setLayout(new BorderLayout());
        RSudo.setBackground(Color.black);

        JLabel RegleS = new JLabel("<html><center><u><font size='20'>Règle Sudoku</font></u><br><br><br><br>" +
                "Nous disposons d'une grille de neuf lignes et de neuf colonnes, soit 81 cases.<br>" +
                "Le but étant de remplir ces cases avec des chiffres allant de 1 à 9<br>" +
                "en veillant toujours à  ce qu'un même chiffre ne figure qu'une seule fois<br>" +
                "par ligne, par colonne, et pas carré de 9 cases.<br>" +
                "Au début du jeu, des chiffres sont préalablement placés, il faut alors compléter la grille.<br>" +
                "Le joueur gagne lorsqu'un arrive à remplir la grille en respectant les règles.</center></html>");
        RegleS.setForeground(Color.white);
        RegleS.setHorizontalAlignment(JLabel.CENTER);
        RegleS.setVerticalAlignment(JLabel.CENTER);
        suivantS.addActionListener(this);
        suivantS.setBackground(Color.black);
        suivantS.setForeground(Color.white);

        RSudo.add(RegleS);
        RSudo.add(suivantS,BorderLayout.SOUTH);

        RegleS.setVisible(true);
        suivantS.setVisible(true);
        RSudo.setVisible(true);
        fenetre1.setContentPane(RSudo);
        fenetre1.setVisible(true);
        fenetre1.repaint();
    }
    /*
        Affiche les règles du Loto
     */
    private void afficherRLoto() {
        JPanel RLoto = new JPanel();
        RLoto.setLayout(new BorderLayout());
        RLoto.setBackground(Color.black);

        JLabel RegleL = new JLabel("<html><center><u><font size='20'>Règle Loto</font></u><br><br><br><br>" +
                "On dispose de cartons de loto composés de 15 chiffres réparties sur 3 rangées de 9 cases.<br>" +
                "Ainsi que 90 pions numérotés de 1 à 90<br><br>" +
                "Au debut d'une partie, chaque joueur reçoit un certain nombre de cartons (ici, entre 1 et 3).<br>" +
                "Un jeton est alors tiré au hasard.<br>" +
                "Tous les joueurs regardent alors si le numéro tiré se trouve sur leurs carton.<br>" +
                "Si oui, le joueur place un pion sur le numéro de son carton.<br>" +
                "Le joueur gagne un lot à chaque ligne remplis.</center></html>");
        RegleL.setForeground(Color.white);
        RegleL.setHorizontalAlignment(JLabel.CENTER);
        RegleL.setVerticalAlignment(JLabel.CENTER);
        suivantL.addActionListener(this);
        suivantL.setBackground(Color.black);
        suivantL.setForeground(Color.white);

        RLoto.add(RegleL);
        RLoto.add(suivantL,BorderLayout.SOUTH);

        RegleL.setVisible(true);
        suivantL.setVisible(true);
        RLoto.setVisible(true);
        fenetre1.setContentPane(RLoto);
        fenetre1.setVisible(true);
        fenetre1.repaint();
    }
    /*
        Affiche les règles du Poker
     */
    private void afficherRPoker() {
        JPanel RPoker = new JPanel();
        RPoker.setLayout(new BorderLayout());
        RPoker.setBackground(Color.black);

        JLabel RegleP = new JLabel("<html><center><u><font size='20'>Règle Poker</font></u><br><br><br><br>" +
                "Pour jouer, nous avons un jeu de 52 cartes, ainsi que des mises.<br><br>" +
                "Avant de commencer la partie, on designe un donneur.<br>" +
                "A chaque tour, le donneur change dans le sens des aiguilles d'une montre.<br>" +
                "Les deux joueurs à gauche du donneur sont désignés 'petite blinde' et 'grosse blinde'.<br>" +
                "Ces jeux sont obligés de miser une somme définie au départ, par l'ensemble des joueurs.<br>" +
                "A chaque manche le donneur, les joueurs 'petite blinde' et 'grosse blinde' changent.<br>" +
                "Les sommes misées augmentent au cours du jeu.<br><br>"+
                "Au début d'une partie, 2 cartes sont distribuées à chaque joueur.<br>" +
                "Le joueur peut :<br>" +
                "- se coucher : Le joueur jete ses cartes et se retire de la partie<br>" +
                "- suivre : Le joueur la même somme que celle déposée par la grosse blinde<br>" +
                "- relancer : Le joueur mise une somme supérieur à celle de la grosse blinde<br>" +
                "3 cartes sont retournées au centre de la table : elles sont appelées le 'Flop'.<br>" +
                "On peut par la suite effectuer des actions supplémentaires:" +
                "- Check : Le joueur peut ne pas mise de somme supplément, si les joueurs précedent n'ont pas msé non plus.<br>" +
                "- Miser : Le joueur décide de mettre la somme qu'il souhaite.<br>" +
                "Au 2eme tour, on rajoute une carte : 4 cartes sont retournées au centre de la table : elles sont appelées le 'Tournant'.<br>" +
                "Au 3eme tour, on rajoute une carte : 5 cartes sont retournées au centre de la table : elles sont appelées la 'Rivière'.<br>" +
                "Puis viens un dernier tour, les joueurs peuvent alors relancer ou suivre.<br>" +
                "Puis les joueurs encore en course montrent leurs cartes.<br>" +
                "Le joueur ayant fait la combinaison la plus importante gagne la somme de toutes les mises.<br>" +
                "</center></html>");
        RegleP.setForeground(Color.white);
        RegleP.setHorizontalAlignment(JLabel.CENTER);
        RegleP.setVerticalAlignment(JLabel.CENTER);
        suivantP.addActionListener(this);
        suivantP.setBackground(Color.black);
        suivantP.setForeground(Color.white);

        RPoker.add(RegleP);
        RPoker.add(suivantP,BorderLayout.SOUTH);

        RegleP.setVisible(true);
        suivantP.setVisible(true);
        RPoker.setVisible(true);
        fenetre1.setContentPane(RPoker);
        fenetre1.setVisible(true);
        fenetre1.repaint();
    }
    /*
        Affiche les règles de la Bataille navale
     */
    private void afficherRBN() {
        JPanel RBN = new JPanel();
        RBN.setLayout(new BorderLayout());
        RBN.setBackground(Color.black);

        JLabel RegleB = new JLabel("<html><center><u><font size='20'>Règle Bataille Navale</font></u><br><br><br><br>" +
                "Sur une grille 10x10, on place les navires suivant: <br>" +
                "- porte avion (5 cases) <br>" +
                "- croiseur (3 cases)<br>" +
                "- contre torpilleur (3 cases)<br>" +
                "- sous-marin (3 cases)<br>" +
                "- torpilleur (2 cases)<br><br><br>" +
                "Au debut, chaque joueur place ses navires de façon stratégique.<br>" +
                "Le but étant de compliquer la tache de son adversaire.<br>" +
                "Quand la partie commence, les joueurs se tirent dessus 1 à 1.<br>" +
                "Si un joueur tire sur un navire ennemi, l'adversaire sera 'touché'.<br>" +
                "Il ne peux pas jouer deux fois de suite.<br>" +
                "Si un joueur ne touche pas de navire, le navire est 'raté'<br>" +
                "Si un navire est entierement touché, il est 'touché coulé'<br><br><br>" +
                "La partie se termine lorsqu'un joueur n'a plus de navires.<br><br><br><br></center></html>");

        RegleB.setForeground(Color.white);
        RegleB.setHorizontalAlignment(JLabel.CENTER);
        RegleB.setVerticalAlignment(JLabel.CENTER);
        suivantB.addActionListener(this);
        suivantB.setBackground(Color.black);
        suivantB.setForeground(Color.white);

        RBN.add(RegleB);
        RBN.add(suivantB,BorderLayout.SOUTH);


        RegleB.setVisible(true);
        suivantB.setVisible(true);
        RBN.setVisible(true);
        fenetre1.setContentPane(RBN);
        fenetre1.setVisible(true);
        fenetre1.repaint();
    }
    /*
        Procédure qui gère les actions en fonction des entrées graphiques
     */
    @Override
    public void actionPerformed(ActionEvent e)  {
        Object source = e.getSource();
        if(source==quitter) // retour au menu principal
        {
            fenetre1.dispose(); MenuPrincipal e1 = new MenuPrincipal(); e1.affichageMenuPrincipal();
        }
        /*
            Gestion affichage du Classement
         */
        if(source==pseudo)
        {
            fenetre1.remove(fenetre1.getContentPane());
            afficherClassement(1);
        }
        if(source==sudo)
        {
            fenetre1.remove(fenetre1.getContentPane());
            afficherClassement(2);
        }
        if(source==lot)
        {
            fenetre1.remove(fenetre1.getContentPane());
            afficherClassement(3);
        }
        if(source==poke)
        {
            fenetre1.remove(fenetre1.getContentPane());
            afficherClassement(4);
        }
        if(source==bat)
        {
            fenetre1.remove(fenetre1.getContentPane());
            afficherClassement(5);
        }
        if(source==sco)
        {
            fenetre1.remove(fenetre1.getContentPane());
            afficherClassement(6);
        }
        if(source==retour) // retour au menu principal
        {
            fenetre1.dispose();
            MenuPrincipal newMenu = new MenuPrincipal(); newMenu.affichageMenuPrincipal();
        }
        /*
            Gestion des bouttons du menu principal
         */
        if(source==bJeux)
        {
            fenetre1.remove(panel); fenetre1.repaint();fenetre1.validate(); afficherMenuJeux();
        }
        if(source==bClassement)
        {
            fenetre1.remove(panel); fenetre1.repaint();fenetre1.validate();afficherClassement(0);
        }
        if(source==bCredits)
        {
            fenetre1.remove(panel); fenetre1.repaint();fenetre1.validate(); afficherCredits();
        }
        if(source==bQuitter)
        {
            fenetre1.setVisible(false);System.exit(0);
        }
        /*
            Gestion de l'affichage des regles du jeu
         */
        if(source==Sudo)
        {
            fenetre1.setVisible(false);
            afficherRSudoku();
        }
        if(source==Loto)
        {
            fenetre1.setVisible(false);
            afficherRLoto();
        }
        if(source==Poker)
        {
            fenetre1.setVisible(false);
            afficherRPoker();
        }
        if(source==BN)
        {
            fenetre1.setVisible(false);
            afficherRBN();
        }
        /*
            lancement des jeux
         */
        if(source==suivantS)
        {
            fenetre1.dispose();
            Sudoku s = new Sudoku();
            s.Jouer();
        }
        if(source==suivantL)
        {
            fenetre1.dispose();
            Depart_Loto loto = new Depart_Loto();
            loto.main();
        }
        if(source==suivantP)
        {
            fenetre1.dispose();
            PartiePoker poker = new PartiePoker();
            poker.main();
        }
        if(source==suivantB)
        {
            fenetre1.dispose();
            Partie p = new Partie();
            p.main();
        }
        if(source==menuP) // retour menu principal
        {
            fenetre1.dispose();
            MenuPrincipal m = new MenuPrincipal();
            m.affichageMenuPrincipal();
        }
    }
}