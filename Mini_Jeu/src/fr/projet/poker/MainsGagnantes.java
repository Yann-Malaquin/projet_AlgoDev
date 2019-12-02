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
                if (cpt == 5) {
                    return true;
                }
                // test qui coupe si on ne peut pas avoir de Quinte Flush
            } else if (i > 2 && cpt < 3) {
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
            for (int j = 0; j < carteMains.size() - 1; j++) {// on parcourt la liste pour tester avec les autres valeurs.
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

    public boolean estFlush(List<AnchorPane> carteMains) {
        int cpt = 1;

        for (int i = 0; i < carteMains.size(); i++) {
            //si on est sur la derniere carte et que celle-ci est de la meme couleur que la precedente alors on a notre flush a condition que le compteur soit à 3
            if ((i + 1) == 7 && cpt == 3 && ((((Group) carteMains.get(6).getChildren().get(0)).getChildren().get(1).getId().compareTo(((Group) carteMains.get(5).getChildren().get(0)).getChildren().get(1).getId()) == 0))) {
                return true;
            }
            //si la carte actuelle est de meme couleur que la suivante on incremente le compteur
            if ((((Group) carteMains.get(i).getChildren().get(0)).getChildren().get(1).getId().compareTo(((Group) carteMains.get(i + 1).getChildren().get(0)).getChildren().get(1).getId()) == 0)) {
                cpt++;
                //si on a les 4 cartes alors on return true
                if (cpt == 5) {
                    return true;
                }
                //test de sortie de boucle pour eviter de  tout parcourir
            } else if (i > 3 && cpt == 1) {
                break;
            } else {
                cpt = 1;
            }
        }
        return false;
    }

    public boolean estQuinte(List<AnchorPane> carteMains) {
        int cpt = 1;

        for (int i = 0; i < carteMains.size(); i++) {
            //si on est a la derniere carte et que l'on a pas notre suite on return false
            if (i + 1 >= carteMains.size() && cpt <= 4) {
                return false;
            }
            //on teste si l'on a la suite
            if ((Integer.parseInt(((Group) carteMains.get(i).getChildren().get(0)).getChildren().get(0).getId()) + 1) == Integer.parseInt(((Group) carteMains.get(i + 1).getChildren().get(0)).getChildren().get(0).getId())) {
                cpt++;
                //si nous avons les 5 cartes alors on return true
                if (cpt == 5) {
                    return true;
                    //condition de sortie pour eviter de tout parcourir
                } else if (i > 3 && cpt < 2) {
                    break;
                }
                //sinon on remet a 1 le compteur
            } else {
                cpt = 1;
            }
        }
        return false;
    }

    public boolean estBrelan(List<AnchorPane> carteMains) {

        List<AnchorPane> copycarteMains = new ArrayList<AnchorPane>(carteMains);

        int cpt = 1;

        for (int i = 0; i < copycarteMains.size(); i++) {
            //si la carte suivante est égale a la carte carte i alors on incremente le compteur
            if (Integer.parseInt(((Group) carteMains.get(i).getChildren().get(0)).getChildren().get(0).getId()) == Integer.parseInt(((Group) carteMains.get(i + 1).getChildren().get(0)).getChildren().get(0).getId())) {
                cpt++;
                //si nous avons les 3 cartes alors on retourne true
                if (cpt == 3) {
                    return true;
                }
                //condition de sortie pour eviter de parcourir toute la liste
            } else if (cpt <= 2 && i > 5) {
                break;
                //sinon on reset le compteur
            } else {
                cpt = 1;
            }
        }
        return false;
    }

    public boolean estDoublePaire(List<AnchorPane> carteMains) {
        //on copie la liste pour eviter de perdre l'originale
        List<AnchorPane> copyCartesMain = new ArrayList<AnchorPane>(carteMains);
        List<Integer> indiceCarte = new ArrayList<Integer>();
        int i;

        //1ere paire
        for (i = 0; i < copyCartesMain.size(); i++) {
            //on teste si la carte suivante est de la meme valeur
            if (Integer.parseInt(((Group) copyCartesMain.get(i).getChildren().get(0)).getChildren().get(0).getId()) == Integer.parseInt(((Group) copyCartesMain.get(i + 1).getChildren().get(0)).getChildren().get(0).getId())) {
                //on ajoute les 2 cartes a la liste des indices
                indiceCarte.add(i);
                indiceCarte.add(i + 1);

                //on supprime les cartes trouvees de la liste des cartes
                copyCartesMain.remove(indiceCarte.get(0));
                copyCartesMain.remove(indiceCarte.get(0));
                break;
                //condition d'arret
            } else if (i > 5) {
                return false;
            }
        }

        //on vide la liste des indices
        indiceCarte.clear();

        //2e paire
        for (i = 0; i < copyCartesMain.size(); i++) {
            if ((Integer.parseInt(((Group) copyCartesMain.get(i).getChildren().get(0)).getChildren().get(0).getId()) == Integer.parseInt(((Group) copyCartesMain.get(i + 1).getChildren().get(0)).getChildren().get(0).getId()))) {
                indiceCarte.add(i);
                indiceCarte.add(i + 1);

                copyCartesMain.remove(indiceCarte.get(0));
                copyCartesMain.remove(indiceCarte.get(0));
                break;
            } else if (i > 5) {
                return false;
            }
        }


        return true;
    }

    public boolean estPaire(List<AnchorPane> carteMains) {
        int cpt = 1;
        for (int i = 0; i < carteMains.size(); i++) {
            //si la carte suivante est egale a la carte i alors on retoure true, on a trouve notre paire.
            if ((Integer.parseInt(((Group) carteMains.get(i).getChildren().get(0)).getChildren().get(0).getId()) == Integer.parseInt(((Group) carteMains.get(i + 1).getChildren().get(0)).getChildren().get(0).getId()))) {
                return true;
                //condition de sortie.
            } else if (i >= 5) {
                break;
            }
        }
        return false;
    }


    public List<AnchorPane> trieCarteNbreCouleur(List<AnchorPane> cartesMilieu) {
        List<AnchorPane> copyCartesMilieu = new ArrayList<AnchorPane>(cartesMilieu);
        List<AnchorPane> cartesMilieuTriee = new ArrayList<AnchorPane>();

        List<AnchorPane> ca = new ArrayList<AnchorPane>();
        List<AnchorPane> co = new ArrayList<AnchorPane>();
        List<AnchorPane> tr = new ArrayList<AnchorPane>();
        List<AnchorPane> pi = new ArrayList<AnchorPane>();

        AnchorPane min = null;

        for (int j = 0; j < copyCartesMilieu.size(); j++) {
            if (((Group) copyCartesMilieu.get(j).getChildren().get(0)).getChildren().get(1).getId().compareTo("Pi") == 0) {
                pi.add(copyCartesMilieu.get(j));
            } else if (((Group) copyCartesMilieu.get(j).getChildren().get(0)).getChildren().get(1).getId().compareTo("Ca") == 0) {
                ca.add(copyCartesMilieu.get(j));
            }
            else if (((Group) copyCartesMilieu.get(j).getChildren().get(0)).getChildren().get(1).getId().compareTo("Co") == 0) {
                co.add(copyCartesMilieu.get(j));
            } else {
                tr.add(copyCartesMilieu.get(j));
            }
        }

        return cartesMilieuTriee;
    }

    public List<AnchorPane> trieCarteNbre(List<AnchorPane> cartesMilieu) {
        List<AnchorPane> copyCartesMilieu = new ArrayList<AnchorPane>(cartesMilieu);
        List<AnchorPane> cartesMilieuTriee = new ArrayList<AnchorPane>();

        AnchorPane min = null;
        int i = 0;

        while (copyCartesMilieu.size() > 0) {
            min = copyCartesMilieu.get(0);

            for (int j = 0; j < copyCartesMilieu.size(); j++) {

                if (Integer.parseInt(((Group) min.getChildren().get(0)).getChildren().get(0).getId()) > (Integer.parseInt(((Group) copyCartesMilieu.get(j).getChildren().get(0)).getChildren().get(0).getId()))) {
                    min = copyCartesMilieu.get(j);
                }
            }
            cartesMilieuTriee.add(min);
            copyCartesMilieu.remove(min);
        }

        return cartesMilieuTriee;
    }

}