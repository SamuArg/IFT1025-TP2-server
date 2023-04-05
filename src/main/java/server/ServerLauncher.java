package server;

/**
 * Cette classe permet de démarrer le server
 */
public class ServerLauncher {
    /**
     * Définie le port sur lequel le server sera lancé
     */
    public final static int PORT = 1337;

    /**
     * Méthode qui est lancée pour démarrer le server
     * @param args
     */
    public static void main(String[] args) {
        Server server;
        try {
            server = new Server(PORT);
            System.out.println("Server is running...");
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}