import java.io.FileWriter;
import java.io.IOException;

public class Image {
    private int width;
    private int height;
    // pixels[y][x][0=R,1=G,2=B]
    private int[][][] pixels; // pixels[y][x][0=R,1=G,2=B]

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    /**
     * Constructeur : initialise une image vide.
     */
    public Image(int width, int hauteur) {
        this.width = width;
        this.height = height;
        pixels = new int[hauteur][largeur][3];
    }

    /**
     * Définit la couleur d'un pixel à la position (x, y)
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            pixels[y][x][0] = r;
            pixels[y][x][1] = g;
            pixels[y][x][2] = b;
        }
    }

    public void save_txt(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("P3\n");
            writer.write(width + " " + height + "\n");
            writer.write("255\n");

            for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = pixels[y][x][0];
                int g = pixels[y][x][1];
                int b = pixels[y][x][2];
                writer.write(r + " " + g + " " + b + " ");
            }
            writer.write("\n");
            }
        }
    }

    /**
     * Lecture d'une image au format texte PPM (P3)
     */
    public static Image read_txt(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename);
                Scanner sc = new Scanner(fis)) {

            // Vérification du format
            String magic = sc.next();
            if (!magic.equals("P3")) {
            throw new IOException("Format PPM non supporté : " + magic);
            }

            // Lecture dimensions + valeur max
            int width = sc.nextInt();
            int height = sc.nextInt();
            int maxVal = sc.nextInt(); // typiquement 255

            // Création de l'image
            Image img = new Image(width, height);

            // Remplissage des pixels
            for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = sc.nextInt();
                int g = sc.nextInt();
                int b = sc.nextInt();
                img.setPixel(x, y, r, g, b);
            }
            }

            return img;
        }
    }

    /**
     * Sauvegarde l'image au format PPM binaire (P6)
     */
    public void save_bin(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        // En-tête
        String header = "P6\n" + width + " " + height + "\n255\n";
        fos.write(header.getBytes());

        // Pixels (R, G, B en octets)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                fos.write((byte) pixels[y][x][0]); // R
                fos.write((byte) pixels[y][x][1]); // G
                fos.write((byte) pixels[y][x][2]); // B
            }
        }

    }

    /**
     * Lecture d'une image au format PPM binaire (P6)
     */
    public static Image read_bin(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            // Lire l'en-tête en texte
            StringBuilder header = new StringBuilder();
            int c;
            int newlines = 0;
            while (newlines <3 && (c = fis.read() != -1)) {
                //....
            }
            //....
        }
    }

}