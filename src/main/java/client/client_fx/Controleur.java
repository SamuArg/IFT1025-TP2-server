package client.client_fx;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Controleur {
    private Modele modele;
    private Vue vue;
    private String currentSession;

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
        ArrayList<String> errors = new ArrayList<>();
        Course course = getSelectedCourse();
        String prenom = this.vue.getPrenomField().getText();
        String nom = this.vue.getNomField().getText();
        String email = this.vue.getEmailField().getText();
        String matricule = this.vue.getMatriculeField().getText();
        if (course == null){
            errors.add("Veuillez choisir un cours.\n");
        }
        if(!this.modele.isName(prenom)){
            errors.add("Veuillez fournir un prénom valide.\n");
        }
        if(!this.modele.isName(nom)){
            errors.add("Veuillez fournir un nom valide.\n");
        }
        if(!this.modele.isMatricule(matricule)){
            errors.add("Veuillez fournir un matricule valide.\n");
        }
        if(!this.modele.isEmail(email)){
            errors.add("Veuillez fournir un email valide.\n");
        }
        if(errors.size()>0){
            String errorMessage = "";
            for (int i=0;i<errors.size();i++){
                errorMessage += errors.get(i);
            }
            this.vue.getErrors().setContentText(errorMessage);
            this.vue.getErrors().show();
        }
        else {
            RegistrationForm form = new RegistrationForm(prenom,nom,email,matricule,course);
            try{
                String success = this.modele.sendForm(form);
                this.vue.getConfirmation().setContentText(success);
                this.vue.getConfirmation().show();
            } catch (Exception e) {
                this.vue.getErrors().setContentText("L'inscription a échoué veuillez réessayer.");
                this.vue.getErrors().show();
            }
        }
    }

    public void charger() {
        String session = this.vue.getSessionChoice();
        if (session != null) {
            ArrayList<Course> courses;
            currentSession = session;
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

    public Course getSelectedCourse(){
        return (Course) this.vue.getTableau().getSelectionModel().getSelectedItem();
    }
}
