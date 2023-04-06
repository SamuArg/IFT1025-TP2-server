package client.client_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client_fx extends Application {

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

    public static void main(String[] args) {
        launch(args);
    }
}
