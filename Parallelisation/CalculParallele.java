public class CalCulParallele {
    private static final int[] DONNEES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int MULTIPLICATEUR = 1000;

    public static void main(String[] args) {
        System.out.println("=== Calcul Séquentiel ===");

        long debut = System.nanoTime();
        long sommeTotal = 0;

        Calcule[] threads = new Calcule[DONNEES.length];

        // TODO: Ce code sera remplacé par une version parallèle
        for (int i = 0; i < DONNEES.length; i++) {
            int valeur = DONNEES[i];

            threads[i] = new Calcule(valeur, MULTIPLICATEUR, i);
            threads[i].start();
        }
        
        long sommeTotal = 0;
        for ( int i = 0; i < DONNEES.length; i++) {
            threads[i].join();
            sommeTotal += threads[i].getResultat();
         }

        long duree = (System.nanoTime() - debut) / 1_000_000;
        System.out.println("Résultat total : " + sommeTotal);
        System.out.println("Durée : " + duree + " ms");
    }
}