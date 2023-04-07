package client.client_fx;

import server.models.Course;

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

    public void envoyer(){

    }

    public void charger(){
        String session = (String) this.vue.getSessionChoice().getValue();
        ArrayList<Course> courses = this.modele.loadCourse(session);
        for (Course cours : courses) {
            this.vue.getTableau().getItems().add(cours);
        }


    }
}
