package fr.projet.poker;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class MainsGagnantes {

    private List<AnchorPane> mainGagnante;

    public MainsGagnantes() {
        mainGagnante = new ArrayList<AnchorPane>();
    }

    public void setMainGagnante(AnchorPane _carte) {
        mainGagnante.add(_carte);
    }

    public List<AnchorPane> getMainGagnante() {
        return mainGagnante;
    }


    public List<AnchorPane> rechercherCarte(List<AnchorPane> carteMains, String valeurCarte) {

        List<AnchorPane> trouve = new ArrayList<AnchorPane>();

        for (int i = 0; i < carteMains.size(); i++) {
            if (carteMains.get(i).getId().contains(valeurCarte)) {
                trouve.add(carteMains.get(i));
            }
        }
        return trouve;
    }

    public boolean estQFR(List<AnchorPane> carteMains) {

        List<AnchorPane> trouve = new ArrayList<AnchorPane>(); // permet de savoir si As il y a.
        int cpt = 1;
        int j;
        trouve = this.rechercherCarte(carteMains, "14");

        // s'il n'y a pas d'As alors il ne peut y avoir Quinte Flush Royale
        if (trouve.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < trouve.size(); i++) { // on parcourt en fonction des as.
                j = 0;
                // on cherche s'il y a un dix pour démarrer le comptage des cartes.
                while (!carteMains.get(j).getId().contains("10") && j < carteMains.size() - 1) {
                    j++;
                }

                System.out.println("As trouve" + trouve.toString());
                while (j < carteMains.size() - 1) { // on parcourt donc de j à 6

                    if ((((Group) trouve.get(i).getChildren().get(0)).getChildren().get(1).getId().compareTo(((Group) carteMains.get(j).getChildren().get(0)).getChildren().get(1).getId()) == 0)
                            && (((Group) trouve.get(i).getChildren().get(0)).getChildren().get(1).getId().compareTo(((Group) carteMains.get(j + 1).getChildren().get(0)).getChildren().get(1).getId()) == 0)
                            && ((Integer.parseInt(((Group) carteMains.get(j).getChildren().get(0)).getChildren().get(0).getId()) + 1) == Integer.parseInt(((Group) carteMains.get(j + 1).getChildren().get(0)).getChildren().get(0).getId()))) {
                        cpt++;
                        System.out.println("CompteurIf " + cpt);

                        // dès que l'on a 4 cartes alors on sait qu'il y a Quinte Flush Royale. 4
                        if (cpt == 4) {
                            // cartes car nous savons qu'il y a déjà un as.
                            return true;
                        }
                        //sinon  si on est supérieur a 2 soit 3 cartes et que l'on a pas encore 3 de correctes cartes alors on ne pourra plus avoir de Quinte Flush Royale ou si la couleur est incorrecte, alors on ne poursuit pas.
                    } else if ((j > 2 && cpt < 3) || ((Label) ((Group) carteMains.get(j).getChildren().get(0)).getChildren().get(1)).getId().compareTo(((Label) ((Group) trouve.get(i).getChildren().get(0)).getChildren().get(1)).getId()) != 0) {
                        break;
                        // sinon on reset le compteur.
                    } else {
                        cpt = 1;
                    }

                System.out.println("J while = " + j);
                j++;
            }
        }
    }
        return false;
}

    public boolean estQF(List<AnchorPane> carteMains) {
        int cpt = 1;
        for (int i = 0; i < carteMains.size() - 1; i++) // on parcourt le paquet de cartes.
        {
            if (((Integer.parseInt(((Group) carteMains.get(i).getChildren().get(0)).getChildren().get(0).getId()) + 1) == Integer.parseInt(((Group) carteMains.get(i + 1).getChildren().get(0)).getChildren().get(0).getId()))
                    && (((Group) carteMains.get(i).getChildren().get(0)).getChildren().get(1).getId().compareTo(((Group) carteMains.get(i + 1).getChildren().get(0)).getChildren().get(1).getId()) == 0)) // si on a 2
            // cartes de la même couleur qui se suivent on incrémente cpt
            {
                cpt++;
                // si on a les 5 cartes pour une main alors on return true
                if (cpt == 5)
                {
                    return true;
                }
                // test qui coupe si on ne peut pas avoir de Quinte Flush
            } else if (i > 2 && cpt < 3)
            {
                break;
            } else {
                cpt = 1;
            }
        }
        return false;
    }

    public boolean estCarre(List<AnchorPane> carteMains) {
        int cpt = 1;
    // on parcourt la liste des cartes 1 par 1 pour tester.

        for (int i = 0; i < carteMains.size() - 1; i++) {
            for (int j = 0; j < carteMains.size() - 1 ; j++) {// on parcourt la liste pour tester avec les autres valeurs.
                if (Integer.parseInt(((Group) carteMains.get(i).getChildren().get(0)).getChildren().get(0).getId()) == Integer.parseInt(((Group) carteMains.get(j).getChildren().get(0)).getChildren().get(0).getId())) {// si on a la même valeur
                    cpt++;// on incrémente le compteur
                    if (cpt == 4)// si on a les 4 cartes on retourne true
                    {
                        return true;
                    }
                } else if (i > 4 && cpt < 2)// si on a déjà parcouru 5 cartes et que le compteur est = a 1 alors on ne
                // pourra plus former de carré, donc on quitte
                {
                    break;
                } else {
                    cpt = 1;// sinon on reset le compteur
                }
            }
        }
        return false;
    }

    public boolean estFullHouse(List<AnchorPane> carteMains) {
        //on copie les cartes puisque l'on va les supprimer au fur et a mesure
        List<AnchorPane> copycarteMains = new ArrayList<AnchorPane>(carteMains);
        //on recupere l'indice des cartes que l'on ajoute dans une liste
        List<Integer> indiceCartes = new ArrayList<Integer>();
        int cpt = 1, i = 0;

        while (i < copycarteMains.size() - 1) {
            //si la la figure a l'indice i est le meme a l'indice i+1 alors on ajoute l'indice a la liste et on incremente le compteur
                if (Integer.parseInt(((Group) copycarteMains.get(i).getChildren().get(0)).getChildren().get(0).getId()) == Integer.parseInt(((Group) copycarteMains.get(i + 1).getChildren().get(0)).getChildren().get(0).getId())) {
                indiceCartes.add(i);
                cpt++;

                //si l'on trouve nos 3 cartes alors on supprime les cartes de la liste copycarteMains, on affecte le fait que l'on a le brelan et on break pour sortir du while
                if (cpt == 3) {
                    copycarteMains.remove(indiceCartes.get(0));
                    copycarteMains.remove(indiceCartes.get(0));
                    copycarteMains.remove(indiceCartes.get(0));
                    break;
                }
                i++;

                // condition permettant de ne pas tester tout le paquet et de pouvoir sortir rapidement
            } else if (cpt < 3 && i > 4) {
                    return false;
            } else {
                    //sinon on vide la liste des indices, on reset le compteur
                indiceCartes.clear();
                cpt = 1;
                i++;
            }
        }

        cpt = 1;
        i = 0;
        indiceCartes.clear();

        while (i < copycarteMains.size()) {
            //si la la figure a l'indice i est le meme a l'indice i+1 alors on ajoute l'indice a la liste et on incremente le compteur
            if (Integer.parseInt(((Group) copycarteMains.get(i).getChildren().get(0)).getChildren().get(0).getId()) == Integer.parseInt(((Group) copycarteMains.get(i + 1).getChildren().get(0)).getChildren().get(0).getId())) {
                indiceCartes.add(i);
                cpt++;
                //si l'on trouve nos 2 cartes alors on supprime les cartes de la liste copycarteMains, on affecte le fait que l'on a le brelan et on break pour sortir du while
                if (cpt == 2) {
                    copycarteMains.remove(indiceCartes.get(0));
                    copycarteMains.remove(indiceCartes.get(0));
                    break;
                }
                i++;
                // condition permettant de ne pas tester tout le paquet et de pouvoir sortir rapidement
            } else if (cpt == 1 && i >= 3) {
                return false;
            } else {
                //sinon on vide la liste des indices, on reset le compteur
                indiceCartes.clear();
                cpt = 1;
                i++;
            }
        }
            return true;

    }
/*
    public boolean estFlush(List<Carte> carteMains) {
        int cpt = 1;

        for (int i = 0; i < carteMains.size() - 1; i++) {

            if ((i + 1) == 7 && cpt == 3
                    && (carteMains.get(6).getCouleur().compareTo(carteMains.get(5).getCouleur()) == 0)) {

                return true;
            }
            if ((carteMains.get(i).getCouleur().compareTo(carteMains.get(i + 1).getCouleur())) == 0) {
                cpt++;
                if ((carteMains.get(i + 1).getCouleur().compareTo(carteMains.get(i + 2).getCouleur()) != 0)) {
                    cpt++;
                }
                System.out.println("cpt =" + cpt);
                if (cpt == 4) {
                    return true;
                }
            } else if (i > 3 && cpt == 0) {
                break;
            } else {
                cpt = 0;
            }
        }
        return false;
    }
/*
    public boolean estQuinte(List<Carte> carteMains) {
        int cpt = 1;

        for (int i = 0; i < carteMains.size(); i++) {
            if (i + 1 >= carteMains.size() && cpt <= 4) {
                return false;
            }
            if (carteMains.get(i).getValeur() + 1 == carteMains.get(i + 1).getValeur()) {
                cpt++;
                System.out.println("cpt= " + cpt);
                if (cpt == 5) {
                    return true;
                } else if (i > 3 && cpt < 2) {
                    break;
                }
            } else {
                cpt = 1;
            }
        }

        return false;
    }
/*
    public boolean estBrelan(List<Carte> carteMains) {
        List<Carte> copycarteMains = new ArrayList<Carte>(carteMains);

        int cpt = 1;

        for (int i = 0; i < copycarteMains.size(); i++) {
            if (i + 1 >= carteMains.size() && cpt <= 2) {
                return false;
            }
            if (carteMains.get(i).getValeur() == carteMains.get(i + 1).getValeur()) {
                cpt++;
                System.out.println("cpt = " + cpt);
                if (cpt == 3) {
                    return true;
                }

            } else if (cpt == 1 && i > 5) {
                break;
            } else {
                cpt = 1;
            }
        }
        return false;
    }
/*
    public boolean estDoublePaire(List<Carte> carteMains) {

        List<Carte> copyCartesMain = new ArrayList<Carte>(carteMains);
        List<Integer> indiceCarte = new ArrayList<Integer>();
        int i, cpt = 1;
        boolean pair1 = false, pair2 = false;

        for (i = 0; i < copyCartesMain.size(); i++) {
            if (i + 1 >= copyCartesMain.size() && cpt == 1) {
                return false;
            }
            if (copyCartesMain.get(i).getValeur() == copyCartesMain.get(i + 1).getValeur()) {
                cpt++;
                indiceCarte.add(i);
                indiceCarte.add(i + 1);

                if (cpt == 2) {
                    pair1 = true;
                    copyCartesMain.remove(indiceCarte.get(0));
                    copyCartesMain.remove(indiceCarte.get(1));
                    break;
                }

            } else if (cpt == 1 && i > 5) {
                break;
            } else {
                cpt = 1;
            }
        }

        indiceCarte.clear();

        for (i = 0; i < copyCartesMain.size(); i++) {
            if (i + 1 >= carteMains.size() && cpt == 1) {
                return false;
            }
            if (carteMains.get(i).getValeur() == carteMains.get(i + 1).getValeur()) {
                cpt++;
                indiceCarte.add(i);
                indiceCarte.add(i + 1);

                if (cpt == 2) {
                    pair2 = true;
                    copyCartesMain.remove(indiceCarte.get(0));
                    copyCartesMain.remove(indiceCarte.get(1));
                    break;
                } else if (cpt == 1 && i > 5) {
                    break;
                } else {
                    cpt = 1;
                }
            }
        }

        if (pair1 && pair2) {
            return true;
        }

        return false;
    }
/*
    public boolean estPaire(List<Carte> carteMains) {
        int cpt = 1;
        for (int i = 0; i < carteMains.size(); i++) {
            if (i + 1 >= carteMains.size() && cpt == 1) {
                return false;
            }
            if (carteMains.get(i).getValeur() == carteMains.get(i + 1).getValeur()) {
                cpt++;
                if (cpt == 2) {
                    return true;
                } else if (cpt == 1 && i > 5) {
                    break;
                } else {
                    cpt = 1;
                }
            }
        }
        return false;
    }


 /*
    public List<Carte> trieCarteNbreCouleur (List<Carte> cartesMilieu)
    {
        List<Carte> copyCartesMilieu= new ArrayList<Carte>(cartesMilieu);
        List<Carte> cartesMilieuTriee  = new ArrayList<Carte>();

        Carte min = null;
        int i = 0;

        while (copyCartesMilieu.size() > 0)
        {
            min = copyCartesMilieu.get(0);

            for(int j = 0 ; j< copyCartesMilieu.size(); j++)
            {

                if((min.getCouleur().compareTo(copyCartesMilieu.get(j).getCouleur()) >= 0) && (min.getValeur() > copyCartesMilieu.get(j).getValeur()))
                {
                    min = copyCartesMilieu.get(j);
                }
            }
            cartesMilieuTriee.add(min);
            copyCartesMilieu.remove(min);
        }
        return cartesMilieuTriee;
    }

/*
    public List<Carte> trieCarteNbre (List<Carte> cartesMilieu)
    {
        List<Carte> copyCartesMilieu= new ArrayList<Carte>(cartesMilieu);
        List<Carte> cartesMilieuTriee  = new ArrayList<Carte>();

        Carte min = null;
        int i = 0;

        while (copyCartesMilieu.size() > 0)
        {
            min = copyCartesMilieu.get(0);

            for(int j = 0 ; j< copyCartesMilieu.size(); j++)
            {

                if(min.getValeur() > copyCartesMilieu.get(j).getValeur())
                {
                    min = copyCartesMilieu.get(j);
                }
            }
            cartesMilieuTriee.add(min);
            copyCartesMilieu.remove(min);
        }

        return cartesMilieuTriee;
    }

 */
}