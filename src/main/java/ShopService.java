import java.math.BigDecimal;
import java.util.ArrayList;
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

    public void placeOrder(Map<String, Integer> productsToOrder) {
        orderRepo.addOrder(new Order(createOrderItemsList(productsToOrder)));
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
}
