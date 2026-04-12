public class CustomExceptionDemo {

    public static void main(String[] args) {
        // Demo 1: Bank account custom exception.
        BankAccount account = new BankAccount("Ali", 1000.0);

        try {
            account.withdraw(500.0);
            System.out.println("Balance after first withdrawal: $" + account.getBalance());

            // This will throw InsufficientBalanceException.
            account.withdraw(800.0);

        } catch (InsufficientBalanceException e) {
            System.out.println("Bank error: " + e.getMessage());
            System.out.println("Attempted amount: $" + e.getAmount());

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

        System.out.println("---");

        // Demo 2: User service custom exception.
        UserService service = new UserService();
        String[] lookups = {"ali123", "nobody999", "sara456"};

        for (String username : lookups) {
            try {
                String fullName = service.findUser(username);
                System.out.println("Found user: " + fullName);

            } catch (UserNotFoundException e) {
                System.out.println("User lookup error: " + e.getMessage());
            }
        }
    }
}
