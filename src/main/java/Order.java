import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Order(String id, LocalDateTime orderDate, List<Product> products) {
    public Order(List<Product> products) {
        String id = UUID.randomUUID().toString();
        LocalDateTime orderDate = LocalDateTime.now();
        this(id, orderDate, products);
    }
}
