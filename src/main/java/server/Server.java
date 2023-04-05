package server;

import javafx.util.Pair;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


import server.models.Course;
import server.models.RegistrationForm;

/**
 * Cette classe représente un serveur qui va traiter les requêtes des clients.
 */
public class Server {

    /**
     * La chaine de charactère qui correspond à la commande pour s'inscrire à un cours
     */
    public final static String REGISTER_COMMAND = "INSCRIRE";
    /**
     * La chaine de charactère qui correspond à la commande pour charger la liste de cours
     */
    public final static String LOAD_COMMAND = "CHARGER";
    private final ServerSocket server;
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final ArrayList<EventHandler> handlers;

    /**
     * Création d'un objet server
     * @param port Déclare quel port sera utilisé pour héberger le server
     * @throws IOException Erreur possible lié au socket du server
     */
    public Server(int port) throws IOException {
        this.server = new ServerSocket(port, 1);
        this.handlers = new ArrayList<EventHandler>();
        this.addEventHandler(this::handleEvents);
    }

    /**
     * Permet d'ajouter les nouveaux événements à une liste qui sera traité par la suite.
     * @param h L'événement à ajouter
     */
    public void addEventHandler(EventHandler h) {
        this.handlers.add(h);
    }

    private void alertHandlers(String cmd, String arg) {
        for (EventHandler h : this.handlers) {
            h.handle(cmd, arg);
        }
    }

    /**
     * Permet de créer et de gérer la relation client server
     */
    public void run() {
        while (true) {
            try {
                client = server.accept();
                System.out.println("Connecté au client: " + client);
                objectInputStream = new ObjectInputStream(client.getInputStream());
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                listen();
                disconnect();
                System.out.println("Client déconnecté!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Permet d'attendre le prochain input d'un client et de gérer les commandes lancées
     * @throws IOException Erreur possible lors de la récupération d'objectInputStream
     * @throws ClassNotFoundException Erreur possible lors de la lecture de l'objet récupéré
     */
    public void listen() throws IOException, ClassNotFoundException {
        String line;
        if ((line = this.objectInputStream.readObject().toString()) != null) {
            Pair<String, String> parts = processCommandLine(line);
            String cmd = parts.getKey();
            String arg = parts.getValue();
            this.alertHandlers(cmd, arg);
        }
    }

    /**
     * Permet de créer des pairs de commandes et d'argument appelé par le client
     * @param line Il s'agit du String de la commande entière envoyé par le client
     * @return Retourne la paire créée
     */
    public Pair<String, String> processCommandLine(String line) {
        String[] parts = line.split(" ");
        String cmd = parts[0];
        String args = String.join(" ", Arrays.asList(parts).subList(1, parts.length));
        return new Pair<>(cmd, args);
    }

    /**
     * Permet d'arrêter la relation client server
     * @throws IOException Erreur possible en lien avec le ObjectOutputStream et ObjectInputStream
     */
    public void disconnect() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        client.close();
    }

    /**
     * Permet de définir quelle commande a été appelée par le client
     * @param cmd Définie la commande appelée par le client
     * @param arg Définie l'argument appelé par le client
     */
    public void handleEvents(String cmd, String arg) {
        if (cmd.equals(REGISTER_COMMAND)) {
            handleRegistration();
        } else if (cmd.equals(LOAD_COMMAND)) {
            handleLoadCourses(arg);
        }
    }

    /**
     Lire un fichier texte contenant des informations sur les cours et les transformer en liste d'objets 'Course'.
     La méthode filtre les cours par la session spécifiée en argument.
     Ensuite, elle renvoie la liste des cours pour une session au client en utilisant l'objet 'objectOutputStream'.
     La méthode gère les exceptions si une erreur se produit lors de la lecture du fichier ou de l'écriture de l'objet dans le flux.
     @param arg la session pour laquelle on veut récupérer la liste des cours
     */
    public void handleLoadCourses(String arg) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/server/data/cours.txt"));
            String course;
            ArrayList<Course> courses = new ArrayList<>();
            while((course = reader.readLine()) != null){
                String[] arrayCourse = course.split("\t");
                if(arrayCourse[2].equals(arg)){
                    courses.add(new Course(arrayCourse[1],arrayCourse[0],arrayCourse[2]));
                }
            }
            objectOutputStream.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     Récupérer l'objet 'RegistrationForm' envoyé par le client en utilisant 'objectInputStream', l'enregistrer dans un fichier texte
     et renvoyer un message de confirmation au client. La méthode gère les exceptions si une erreur se produit lors de la lecture de l'objet,
     l'écriture dans un fichier ou dans le flux de sortie.
     */
    public void handleRegistration() {
        try{
            RegistrationForm input = (RegistrationForm) objectInputStream.readObject();
            String session = input.getCourse().getSession();
            String code = input.getCourse().getCode();
            String matricule = input.getMatricule();
            String nom = input.getNom();
            String prenom = input.getPrenom();
            String email = input.getEmail();
            FileWriter fw = new FileWriter("src/main/java/server/data/inscription.txt", true);
            BufferedWriter writer = new BufferedWriter(fw);
            String newInscription = session + "\t" + code + "\t" + matricule + "\t" + nom + "\t" + prenom + "\t" + email + "\n";
            writer.append(newInscription);
            writer.close();
            objectOutputStream.writeObject("Félicitations! Inscription réussie de " + prenom + " au cours " + code +".");

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

