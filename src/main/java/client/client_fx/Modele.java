package client.client_fx;

import server.models.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Modele {
    public Socket clientSocket;
    public ObjectOutputStream objectOutputStream;
    public ObjectInputStream objectInputStream;
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
}
