import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CheckedExceptionDemo {

    public static void main(String[] args) {
        // Unchecked example (RuntimeException family): not forced by compiler.
        // String name = null;
        // System.out.println(name.length());

        // Checked example: compiler forces handling for file operations.
        try {
            FileReader file = new FileReader("data.txt");
            System.out.println("File opened successfully.");
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("Checked exception caught: file not found.");

        } catch (IOException e) {
            System.out.println("Checked exception caught while closing file: " + e.getMessage());
        }
    }
}
