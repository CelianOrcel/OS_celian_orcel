import java.io.FileWriter;
import java.io.IOException;

public class FirstPPM {
  public static void main(String[] args) {
    try {
      FileWriter writer = new FileWriter("FirstPPM.ppm");

      // En-tête PPM
      writer.write("P3\n");          // Format texte
      writer.write("3 2\n");         // Largeur=3, Hauteur=2
      writer.write("255\n");         // Valeur maximale

      // Première ligne : rouge, vert, bleu
      writer.write("255 0 0   ");    // Rouge
      writer.write("0 255 0   ");    // Vert
      writer.write("0 0 255\n");     // Bleu

      // Deuxième ligne : jaune, blanc, noir
      writer.write("255 255 0   ");  // Jaune
      writer.write("255 255 255   "); // Blanc
      writer.write("0 0 0\n");       // Noir

      writer.close(); // Fermeture du fichier

      System.out.println("Image PPM créée avec succès !");
    } catch (IOException e) {
      System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
    }
  }
}