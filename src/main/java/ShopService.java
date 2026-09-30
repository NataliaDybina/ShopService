import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public void placeOrder(List<String> productIds) {
        List<Product> productsToOrder = new ArrayList<>();
        for (String productId : productIds) {
            Product product = productRepo.getProductById(productId);
            if (product == null) {
                System.out.println("Product with id " + productId + " not found");
            }
            productsToOrder.add(product);
        }
        orderRepo.addOrder(new Order(productsToOrder));
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
}
