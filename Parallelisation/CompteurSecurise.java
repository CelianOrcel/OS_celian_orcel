public class CompteurSecurise {
    private static int compteurGlobal = 0;  // Variable partagée MODIFIABLE
    private static final Object verrou = new Object();

    static class Incrementeur implements Runnable {
        private final String nom;
        private final int nombreIncrements;

        synchronized(verrou) {
            // TODO: Placer ici l'opération critique
            compteurGlobal++;
        }
        
        public Incrementeur(String nom, int nombreIncrements) {
            this.nom = nom;
            this.nombreIncrements = nombreIncrements;
        }

        @Override
        public void run() {
            for (int i = 0; i < nombreIncrements; i++) {
                // Cette ligne pose probleme car plusieurs threads peuvent etre créé en meme temps
                compteurGlobal++;  // RACE CONDITION ICI !
            }
            System.out.println(nom + " terminé. Compteur vu : " + compteurGlobal);
        }
    }

    public static int getCompteur() {
        return compteurGlobal;
    }

    public static void resetCompteur() {
        compteurGlobal = 0;
    }
}