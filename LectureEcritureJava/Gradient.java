public class Gradient {
    public static void main(String[] args) {
        int largeur = 200;
        int hauteur = 100;
        Image img = new Image(largeur, hauteur);


        // Génération du dégradé de bleu
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                int bleu = (int) (255.0 * x / (largeur - 1));
                img.setPixel(x, y, 0, 0, bleu);
            }
        }

        try {
            img.save("gradient.ppm");
            System.out.println("Dégradé créé avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du dégradé : " + e.getMessage());
        }
    }
}