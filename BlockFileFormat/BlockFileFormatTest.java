import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe de démonstration pour manipuler et afficher des fichiers au format
 * BlockFileFormat.
 *
 * Cette classe charge un fichier `.blk` via
 * {@link BlockFileFormat#readFromFile(String)} puis affiche son en-tête et le
 * contenu des sections (type, nom de fichier, nombre de blocs), ainsi qu'un
 * court aperçu du premier bloc de données de chaque section.
 *
 * Usage: exécuter {@link #main(String[])} pour parcourir des fichiers d'exemple.
 */
public class BlockFileFormatTest {
    /**
     * Affiche sur la sortie standard les informations contenues dans une instance de {@link BlockFileFormat}.
     *
     * @param format instance déjà chargée via {@link BlockFileFormat#readFromFile(String)}
     */
    public static void dumpBlockFile(BlockFileFormat format) {
        BlockFileFormat.Header header = format.getHeader();
        System.out.println("Magic: 0x" + Integer.toHexString(header.magic));
        System.out.println("Version: " + header.version);
        System.out.println("Sections: " + header.sectionPointers.length);

        // Afficher les sections
        System.out.println("\n--- Sections ---");
        for (int i = 0; i < format.getSections().length; i++) {
            BlockFileFormat.Section s = format.getSections()[i];
            int blocks = s.dataBlocks.length;
            // Aperçu textuel du premier bloc (tronqué à 50 octets), avec échappement des retours à la ligne
            String preview = blocks > 0 ? new String(s.dataBlocks[0].getData(0, Math.min(50, BlockFileFormat.BLOCK_SIZE))) : "";
            System.out.printf("%d. type=%d, filename=\"%s\", blocsDonnees=%d, preview=\"%s\"\n",
                                i + 1, s.type, s.filename, blocks, preview.replace("\n", "\\n"));
        }
    }

    /**
     * Programme de démonstration: lit plusieurs fichiers `.blk` et affiche leur
     * contenu structuré via {@link #dumpBlockFile(BlockFileFormat)}.
     *
     * @param args non utilisés
     */
    public static void main(String[] args) {
        try {
            String[] filenames = {
                "level1_header_only.blk",
                "level2_single_section_single_block.blk",
                "level3_multi_sections_single_block.blk",
                "level4_section_multi_blocks.blk",
                "level5_multi_sections_multi_blocks.blk"
            };

            // === LECTURE DU FICHIER ===

            for (String filename : filenames) {
                System.out.println("\n=== Lecture du fichier ===");
                System.out.println("Fichier : " + filename);
                dumpBlockFile(BlockFileFormat.readFromFile(filename));
            }

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}