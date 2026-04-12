import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThrowsDemo {

    // This method passes exception responsibility to caller.
    public static String readFirstLine(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.readLine();
        }
    }

    public static void main(String[] args) {
        try {
            String line = readFirstLine("data.txt");
            System.out.println("First line: " + line);

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }
}
