import lombok.With;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@With
public record Order(String id, Instant date, OrderStatus status, List<OrderItem> orderItems) {
    public Order(String id, List<OrderItem> productsWithQuantity) {
        Instant orderDate = Instant.now();
        this(id, orderDate, OrderStatus.PROCESSING, productsWithQuantity);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            BigDecimal totalPriceForProduct = orderItem.product().price().multiply(BigDecimal.valueOf(orderItem.quantity()));
            totalPrice = totalPrice.add(totalPriceForProduct);
        }
        return totalPrice;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime orderDate = LocalDateTime.ofInstant(date(), ZoneId.systemDefault());
        return "Order number: " + id + ", date: " + orderDate.format(formatter)
                + ", status: " + status
                + ", total: " + getTotalPrice() + "$\n " + orderItems;
    }
}
