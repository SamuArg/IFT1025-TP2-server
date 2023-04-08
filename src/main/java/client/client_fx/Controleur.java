package client.client_fx;

import server.models.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Controleur {
    private Modele modele;
    private Vue vue;

    public Controleur(Modele modele, Vue vue) {
        this.modele = modele;
        this.vue = vue;

        this.vue.getCharger().setOnAction((action) -> {
            this.charger();
        });

        this.vue.getEnvoyer().setOnAction((action) -> {
            this.envoyer();
        });
    }

    public void envoyer() {

    }

    public void charger() {
        String session = this.vue.getSessionChoice();
        if (session != null) {
            ArrayList<Course> courses;
            try {
                courses = this.modele.loadCourse(session);
                this.vue.setCourses(courses);
                this.vue.getTableau().getItems().clear();
                for (Course cours : courses) {
                    this.vue.getTableau().getItems().add(cours);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
