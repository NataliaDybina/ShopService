import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {
    private ProductRepo productRepo;
    private ShopService shopService;
    private OrderRepo orderRepo;

    @BeforeEach
    void setUp() {
        productRepo = new ProductRepo();
        orderRepo = new OrderListRepo();
        shopService = new ShopService(productRepo, orderRepo);
    }

    @Test
    void changeOrder_shouldChangeOrder() {
        Product product1 = new Product("m1", "Milk", BigDecimal.valueOf(2.30));
        Product product2 = new Product("f1", "Banana", BigDecimal.valueOf(4.50));
        Product product3 = new Product("m2", "Butter", BigDecimal.valueOf(3.40));
        productRepo.addProduct(product1, 1);
        productRepo.addProduct(product2, 1);
        productRepo.addProduct(product3, 1);

        Map<String, Integer> productsToOrder = new HashMap<>();
        productsToOrder.put("m1", 1);
        productsToOrder.put("f1", 2);
        String orderId = shopService.placeOrder(productsToOrder);
        Map<String, Integer> changedProductsToOrder = new HashMap<>();
        changedProductsToOrder.put("m1", 2);
        changedProductsToOrder.put("f1", 1);
        changedProductsToOrder.put("m2", 1);

        Instant beforeUpdate = Instant.now();
        Order updatedOrder = shopService.changeOrder(orderId, changedProductsToOrder);
        Instant afterUpdate = Instant.now();
        Order expectedOrder = new Order(orderId, updatedOrder.date(), OrderStatus.PROCESSING, shopService.createOrderItemsList(changedProductsToOrder));
        assertEquals(updatedOrder, expectedOrder);
        assertFalse(updatedOrder.date().isBefore(beforeUpdate));
        assertFalse(updatedOrder.date().isAfter(afterUpdate));
    }

    @Test
    void getProductById_shouldReturnProduct() {
        Product product = new Product("m1", "Milk", BigDecimal.valueOf(1.29));
        productRepo.addProduct(product, 1);

        Optional<Product> foundProduct = productRepo.getProductById("m1");
        assertTrue(foundProduct.isPresent(), "Product not found");
        assertEquals("Milk", foundProduct.get().name());
        assertEquals(product, foundProduct.get());
    }

    @Test
    void getProductById_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> shopService.getProductById("non-existent-id"));
    }

    @Test
    void getOrdersByOrderStatus_shouldReturnOrders() {
        Product product1 = new Product("m1", "Milk", BigDecimal.valueOf(2.30));
        Product product2 = new Product("f1", "Banana", BigDecimal.valueOf(4.50));
        productRepo.addProduct(product1, 1);
        productRepo.addProduct(product2, 1);
        OrderItem order1Item1 = new OrderItem(product1, 1);
        OrderItem order1Item2 = new OrderItem(product2, 2);
        Order order1 = new Order(Arrays.asList(order1Item1, order1Item2));
        Order order2 = new Order(Arrays.asList(order1Item1, order1Item2));
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        order2 = shopService.updateOrderStatus(order2.id(), OrderStatus.COMPLETED);
        orderRepo.updateOrder(order2);
        assertEquals(List.of(order1), shopService.getOrdersByOrderStatus(OrderStatus.PROCESSING));
    }

    @Test
    void getOrdersByOrderStatus_shouldReturnEmptyList() {
        Product product1 = new Product("m1", "Milk", BigDecimal.valueOf(2.30));
        Product product2 = new Product("f1", "Banana", BigDecimal.valueOf(4.50));
        productRepo.addProduct(product1, 1);
        productRepo.addProduct(product2, 1);
        OrderItem order1Item1 = new OrderItem(product1, 1);
        OrderItem order1Item2 = new OrderItem(product2, 2);
        Order order1 = new Order(Arrays.asList(order1Item1, order1Item2));
        Order order2 = new Order(Arrays.asList(order1Item1, order1Item2));
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        assertEquals(List.of(), shopService.getOrdersByOrderStatus(OrderStatus.COMPLETED));
    }

    @Test
    void updateOrderStatus_shouldUpdateOrderStatus() {
        Product product1 = new Product("m1", "Milk", BigDecimal.valueOf(2.30));
        Product product2 = new Product("f1", "Banana", BigDecimal.valueOf(4.50));
        productRepo.addProduct(product1, 1);
        productRepo.addProduct(product2, 1);
        OrderItem order1Item1 = new OrderItem(product1, 1);
        OrderItem order1Item2 = new OrderItem(product2, 2);
        Order order = new Order(Arrays.asList(order1Item1, order1Item2));
        orderRepo.addOrder(order);
        Order updatedOrder = shopService.updateOrderStatus(order.id(), OrderStatus.COMPLETED);
        assertEquals(OrderStatus.COMPLETED, updatedOrder.status());
    }
}