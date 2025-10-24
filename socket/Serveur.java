import java.net.*;
import java.io.*;

public class Serveur {
    public static void main(String[] args) throws Exception {
        // 1. Création du serveur et liaison au port 5000
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Serveur démarré sur le port 5000");

        while(true) {
            // 2. Acceptation d'une connexion cliente
            Socket client = serverSocket.accept();
            System.out.println("Client connecté : " + client.getInetAddress());
            // 3. Création des canaux input/output
            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream())
            );
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            // 4. Boucle d'écho (lecture/écriture)
            String ligne;
            while ((ligne = in.readLine()) != null) {
                System.out.println("Reçu : " + ligne);
                out.println("ECHO: " + ligne);
            }
            // 5. Fermeture de la connexion client
            System.out.println("Client déconnecté");
            in.close();
            out.close();
            client.close();
        }
    }
}