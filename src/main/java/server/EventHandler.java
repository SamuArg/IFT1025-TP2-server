package server;

/**
 * Définie l'interface fonctionnelle EventHandler qui sert à gérer les différents événements dans le server
 */
@FunctionalInterface
public interface EventHandler {
    void handle(String cmd, String arg);
}
