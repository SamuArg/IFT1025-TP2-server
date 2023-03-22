package server.models;

import java.io.Serializable;

/**
 * Symbolise un course à laquel un étudiant peut s'inscrire ou s'informer dessus
 */
public class Course implements Serializable {

    private String name;
    private String code;
    private String session;

    /**
     * Création d'un objet course avec les attributs correspondants
     * @param name Représente le nom du cours
     * @param code Représente le code du cours
     * @param session Représente la session où le cours est donné
     */
    public Course(String name, String code, String session) {
        this.name = name;
        this.code = code;
        this.session = session;
    }

     /**
     * Sert à connaitre l'attribut name de l'objet
     * @return Retourne l'attribut name de l'objet
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de changer l'attribut name
     * @param name name par lequel on va changer l'attribut name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sert à connaitre l'attribut code de l'objet
     * @return Retourne l'attribut code de l'objet
     */
    public String getCode() {
        return code;
    }

    /**
     * Permet de changer l'attribut code
     * @param code code par lequel on va changer l'attribut code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Sert à connaitre l'attribut session de l'objet
     * @return Retourne l'attribut session de l'objet
     */
    public String getSession() {
        return session;
    }

    /**
     * Permet de changer l'attribut session
     * @param session session par lequel on va changer l'attribut session
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Redéfinie la méthode toString()
     * @return retourne le String qui représente bien l'objet
     */
    @Override
    public String toString() {
        return "Course{" +
                "name=" + name +
                ", code=" + code +
                ", session=" + session +
                '}';
    }
}
