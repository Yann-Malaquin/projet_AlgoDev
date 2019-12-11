package fr.projet.bataillenavale;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;

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
        InputStream input = this.getClass().getResourceAsStream("/resources/bataillenavale/icone_louper.png");
        setFill(new ImagePattern(new Image(input)));

        if (bateau != null) {
            input = this.getClass().getResourceAsStream("/resources/bataillenavale/icone_toucher.png");
            bateau.touche();
            setStroke(Color.RED);
            setFill(new ImagePattern(new Image(input)));
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
