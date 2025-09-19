import java.io.*;
import java.util.Scanner;

public class Filter {

    public static void main(String[] args) {
        // Vérification des arguments
        if (args.length < 3) {
            System.err.println("Usage: java Filter <input_file> <output_file> <filter_type>");
            System.err.println("You should provide an input and an output filenames and a filter type. Aborting");
            System.exit(-1);
        }

        String inputFilename = args[0];
        String outputFilename = args[1];
        String filterType = args[2];

        Image source = null;

        // Lecture de l'image
        try {
            source = Image.read(inputFilename);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            System.exit(-1);
        }

        int h = source.getHeight();
        int w = source.getWidth();
        Image destination = new Image(w, h);

        // Application du filtre pixel par pixel
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rouge = source.getRed(x, y);
                int vert = source.getGreen(x, y);
                int bleu = source.getBlue(x, y);

                switch (filterType.toLowerCase()) {
                    case "copy":
                        // TODO
                        break;
                    case "dark":
                        // TODO
                        break;
                    case "bright":
                        // TODO
                        break;
                    case "grayscale":
                        // TODO
                        break;
                    default:
                        System.err.println("Unknown filter type: " + filterType);
                        System.exit(-1);
                }
            }
        }

        // Sauvegarde de l'image filtrée
        try {
            destination.write(outputFilename);
            System.out.println("Filtered image saved to " + outputFilename);
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }
}