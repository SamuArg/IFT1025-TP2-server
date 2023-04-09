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
    private String currentSession;

    /**
     * Ce constructeur permet de lié les différentes classes ensemble et de définir l'action des boutons de l'interface graphique
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

    /**
     * Cette méthode définie les actions qui se passent lorsqu'on clique sur le bouton charger
     * Elle charge les cours de la session choisie dans le tableau
     */
    public void charger() {
        String session = this.vue.getSessionChoice();
        if (session != null) {
            ArrayList<Course> courses;
            currentSession = session;
            try {
                courses = this.modele.loadCourse(session);
                if (courses == null){
                    this.vue.getErrors().setContentText("Erreur serveur, veuillez réessayer plus tard");
                    this.vue.getErrors().show();
                    throw new IOException();
                }
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
