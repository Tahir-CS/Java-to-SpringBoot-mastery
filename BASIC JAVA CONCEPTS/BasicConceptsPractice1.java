public class BasicConceptsPractice1 {
    public static void main(String[] args) {
        int age = 25;
        double salary = 50000.50;
        char grade = 'A';
        boolean isEmployed = true;
        String name = "Tahir";

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Grade: " + grade);
        System.out.println("Employed: " + isEmployed);

        
        int sum = age + 5;
        double bonus = salary * 0.10;
        System.out.println("Sum: " + sum);
        System.out.println("Bonus: " + bonus);
    }
} 
public class AtmSimulator {

    static double currentBalance = 500.00;

    public static void main(String[] args) {
        System.out.println("--- Welcome to JavaBank ---");
        System.out.println("Starting Balance: $" + currentBalance + "\n");

        double[] pendingTransactions = {200.00, -50.00, -800.00, 15.50, -100.00};
        
        int index = 0;

        while (index < pendingTransactions.length) {
            double amount = pendingTransactions[index];
            
            if (amount > 0) {
                processDeposit(amount);
            } else if (amount < 0) {
                processWithdrawal(amount * -1); 
            } else {
                System.out.println("Ignoring $0.00 transaction.");
            }
            
            index++;
        }

        System.out.println("\n--- All transactions processed ---");
        System.out.println("Final Balance: $" + currentBalance);
    }

    public static void processDeposit(double amount) {
        currentBalance = currentBalance + amount;
        System.out.println("✅ Deposited $" + amount + " | New Balance: $" + currentBalance);
    }

    public static void processWithdrawal(double amount) {
        if (amount > currentBalance) {
            System.out.println("❌ DECLINED: Tried to withdraw $" + amount + " (Insufficient Funds)");
        } else {
            currentBalance = currentBalance - amount;
            System.out.println("💸 Withdrew $" + amount + " | New Balance: $" + currentBalance);
        }
    }
}