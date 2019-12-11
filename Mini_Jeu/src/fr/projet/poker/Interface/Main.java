package fr.projet.poker.Interface;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuController controller = new MenuController();
        controller.initMenu(primaryStage);


        /*JoueurPoker jp1 = new JoueurPoker("jp1",150);
        JoueurPoker jp2 = new JoueurPoker("jp2",150);
        JoueurPoker jp3 = new JoueurPoker("jp3",150);
        JoueurPoker jp4 = new JoueurPoker("jp4",150);
        JoueurPoker jp5 = new JoueurPoker("jp5",150);
        List<JoueurPoker> lEndGame = new ArrayList<JoueurPoker>();
        int k = 0, i = 0;
        boolean find = false;
        TableController tb = new TableController();
        tb.setlJP(jp1);
        tb.setlJP(jp2);
        tb.setlJP(jp3);
        tb.setlJP(jp4);
        tb.setlJP(jp5);
        tb.setInitJoueur(0);
        tb.initDonneur();
        tb.initPb();
        tb.initGb();
        System.out.println(tb.getInitJoueur() + "fdfdsfsdf");
        System.out.println(tb.getlJP());*/

    }

    public static void main(String[] args) {
        launch(args);
    }


}