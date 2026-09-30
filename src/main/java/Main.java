import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("m1", "Milk", BigDecimal.valueOf(2.30), true);
        Product product2 = new Product("f1", "Banana", BigDecimal.valueOf(4.50), true);
        Product product3 = new Product("b1", "Bread", BigDecimal.valueOf(0.80), true);
        Product product4 = new Product("m2", "Cheese", BigDecimal.valueOf(1.50), true);

        List<Product> allProducts = Arrays.asList(product1, product2, product3, product4);
        ProductRepo productRepo = new ProductRepo(allProducts);

        List<Product> productList = Arrays.asList(product1, product3, product4);
        List<Product> productList2 = Arrays.asList(product2, product4);

        Order order1 = new Order(productList);
        Order order2 = new Order(productList2);
        Order order3 = new Order(productList2);

        OrderRepo orderRepo = new OrderListRepo();
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        orderRepo.addOrder(order3);

        OrderRepo orderRepo2 = new OrderMapRepo();
        orderRepo2.addOrder(order1);
        orderRepo2.addOrder(order2);

        ShopService shopService = new ShopService(productRepo, orderRepo);
        System.out.println(shopService.getOrderRepo().getOrdersCount());
        List<String> productsIdToOrder = Arrays.asList("m1", "m2", "b1");
        shopService.placeOrder(productsIdToOrder);
        System.out.println(shopService.getOrderRepo().getOrdersCount());
    }
}
