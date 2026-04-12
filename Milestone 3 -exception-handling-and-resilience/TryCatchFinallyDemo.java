public class TryCatchFinallyDemo {

    public static void main(String[] args) {
        try {
            // Risky code: index 10 does not exist in this array.
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[10]);

            // This line is skipped because exception is thrown above.
            System.out.println("This will not print.");

        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle the specific failure gracefully.
            System.out.println("Caught exception: index is out of range.");
            System.out.println("Details: " + e.getMessage());

        } finally {
            // finally always runs, good for cleanup tasks.
            System.out.println("Finally block executed.");
        }

        // Program continues normally after catch/finally.
        System.out.println("Program is still running.");
    }
}
