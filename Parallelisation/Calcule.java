public class Calcule extends Thread {
    
    // déclaration des variables d'instance
    private int valeur;
    private int multplicateur;
    private int index;

    /**
     * Constructeur de la classe Calcule
     * @param valeur La valeur à traiter
     * @param multplicateur Le multiplicateur pour le calcul intensif
     * @param index L'index de la valeur dans le tableau d'origine
     */
    public Calcul (int valeur, int multplicateur, int index) {
        this.valeur = valeur;
        this.multplicateur = multplicateur;
        this.index = index;
    }

    /**
     * Méthode run exécutée lors du démarrage du thread
     */
    @Override
    public void run() {
        // Même logique que dans la boucle interne de CalculSequentiel
        long resultat = 0;
        for (int j = 0; j < MULTIPLICATEUR; j++) {
            resultat += valeur * valeur + valeur;
        }
    }

    /**
     * Méthode pour récupérer le résultat du calcul
     * @return Le résultat du calcul
     */
    public long getResultat() {
        return resultat;
    }
}