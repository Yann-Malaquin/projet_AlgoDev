/**
 * 
 */
/**
 * @author Bitfenix
 *
 */
module Mini_Jeu {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports fr.projet.sudoku;
    exports fr.projet.loto to javafx.graphics,javafx.fxml;
    opens fr.projet.loto;
}