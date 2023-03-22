package server.models;

import java.io.Serializable;

/**
 * Symbolise le formulaire qui sert à s'inscrire à un cours
 */
public class RegistrationForm implements Serializable {
    private String prenom;
    private String nom;
    private String email;
    private String matricule;
    private Course course;

    /**
     * Création d'un formulaire pour s'inscrire à un cours
     * @param prenom Prénom de l'étudiant qui s'incrit
     * @param nom Nom de l'étudiant qui s'inscrit
     * @param email Email de l'étudiant qui s'inscrit
     * @param matricule Matricule de l'étudiant qui s'inscrit
     * @param course Cours auquel l'étudiant s'inscrit
     */
    public RegistrationForm(String prenom, String nom, String email, String matricule, Course course) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.matricule = matricule;
        this.course = course;
    }

    /**
     * Sert à connaitre l'attribut prenom de l'objet
     * @return Retourne l'attribut prenom de l'objet
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Permet de changer l'attribut prenom
     * @param prenom Prenom par lequel on va changer l'attribut prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Sert à connaitre l'attribut nom de l'objet
     * @return Retourne l'attribut nom de l'objet
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de changer l'attribut nom
     * @param nom nom par lequel on va changer l'attribut nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Sert à connaitre l'attribut email de l'objet
     * @return Retourne l'attribut email de l'objet
     */
    public String getEmail() {
        return email;
    }

    /**
     * Permet de changer l'attribut email
     * @param email email par lequel on va changer l'attribut email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sert à connaitre l'attribut matricule de l'objet
     * @return Retourne l'attribut matricule de l'objet
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Permet de changer l'attribut matricule
     * @param matricule matricule par lequel on va changer l'attribut matricule
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Sert à connaitre l'attribut course de l'objet
     * @return Retourne l'attribut course de l'objet
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Permet de changer l'attribut course
     * @param course course par lequel on va changer l'attribut course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Redéfinie la méthode toString()
     * @return retourne le String qui représente bien l'objet
     */
    @Override
    public String toString() {
        return "InscriptionForm{" + "prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + ", email='" + email + '\'' + ", matricule='" + matricule + '\'' + ", course='" + course + '\'' + '}';
    }
}
