package client;

import java.io.*;
import java.net.Socket;

public class Client_simple {
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Client_simple(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
    }

    public void run(){
        try{
            objectOutputStream = (ObjectOutputStream) clientSocket.getOutputStream();
            objectInputStream = (ObjectInputStream) clientSocket.getInputStream();
        } catch (IOException e) {

        }
    }

}
