package client;


public class Client_simpleLauncher {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 1337;
    public static void main(String[] args)  {
        Client_simple client;
        System.out.println("*** Bienvenue au portail d'inscriptions de cours de l'UDEM ***");
        client = new Client_simple();
        client.choice();
    }
}
