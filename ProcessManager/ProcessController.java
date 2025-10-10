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
        // Construire le tableau complet de la commande
        String[] fullCommand; 

        if (args == null) {
            fullCommand = new String[] {command};
        } else {
            fullCommand = new String[args.length + 1];
            fullCommand[0] = command;
            System.arraycopy(args, 0, fullCommand, 1, args.length);
        }
        
        // Configurer la commande dans ProcessBuilder
        processBuilder.command(fullCommand);
        // Lancer le processus
        currentProcess = processBuilder.start();
        // Afficher la commande pour le debug
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

        // Utiliser executeSimple pour lancer le processus de base
        Process process = executeSimple(command, args);

        // Si outputFile n'est pas null, configurer la redirection
        if (outputFile != null) {
            processBuilder.redirectOutput(outputFile);
        }

        // Si errorFile n'est pas null, configurer la redirection d'erreur
        if (errorFile != null) {
            processBuilder.redirectError(errorFile);
        }
        

        System.out.println("Redirection configurée - Sortie: " + outputFile + ", Erreur: " + errorFile);

        // Relancer le processus avec les redirections
        currentProcess = processBuilder.start();
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
        // Utiliser executeSimple pour lancer le processus
        Process process = executeSimple(command, args);
        // (Les flux restent accessibles par défaut)

        System.out.println("Mode interactif activé pour : " + command);

        return process;
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
            process.waitFor();
            return process.exitValue();
        } else {
            // Utiliser process.waitFor(timeoutSeconds, java.util.concurrent.TimeUnit.SECONDS)
            boolean finishedInTime = process.waitFor(timeoutSeconds, java.util.concurrent.TimeUnit.SECONDS);
            // Si le processus se termine dans les temps, retourner process.exitValue()
            if (finishedInTime) {
                return process.exitValue();
            } else {
                process.destroyForcibly();
                return -1;
            }
        }
    }

    /**
     * Envoie des données à l'entrée standard d'un processus interactif.
     */
    public void sendInput(Process process, String input) throws IOException {
        // Obtenir l'OutputStream du processus
        OutputStream outputStream = process.getOutputStream();

        if (outputStream != null) {
            // Écrire les données + retour à la ligne
            outputStream.write(input + "\n");
            // TODO Appeler flush() pour forcer l'envoi
            outputStream.flush();
        }

        System.out.println("Envoi vers le processus : " + input);
    }

    /**
     * Lit la sortie standard d'un processus de manière non-bloquante.
     */
    public String readOutput(Process process) throws IOException {
        // TODO Obtenir l'InputStream du processus
        InputStream inputStream = process.getInputStream();

        if (inputStream != null) {
            // TODO Vérifier s'il y a des données avec inputStream.available()
            if (inputStream.available() > 0) {
                // Si oui, les lire et les retourner comme String
                byte[] buffer = new byte[inputStream.available()];
                int bytesRead = inputStream.read(buffer);
                return new String(buffer, 0, bytesRead);
            }
        }

        return "";
    }

    // Getters
    public Process getCurrentProcess() { 
        return currentProcess; 
    }

    public static void main (String[] args) {
        
        System.out.println("Test N°1");
        ProcessController process1 = new ProcessController();

        String[] param = {"/c", "echo", "Hello World"};

        try {
            process1.executeSimple("cmd", param);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Test N°2");
        ProcessController process2 = new ProcessController();
        File outputFile = new File("output.txt");
        File error = new File("error.txt");

        try {
            process2.executeWithRedirection("cmd", outputFile, error, param);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'exécution avec la redirection : " + e.getMessage());
            e.printStackTrace();
        }
    }
} 