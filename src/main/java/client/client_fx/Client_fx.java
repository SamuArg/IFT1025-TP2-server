package client.client_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Cette classe permet de démarrée l'application graphique
 */
public class Client_fx extends Application {

    /**
     * Redéfinie la méthode start pour lancé l'application
     * @param primaryStage Représente le stage dans lequel l'interface graphique est situé
     */
    @Override
    public void start(Stage primaryStage) {
        try{
            Modele modele = new Modele();
            Vue vue = new Vue();
            Controleur controleur = new Controleur(modele, vue);
            Scene scene = new Scene(vue, 900, 550);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Inscription UDEM");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode lance l'application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
