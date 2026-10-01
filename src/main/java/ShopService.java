import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopService {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public List<OrderItem> createOrderItemsList(Map<String, Integer> productsToOrder) {
        List<OrderItem> orderItemsList = new ArrayList<>();
        for (String productId : productsToOrder.keySet()) {
            Product product = productRepo.getProductById(productId);
            if (product == null) {
                System.out.println("Product with id " + productId + " not found");
            }
            OrderItem orderItem = new OrderItem(product, productsToOrder.get(productId));
            orderItemsList.add(orderItem);
        }
        return orderItemsList;
    }

    public String placeOrder(Map<String, Integer> productsToOrder) {
        Order order = new Order(createOrderItemsList(productsToOrder));
        orderRepo.addOrder(order);
        return order.id();
    }

    public void changeOrder(String orderId, Map<String, Integer> productsToOrder) {
        Order order = orderRepo.getOrderById(orderId);
        order.withOrderItems(createOrderItemsList(productsToOrder));
    }

    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public OrderRepo getOrderRepo() {
        return orderRepo;
    }

    public void setOrderRepo(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
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

    public void deleteOrder(String orderId) {
        Order order = orderRepo.getOrderById(orderId);
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
        Product product = productRepo.getProductById(productId);
        return product != null;
    }

    public boolean checkIfOrderExists(String orderId) {
        Order order = orderRepo.getOrderById(orderId);
        return order != null;
    }

}
