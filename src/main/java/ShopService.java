import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Data
@AllArgsConstructor
public class ShopService {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    private IdService idService;

    public List<OrderItem> createOrderItemsList(Map<String, Integer> productsToOrder) {
        List<OrderItem> orderItemsList = new ArrayList<>();
        for (String productId : productsToOrder.keySet()) {
            Product product = getProductById(productId);
            OrderItem orderItem = new OrderItem(product, productsToOrder.get(productId));
            orderItemsList.add(orderItem);
        }
        return orderItemsList;
    }

    public String placeOrder(Map<String, Integer> productsToOrder) {
        String orderId = idService.generateId();
        Order order = new Order(orderId, createOrderItemsList(productsToOrder));
        orderRepo.addOrder(order);
        productRepo.decreaseQuantity(productsToOrder);
        return order.id();
    }

    public Order changeOrder(String orderId, Map<String, Integer> productsToOrder) {
        Order order = orderRepo.getOrderById(orderId);
        Order updatedOrder = order.withOrderItems(createOrderItemsList(productsToOrder)).withDate(Instant.now());
        orderRepo.updateOrder(updatedOrder);
        //productRepo.decreaseQuantity(productsToOrder); //actually it should increase or decrease depending
        // on changes in the order, but I didn't have time to finish it
        return updatedOrder;
    }

    public int countOrders() {
        return orderRepo.getOrdersCount();
    }

    public BigDecimal getTotalPrice(String orderId) {
        Order order = orderRepo.getOrderById(orderId);
        return order.getTotalPrice();
    }

    public void printAllProducts() {
        System.out.println(productRepo.printProducts());
    }

    public void printOrder(String orderId) {
        Order order = orderRepo.getOrderById(orderId);
        System.out.println(order);
    }

    public Product getProductById(String productId) {
        return productRepo.getProductById(productId).orElseThrow(() -> new IllegalArgumentException("Product with id " + productId + " not found"));
    }

    public Map<String, Integer> getProductsToOrder(List<OrderItem> orderItems) {
        Map<String, Integer> productsToOrder = new HashMap<>();
        for (OrderItem orderItem : orderItems) {
            productsToOrder.put(orderItem.product().id(), orderItem.quantity());
        }
        return productsToOrder;
    }

    public void deleteOrder(String orderId) {
        Order order = orderRepo.getOrderById(orderId);
        productRepo.increaseQuantity(getProductsToOrder(order.orderItems()));
        orderRepo.removeOrder(order);
    }

    public Map<String, Integer> getProductsIdAndQuantityToOrder(String orderId) {
        Map<String, Integer> productsIdAndQuantityToOrder = new HashMap<>();
        Order order = orderRepo.getOrderById(orderId);
        order.orderItems().forEach(orderItem ->
                productsIdAndQuantityToOrder.put(orderItem.product().id(), orderItem.quantity()));
        return productsIdAndQuantityToOrder;
    }

    public boolean checkIfProductExists(String productId) {
        return productRepo.getProductById(productId).isPresent();
    }

    public boolean checkIfOrderExists(String orderId) {
        Order order = orderRepo.getOrderById(orderId);
        return order != null;
    }

    public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.status().equals(orderStatus)).toList();
    }

    public Order updateOrderStatus(String orderId, OrderStatus orderStatus) {
        return orderRepo.getOrderById(orderId).withStatus(orderStatus);

    }
}
