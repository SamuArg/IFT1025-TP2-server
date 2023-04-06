package client.client_simple;

import client.client_simple.Client_simple;

/**
 * Cette classe permet de démarrer un nouveau client
 */
public class Client_simpleLauncher {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 1337;

    /**
     * Cette méthode permet de démarrer un nouveau Client_simple
     * @param args
     */
    public static void main(String[] args)  {
        Client_simple client;
        System.out.println("*** Bienvenue au portail d'inscriptions de cours de l'UDEM ***");
        client = new Client_simple();
        client.choice();
    }
}
