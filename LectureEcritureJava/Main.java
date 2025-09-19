import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("firstPPM.ppm")
            byte[] buffer = new byte[128];
            int bytesRead = fis.read(buffer);
            System.out.println(new String(buffer, 0, bytesRead));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}