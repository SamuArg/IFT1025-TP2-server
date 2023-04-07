package client.client_fx;

import server.models.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Modele {
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ArrayList<Course> courses;
    public ArrayList<Course> loadCourse(String session){
        try{
            connect();
            objectOutputStream.writeObject("CHARGER " + session);
            courses = (ArrayList<Course>) objectInputStream.readObject();
            return courses;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void connect(){
        try {
            clientSocket = new Socket("127.0.0.1",1337);
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
