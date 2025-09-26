public class Inode {
    private MemoryManager memoryManager;
    private int inodeNumber;

    // Taille fixe d'un inode en mémoire
    public static final int INODE_SIZE = 128;
    public static final int DIRECT_POINTERS = 10;

    public Inode(MemoryManager memoryManager, int inodeNumber) {
        this.memoryManager = memoryManager;
        this.inodeNumber = inodeNumber;
    }

    private int getInodeOffset() {
        return MemoryManager.INODE_TABLE_OFFSET + (inodeNumber * INODE_SIZE);
    }

    // QUESTION 3: Pourquoi calcule-t-on un offset ?
    public void writeToMemory(int fileType, int fileSize, long creationTime, 
                             long modificationTime, int[] directPointers, 
                             int indirectPointer, short permissions, int linkCount) {
        byte[] memory = memoryManager.getFilesystemMemory();
        int offset = getInodeOffset();

        // Écrire le numéro d'inode
        Utils.writeInt(memory, offset, inodeNumber);
        offset += 4;  // Avancer de 4 octets
                // Il exist une meilleur solution, vérifier la définition de writeInt

                // TODO: Complétez les étapes suivantes !
        // Écrire le type de fichier
        // Écrire la taille
        // Écrire les timestamps
        // Écrire les pointeurs directs
        for (int i = 0; i < DIRECT_POINTERS; i++) {
        }
        // Écrire le pointeur indirect
        // Écrire les permissions
        // Écrire le nombre de liens
    }

    public int getFileType() {
        byte[] memory = memoryManager.getFilesystemMemory();
        int offset = getInodeOffset(); // Skip inode number
        return readInt(memory, offset);
    }

    public int getFileSize() {
        byte[] memory = memoryManager.getFilesystemMemory();
        int offset = getInodeOffset() + 8; // Skip inode number + file type
        return readInt(memory, offset);
    }

    public void setFileSize(int newSize) {
        byte[] memory = memoryManager.getFilesystemMemory();
        int offset = getInodeOffset(); // Skip ...
        writeInt(memory, offset, newSize);
        // Mettre à jour le timestamp de modification
    }

    public int[] getDirectPointers() {
        byte[] memory = memoryManager.getFilesystemMemory();
        int offset = getInodeOffset(); // Skip header fields
        int[] pointers = new int[DIRECT_POINTERS];

        for (int i = 0; i < DIRECT_POINTERS; i++) {
            // pointers[i] = ?
            // offset += ?;
        }
        return pointers;
    }
}