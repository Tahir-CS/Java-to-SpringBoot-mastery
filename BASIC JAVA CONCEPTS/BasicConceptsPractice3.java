public class BasicConceptsPractice3 {
    
    public static int add(int a, int b) {
        return a + b;
    }

    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int result = add(10, 20);
        System.out.println("Sum: " + result);

        int[] numbers = {1, 2, 3, 4, 5};
        System.out.print("Array: ");
        printArray(numbers);
    }
}

public class PasswordAnalyzer {

    public static void main(String[] args) {
        
        System.out.println("--- Starting Password Gauntlet ---\n");

        
        if (args.length == 0) {
            System.out.println("No passwords provided to test!");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            String currentPassword = passwordsToTest[i];

            boolean lengthCheck = isLongEnough(currentPassword);
            boolean numberCheck = hasNumber(currentPassword);
            
          
            boolean isStrong = lengthCheck && numberCheck;
           
            printResult(currentPassword, isStrong);
        }
    }

    public static boolean isLongEnough(String password) {
        return password.length() >= 8;
    }

    public static boolean hasNumber(String password) {
      
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true; 
        }
        return false;
    }

    public static void printResult(String password, boolean isValid) {
        if (isValid) {
            System.out.println("STRONG: " + password);
        } else {
            System.out.println(" WEAK:   " + password);
        }
    }
}