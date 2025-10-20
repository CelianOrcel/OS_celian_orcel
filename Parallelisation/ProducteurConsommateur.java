import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;

private static final int TAILLE_BUFFER = 5;
private final Integer[] buffer = new Integer[TAILLE_BUFFER];

// Déclarer 3 sémaphores avec les bonnes valeurs initiales
private final Semaphore placesLibres = new Semaphore(TAILLE_BUFFER);
private final Semaphore elementsDisponibles = new Semaphore(0);  
private final Semaphore mutexBuffer = new Semaphore(1); // quel erreur ici ? vous pouvez le résoudre plus tard


class Producteur implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            int produit = genererProduit();

            // Attendre une place libre
            placesLibres.acquire();
            // Acquérir l'accès exclusif au buffer  
            mutexBuffer.acquire();
            // Ajouter l'élément au buffer
            buffer.add(produit);
            // Libérer l'accès au buffer
            mutexBuffer.release();
            // Signaler qu'un élément est disponible
            elementsDisponibles.release();
        }
    }
}

class Consommateur implements Runnable {
    public void run() {
        for (int i = 0; i < 7; i++) {
            // Attendre qu'un élément soit disponible
            elementsDisponibles.acquire();
            // Acquérir l'accès exclusif au buffer
            mutexBuffer.acquire();
            // Retirer un élément du buffer  
            Integer produit = buffer.poll();
            // Libérer l'accès au buffer
            mutexBuffer.release();
            // ignaler qu'une place est libre
            placesLibres.release();
        }
    }
}
