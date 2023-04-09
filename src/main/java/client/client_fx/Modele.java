package client.client_fx;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Modele {
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ArrayList<Course> courses;

    /**
     * Cette méthode permet de charger les cours d'une certaine session via le server
     * @param session La session pour laquelle on demande la liste de cours
     * @return Retourne une ArrayList des cours
     * @throws IOException Si il y a une erreur lors de l'envoie ou de la réception de la requête vers le server
     * @throws ClassNotFoundException Si la classe Course n'est pas trouvé lors de la lecture de l'input du server
     */
    public ArrayList<Course> loadCourse(String session) throws IOException, ClassNotFoundException {
        connect();
        objectOutputStream.writeObject("CHARGER "+session);
        courses = (ArrayList<Course>) objectInputStream.readObject();
        return courses;
    }

    /**
     * Cette méthode permet au client de se connecter au server
     * @throws IOException Si il y a une erreur lors de la création de la connection entre le client et le serveur
     */
    public void connect() throws IOException {
        clientSocket = new Socket("127.0.0.1",1337);
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        }

    /**
     * Cette méthode permet d'envoyer un RegistrationForm du client vers le serveur pour s'inscrire à un cours
     * @param form Le RegistrationForm à envoyé au server
     * @return Retourne un message de disant si l'inscription est réussi ou non
     * @throws IOException Si il y a une erreur entre le client et le server
     * @throws ClassNotFoundException Si la classe RegistrationForm n'est pas trouvé
     */
    public String sendForm(RegistrationForm form) throws IOException, ClassNotFoundException {
        connect();
        objectOutputStream.writeObject("INSCRIRE");
        objectOutputStream.writeObject(form);
        String success = (String) objectInputStream.readObject();
        return success;
    }

    /**
     * Cette méthode permet de vérifier si une String respecte le format d'un email valide
     * @param input La String qu'il faut vérifier le format
     * @return Retourne true si la String respecte le format, false sinon
     */
    public boolean isEmail(String input){
        Pattern emailRegex = Pattern.compile("\\S+@\\S+\\.\\S+");
        Matcher matcher = emailRegex.matcher(input);
        return matcher.find();
    }

    /**
     * Cette méthode permet de vérifier si une String respecte le format d'un nom
     * @param input La String qu'il faut vérifier le format
     * @return Retourne true si la String respecte le format, false sinon
     */
    public boolean isName(String input){
        Pattern nameRegex = Pattern.compile("\\S+");
        Matcher matcher = nameRegex.matcher(input);
        return matcher.find();
    }
    /**
     * Cette méthode permet de vérifier si une String respecte le format d'un matricule
     * @param input La String qu'il faut vérifier le format
     * @return Retourne true si la String respecte le format, false sinon
     */
    public boolean isMatricule(String input){
        Pattern matriculeRegex = Pattern.compile("[0-9]{8}");
        Matcher matcher = matriculeRegex.matcher(input);
        return matcher.find();
    }
}
