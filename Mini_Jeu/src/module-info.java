/**
 * @author Bitfenix
 *
 */
module Mini_Jeu {
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.controls;
    exports fr.projet.sudoku;
    exports fr.projet.poker.Interface to javafx.graphics;
    opens fr.projet.poker.Interface to javafx.fxml;
    exports fr.projet.bataillenavale to javafx.graphics;
    exports fr.projet.loto to javafx.graphics,javafx.fxml;
    opens fr.projet.loto;
}