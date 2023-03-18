package client;

import java.io.IOException;

public class Client_simpleLauncher {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 1337;
    public static void main(String[] args) {
        Client_simple client;
        try{
            client = new Client_simple(IP, PORT);
            client.run();
        } catch (IOException e) {
        }
    }
}
