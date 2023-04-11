package client.client_fx;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Cette classe permet de faire le lien entre la classe Vue et la classe Modele
 */
public class Controleur {
    private Modele modele;
    private Vue vue;


    /**
     * Ce constructeur permet de lier les différentes classes ensemble et de définir l'action des boutons de l'interface graphique
     * @param modele L'objet modele qui est utilisé pour faire le lien
     * @param vue L'objet vue qui est utilisé pour faire le lien
     */
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

    /**
     * Cette méthode définie les actions que le bouton envoyer fait lorsqu'on clique dessus.
     * Elle envoie les informations du formulaire vers le server pour s'inscrire à un cours
     */
    public void envoyer() {
        ArrayList<String> errors = new ArrayList<>();
        Course course = this.vue.getSelectedCourse();
        String prenom = this.vue.getPrenomField().getText();
        String nom = this.vue.getNomField().getText();
        String email = this.vue.getEmailField().getText();
        String matricule = this.vue.getMatriculeField().getText();
        try {
            if (course == null) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            errors.add("Veuillez choisir un cours.\n");
        }
        try{
            if(!this.modele.isName(prenom)){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            errors.add("Veuillez fournir un prénom valide.\n");
        }
        try{
            if(!this.modele.isName(nom)){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            errors.add("Veuillez fournir un nom valide.\n");
        }
        try{
            if(!this.modele.isMatricule(matricule)){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            errors.add("Veuillez fournir un matricule valide.\n");
        }
        try{
            if(!this.modele.isEmail(email)){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
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

    /**
     * Cette méthode définie les actions qui se passent lorsqu'on clique sur le bouton charger.
     * Elle charge les cours de la session choisie dans le tableau
     */
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
                this.vue.getErrors().setContentText("Erreur serveur, veuillez réessayez plus tard.");
                this.vue.getErrors().show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
