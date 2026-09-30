import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Order(String id, LocalDateTime orderDate, List<OrderItem> orderItems) {
    public Order(List<OrderItem> productsWithQuantity) {
        String id = UUID.randomUUID().toString();
        LocalDateTime orderDate = LocalDateTime.now();
        this(id, orderDate, productsWithQuantity);
    }

    public Order withOrderItems(List<OrderItem> orderItems) {
        return new Order(id, orderDate, orderItems);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            BigDecimal totalPriceForProduct = orderItem.product().price().multiply(BigDecimal.valueOf(orderItem.quantity()));
            totalPrice = totalPrice.add(totalPriceForProduct);
        }
        return totalPrice;
    }
}
