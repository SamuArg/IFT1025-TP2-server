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

    public ArrayList<Course> loadCourse(String session) throws IOException, ClassNotFoundException {
        connect();
        objectOutputStream.writeObject("CHARGER "+session);
        courses = (ArrayList<Course>) objectInputStream.readObject();
        return courses;
    }

    public void connect() throws IOException {
        clientSocket = new Socket("127.0.0.1",1337);
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        }
    public String sendForm(RegistrationForm form) throws IOException, ClassNotFoundException {
        connect();
        objectOutputStream.writeObject("INSCRIRE");
        objectOutputStream.writeObject(form);
        String success = (String) objectInputStream.readObject();
        return success;
    }

    public boolean isEmail(String input){
        Pattern emailRegex = Pattern.compile("\\S+@\\S+\\.\\S+");
        Matcher matcher = emailRegex.matcher(input);
        return matcher.find();
    }
    public boolean isName(String input){
        Pattern nameRegex = Pattern.compile("\\S+");
        Matcher matcher = nameRegex.matcher(input);
        return matcher.find();
    }
    public boolean isMatricule(String input){
        Pattern matriculeRegex = Pattern.compile("[0-9]{8}");
        Matcher matcher = matriculeRegex.matcher(input);
        return matcher.find();
    }
}
