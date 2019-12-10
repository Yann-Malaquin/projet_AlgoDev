package fr.projet.loto;

import java.util.ArrayList;
import java.util.List;
import fr.projet.Joueur;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class JoueurLoto {
	private Joueur J;
	private List<GrilleLoto> LGJ;
	private int nombrefav;
	private int nombredecartonsvoulus;

	public JoueurLoto() {
		super();
	}

	public JoueurLoto(int nombrefav, int nombredecartonsvoulus) {
		super();
		this.nombrefav = nombrefav;
		this.nombredecartonsvoulus = nombredecartonsvoulus;
		this.LGJ = new ArrayList<GrilleLoto>();
	}

	public void creation_joueur(String nom) {
		J = new Joueur(nom);
	}

	public Joueur getJ() {
		return J;
	}

	public void setJ(Joueur j) {
		J = j;
	}

	public List<GrilleLoto> getLGJ() {
		return LGJ;
	}

	public void setLGJ(GrilleLoto grille) {
		this.LGJ.add(grille);
	}

	public int getNombrefav() {
		return nombrefav;
	}

	public void setNombrefav(int nombrefav) {
		this.nombrefav = nombrefav;
	}

	public int getNombredecartonsvoulus() {
		return nombredecartonsvoulus;
	}

	public void setNombredecartonsvoulus(int nombredecartonsvoulus) {
		this.nombredecartonsvoulus = nombredecartonsvoulus;
	}

	@Override
	public String toString() {
		return "JoueurLoto [J=" + J.getPseudo() + ", nombrefav=" + nombrefav + ", nombredecartonsvoulus=" + nombredecartonsvoulus
				+ "]";
	}
}
