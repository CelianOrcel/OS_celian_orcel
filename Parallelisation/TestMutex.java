public class TestMutex {
    private static final int NOMBRE_THREADS = 9;
    private static final int INCREMENTS_PAR_THREAD = 1111111;
    private static Thread[] threads = new Thread[NOMBRE_THREADS];

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Test Compteur Dangereux ===");

        CompteurSecurise.resetCompteur();

        for (int i = 0; i < NOMBRE_THREADS; i++) {
            threads[i] = new Thread(new CompteurSecurise.Incrementeur("Thread-" + i, INCREMENTS_PAR_THREAD));
            threads[i].start();
        }
        
        for (int i = 0; i < NOMBRE_THREADS; i++) {
            threads[i].join();
        }
        System.out.println("Compteur final attendu : " + (NOMBRE_THREADS * INCREMENTS_PAR_THREAD));
        System.out.println("Compteur final réel : " + CompteurSecurise.getCompteur());
    }
}