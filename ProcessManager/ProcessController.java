import java.io.*;

/**
 * Gestionnaire principal pour le lancement et le contrôle de processus externes.
 * Cette classe encapsule les fonctionnalités de ProcessBuilder en offrant
 * une interface simplifiée pour la gestion des processus.
 */
public class ProcessController {

    public static final int DEFAULT_TIMEOUT_SECONDS = 30;

    // Variables d'instance
    private ProcessBuilder processBuilder;
    private Process currentProcess;

    public ProcessController() {
        this.processBuilder = new ProcessBuilder();
        this.currentProcess = null;
    }

    /**
     * Lance un processus simple avec une commande et ses arguments.
     * Cette méthode constitue le point d'entrée basique pour l'exécution
     * de programmes externes.
     *
     * @param command commande à exécuter (ex: "ls", "python3", "notepad.exe")
     * @param args arguments optionnels de la commande
     * @return le processus lancé
     * @throws IOException si le lancement échoue
     */
    public Process executeSimple(String command, String[] args) throws IOException {
        String[] fullCommand = {"ls", "python3", "notepad.exe"}; 

        if (args == null) {
            fullCommand = new String[] {command};
        } else {
            fullCommand = new String[] {command};
            for (int i = 0; i < args.length; i++) {
                fullCommand[i + 1] = args[i];
            }
        }
        
        processBuilder pb = new ProcessBuilder();
        pb.command(fullCommand);

        currentProcess = pb.start();

        System.out.println("Lancement de : " + command);
        return currentProcess;
    }

    /**
     * Lance un processus avec redirection des flux vers des fichiers.
     * Permet de capturer facilement les sorties standard et d'erreur.
     *
     * @param command commande à exécuter
     * @param outputFile fichier pour la sortie standard (null = pas de redirection)
     * @param errorFile fichier pour la sortie d'erreur (null = pas de redirection)  
     * @param args arguments de la commande
     * @return le processus configuré et lancé
     * @throws IOException si la configuration ou le lancement échoue
     */
    public Process executeWithRedirection(String command, File outputFile, 
                                        File errorFile, String[] args) throws IOException {

        // TODO Utiliser executeSimple pour lancer le processus de base
        Process process = null;

        // TODO Si outputFile n'est pas null, configurer la redirection
        // processBuilder.redirectOutput(outputFile);

        // TODO Si errorFile n'est pas null, configurer la redirection d'erreur
        // processBuilder.redirectError(errorFile);

        System.out.println("Redirection configurée - Sortie: " + outputFile + ", Erreur: " + errorFile);

        // TODO Relancer le processus avec les redirections
        currentProcess = null;
        return currentProcess;
    }

    /**
     * Lance un processus interactif permettant l'envoi de données via l'entrée standard
     * et la lecture temps réel des sorties.
     *
     * @param command commande à lancer
     * @param args arguments
     * @return le processus interactif
     * @throws IOException si le lancement échoue
     */
    public Process executeInteractive(String command, String[] args) throws IOException {
        // TODO Utiliser executeSimple pour lancer le processus
        // (Les flux restent accessibles par défaut)

        System.out.println("Mode interactif activé pour : " + command);

        return null;
    }

    /**
     * Attend la fin d'exécution d'un processus avec un timeout optionnel.
     * Retourne le code de sortie.
     *
     * @param process processus à attendre
     * @param timeoutSeconds délai maximum d'attente (0 = pas de timeout)
     * @return code de sortie du processus (-1 si timeout)
     * @throws InterruptedException si l'attente est interrompue
     */
    public int waitForProcess(Process process, int timeoutSeconds) throws InterruptedException {

        if (timeoutSeconds <= 0) {
            // TODO Attendre indéfiniment avec process.waitFor()
            return 0;
        } else {
            // TODO Utiliser process.waitFor(timeoutSeconds, java.util.concurrent.TimeUnit.SECONDS)
            // TODO Si le processus se termine dans les temps, retourner process.exitValue()
            // TODO Sinon, appeler process.destroyForcibly() et retourner -1
            return -1;
        }
    }

    /**
     * Envoie des données à l'entrée standard d'un processus interactif.
     */
    public void sendInput(Process process, String input) throws IOException {
        // TODO Obtenir l'OutputStream du processus
        OutputStream outputStream = null;

        if (outputStream != null) {
            // TODO Écrire les données + retour à la ligne
            // TODO Appeler flush() pour forcer l'envoi
        }

        System.out.println("Envoi vers le processus : " + input);
    }

    /**
     * Lit la sortie standard d'un processus de manière non-bloquante.
     */
    public String readOutput(Process process) throws IOException {
        // TODO Obtenir l'InputStream du processus
        InputStream inputStream = null;

        if (inputStream != null) {
            // TODO Vérifier s'il y a des données avec inputStream.available()
            // TODO Si oui, les lire et les retourner comme String
        }

        return "";
    }

    // Getters
    public Process getCurrentProcess() { 
        return currentProcess; 
    }
}