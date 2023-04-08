package client.client_simple;

/**
 * Cette classe permet de démarrer un nouveau client
 */
public class Client_simpleLauncher {

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
