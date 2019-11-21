package fr.projet;

import fr.projet.bataillenavale.Partie;
import fr.projet.poker.Interface.Main;
import fr.projet.sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MenuPrincipal {

    private JFrame fenetre1 = new JFrame();
    private boolean sudoku = false;
    private boolean loto = false;
    private boolean bn = false;
    private boolean poker = false;

    public MenuPrincipal(){}

    public void affichageMenuPrincipal() throws IOException {

        ImageIcon fond = new ImageIcon("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\Fond_Menu.jpg");
        JPanel panel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(fond.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };

        JButton bJeux = new JButton();
        JButton bClassement = new JButton();
        JButton bCredits = new JButton();
        JButton bQuitter = new JButton();

        MouseListener mouse1 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { fenetre1.remove(panel); fenetre1.repaint();fenetre1.validate(); afficherMenuJeux();}
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
            public void mouseClicked(MouseEvent e) { /*afficherClassement();*/}
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
            public void mouseClicked(MouseEvent e) { /*afficherCredits();*/}
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
            public void mouseClicked(MouseEvent e) { fenetre1.setVisible(false);System.exit(0);}
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
        bClassement.setBackground(Color.white);
        bClassement.setPreferredSize(new Dimension(200,30));
        bClassement.setFont(new Font("Arial",Font.BOLD,20));
        bClassement.setForeground(Color.black);
        bClassement.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        bCredits.setBackground(Color.white);
        bCredits.setFont(new Font("Arial",Font.BOLD,20));
        bCredits.setForeground(Color.black);
        bCredits.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        bQuitter.setBackground(Color.white);
        bQuitter.setFont(new Font("Arial",Font.BOLD,20));
        bQuitter.setForeground(Color.black);
        bQuitter.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        panel.add(bJeux,aligne);
        panel.add(bClassement,aligne);
        panel.add(bCredits,aligne);
        panel.add(bQuitter,aligne);
        fenetre1.setContentPane(panel);

        bJeux.setText("   Jeux  ");
        bClassement.setText("  Classement  ");
        bCredits.setText("  Credit  ");
        bQuitter.setText("  Quitter  ");

        bJeux.addMouseListener(mouse1);
        bClassement.addMouseListener(mouse2);
        bCredits.addMouseListener(mouse3);
        bQuitter.addMouseListener(mouse4);


        bJeux.setVisible(true);
        bClassement.setVisible(true);
        bCredits.setVisible(true);
        bQuitter.setVisible(true);
        panel.setVisible(true);
        fenetre1.setVisible(true);
        fenetre1.repaint();
        while(!sudoku && !loto && !bn && !poker){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(sudoku) {
            Sudoku s = new Sudoku();
            s.Jouer();
        }
        if(bn) {
            Partie bn1 = new Partie();
            bn1.lancer();
        }
        if(loto){
            //lancer loto
        }
        if(poker){
            Main poker = new Main();
            poker.lancer();
        }
    }

    private void afficherMenuJeux() {
        ImageIcon f = new ImageIcon("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\Fond_Jeux.jpg");
        JPanel pan = new JPanel(){
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(f.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        fenetre1.setContentPane(pan);
        fenetre1.setTitle("Jeux");
        int n = 250;

        Dimension d = new Dimension(n,n);
        Font font = new Font("Arial",Font.BOLD,20);

        MouseListener m1 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {fenetre1.setVisible(false);sudoku=true;}
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        };
        MouseListener m3 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {fenetre1.setVisible(false);poker=true;}
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        };
        MouseListener m4 = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {fenetre1.setVisible(false);bn=true;}
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        };

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.LINE_AXIS));
        p1.setOpaque(false);

        JLabel TSudo = new JLabel();
        TSudo.setText("Sudoku");
        TSudo.setForeground(Color.black);
        TSudo.setFont(font);
        TSudo.setOpaque(false);
        TSudo.setBorder(BorderFactory.createEmptyBorder(100,120,20,100));
        p1.add(TSudo);

        JLabel TLoto = new JLabel();
        TLoto.setText("Loto");
        TLoto.setForeground(Color.black);
        TLoto.setFont(font);
        TLoto.setOpaque(false);
        TLoto.setBorder(BorderFactory.createEmptyBorder(100,120,20,100));
        p1.add(TLoto);

        JLabel TPoker = new JLabel();
        TPoker.setText("Poker");
        TPoker.setForeground(Color.black);
        TPoker.setFont(font);
        TPoker.setOpaque(false);
        TPoker.setBorder(BorderFactory.createEmptyBorder(100,120,20,100));
        p1.add(TPoker);

        JLabel TBN = new JLabel();
        TBN.setText("Bataille  Navale");
        TBN.setForeground(Color.black);
        TBN.setFont(font);
        TBN.setOpaque(false);
        TBN.setBorder(BorderFactory.createEmptyBorder(100,120,20,100));
        p1.add(TBN);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
        p2.setOpaque(false);
        JButton espace1 = new JButton();
        espace1.setBorder(BorderFactory.createEmptyBorder(0,0,50,50));
        espace1.setOpaque(false);
        espace1.setBackground(Color.white);
        JButton espace2 = new JButton();
        espace2.setBorder(BorderFactory.createEmptyBorder(0,0,50,50));
        espace2.setOpaque(false);
        espace2.setBackground(Color.white);
        JButton espace3 = new JButton();
        espace3.setBorder(BorderFactory.createEmptyBorder(0,0,50,50));
        espace3.setOpaque(false);
        espace3.setBackground(Color.white);

        JButton Sudo = new JButton();
        Sudo.setPreferredSize(d);
        Sudo.setIcon(new ImageIcon("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\Sudoku.jpg"));
        Sudo.setText("Sudoku");
        Sudo.addMouseListener(m1);
        p2.add(Sudo);
        p2.add(espace1);

        JButton Loto = new JButton();
        Loto.setPreferredSize(d);
        Loto.setIcon(new ImageIcon("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\Loto.jpg"));
        Loto.setText("Loto");
        p2.add(Loto);
        p2.add(espace2);

        JButton Poker = new JButton();
        Poker.setPreferredSize(d);
        Poker.setIcon(new ImageIcon("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\Poker.jpg"));
        Poker.setText("Poker");
        Poker.addMouseListener(m3);
        p2.add(Poker);
        p2.add(espace3);

        JButton BN = new JButton();
        BN.setPreferredSize(d);
        BN.setIcon(new ImageIcon("C:\\Users\\Bitfenix\\Desktop\\Projet\\Mini_Jeu\\src\\fr\\projet\\BN.jpg"));
        BN.setText("BN");
        BN.addMouseListener(m4);
        p2.add(BN);


        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
        p3.setOpaque(false);

        JLabel RSudo = new JLabel();
        RSudo.setText("Regle Sudoku");
        RSudo.setForeground(Color.black);
        RSudo.setFont(font);
        RSudo.setOpaque(false);
        RSudo.setBorder(BorderFactory.createEmptyBorder(100,20,20,20));
        p3.add(RSudo);

        JLabel RLoto = new JLabel();
        RLoto.setText("Regle Loto");
        RLoto.setForeground(Color.black);
        RLoto.setFont(font);
        RLoto.setOpaque(false);
        RLoto.setBorder(BorderFactory.createEmptyBorder(100,20,20,20));
        p3.add(RLoto);

        JLabel RPoker = new JLabel();
        RPoker.setText("Regle Poker");
        RPoker.setForeground(Color.black);
        RPoker.setFont(font);
        RPoker.setOpaque(false);
        RPoker.setBorder(BorderFactory.createEmptyBorder(100,20,20,20));
        p3.add(RPoker);

        JLabel RBN = new JLabel();
        RBN.setText("Regle Bataille Navale");
        RBN.setForeground(Color.black);
        RBN.setFont(font);
        RBN.setOpaque(false);
        RBN.setBorder(BorderFactory.createEmptyBorder(100,20,20,20));
        p3.add(RBN);


        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4,BoxLayout.PAGE_AXIS));
        p4.setOpaque(false);
        p4.add(p1);
        p4.add(p2);
        p4.add(p3);

        fenetre1.getContentPane().add(p4);

        p1.setVisible(true);
        p2.setVisible(true);
        p4.setVisible(true);
        pan.setVisible(true);
        fenetre1.validate();
        fenetre1.repaint();

    }
    private void afficherClassement()
    { }
    private void afficherCredits()
    { }
}
