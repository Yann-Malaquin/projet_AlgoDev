package fr.projet.bataillenavale;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CaseBN extends Rectangle {
    private int x, y;
    private Bateau bateau = null;
    private boolean utilise = false;
    private GrilleBN grille;

    public CaseBN(int x, int y, GrilleBN grille) {
        super(32, 32);
        this.x = x;
        this.y = y;
        this.grille = grille;
    }

    public boolean tirer() {
        utilise = true;
        setFill(new ImagePattern(new Image("fr/projet/bataillenavale/icone_louper.png")));

        if (bateau != null) {
            bateau.touche();
            setStroke(Color.RED);
            setFill(new ImagePattern(new Image("fr/projet/bataillenavale/icone_toucher.png")));
            if (!bateau.estVivant()) {
                grille.setBateau((grille.getBateau())-1);
            }
            return true;
        }

        return false;
    }

    public boolean isUtilise() {
        return utilise;
    }

    public void setUtilise(boolean utilise) {
        this.utilise = utilise;
    }

    public void setBateau(Bateau bateau){
        this.bateau = bateau;
    }
    public Bateau getBateau(){
        return this.bateau;
    }


    public int get_X() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


    public int get_Y() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
