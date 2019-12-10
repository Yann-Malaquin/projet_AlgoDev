package fr.projet.poker;

import java.util.ArrayList;
import java.util.List;

public class MainsGagnantes {

    private List<Carte> mainGagnante;
    public List<Carte> sumHand;

    public MainsGagnantes() {
        mainGagnante = new ArrayList<Carte>();
        sumHand = new ArrayList<Carte>();
    }

    public void setMainGagnante(Carte _carte) {
        mainGagnante.add(_carte);
    }

    public List<Carte> getMainGagnante() {
        return mainGagnante;
    }

    public void setSumHand(Carte _sumHand) {
        this.sumHand.add(_sumHand);
    }

    public List<Carte> getSumHand() {
        return this.sumHand;
    }

    public List<Carte> deleteDouble(List<Carte> deck) {
        int i = 0;
        while (i < deck.size() - 1) {
            if (deck.get(i).getValeur() == deck.get(i + 1).getValeur()) {
                deck.remove(i);
                i = i;
            } else {
                i++;
            }
        }
        return deck;
    }

    //methode qui permet de rechercher une carte precise.
    List<Carte> rechercherCarte(List<Carte> paquetCartes, int valeurCarte) {

        List<Carte> trouve = new ArrayList<Carte>();

        for (int i = 0; i < paquetCartes.size(); i++) {
            if (paquetCartes.get(i).getValeur() == 14) {
                trouve.add(paquetCartes.get(i));
            }
        }

        return trouve;
    }

    //methode qui renvoie vraie si on a une Quinte Flush Royal
    public boolean estQFR(List<Carte> carteMains, JoueurPoker jp) {

        // permet de savoir si As il y a.
        List<Carte> trouve = new ArrayList<Carte>();
        int cpt = 1;
        int j = 0;
        trouve = this.rechercherCarte(carteMains, 14);

        // s'il n'y a pas d'As alors il ne peut y avoir Quinte Flush Royale
        if (trouve.isEmpty()) {
            return false;
        } else {
            // on parcourt en fonction des as.
            for (int i = 0; i < trouve.size(); i++) {
                j = 0;
                //on cherche s'il y a un dix
                while (carteMains.get(j).getValeur() != 10 && j < carteMains.size() - 1) {
                    // pour démarrer le comptage des cartes.
                    j++;
                }
                // on parcourt donc de j à 6
                while (j < carteMains.size() - 1) {
                    // si on est supérieur a 2 soit 3 cartes et que l'on a pas encore 3 de correctes cartes alors on ne pourra plus avoir de Quinte Flush Royale ou si la couleur est incorrecte, alors on ne poursuit pas.
                    if ((j > 2 && cpt < 2) || (carteMains.get(j).getCouleur().compareTo(trouve.get(i).getCouleur()) != 0)) {
                        break;
                    } else {
                        if ((trouve.get(i).getCouleur().compareTo(carteMains.get(j).getCouleur()) == 0)
                                && (trouve.get(i).getCouleur().compareTo(carteMains.get(j + 1).getCouleur()) == 0)
                                && (carteMains.get(j).getValeur() + 1 == carteMains.get(j + 1).getValeur())) {
                            cpt++;
                            // dès que l'on a 4 cartes alors on sait qu'il y a Quinte Flush Royale. 4 cartes car nous savons qu'il y a déjà un as et on calcule la somme de la main pour pouvoir tester ensuite
                            if (cpt == 4) {
                                jp.setTypeDeMain(900);
                                return true;
                            }
                            // sinon on reset le compteur.
                        } else {
                            cpt = 1;
                        }
                    }
                    j++;
                }
            }
        }
        return false;
    }

    //methode qui renvoie vraie si on a une Quinte Flush
    public boolean estQF(List<Carte> carteMains, JoueurPoker jp) {
        int cpt = 1, _sumHand = 0;
        //on parcourt le paquet de cartes.
        for (int i = 0; i < carteMains.size(); i++) {
            if (i + 1 == carteMains.size()) {
                return false;
            } else {
                if (carteMains.get(i).getValeur() + 1 == carteMains.get(i + 1).getValeur()
                        && (carteMains.get(i).getCouleur().compareTo(carteMains.get(i + 1).getCouleur())) == 0)
                //si on a 2 cartes de la même couleurs alors on incremente le compteur
                {
                    cpt++;
                    this.setSumHand(carteMains.get(i));
                    // si on a les 5 cartes pour une main alors on return true et on calcule la somme de la main pour pouvoir tester ensuite
                    if (cpt == 5) {
                        this.setSumHand(carteMains.get(i + 1));
                        for (Carte _c : this.sumHand) {
                            _sumHand += _c.getValeur();
                        }

                        jp.setTypeDeMain(800 + _sumHand);
                        this.getSumHand().clear();
                        return true;
                    }
                    // test qui coupe si on ne peut pas avoir de Quinte Flush
                } else if (i > 2 && cpt < 3) {
                    this.getSumHand().clear();
                    break;
                } else {
                    this.getSumHand().clear();
                    cpt = 1;
                }
            }
        }
        return false;
    }

    //methode qui retourne vraie si on a un carre
    public boolean estCarre(List<Carte> carteMains, JoueurPoker jp) {
        int cpt = 1;
        // on parcourt la liste pour tester avec les autres valeurs.
        for (int j = 0; j < carteMains.size(); j++) {
            if (j + 1 == carteMains.size()) {
                return false;
            } else {
                if (carteMains.get(j).getValeur() == carteMains.get(j + 1).getValeur()) {
                    // si on a la même valeur on incrémente le compteur
                    cpt++;
                    // si on a les 4 cartes on retourne true et on calcule la somme de la main pour pouvoir tester ensuite
                    if (cpt == 4) {
                        jp.setTypeDeMain(700 + (4 * carteMains.get(j).getValeur()));
                        return true;
                    }
                }
                // si on a déjà parcouru 5 cartes et que le compteur est = a 1 alors on ne pourra plus former de carré, donc on quitte
                else if (j > 4 && cpt < 2) {
                    break;
                } else {
                    // sinon on reset le compteur
                    cpt = 1;
                }
            }
        }

        return false;
    }

    //methode qui retourne vraie si l'on a un FullHouse
    public boolean estFullHouse(List<Carte> carteMains, JoueurPoker jp) {
        List<Carte> copycarteMains = new ArrayList<Carte>(carteMains);
        List<Integer> indiceCartes = new ArrayList<Integer>();
        int cpt = 1, i = 0, _sumHand = 0;

        while (i < copycarteMains.size() - 1) {
            //on cherche si l'on  3 cartes de la meme valeur et on calcule la somme uniquement du brelan  pour pouvoir tester ensuite
            if (copycarteMains.get(i).getValeur() == copycarteMains.get(i + 1).getValeur()) {
                indiceCartes.add(i);
                cpt++;

                this.setSumHand(copycarteMains.get(i));

                if (cpt == 3) {
                    indiceCartes.add(i + 1);
                    this.setSumHand(copycarteMains.get(i + 1));
                    copycarteMains.remove(indiceCartes.get(0));
                    indiceCartes.remove(0);
                    copycarteMains.remove(indiceCartes.get(0) - 1);
                    indiceCartes.remove(0);
                    copycarteMains.remove(indiceCartes.get(0) - 2);


                    for (Carte _c : this.getSumHand()) {
                        _sumHand += _c.getValeur();
                    }

                    jp.setTypeDeMain(600 + _sumHand);
                    this.getSumHand().clear();
                    break;
                }
                i++;
            } else if (cpt <= 2 && i >= 5) {
                this.getSumHand().clear();
                return false;
            } else {
                this.getSumHand().clear();
                cpt = 1;
                i++;
            }
        }

        if (cpt <= 2 && i >= 5) {
            this.getSumHand().clear();
            return false;
        }
        i = 0;
        indiceCartes.clear();
        cpt = 1;

        //on cherche si l'on a une paire
        while (i < copycarteMains.size() - 1) {
            if (copycarteMains.get(i).getValeur() == copycarteMains.get(i + 1).getValeur()) {
                return true;
            } else if (cpt <= 2 && i == 4) {
                return false;
            } else {
                i++;
            }
        }
        return false;
    }


    //methode qui retourne vraie si l'on a une flush
    public boolean estFlush(List<Carte> carteMains, JoueurPoker jp) {
        int cpt = 1, _sumHand = 0;


        for (int i = 0; i < carteMains.size(); i++) {
            if (i + 1 == carteMains.size()) {
                return false;
            } else {
                //si les cartes se suivent alors on incremente le compteur.
                if ((carteMains.get(i).getCouleur().compareTo(carteMains.get(i + 1).getCouleur())) == 0) {
                    cpt++;
                    this.setSumHand(carteMains.get(i));

                    //si l'on est sur l'avant derniere carte et que la derniere est differente on incremente le compteur
                    if (cpt == 5) {
                        this.setSumHand(carteMains.get(i + 1));

                        for (Carte _c : this.sumHand) {
                            _sumHand += _c.getValeur();
                        }

                        jp.setTypeDeMain(500 + _sumHand);
                        this.getSumHand().clear();
                        return true;
                    }

                } else if (i > 3 && cpt == 1) {
                    this.getSumHand().clear();
                    break;
                } else {
                    this.getSumHand().clear();
                    cpt = 1;
                }
            }
        }
        return false;
    }

    //methode qui renvoie vraie si l'on a une Quinte
    public boolean estQuinte(List<Carte> carteMains, JoueurPoker jp) {
        int cpt = 1, _sumHand = 0;

        //si l 'est sur la derniere et que le compteur n'est pas a 5 alors on quitte
        for (int i = 0; i < carteMains.size(); i++) {
            if (i + 1 == carteMains.size()) {
                return false;
            }
            //si les cartes se suivent alors on incremente le compteur
            if (carteMains.get(i).getValeur() + 1 == carteMains.get(i + 1).getValeur()) {
                cpt++;
                this.setSumHand(carteMains.get(i));

                //si l'on est sur l'avant derniere carte et que la derniere est differente on incremente le compteur
                if (cpt == 5) {
                    this.setSumHand(carteMains.get(i + 1));

                    for (Carte _c : this.sumHand) {
                        _sumHand += _c.getValeur();
                    }

                    jp.setTypeDeMain(400 + _sumHand);
                    this.getSumHand().clear();
                    return true;
                }
            } else if (i > 3 && cpt < 2) {
                this.getSumHand().clear();
                break;
            } else {
                this.getSumHand().clear();
                cpt = 1;
            }
        }
        return false;
    }

    //methode qui retourne vraie si l'on a un Brelan
    public boolean estBrelan(List<Carte> carteMains, JoueurPoker jp) {
        List<Carte> copycarteMains = new ArrayList<Carte>(carteMains);
        int cpt = 1, _sumHand = 0;

        for (int i = 0; i < copycarteMains.size(); i++) {
            //condition de sortie si l'on est sur la derniere carte
            if (i + 1 == carteMains.size()) {
                return false;
            }
            //si les cartes se suivent ont la meme valeur, alors on incremente le compteur
            if (carteMains.get(i).getValeur() == carteMains.get(i + 1).getValeur()) {
                cpt++;
                this.setSumHand(carteMains.get(i));

                //si l'on est sur l'avant derniere carte et que la derniere est differente on incremente le compteur
                if (cpt == 3) {
                    this.setSumHand(carteMains.get(i + 1));

                    for (Carte _c : this.sumHand) {
                        _sumHand += _c.getValeur();
                    }

                    jp.setTypeDeMain(300 + _sumHand);
                    this.getSumHand().clear();
                    return true;
                }
            } else if (cpt == 1 && i > 5) {
                this.getSumHand().clear();
                break;
            } else {
                this.getSumHand().clear();
                cpt = 1;
            }
        }
        return false;
    }

    //methode qui retourne vraie si l'on a une double paire.
    public boolean estDoublePaire(List<Carte> deck, JoueurPoker jp) {
        List<Integer> stockPaire = new ArrayList<Integer>();

        for (int i = 0; i < deck.size() - 1; i++) {
            if (deck.get(i).getValeur() == deck.get(i + 1).getValeur()) {
                stockPaire.add(deck.get(i).getValeur());
            }
        }

        if (stockPaire.size() >= 2) {
            jp.setTypeDeMain(200 + stockPaire.get(stockPaire.size() - 1) * 2);
            return true;
        }
        return false;
    }


    //methode qui retourne vraie si l'on a une paire.
    public boolean estPaire(List<Carte> carteMains, JoueurPoker jp) {
        int _sumHand = 0;
        for (int i = 0; i < carteMains.size(); i++) {
            if (i + 1 == carteMains.size()) {
                return false;
            }
            if (carteMains.get(i).getValeur() == carteMains.get(i + 1).getValeur()) {

                this.setSumHand(carteMains.get(i));
                this.setSumHand(carteMains.get(i + 1));
                for (Carte _c : this.sumHand) {
                    _sumHand += _c.getValeur();
                }
                jp.setTypeDeMain(100 + _sumHand);
                this.getSumHand().clear();
                break;
            } else if (i > 5) {
                this.getSumHand().clear();
                return false;
            }
        }
        return true;
    }

    public void highCard(List<Carte> carteMains, JoueurPoker jp) {
        int _sumHand = 0;
        for (Carte _c : carteMains) {
            _sumHand += _c.getValeur();
        }
        jp.setTypeDeMain(_sumHand);
        this.getSumHand().clear();
    }

    //methode qui trie par couleur et par figure
    public List<Carte> trieCarteNbreCouleur(List<Carte> cartesMilieu) {
        List<Carte> copyCartesMilieu = new ArrayList<Carte>(cartesMilieu);
        List<Carte> cartesMilieuTriee = new ArrayList<Carte>(), tmpCarreau = new ArrayList<Carte>(), tmpCoeur = new ArrayList<Carte>(), tmpPique = new ArrayList<Carte>(), tmpTrefle = new ArrayList<Carte>();

        List<Carte> carreau = new ArrayList<Carte>();
        List<Carte> coeur = new ArrayList<Carte>();
        List<Carte> trefle = new ArrayList<Carte>();
        List<Carte> pique = new ArrayList<Carte>();


        int i = 0;

        for (Carte _c : cartesMilieu) {
            if (_c.getCouleur().compareTo("carreau") == 0) {
                carreau.add(_c);
            } else if (_c.getCouleur().compareTo("coeur") == 0) {
                coeur.add(_c);
            } else if (_c.getCouleur().compareTo("trefle") == 0) {
                trefle.add(_c);
            } else {
                pique.add(_c);
            }
        }

        tmpCarreau = trieCarteNbre(carreau);
        tmpCoeur = trieCarteNbre(coeur);
        tmpPique = trieCarteNbre(trefle);
        tmpTrefle = trieCarteNbre(pique);

        for (Carte _c : tmpPique) {
            cartesMilieuTriee.add(_c);
        }

        for (Carte _c : tmpCarreau) {
            cartesMilieuTriee.add(_c);
        }
        for (Carte _c : tmpCoeur) {
            cartesMilieuTriee.add(_c);
        }
        for (Carte _c : tmpTrefle) {
            cartesMilieuTriee.add(_c);
        }

        return cartesMilieuTriee;
    }

    //methode qui trie par figure
    public List<Carte> trieCarteNbre(List<Carte> cartesMilieu) {
        List<Carte> copyCartesMilieu = new ArrayList<Carte>(cartesMilieu);
        List<Carte> cartesMilieuTriee = new ArrayList<Carte>();

        Carte min = null;
        int i = 0;

        while (copyCartesMilieu.size() > 0) {
            min = copyCartesMilieu.get(0);

            for (int j = 0; j < copyCartesMilieu.size(); j++) {

                if (min.getValeur() > copyCartesMilieu.get(j).getValeur()) {
                    min = copyCartesMilieu.get(j);
                }
            }
            cartesMilieuTriee.add(min);
            copyCartesMilieu.remove(min);
        }
        return cartesMilieuTriee;
    }


}