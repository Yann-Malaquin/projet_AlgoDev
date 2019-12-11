package fr.projet.poker.Interface;

import fr.projet.poker.Carte;
import fr.projet.poker.JoueurPoker;
import fr.projet.poker.MainsGagnantes;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableController extends Thread {

    private List<Carte> deck = new ArrayList<Carte>();
    private List<Carte> mixDeck = new ArrayList<Carte>();
    private List<JoueurPoker> lJP = new ArrayList<JoueurPoker>();
    private List<JoueurPoker> finTour = new ArrayList<JoueurPoker>();
    private List<JoueurPoker> lEndGame = new ArrayList<JoueurPoker>();
    private List<Integer> lFold = new ArrayList<Integer>();
    private List<Integer> lWinner = new ArrayList<Integer>();
    private Group root = new Group();
    Group joueur;
    private Button bnAfficher, bnDistrib, bnRelancer, bnSeCoucher, bnSuivre, bnMiser, bnTapis, bnCheck;
    private TextField barreMiser;
    private Label pot;
    private int indexJoueur;
    private int turn;
    private double suivre;
    private MainsGagnantes m = new MainsGagnantes();
    private double pb;
    private int initJoueur;
    private AnchorPane carteJ;

    public TableController() {
        super();
    }

    public void setlJP(JoueurPoker jp) {
        this.lJP.add(jp);
    }

    public List<JoueurPoker> getlJP() {
        return this.lJP;
    }

    public void setIndexJoueur(int _indexJoueur) {
        this.indexJoueur = _indexJoueur;
    }

    public int getIndexJoueur() {
        return this.indexJoueur;
    }

    public void setFinTour(JoueurPoker _jp) {
        this.finTour.add(_jp);
    }

    public List<JoueurPoker> getFinTour() {
        return this.finTour;
    }

    public void setlFold(int _index) {
        this.lFold.add(_index);
    }

    public List<Integer> getlFold() {
        return this.lFold;
    }

    public void setlWinner(int _winner) {
        this.lWinner.add(_winner);
    }

    public List<Integer> getlWinner() {
        return this.lWinner;
    }

    public void setDeck(Carte _card) {
        this.deck.add(_card);
    }

    public List<Carte> getDeck() {
        return this.deck;
    }

    public void setPb(double _pb) {
        this.pb = _pb;
    }

    public double getPb() {
        return this.pb;
    }

    public void setInitJoueur(int _initJoueur) {
        this.initJoueur = _initJoueur;
    }

    public int getInitJoueur() {
        return this.initJoueur;
    }

    public void setlEndGame(JoueurPoker _jp) {
        this.lEndGame.add(_jp);
    }

    public List<JoueurPoker> getlEndGame() {
        return this.lEndGame;
    }

    public void initTable(Stage primaryStage) {

        AnchorPane fenetre, table, carteP;
        Group allJoueur, plateau;

        double w, h;

        //on charge l'image de fond.
        InputStream input = this.getClass().getResourceAsStream("/resources/poker/table_poker.png");
        try {
            //on charge le fichier Table.FXML puisque l'on travail dessus.
            root = FXMLLoader.load(this.getClass().getResource("Table.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //on recupere les differentes partie de l'interface pour pouvoir ensuite les MAJ
        fenetre = (AnchorPane) ((Group) root.getChildren().get(0)).getChildren().get(0);
        table = (AnchorPane) fenetre.getChildren().get(0);
        allJoueur = (Group) table.getChildren().get(0);
        plateau = (Group) allJoueur.getChildren().get(5);
        carteP = (AnchorPane) plateau.getChildren().get(0);
        bnAfficher = (Button) plateau.getChildren().get(1);
        bnDistrib = (Button) plateau.getChildren().get(2);
        barreMiser = (TextField) plateau.getChildren().get(9);


        //on definit la taille de la fenetre par rapport a l'ecran.
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        fenetre.setPrefWidth(primaryStage.getWidth());
        fenetre.setPrefHeight(primaryStage.getHeight());
        w = primaryStage.getWidth();
        h = primaryStage.getHeight();

        table.setLayoutX(200);
        table.setLayoutY(100);
        //on modifie le background de notre fenetre
        Image im = new Image(input, w - (w * 0.20), h - (h * 0.20), false, false);
        BackgroundImage myBI = new BackgroundImage(im,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        table.setBackground(new Background(myBI));

        //on genere les cartes

        this.setNamePlayer(allJoueur);
        this.setBankPlayer(allJoueur);
        this.setDonneur(allJoueur);
        this.setpbGB(allJoueur, this.getPb());
        this.setButton(plateau);
        this.generateDeck();
        this.initJoueur();


        //creation de l'evenement sur le bouton afficher
        bnAfficher.setOnAction((event) -> {
            this.displayDeck(carteP, deck);
            this.cardJGenerate(allJoueur);
            bnAfficher.setVisible(false);
            bnDistrib.setVisible(true);
        });


        bnDistrib.setOnAction((event) -> {
            carteP.getChildren().clear();

            this.mixDeck = this.mixDeckCard(deck);
            this.distributeCard(allJoueur, mixDeck);
            this.cardPGenerate(allJoueur);
            bnDistrib.setVisible(false);

            joueur = (Group) allJoueur.getChildren().get(indexJoueur);

            carteJ = (AnchorPane) joueur.getChildren().get(3);
            AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
            i = new AnchorPane();
            i = (AnchorPane) carteJ.getChildren().get(0);
            i.getChildren().remove(i.getChildren().get(1));

            carteJ = (AnchorPane) joueur.getChildren().get(4);
            i = new AnchorPane();
            i = (AnchorPane) carteJ.getChildren().get(0);
            i.getChildren().remove(i.getChildren().get(1));
        });

        this.turn(allJoueur);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    //generer une carte
    public Carte generateCard(String _figure, String _couleur, int _valeur) {

        Carte c = new Carte(_figure, _couleur, _valeur);
        //chargement de l'image
        InputStream couleur = this.getClass().getResourceAsStream("/resources/poker/Couleur_" + _couleur + ".png");

        c.setId("Carte_" + _figure + "_" + _couleur);

        //different label accueillant la figure et la couleur
        Label HG = new Label(), C = new Label(), BD = new Label(), HGC = new Label(), BDC = new Label();
        Group label = new Group();

        HG.setId(Integer.toString(_valeur));
        C.setId(_couleur);
        BD.setId("BD" + _valeur);

        ImageView iw = new ImageView(new Image(couleur));

        HG.setPrefSize(15, 15);
        HG.setLayoutX(10);
        HG.setLayoutY(10);


        iw.setFitWidth(25);
        iw.setFitHeight(25);
        C.setPrefSize(25, 25);
        C.setLayoutX(14.5);
        C.setLayoutY(30);
        C.setGraphic(iw);

        BD.setPrefSize(15, 15);
        BD.setRotate(180);
        BD.setLayoutX(29);
        BD.setLayoutY(60);

        if (_couleur.compareTo("Carreau") == 0 || _couleur.compareTo("Coeur") == 0) {
            HG.setText(_figure);
            HG.setTextFill(Color.RED);
            BD.setText(_figure);
            BD.setTextFill(Color.RED);
        } else {
            HG.setText(_figure);
            HG.setTextFill(Color.BLACK);
            BD.setText(_figure);
            BD.setTextFill(Color.BLACK);
        }

        if (_couleur.compareTo("Carreau") == 0) {
            label.setId("Carte" + _valeur + "Ca");
        } else if (_couleur.compareTo("Coeur") == 0) {
            label.setId("Carte" + _valeur + "Co");
        } else if (_couleur.compareTo("Trefle") == 0) {
            label.setId("Carte" + _valeur + "Tr");
        } else {
            label.setId("Carte" + _valeur + "Pi");
        }

        //ajout des 3 label a un groupe
        label.getChildren().addAll(HG, C, BD, HGC, BDC);
        //ajout du groupe label a l'anchorpane c
        c.getChildren().add(label);
        c.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        //ajout de bordure noir à la carte
        c.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        return c;
    }

    //generer un paquet de carte
    public void generateDeck() {

        // tableau regroupant toutes les figures
        String figure[] = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "As"};
        //tableau regroupant les 4 couleurs
        String couleur[] = {"carreau", "coeur", "trefle", "pique"};

        int x = 0, y = 0;
        int _valeur = 2;

        // parcourir les 2 tableaux
        for (String c : couleur) {
            for (String n : figure) {
                Carte carte = new Carte();
                carte = this.generateCard(n, c, _valeur);
                this.setDeck(carte);
                _valeur++;
            }
            _valeur = 2;
        }
    }

    //afficher un paquet de carte
    public void displayDeck(AnchorPane carteP, List<Carte> deck) {

        //on demarre a x et y de distance dans l'AP, on definit egalement l'angle initial r.
        double y = 300, r = -50, x = 0, cos = 0, sin = 0;

        for (Carte c : deck) {
            //on modifie le layout de chaque carte
            c.setLayoutX(x);
            c.setLayoutY(y);
            c.setRotate(r);

            //on l'ajoute en child de l'AP
            carteP.getChildren().add(c);

            //permet de faire l'arc de cercle avec les cartes
            cos = Math.toRadians(r);
            sin = Math.toRadians(r);
            x += Math.cos(cos) * 15;
            y += Math.sin(sin) * 15;

            //on modifie l'angle de la carte
            r += (2 * (90 / 52));
        }
    }

    //permet d'afficher le nom de chaque joueur a la table
    public void setNamePlayer(Group allJoueur) {
        int i = 0;
        Group joueur;
        Label name;
        Font f;
        InputStream input1 = this.getClass().getResourceAsStream("/resources/poker/SaloonExt Th.ttf");

        f = Font.loadFont(input1, 18);


        while (i < allJoueur.getChildren().size() - 1) {
            joueur = (Group) allJoueur.getChildren().get(i);
            name = (Label) joueur.getChildren().get(0);
            name.setText(lJP.get(i).getPseudo());
            name.setFont(f);

            i++;
        }
    }

    //permet d'affiche la somme de chaque joueur a la table
    public void setBankPlayer(Group allJoueur) {
        int i = 0;
        Group joueur;
        Label bank;


        while (i < allJoueur.getChildren().size() - 1) {
            joueur = (Group) allJoueur.getChildren().get(i);
            bank = (Label) joueur.getChildren().get(2);
            bank.setText(lJP.get(i).getWallet() + " €");
            i++;
        }
    }

    //permet "d'effacer" tout les boutons inutile au debut
    public void setButton(Group plateau) {

        bnAfficher = (Button) plateau.getChildren().get(1);
        bnDistrib = (Button) plateau.getChildren().get(2);
        bnSeCoucher = (Button) plateau.getChildren().get(3);
        bnSuivre = (Button) plateau.getChildren().get(4);
        bnRelancer = (Button) plateau.getChildren().get(5);
        bnMiser = (Button) plateau.getChildren().get(6);
        bnTapis = (Button) plateau.getChildren().get(7);
        bnCheck = (Button) plateau.getChildren().get(8);
        barreMiser = (TextField) plateau.getChildren().get(9);
        pot = (Label) plateau.getChildren().get(10);


        bnAfficher.setText("Afficher Carte");
        bnAfficher.setVisible(true);
        bnDistrib.setText("Distribuer cartes");
        bnDistrib.setVisible(false);
        bnRelancer.setVisible(false);
        bnSeCoucher.setVisible(false);
        bnMiser.setVisible(false);
        bnCheck.setVisible(false);
        bnTapis.setVisible(false);
        bnSuivre.setVisible(false);
        pot.setVisible(true);
        barreMiser.setVisible(false);
    }

    public void setpbGB(Group allJoueur, double pb) {
        double b = 0, p = 0, j = 0;
        Label role = new Label(), bank = new Label();
        Group plateau = new Group();
        plateau = (Group) allJoueur.getChildren().get(allJoueur.getChildren().size() - 1);

        for (int i = 0; i < this.getlJP().size(); i++) {

            joueur = (Group) allJoueur.getChildren().get(i);
            role = (Label) joueur.getChildren().get(1);
            bank = (Label) joueur.getChildren().get(2);
            pot = (Label) plateau.getChildren().get(10);

            if (role.getText().compareTo("GB") == 0) {
                ///////////////////////////////////////////
                //Bank
                if (!checkBankTurn(this.getlJP().get(i), 2 * pb)) {
                    this.tapis(allJoueur, joueur, this.getlJP().get(i));
                } else {
                    String tmpBank = bank.getText();
                    String tmpBank2 = tmpBank.substring(0, tmpBank.length() - 2);
                    b = Double.parseDouble(tmpBank2) - (2 * pb);
                    this.getlJP().get(i).setWallet(b);
                    bank.setText(tmpBank.replaceAll(tmpBank2, Double.toString(b)));

                    //////////////////////////////////////////
                    //Pot
                    String tmpPot = pot.getText();
                    String tmpPot2 = tmpPot.substring(12, tmpPot.length() - 2);
                    p = (2 * pb) + Double.parseDouble(tmpPot2);
                    pot.setText(tmpPot.replaceAll(tmpPot2, Double.toString(p)));
                    j++;
                }
            } else if (role.getText().compareTo("PB") == 0) {
                ///////////////////////////////////////////
                //Bank
                if (!checkBankTurn(this.getlJP().get(i), pb)) {
                    this.tapis(allJoueur, joueur, this.getlJP().get(i));
                } else {
                    String tmpBank = bank.getText();
                    String tmpBank2 = tmpBank.substring(0, tmpBank.length() - 2);
                    b = Double.parseDouble(tmpBank2) - pb;
                    this.getlJP().get(i).setWallet(b);
                    bank.setText(tmpBank.replaceAll(tmpBank2, Double.toString(b)));

                    //////////////////////////////////////////
                    //Pot
                    String tmpPot = pot.getText();
                    String tmpPot2 = tmpPot.substring(12, tmpPot.length() - 2);
                    p = pb + Double.parseDouble(tmpPot2);
                    pot.setText(tmpPot.replaceAll(tmpPot2, Double.toString(p)));
                    j++;
                }
            }

            if (j == 2) {
                break;
            }
            this.suivre = this.getPb();
        }
    }

    //permet d'afficher le donneur, la petite blinde et la grosse blinde sur la table
    public void setDonneur(Group allJoueur) {
        int i = 0;
        Label role;
        Group joueur;
        //on cherche qui est le donneur
        while (i < lJP.size()) {
            if (lJP.get(i).getEtatJoueur().contains("Donneur")) {
                joueur = (Group) allJoueur.getChildren().get(i);
                role = (Label) joueur.getChildren().get(1);
                role.setText("D");
                break;
            } else {
                i++;
            }
        }

        //ensuite on fait en fonction de la position pour le decalage si le le dernier joueur est le donneur alors position 0 et 1 en PB et GB
        if (i + 1 == allJoueur.getChildren().size() - 1) {
            joueur = (Group) allJoueur.getChildren().get(0);
            role = (Label) joueur.getChildren().get(1);
            role.setText("PB");
            lJP.get(0).setEtatJoueur("PB");
            joueur = (Group) allJoueur.getChildren().get(1);
            role = (Label) joueur.getChildren().get(1);
            role.setText("GB");
            lJP.get(1).setEtatJoueur("GB");
            //si avant dernier alors position dernier et 0
        } else if (i + 2 == allJoueur.getChildren().size() - 1) {
            joueur = (Group) allJoueur.getChildren().get(i + 1);
            role = (Label) joueur.getChildren().get(1);
            role.setText("PB");
            lJP.get(i + 1).setEtatJoueur("PB");
            joueur = (Group) allJoueur.getChildren().get(0);
            role = (Label) joueur.getChildren().get(1);
            role.setText("GB");
            lJP.get(0).setEtatJoueur("GB");
            //sinon on fait normalement a partir du donneur
        } else {
            joueur = (Group) allJoueur.getChildren().get(i + 1);
            role = (Label) joueur.getChildren().get(1);
            role.setText("PB");
            lJP.get(i + 1).setEtatJoueur("PB");
            joueur = (Group) allJoueur.getChildren().get(i + 2);
            role = (Label) joueur.getChildren().get(1);
            role.setText("GB");
            lJP.get(i + 2).setEtatJoueur("GB");
        }

    }

    //distribuer les cartes aux joueurs
    public void distributeCard(Group allJoueur, List<Carte> mixDeck) {

        AnchorPane carteJ, carteP;
        Carte tmp;
        Group joueur;
        JoueurPoker jp;
        int i, pdonneur = 0, init;


        for (i = 0; i < this.getlJP().size(); i++) {
            if (this.getlJP().get(i).getEtatJoueur().contains("Donneur")) {
                break;
            } else {
                pdonneur++;
            }
        }
        init = pdonneur;

        for (i = 3; i < 5; i++) {

            if ((pdonneur + 1) == allJoueur.getChildren().size() - 1) {
                pdonneur = 0;
            } else {
                pdonneur++;
            }

            while (pdonneur < allJoueur.getChildren().size() - 1) {
                while (this.getlFold().contains(pdonneur)) {
                    this.setFinTour(this.lJP.get(pdonneur));
                    pdonneur++;
                }
                while (this.getlEndGame().contains(this.lJP.get(pdonneur))) {
                    this.setFinTour(this.getlJP().get(pdonneur));
                    pdonneur++;
                }

                joueur = (Group) allJoueur.getChildren().get(pdonneur);
                carteJ = (AnchorPane) joueur.getChildren().get(i);
                jp = lJP.get(pdonneur);
                InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                Image im = new Image(input);
                ImageView iw = new ImageView(im);
                iw.setFitHeight(85);
                iw.setFitWidth(54);

                tmp = mixDeck.get(0);
                mixDeck.remove(0);
                tmp.setLayoutX(0);
                tmp.setLayoutY(0);
                tmp.setRotate(0);
                tmp.setPrefHeight(85);
                tmp.setPrefWidth(54);
                tmp.getChildren().add(iw);
                jp.setMainJoueur(tmp);
                carteJ.getChildren().add(tmp);


                // si on arrive sur le donneur alors on quitte le 1er et on entame le 2e tour
                if (pdonneur == init) {
                    break;
                    // si on arrive a la fin de la liste on retourne au 1er joueur
                } else if (pdonneur == (allJoueur.getChildren().size() - 2)) {
                    pdonneur = 0;
                } else {
                    pdonneur++;
                }

            }
            pdonneur = init;
        }

        this.mixDeck.remove(0);
        bnMiser.setVisible(true);
        bnCheck.setVisible(true);
        bnDistrib.setVisible(false);
        bnSuivre.setVisible(true);
        bnRelancer.setVisible(true);
        bnTapis.setVisible(true);
        pot.setVisible(true);
        barreMiser.setVisible(true);
        bnSeCoucher.setVisible(true);

    }

    //permet de mélanger un paquet de carte, avant la distribution
    public List<Carte> mixDeckCard(List<Carte> deck) {
        int i;

        for (i = 0; i < 2; i++) {
            // on mélange le paquet de carte
            List<Carte> mixDecktmp = this.mixDeckUn(deck);
            deck = this.mixDeckUn(mixDecktmp);
        }

        this.mixDeck.addAll(deck);
        return this.mixDeck;
    }

    // melanger le paquet de cartes 1 fois
    public List<Carte> mixDeckUn(List<Carte> deck) {
        List<Carte> mixDecktmp = new ArrayList<Carte>();
        int indexAlea;
        int i = deck.size();
        Random rand = new Random();

        while (i > 0) {
            indexAlea = rand.nextInt(i);
            mixDecktmp.add(deck.get(indexAlea));
            deck.remove(indexAlea);
            i--;
        }

        return mixDecktmp;
    }

    public void turn(Group allJoueur) {
        turn = 1;
        joueur = (Group) allJoueur.getChildren().get(indexJoueur);

        /*donne l'action au bouton miser
         **on appelle la fonction miser et on incremente l'index en verifiant toujours si l'on est pas sur le dernier joueur
         */

        bnMiser.setOnAction((event) -> {
            double mise = 0;
            mise = Double.parseDouble(barreMiser.getText());
            this.suivre = mise;
            bnCheck.setVisible(true);
            bnSuivre.setVisible(true);
            bnRelancer.setVisible(true);
            bnTapis.setVisible(true);
            pot.setVisible(true);
            barreMiser.setVisible(true);
            bnSeCoucher.setVisible(true);

            while (this.getlFold().contains(indexJoueur)) {
                this.setFinTour(this.lJP.get(indexJoueur));
                indexJoueur++;
            }
            while (this.getlEndGame().contains(this.lJP.get(indexJoueur))) {
                this.setFinTour(this.getlJP().get(indexJoueur));
                indexJoueur++;
            }

            if (this.checkBankTurn(this.getlJP().get(indexJoueur), mise)) {
                this.miser(allJoueur, (Group) allJoueur.getChildren().get(indexJoueur), this.getlJP().get(indexJoueur), mise);
            } else {
                this.tapis(allJoueur, (Group) allJoueur.getChildren().get(indexJoueur), this.getlJP().get(indexJoueur));
            }
            if (indexJoueur + 1 == this.getlJP().size()) {
                InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                Image im = new Image(input);
                ImageView iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);

                input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                im = new Image(input);
                iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);
                this.setIndexJoueur(0);
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));
            } else {
                InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                Image im = new Image(input);
                ImageView iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);

                input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                im = new Image(input);
                iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);
                indexJoueur++;
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));
            }

            this.getFinTour().clear();

        });

        bnSuivre.setOnAction((event) -> {
            while (this.getlFold().contains(indexJoueur)) {
                this.setFinTour(this.lJP.get(indexJoueur));
                indexJoueur++;
            }
            while (this.getlEndGame().contains(this.lJP.get(indexJoueur))) {
                this.setFinTour(this.getlJP().get(indexJoueur));
                indexJoueur++;
            }
            if (this.checkBankTurn(this.getlJP().get(indexJoueur), suivre)) {
                this.suivre(allJoueur, (Group) allJoueur.getChildren().get(indexJoueur), this.getlJP().get(indexJoueur), suivre);
            } else {
                this.tapis(allJoueur, (Group) allJoueur.getChildren().get(indexJoueur), this.getlJP().get(indexJoueur));
            }
            this.setFinTour(this.lJP.get(indexJoueur));

            if (turn == 1) {
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                if ((this.getFinTour().size() == this.getlJP().size() - 1) && (((Label) joueur.getChildren().get(1)).getText().compareTo("PB /S") == 0)) {

                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    this.displayCardT(allJoueur, 0, 3);
                    bnMiser.setVisible(true);
                    bnSuivre.setVisible(false);
                    bnTapis.setVisible(false);
                    bnRelancer.setVisible(false);
                    bnSeCoucher.setVisible(false);
                    if ((initJoueur + 1) == this.getlJP().size()) {

                        indexJoueur = 0;

                        joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                        carteJ = (AnchorPane) joueur.getChildren().get(3);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));

                        carteJ = (AnchorPane) joueur.getChildren().get(4);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));

                    } else {
                        indexJoueur = (initJoueur + 1);

                        joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                        carteJ = (AnchorPane) joueur.getChildren().get(3);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));

                        carteJ = (AnchorPane) joueur.getChildren().get(4);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));
                    }
                    turn++;
                } else if (this.getFinTour().size() == (this.getlJP().size() - 1)) {

                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    this.displayCardT(allJoueur, 0, 3);
                    bnMiser.setVisible(true);
                    bnSuivre.setVisible(false);
                    bnTapis.setVisible(false);
                    bnRelancer.setVisible(false);
                    bnSeCoucher.setVisible(false);

                    if ((initJoueur + 1) == this.getlJP().size()) {
                        indexJoueur = 0;

                        joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                        carteJ = (AnchorPane) joueur.getChildren().get(3);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));

                        carteJ = (AnchorPane) joueur.getChildren().get(4);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));
                    } else {
                        indexJoueur = (initJoueur + 1);

                        joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                        carteJ = (AnchorPane) joueur.getChildren().get(3);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));

                        carteJ = (AnchorPane) joueur.getChildren().get(4);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));
                    }
                    turn++;
                } else if (indexJoueur == this.getlJP().size() - 1) {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    indexJoueur = 0;
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                } else {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    indexJoueur++;

                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                }
            } else if (turn == 2) {
                if (this.getFinTour().size() == (this.getlJP().size() - 1)) {

                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    this.displayCardT(allJoueur, 3, 4);
                    bnMiser.setVisible(true);
                    bnSuivre.setVisible(false);
                    bnTapis.setVisible(false);
                    bnRelancer.setVisible(false);
                    bnSeCoucher.setVisible(false);

                    if ((initJoueur + 1) == this.getlJP().size()) {
                        indexJoueur = 0;

                        joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                        carteJ = (AnchorPane) joueur.getChildren().get(3);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));

                        carteJ = (AnchorPane) joueur.getChildren().get(4);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));
                    } else {
                        indexJoueur = (initJoueur + 1);

                        joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                        carteJ = (AnchorPane) joueur.getChildren().get(3);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));

                        carteJ = (AnchorPane) joueur.getChildren().get(4);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));
                    }
                    turn++;
                } else if (indexJoueur == this.getlJP().size() - 1) {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    indexJoueur = 0;
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                } else {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    indexJoueur++;

                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                }
            } else if (turn == 3) {
                if (this.getFinTour().size() == (this.getlJP().size() - 1)) {

                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    this.displayCardT(allJoueur, 4, 5);
                    bnMiser.setVisible(true);
                    bnSuivre.setVisible(false);
                    bnTapis.setVisible(false);
                    bnRelancer.setVisible(false);
                    bnSeCoucher.setVisible(false);

                    if ((initJoueur + 1) == this.getlJP().size()) {
                        indexJoueur = 0;

                        joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                        carteJ = (AnchorPane) joueur.getChildren().get(3);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));

                        carteJ = (AnchorPane) joueur.getChildren().get(4);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));
                    } else {
                        indexJoueur = (initJoueur + 1);

                        joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                        carteJ = (AnchorPane) joueur.getChildren().get(3);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));

                        carteJ = (AnchorPane) joueur.getChildren().get(4);
                        i = new AnchorPane();
                        i = (AnchorPane) carteJ.getChildren().get(0);
                        i.getChildren().remove(i.getChildren().get(1));
                    }
                    turn++;
                } else if (indexJoueur == this.getlJP().size() - 1) {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    indexJoueur = 0;
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                } else {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    indexJoueur++;

                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                }
            } else if (turn == 4) {
                if (this.getFinTour().size() == (this.getlJP().size() - 1)) {

                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);
                    turn = 1;
                    this.checkHand();
                    this.checkWinner();
                    this.displayPopupWinner();
                    this.setGain(allJoueur);
                    PauseTransition delay = new PauseTransition(Duration.seconds(20));
                    delay.play();
                    this.clearTable(allJoueur);
                } else if (indexJoueur == this.getlJP().size() - 1) {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    indexJoueur = 0;
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                } else {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    indexJoueur++;

                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                }
            }
        });

        bnCheck.setOnAction((event) ->

        {
            while (this.getlFold().contains(indexJoueur)) {
                this.setFinTour(this.lJP.get(indexJoueur));
                indexJoueur++;
            }
            while (this.getlEndGame().contains(this.lJP.get(indexJoueur))) {
                this.setFinTour(this.getlJP().get(indexJoueur));
                indexJoueur++;
            }
            this.check((Group) allJoueur.getChildren().get(indexJoueur));
            if (turn == 1) {
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                if ((this.getFinTour().size() == this.getlJP().size() - 1) && (((Label) joueur.getChildren().get(1)).getText().compareTo("PB /S") == 0)) {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    this.displayCardT(allJoueur, 0, 3);
                    bnMiser.setVisible(true);
                    bnSuivre.setVisible(false);
                    bnTapis.setVisible(false);
                    bnRelancer.setVisible(false);
                    bnSeCoucher.setVisible(false);
                    indexJoueur = (initJoueur + 1);
                    turn++;
                } else if (this.getFinTour().size() == (this.getlJP().size() - 1)) {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    this.displayCardT(allJoueur, 0, 3);
                    bnMiser.setVisible(true);
                    bnSuivre.setVisible(false);
                    bnTapis.setVisible(false);
                    bnRelancer.setVisible(false);
                    bnSeCoucher.setVisible(false);

                    indexJoueur = (initJoueur + 1);
                    turn++;
                } else if (indexJoueur == this.getlJP().size() - 1) {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    indexJoueur = 0;
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                } else {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);

                    indexJoueur++;
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));

                    carteJ = (AnchorPane) joueur.getChildren().get(4);
                    i = new AnchorPane();
                    i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().remove(i.getChildren().get(1));
                }
            } else if (this.getFinTour().size() == (this.getlJP().size() - 1)) {
                if (turn == 2) {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    this.displayCardT(allJoueur, 3, 4);
                    bnMiser.setVisible(true);
                    bnSuivre.setVisible(false);
                    bnTapis.setVisible(false);
                    bnRelancer.setVisible(false);
                    bnSeCoucher.setVisible(false);

                    indexJoueur = (initJoueur + 1);
                    turn++;
                } else if (turn == 3) {
                    InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    Image im = new Image(input);
                    ImageView iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                    carteJ = (AnchorPane) joueur.getChildren().get(3);
                    AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                    i.getChildren().add(iw);

                    input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                    im = new Image(input);
                    iw = new ImageView(im);
                    iw.setFitWidth(54);
                    iw.setFitHeight(85);
                    this.displayCardT(allJoueur, 4, 5);
                    bnMiser.setVisible(true);
                    bnSuivre.setVisible(false);
                    bnTapis.setVisible(false);
                    bnRelancer.setVisible(false);

                    indexJoueur = (initJoueur + 1);
                    turn++;
                } else if (turn == 4) {
                    this.checkHand();
                    this.checkWinner();
                    this.displayPopupWinner();
                    this.setGain(allJoueur);
                    PauseTransition delay = new PauseTransition(Duration.seconds(20));
                    delay.play();
                    this.clearTable(allJoueur);

                    turn = 1;
                }
            } else if (indexJoueur == this.getlJP().size() - 1) {
                InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                Image im = new Image(input);
                ImageView iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);

                input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                im = new Image(input);
                iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                indexJoueur = 0;
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                i = (AnchorPane) carteJ.getChildren().get(0);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));
            } else {
                InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                Image im = new Image(input);
                ImageView iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);

                input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                im = new Image(input);
                iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                indexJoueur++;
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                i = (AnchorPane) carteJ.getChildren().get(0);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));
            }

        });

        bnRelancer.setOnAction((event) ->

        {
            double relance = 0;
            relance = Double.parseDouble(barreMiser.getText());
            this.suivre = relance;

            while (this.getlFold().contains(indexJoueur)) {
                this.setFinTour(this.lJP.get(indexJoueur));
                indexJoueur++;
            }
            while (this.getlEndGame().contains(this.lJP.get(indexJoueur))) {
                this.setFinTour(this.getlJP().get(indexJoueur));
                indexJoueur++;
            }

            if (this.checkBankTurn(this.getlJP().get(indexJoueur), relance)) {
                this.relancer(allJoueur, (Group) allJoueur.getChildren().get(indexJoueur), this.getlJP().get(indexJoueur), relance);
            } else {
                this.tapis(allJoueur, (Group) allJoueur.getChildren().get(this.getIndexJoueur()), this.getlJP().get(indexJoueur));
            }

            if ((indexJoueur + 1) == this.getlJP().size()) {
                InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                Image im = new Image(input);
                ImageView iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);

                input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                im = new Image(input);
                iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                indexJoueur = 0;
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                i = (AnchorPane) carteJ.getChildren().get(0);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));
            } else {
                InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                Image im = new Image(input);
                ImageView iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);

                input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                im = new Image(input);
                iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                indexJoueur++;
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                i = (AnchorPane) carteJ.getChildren().get(0);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));
            }

            this.getFinTour().clear();
        });

        bnTapis.setOnAction((event) ->

        {
            while (this.getlFold().contains(indexJoueur)) {
                this.setFinTour(this.lJP.get(indexJoueur));
                indexJoueur++;
            }
            while (this.getlEndGame().contains(this.lJP.get(indexJoueur))) {
                this.setFinTour(this.getlJP().get(indexJoueur));
                indexJoueur++;
            }
            this.tapis(allJoueur, (Group) allJoueur.getChildren().get(indexJoueur), this.getlJP().get(indexJoueur));

            if ((indexJoueur + 1) == this.getlJP().size()) {
                InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                Image im = new Image(input);
                ImageView iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);

                input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                im = new Image(input);
                iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                indexJoueur = 0;
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                i = (AnchorPane) carteJ.getChildren().get(0);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));
            } else {
                InputStream input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                Image im = new Image(input);
                ImageView iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().add(iw);

                input = this.getClass().getResourceAsStream("/resources/poker/arriere_carte.jpg");
                im = new Image(input);
                iw = new ImageView(im);
                iw.setFitWidth(54);
                iw.setFitHeight(85);
                indexJoueur++;
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                i = (AnchorPane) carteJ.getChildren().get(0);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

            }

            this.getFinTour().clear();
        });

        bnSeCoucher.setOnAction((event) ->
        {
            while (this.getlFold().contains(indexJoueur)) {
                this.setFinTour(this.lJP.get(indexJoueur));
                indexJoueur++;
            }
            while (this.getlEndGame().contains(this.lJP.get(indexJoueur))) {
                this.setFinTour(this.getlJP().get(indexJoueur));
                indexJoueur++;
            }
            this.seCoucher(allJoueur, (Group) allJoueur.getChildren().get(indexJoueur));
            this.setlFold(indexJoueur);

            if ((indexJoueur + 1) == this.getlJP().size()) {
                indexJoueur = 0;
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));
            } else {
                indexJoueur++;
                joueur = (Group) allJoueur.getChildren().get(indexJoueur);

                carteJ = (AnchorPane) joueur.getChildren().get(3);
                AnchorPane i = (AnchorPane) carteJ.getChildren().get(0);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

                carteJ = (AnchorPane) joueur.getChildren().get(4);
                i = new AnchorPane();
                i = (AnchorPane) carteJ.getChildren().get(0);
                i.getChildren().remove(i.getChildren().get(1));

            }
        });
    }

    //fonction permettant d'avoir les etapes de la mise
    public void miser(Group allJoueur, Group _joueur, JoueurPoker joueur, double mise) {
        double b = 0, p = 0;
        Label bank;

        Group plateau;
        Label role;

        plateau = (Group) allJoueur.getChildren().get(5);
        role = (Label) _joueur.getChildren().get(1);
        role.setText(role.getText() + " /M");
        bank = (Label) _joueur.getChildren().get(2);
        pot = (Label) plateau.getChildren().get(10);
        ///////////////////////////////////////////
        //Bank

        //on modifie uniquement la partie chiffre de la bank du joueur
        String tmpBank = bank.getText();
        String tmpBank2 = tmpBank.substring(0, tmpBank.length() - 2);
        b = Double.parseDouble(tmpBank2) - mise;
        joueur.setWallet(b);
        bank.setText(tmpBank.replaceAll(tmpBank2, Double.toString(b)));

        //////////////////////////////////////////
        //Pot

        //on modifie uniquement la partie chiffre du pot
        String tmpPot = pot.getText();
        String tmpPot2 = tmpPot.substring(12, tmpPot.length() - 2);
        p = mise + Double.parseDouble(tmpPot2);
        pot.setText(tmpPot.replaceAll(tmpPot2, Double.toString(p)));
    }

    public void suivre(Group allJoueur, Group _joueur, JoueurPoker joueur, double suivie) {
        double b = 0, p = 0;
        Label bank;

        Group plateau;
        Label role;

        plateau = (Group) allJoueur.getChildren().get(5);
        role = (Label) _joueur.getChildren().get(1);
        role.setText(role.getText() + " /S");
        bank = (Label) _joueur.getChildren().get(2);
        pot = (Label) plateau.getChildren().get(10);
        ///////////////////////////////////////////
        //Bank

        //on modifie uniquement la partie chiffre de la bank du joueur
        String tmpBank = bank.getText();
        String tmpBank2 = tmpBank.substring(0, tmpBank.length() - 2);
        b = Double.parseDouble(tmpBank2) - suivie;
        joueur.setWallet(b);
        bank.setText(tmpBank.replaceAll(tmpBank2, Double.toString(b)));

        //////////////////////////////////////////
        //Pot

        //on modifie uniquement la partie chiffre du pot
        String tmpPot = pot.getText();
        String tmpPot2 = tmpPot.substring(12, tmpPot.length() - 2);
        p = suivie + Double.parseDouble(tmpPot2);
        pot.setText(tmpPot.replaceAll(tmpPot2, Double.toString(p)));
    }

    public void check(Group joueur) {
        Label role;

        role = (Label) joueur.getChildren().get(1);
        role.setText(role.getText() + " /C");
    }

    public void relancer(Group allJoueur, Group _joueur, JoueurPoker joueur, double relance) {
        double b = 0, p = 0;
        Label role, bank, pot;
        Group plateau;

        plateau = (Group) allJoueur.getChildren().get(5);
        role = (Label) _joueur.getChildren().get(1);
        role.setText(role.getText() + " /R");
        bank = (Label) _joueur.getChildren().get(2);
        pot = (Label) plateau.getChildren().get(10);
        ///////////////////////////////////////////
        //Bank

        String tmpBank = bank.getText();
        String tmpBank2 = tmpBank.substring(0, tmpBank.length() - 2);
        b = Double.parseDouble(tmpBank2) - relance;
        joueur.setWallet(b);
        bank.setText(tmpBank.replaceAll(tmpBank2, Double.toString(b)));

        //////////////////////////////////////////
        //Pot
        String tmpPot = pot.getText();
        String tmpPot2 = tmpPot.substring(12, tmpPot.length() - 2);
        p = relance + Double.parseDouble(tmpPot2);
        pot.setText(tmpPot.replaceAll(tmpPot2, Double.toString(p)));

    }

    public void tapis(Group allJoueur, Group _joueur, JoueurPoker joueur) {
        double b = 0, p = 0;
        Label role, bank, pot;
        Group plateau;

        plateau = (Group) allJoueur.getChildren().get(5);
        role = (Label) _joueur.getChildren().get(1);
        role.setText(role.getText() + " /T");
        bank = (Label) _joueur.getChildren().get(2);
        pot = (Label) plateau.getChildren().get(10);
        ///////////////////////////////////////////
        //Bank

        String tmpBank = bank.getText();
        String tmpBank2 = tmpBank.substring(0, tmpBank.length() - 2);
        b = Double.parseDouble(tmpBank2);
        joueur.setWallet(b);
        bank.setText(tmpBank.replaceAll(tmpBank2, "0"));

        //////////////////////////////////////////
        //Pot
        String tmpPot = pot.getText();
        String tmpPot2 = tmpPot.substring(12, tmpPot.length() - 2);
        p = b + Double.parseDouble(tmpPot2);
        pot.setText(tmpPot.replaceAll(tmpPot2, Double.toString(p)));
    }

    public void seCoucher(Group allJoueur, Group joueur) {
        Label role;

        joueur.getChildren().remove(3);
        joueur.getChildren().remove(3);
        role = (Label) joueur.getChildren().get(1);
        role.setText(role.getText() + " /SC");
    }

    //genere les emplacements des cartes du milieu pour pouvoir les placer ensuite.
    public void cardPGenerate(Group allJoueur) {

        AnchorPane carteM, carteP = (AnchorPane) ((Group) allJoueur.getChildren().get(5)).getChildren().get(0);
        int x = 218;

        for (int i = 0; i < 5; i++) {
            carteM = new AnchorPane();
            carteM.setId("CarteM" + (i + 1));
            carteM.setLayoutX(x);
            carteM.setLayoutY(115);
            carteM.setPrefWidth(54);
            carteM.setPrefHeight(85);
            carteP.getChildren().add(carteM);
            x += 70;
        }
    }

    //genere les emplacements des joueurs
    public void cardJGenerate(Group allJoueur) {
        AnchorPane carte;
        //Joueur1
        carte = new AnchorPane();
        joueur = (Group) allJoueur.getChildren().get(0);
        carte.setId("CarteJ11");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(36);
        carte.setLayoutY(27);
        carte.setRotate(30);
        joueur.getChildren().add(carte);
        carte = new AnchorPane();
        carte.setId("CarteJ12");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(-9);
        carte.setLayoutY(27);
        carte.setRotate(30);
        joueur.getChildren().add(carte);
        //Joueur2
        carte = new AnchorPane();
        joueur = (Group) allJoueur.getChildren().get(1);
        carte.setId("CarteJ21");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(36);
        carte.setLayoutY(27);
        carte.setRotate(-30);
        joueur.getChildren().add(carte);
        carte = new AnchorPane();
        carte.setId("CarteJ22");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(-9);
        carte.setLayoutY(27);
        carte.setRotate(-30);
        joueur.getChildren().add(carte);
        //Joueur3
        carte = new AnchorPane();
        joueur = (Group) allJoueur.getChildren().get(2);
        carte.setId("CarteJ31");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(91);
        carte.setLayoutY(36);
        carte.setRotate(0);
        joueur.getChildren().add(carte);
        carte = new AnchorPane();
        carte.setId("CarteJ32");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(41);
        carte.setLayoutY(36);
        carte.setRotate(0);
        joueur.getChildren().add(carte);
        //Joueur4
        carte = new AnchorPane();
        joueur = (Group) allJoueur.getChildren().get(3);
        carte.setId("CarteJ41");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(36);
        carte.setLayoutY(27);
        carte.setRotate(30);
        joueur.getChildren().add(carte);
        carte = new AnchorPane();
        carte.setId("CarteJ42");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(-9);
        carte.setLayoutY(27);
        carte.setRotate(30);
        joueur.getChildren().add(carte);
        //Joueur5
        carte = new AnchorPane();
        joueur = (Group) allJoueur.getChildren().get(4);
        carte.setId("CarteJ51");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(36);
        carte.setLayoutY(27);
        carte.setRotate(-30);
        joueur.getChildren().add(carte);
        carte = new AnchorPane();
        carte.setId("CarteJ52");
        carte.setPrefWidth(54);
        carte.setPrefHeight(85);
        carte.setLayoutX(-9);
        carte.setLayoutY(27);
        carte.setRotate(-30);
        joueur.getChildren().add(carte);
    }

    //afficher les cartes au centre
    public void displayCardT(Group allJoueur, int i, int n) {

        AnchorPane carteP = (AnchorPane) ((Group) allJoueur.getChildren().get(5)).getChildren().get(0);
        Carte tmp;

        for (int j = i; j < n; j++) {
            tmp = mixDeck.get(0);
            mixDeck.remove(0);

            tmp.setLayoutX(0);
            tmp.setLayoutY(0);
            tmp.setRotate(0);
            tmp.setPrefHeight(85);
            tmp.setPrefWidth(54);

            m.setMainGagnante(tmp);
            ((AnchorPane) carteP.getChildren().get(j)).getChildren().add(tmp);
            this.getFinTour().clear();
        }
    }

    //affecte une valeur de main a chaque joueurs
    public void checkHand() {
        int sumHand;
        List<Carte> copyHand;
        //on parcourt la liste des joueurs
        for (int w = 0; w < this.getlJP().size(); w++) {
            //si des joueurs se sont couchés alors on saute le joueur
            if (this.getlFold().contains(w) || this.getlEndGame().contains(this.getlJP().get(w))) {
                continue;
            } else {
                //on copie les cartes du milieu
                copyHand = new ArrayList<Carte>(m.getMainGagnante());
                //on ajoute les 2 cartes du joueur
                copyHand.add(this.getlJP().get(w).getMainJoueur().get(0));
                copyHand.add(this.getlJP().get(w).getMainJoueur().get(1));


                /*On verifie le type de main. Chaque main a une valeur de depart, Quinte Flush Royal 900, Quinte Flush 800, ...
                 **On ajoute la somme des cartes aux valeurs de base pour prendre en compte la carte la plus haute
                 */
                List<Carte> lTrieeC = new ArrayList<Carte>();
                List<Carte> lTrieeN = new ArrayList<Carte>();
                List<Carte> lTrieeD = new ArrayList<Carte>();
                lTrieeN = m.trieCarteNbre(copyHand);
                lTrieeC = m.trieCarteNbreCouleur(copyHand);
                lTrieeD = m.deleteDouble(m.trieCarteNbreCouleur(copyHand));

                if (m.estQFR(lTrieeC, this.getlJP().get(w))) {
                    System.out.println("QFR");
                } else if (m.estQF(lTrieeC, this.getlJP().get(w))) {
                    System.out.println("QF");
                } else if (m.estCarre(lTrieeN, this.getlJP().get(w))) {
                    System.out.println("Carre");
                } else if (m.estFullHouse(lTrieeN, this.getlJP().get(w))) {
                    System.out.println("FH");
                } else if (m.estFlush(lTrieeC, this.getlJP().get(w))) {
                    System.out.println("F");
                } else if (m.estQuinte(lTrieeD, this.getlJP().get(w))) {
                    System.out.println("Q");
                } else if (m.estBrelan(lTrieeN, this.getlJP().get(w))) {
                    System.out.println("B");
                } else if (m.estDoublePaire(lTrieeN, this.getlJP().get(w))) {
                    System.out.println("DP");
                } else if (m.estPaire(lTrieeN, this.getlJP().get(w))) {
                    System.out.println("P");
                } else {
                    m.highCard(copyHand, this.getlJP().get(w));
                }
            }
        }
    }

    public void checkWinner() {

        int maxW1 = 0, maxW2 = 0;
        int w1 = 0, w2 = 0;


        for (int w = 0; w < this.getlJP().size(); w++) {
            if (this.getlFold().contains(w)) {
                continue;
            } else {
                if (this.getlJP().get(w).getTypeDeMain() == maxW1) {
                    maxW2 = this.getlJP().get(w).getTypeDeMain();
                    w2 = w;
                } else if (this.getlJP().get(w).getTypeDeMain() > maxW1) {
                    maxW1 = this.getlJP().get(w).getTypeDeMain();
                    maxW2 = 0;
                    w1 = w;
                }
            }
        }

        if (maxW2 != 0) {
            this.setlWinner(w1);
            this.setlWinner(w2);
        } else {
            this.setlWinner(w1);
        }
    }

    public void setGain(Group allJoueur) {
        double b = 0, p = 0;
        String tmpPot = null, tmpPot2 = null, tmpBank = null, tmpBank2 = null;
        Label bank = null, pot = null;
        Group plateau;
        plateau = (Group) allJoueur.getChildren().get(5);


        if (this.getlWinner().size() == 1) {

            joueur = (Group) allJoueur.getChildren().get(this.getlWinner().get(0));
            bank = (Label) joueur.getChildren().get(2);
            pot = (Label) plateau.getChildren().get(10);

            //////////////////////////////////////////
            //Pot
            tmpPot = pot.getText();
            tmpPot2 = tmpPot.substring(12, tmpPot.length() - 2);
            pot.setText(tmpPot.replaceAll(tmpPot2, Double.toString(0)));

            ///////////////////////////////////////////
            //Bank

            tmpBank = bank.getText();
            tmpBank2 = tmpBank.substring(0, tmpBank.length() - 2);
            b = Double.parseDouble(tmpPot2) + Double.parseDouble(tmpBank2);
            this.getlJP().get(this.getlWinner().get(0)).setWallet(b);
            bank.setText(tmpBank.replaceAll(tmpBank2, Double.toString(b)));

        } else {
            for (int i = 0; i < 2; i++) {

                joueur = (Group) allJoueur.getChildren().get(this.getlWinner().get(i));
                bank = (Label) joueur.getChildren().get(2);
                pot = (Label) plateau.getChildren().get(10);

                //////////////////////////////////////////
                //Pot
                tmpPot = pot.getText();
                tmpPot2 = tmpPot.substring(12, tmpPot.length() - 2);

                ///////////////////////////////////////////
                //Bank

                tmpBank = bank.getText();
                tmpBank2 = tmpBank.substring(0, tmpBank.length() - 2);
                b = (Double.parseDouble(tmpPot2) / 2) + Double.parseDouble(tmpBank2);

                this.getlJP().get(this.getlWinner().get(i)).setWallet(b);
                bank.setText(tmpBank.replaceAll(tmpBank2, Double.toString(b)));
            }
            pot.setText(tmpPot.replaceAll(tmpPot2, Double.toString(0)));
        }
    }

    public void displayPopupWinner() {
        Stage primaryStage = new Stage();
        VBox vb = new VBox();
        HBox hb = new HBox(), hb1 = new HBox();
        Label msg = new Label(), winner = new Label();

        Group root = new Group();
        try {
            root = FXMLLoader.load(this.getClass().getResource("PopupDonneur.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (this.getlWinner().size() == 1) {
            vb.setPrefHeight(200);
            vb.setPrefWidth(400);
            hb.setPrefHeight(200);

            HBox.setMargin(msg, new Insets(40, 0, 0, 20));
            HBox.setMargin(winner, new Insets(40, 0, 0, 20));

            msg.setText("Joueur remportant les gains");
            winner.setText(this.getlJP().get(this.getlWinner().get(0)).getPseudo());
            hb.getChildren().addAll(msg, winner);
            vb.getChildren().add(hb);
            root.getChildren().add(vb);
        } else {
            vb.setPrefHeight(200);
            vb.setPrefWidth(400);
            hb.setPrefHeight(100);
            hb.setPrefWidth(400);
            hb1.setPrefHeight(100);
            hb1.setPrefWidth(400);

            HBox.setMargin(msg, new Insets(40, 0, 0, 20));
            HBox.setMargin(winner, new Insets(40, 0, 0, 20));

            msg.setText("Joueur remportant les gains");
            winner.setText(this.getlJP().get(this.getlWinner().get(0)).getPseudo());
            hb.getChildren().addAll(msg, winner);
            vb.getChildren().add(hb);


            winner = new Label();
            winner.setText(this.getlJP().get(this.getlWinner().get(1)).getPseudo());
            msg = new Label();
            msg.setText("Joueur remportant les gains");
            hb1.getChildren().addAll(msg, winner);

            HBox.setMargin(msg, new Insets(40, 0, 0, 20));
            HBox.setMargin(winner, new Insets(40, 0, 0, 20));

            vb.getChildren().add(hb1);
            root.getChildren().add(vb);

        }

        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> primaryStage.close());
        delay.play();
    }

    public void clearTable(Group allJoueur) {
        PauseTransition delay = new PauseTransition(Duration.seconds(10));
        delay.setOnFinished(event -> clearTabletmp(allJoueur));
        delay.play();
    }


    public void clearTabletmp(Group allJoueur) {
        Group plateau = (Group) allJoueur.getChildren().get(allJoueur.getChildren().size() - 1);
        AnchorPane carteJ, carteP = (AnchorPane) plateau.getChildren().get(0);
        Label role = new Label(), name = new Label(), bank = new Label();
        for (int j = 0; j < this.getlJP().size(); j++) {
            if (!checkBankEndTurn(this.getlJP().get(j))) {
                this.setlEndGame(this.getlJP().get(j));
            }
        }

        for (int i = 0; i < this.getlJP().size(); i++) {
            if (this.getlFold().contains(i)) {
                joueur = (Group) allJoueur.getChildren().get(i);
                role = (Label) joueur.getChildren().get(1);
                role.setText("");
                continue;
            } else if (this.getlEndGame().contains(this.getlJP().get(i))) {
                joueur = (Group) allJoueur.getChildren().get(i);
                for (int j = 3; j < 5; j++) {
                    this.getlJP().get(i).getMainJoueur().clear();
                    this.getlJP().get(i).setTypeDeMain(0);
                    this.getlJP().get(i).getEtatJoueur().clear();
                    carteJ = (AnchorPane) joueur.getChildren().get(j);
                    name = (Label) joueur.getChildren().get(0);
                    role = (Label) joueur.getChildren().get(1);
                    bank = (Label) joueur.getChildren().get(2);
                    name.setText("");
                    role.setText("");
                    bank.setText("");
                    carteJ.getChildren().clear();
                }
            } else {
                joueur = (Group) allJoueur.getChildren().get(i);
                for (int j = 3; j < 5; j++) {
                    this.getlJP().get(i).getMainJoueur().clear();
                    this.getlJP().get(i).setTypeDeMain(0);
                    this.getlJP().get(i).getEtatJoueur().clear();
                    carteJ = (AnchorPane) joueur.getChildren().get(j);
                    role = (Label) joueur.getChildren().get(1);
                    role.setText("");
                    carteJ.getChildren().clear();
                }
            }
        }

        m.getMainGagnante().clear();
        carteP.getChildren().clear();
        deck.clear();
        mixDeck.clear();
        this.getlWinner().clear();
        this.getFinTour().clear();
        this.getlFold().clear();
        for (JoueurPoker jp : this.getlJP()) {
            jp.getEtatJoueur().clear();
        }
        this.checkWinnerGame();
        this.generateDeck();
        this.setButton(plateau);
        this.initTour(allJoueur);
        this.initJoueur();

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> this.setpbGB(allJoueur, this.getPb()));
        delay.play();
    }

    public void initTour(Group allJoueur) {
        int i = 0, k = 0;
        boolean find = false;
        Label role;
        Group joueur = new Group();
        System.out.println(this.getInitJoueur());
        this.initDonneur();
        System.out.println(this.getInitJoueur());
        this.initPb();
        System.out.println(this.getInitJoueur());
        this.initGb();
        System.out.println(this.getInitJoueur());

        System.out.println(this.getlJP());

        //on affiche les roles des joueurs
        while (i < lJP.size()) {
            System.out.println(i + "fdfdsfsdf");
            joueur = (Group) allJoueur.getChildren().get(i);
            if (lJP.get(i).getEtatJoueur().contains("Donneur")) {
                role = (Label) joueur.getChildren().get(1);
                role.setText("D");
                i++;
            } else if (lJP.get(i).getEtatJoueur().contains("PB")) {
                role = (Label) joueur.getChildren().get(1);
                role.setText("PB");
                i++;
            } else if (lJP.get(i).getEtatJoueur().contains("GB")) {
                role = (Label) joueur.getChildren().get(1);
                role.setText("GB");
                i++;
            } else {
                i++;
            }
        }

    }

    public void initJoueur() {
        //on cherche la grosse blinde pour permettre de commencer a gauche de celle ci le tour
        this.setIndexJoueur(0);
        while (this.getIndexJoueur() < this.getlJP().size()) {
            if (this.getlJP().get(this.getIndexJoueur()).getEtatJoueur().contains("GB")) {
                break;
            } else {
                this.setIndexJoueur(this.getIndexJoueur() + 1);
            }
        }
        //on garde en memoire la GB pour redemarrer a gauche de celle ci lors des differents tours.
        this.setInitJoueur(this.getIndexJoueur());
        this.setIndexJoueur(this.getIndexJoueur() + 1);

        //si on est sur le dernier jouer alors on revient au ddebut de la table
        if (this.getIndexJoueur() == this.getlJP().size()) {
            this.setIndexJoueur(0);
        }
    }

    //methode qui verifie l'argent du joueur pendant son tour.
    public boolean checkBankTurn(JoueurPoker joueur, double mise) {
        //si sa mise est superieure à sa bank, on le passe en tapis

        if (joueur.getWallet() <= mise) {
            return false;
        }
        //sinon il peut miser normalement
        return true;
    }

    //metthode qui verif l'argent du joueur a la fin du tour
    public boolean checkBankEndTurn(JoueurPoker joueur) {
        if (joueur.getWallet() == 0) {
            return false;
        }
        //sinon il peut rester
        return true;
    }

    public void checkWinnerGame() {

        int i = 0, index = 0;
        if (this.getlEndGame().size() == 4) {
            while (i < this.lJP.size()) {
                if (this.getlEndGame().contains(this.getlJP().get(i))) {
                    i++;
                } else {
                    break;
                }

                Stage primaryStage = (Stage) root.getScene().getWindow();
                WinnerController controller = new WinnerController();
                controller.setWinner(this.lJP.get(i).getPseudo());
                controller.initMenu(primaryStage);
            }
        }
    }


    //permet d'initialiser le donneur lors de changement de tour
    public void initDonneur() {
        int i = 0, tmp = this.getInitJoueur();
        tmp--;
        while (i < getlEndGame().size()) {

            if (tmp == 5) {
                tmp = 0;
            }
            if (this.getlEndGame().contains(this.getlJP().get(tmp))) {
                tmp++;
            } else {
                break;
            }
            i++;
        }

        if (tmp + 1 == 3) {
            lJP.get(2).setEtatJoueur("Donneur");
        } else if (tmp + 1 == 4) {
            lJP.get(3).setEtatJoueur("Donneur");
        } else if (tmp + 1 == 0) {
            lJP.get(4).setEtatJoueur("Donneur");
        } else if (tmp + 1 == 1) {
            lJP.get(0).setEtatJoueur("Donneur");
        } else if (tmp + 1 == 2) {
            lJP.get(1).setEtatJoueur("Donneur");
        }

    }

    //permet d'initialiser la petite blinde lors de changement de tour
    public void initPb() {
        int i = 0, tmp = this.getInitJoueur();
        while (i < getlEndGame().size()) {

            if (tmp == 5) {
                tmp = 0;
            }
            if (this.getlEndGame().contains(this.getlJP().get(tmp))) {
                tmp++;
            } else {
                break;
            }
        }

        if (tmp == 3) {
            lJP.get(3).setEtatJoueur("PB");
        } else if (tmp == 4) {
            lJP.get(4).setEtatJoueur("PB");
        } else if (tmp == 0) {
            lJP.get(0).setEtatJoueur("PB");
        } else if (tmp == 1) {
            lJP.get(1).setEtatJoueur("PB");
        } else if (tmp == 2) {
            lJP.get(2).setEtatJoueur("PB");
        }

    }

    //permet d'initialiser la grosse blinde lors de changement de tour
    public void initGb() {
        int i = 0, tmp = this.getInitJoueur();
        tmp++;
        while (i < getlEndGame().size()) {

            if (tmp == 5) {
                tmp = 0;
            }
            if (this.getlEndGame().contains(this.getlJP().get(tmp))) {
                tmp++;
            } else {
                break;
            }
        }
        if (tmp - 1 == 3) {
            lJP.get(4).setEtatJoueur("GB");
        } else if (tmp - 1 == 4) {
            lJP.get(0).setEtatJoueur("GB");
        } else if (tmp - 1 == 0) {
            lJP.get(1).setEtatJoueur("GB");
        } else if (tmp - 1 == 1) {
            lJP.get(2).setEtatJoueur("GB");
        } else if (tmp - 1 == 2) {
            lJP.get(3).setEtatJoueur("GB");
        }
    }
}