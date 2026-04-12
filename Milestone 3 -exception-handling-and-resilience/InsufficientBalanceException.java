public class InsufficientBalanceException extends RuntimeException {
    private final double amount;

    public InsufficientBalanceException(double amount, double currentBalance) {
        super("Insufficient balance. Tried to withdraw: $" + amount + ", available: $" + currentBalance);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
