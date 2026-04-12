public class MultipleCatchDemo {

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30};

        // Different inputs to trigger different exceptions.
        String[] testInputs = {"1", "abc", "5", "0"};

        for (String input : testInputs) {
            try {
                // Could throw NumberFormatException for non-numeric input.
                int index = Integer.parseInt(input);

                // Could throw ArrayIndexOutOfBoundsException.
                int value = numbers[index];

                // Could throw ArithmeticException when index is 0.
                int result = value / index;

                System.out.println("Input " + input + " -> result: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Input " + input + " -> invalid number format.");

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Input " + input + " -> index out of range (0-2 only).");

            } catch (ArithmeticException e) {
                System.out.println("Input " + input + " -> math error: " + e.getMessage());

            } catch (Exception e) {
                // Keep the general catch last.
                System.out.println("Input " + input + " -> unexpected error: " + e.getMessage());
            }
        }
    }
}
