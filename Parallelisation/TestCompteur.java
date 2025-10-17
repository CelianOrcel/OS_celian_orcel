public class TestCompteur {
    private final int NOMBRE_THREADS = 50;
    private final int INCREMENTS_PAR_THREAD = 1000;
    private Thread[] threads = new Thread[NOMBRE_THREADS];

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Test Compteur Dangereux ===");

        CompteurDangereux.resetCompteur();

        for (int i = 0; i < NOMBRE_THREADS; i++) {
            threads[i] = new CompteurDangereux.Incrementeur("Thread-" + i, INCREMENTS_PAR_THREAD);
            thread[i].start();
        }
        
        for (int i = 0; i < NOMBRE_THREADS; i++) {
            thread.join();
        }
        System.out.println("Compteur final attendu : " + (NOMBRE_THREADS * INCREMENTS_PAR_THREAD));
        System.out.println("Compteur final réel : " + CompteurDangereux.getCompteur());
    }
}