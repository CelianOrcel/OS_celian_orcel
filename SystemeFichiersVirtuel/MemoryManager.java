import java.io.*;

public class MemoryManager {
    public static final int BLOCK_SIZE = 512;
    public static final int TOTAL_MEMORY = 1024 * 1024; // 1 Mo
    public static final int NUM_BLOCKS = TOTAL_MEMORY / BLOCK_SIZE; // 2048

    // Offsets des différentes zones
    public static final int SUPERBLOCK_OFFSET = 0;
    public static final int BITMAP_OFFSET = BLOCK_SIZE;
    public static final int INODE_TABLE_OFFSET = 2 * BLOCK_SIZE;
    public static final int DATA_OFFSET = 129 * BLOCK_SIZE;

    public static final int INODE_SIZE = 128; // Taille d'un inode en octets
    public static final int INODE_TABLE_SIZE = DATA_OFFSET - INODE_TABLE_OFFSET;
    public static final int MAX_INODES = INODE_TABLE_SIZE / INODE_SIZE; // => 508

    // CONTRAINTE : Un seul tableau pour TOUT le système de fichiers
    private byte[] filesystemMemory;

    public MemoryManager() {
        this.filesystemMemory = new byte[TOTAL_MEMORY];
        initializeFilesystem();
    }

    private void initializeFilesystem() {
        // Initialiser le superbloc
        writeSuperblock();

        // Marquer les blocs système comme occupés
        setBlockUsed(0);  // superbloc
        setBlockUsed(1);  // bitmap

        for (int i = 2; i < 129; i++)
            setBlockUsed(i);  // table des inodes
    }

    private void writeSuperblock() {
        String signature = "MYFS1.0";
        System.arraycopy(signature, 0, filesystemMemory, 0, Math.max(signature.length, 28));

        int [] parameters = new int [] {
            BLOCK_SIZE,
            TOTAL_MEMORY
        };

        System.arraycopy(parameters, 0, filesystemMemory, 0, 4*parameters.length);

        // copier BLOCK_SIZE
        //Utils.writeInt(filesystemMemory, 28, BLOCK_SIZE);
        //Utils.writeInt(filesystemMemory, 34, TOTAL_MEMORY);
    }

    public boolean setBlockUsed(int blockNumber, boolean used) {
        if (blockNumber < 0 || blockNumber >= NUM_BLOCKS)
            return false;

        int byteIndex = blockNumber / 8;
        int bitPosition = blockNumber % 8;
        int offset = BITMAP_OFFSET + byteIndex;

              //! Expliquez pourquoi on utilise un décallage de bit ?

        return true;
    }

    public int isBlockUsed(int blockNumber) {
        if (blockNumber < 0 || blockNumber >= NUM_BLOCKS)
                    return -1;

        int byteIndex = blockNumber / 8;
        int bitPosition = blockNumber % 8;
        int offset = BITMAP_OFFSET + byteIndex;

        return (filesystemMemory[offset] >> bitPosition) & 1;
    }

    public int allocateBlock() {
        // Trouvez un block libre
                // Dans ce cas, marqué le comme non libre
                // Et retourner le numéro du block
        return -1; // Pas de bloc libre
    }

    public byte[] getFilesystemMemory() {
        return filesystemMemory;
    }

    public void saveToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream("memfs.raw");
        fos.write(filesystemMemory);
        fos.close();
    }

    public void loadFromFile() throws IOException {
            // Complété la sauvegarde du system avec FileInputStream
    }
}