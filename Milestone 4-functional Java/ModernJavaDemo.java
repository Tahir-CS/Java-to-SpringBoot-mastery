import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ModernJavaDemo {

    // Record for clean immutable product data.
    record Product(String name, String category, double price) {
    }

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Laptop", "Electronics", 999.99),
                new Product("Phone", "Electronics", 599.99),
                new Product("Notebook", "Stationery", 4.99),
                new Product("Pen", "Stationery", 1.99),
                new Product("Tablet", "Electronics", 399.99)
        );

        // Stream pipeline: filter -> sort -> map -> collect.
        List<String> affordableElectronics = products.stream()
                .filter(p -> p.category().equals("Electronics"))
                .filter(p -> p.price() < 700.0)
                .sorted(Comparator.comparingDouble(Product::price))
                .map(p -> p.name() + " ($" + p.price() + ")")
                .toList();

        System.out.println("Affordable electronics:");
        affordableElectronics.forEach(System.out::println);

        // Optional result from max operation.
        Optional<Product> mostExpensive = products.stream()
                .max(Comparator.comparingDouble(Product::price));

        mostExpensive.ifPresent(p ->
                System.out.println("Most expensive: " + p.name() + " at $" + p.price())
        );

        // Aggregate numeric result.
        double totalInventoryValue = products.stream()
                .mapToDouble(Product::price)
                .sum();

        System.out.println("Total inventory value: $" + totalInventoryValue);
    }
}
