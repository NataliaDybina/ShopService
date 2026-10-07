import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static void main(String[] args) {
       /* Product product1 = new Product("m1", "Milk", BigDecimal.valueOf(2.30));
        Product product2 = new Product("f1", "Banana", BigDecimal.valueOf(4.50));
        Product product3 = new Product("b1", "Bread", BigDecimal.valueOf(0.80));
        Product product4 = new Product("m2", "Cheese", BigDecimal.valueOf(1.50));

        Map<String, Product> products = new HashMap<>();
        products.put("m1", product1);
        products.put("m2", product4);
        products.put("f1", product2);
        products.put("b1", product3);
        Map<String, Integer> productQuantity = new HashMap<>();
        productQuantity.put("m1", 3);
        productQuantity.put("m2", 2);
        productQuantity.put("b1", 1);
        productQuantity.put("f1", 2);
        ProductRepo productRepo = new ProductRepo(products, productQuantity);

        OrderItem order1Item1 = new OrderItem(product1, 1);
        OrderItem order1Item2 = new OrderItem(product2, 2);
        OrderItem order1Item3 = new OrderItem(product3, 3);
        Order order1 = new Order(Arrays.asList(order1Item1, order1Item2, order1Item3));

        OrderItem order2Item1 = new OrderItem(product1, 2);
        OrderItem order2Item2 = new OrderItem(product2, 1);
        OrderItem order2Item3 = new OrderItem(product4, 2);
        Order order2 = new Order(Arrays.asList(order2Item1, order2Item2, order2Item3));

        OrderRepo orderRepo = new OrderListRepo();
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        OrderRepo orderRepo2 = new OrderMapRepo();
        orderRepo2.addOrder(order1);
        orderRepo2.addOrder(order2);

        ShopService shopService = new ShopService(productRepo, orderRepo);
        System.out.println(shopService.getOrderRepo().getOrdersCount());

        Map<String, Integer> productsIdAndQuantityToOrder = new HashMap<>();
        productsIdAndQuantityToOrder.put("m1", 2);
        productsIdAndQuantityToOrder.put("f1", 3);
        productsIdAndQuantityToOrder.put("b1", 1);
        shopService.placeOrder(productsIdAndQuantityToOrder);

        System.out.println(shopService.getOrderRepo().getOrdersCount());
        System.out.println(shopService.getOrdersByOrderStatus(OrderStatus.PROCESSING));*/

        ProductRepo productRepo = CsvProductLoader.loadProductsFromCsv("products.csv");
        OrderRepo orderRepo = new OrderListRepo();
        IdService idService = new UUIDService();
        ShopService shopService = new ShopService(productRepo, orderRepo, idService);
        ShopConsole console = new ShopConsole(shopService);
        console.start();
    }
}
