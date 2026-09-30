import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShopConsole {
    ShopService shopService;
    Scanner input = new Scanner(System.in);

    public ShopConsole(ShopService shopService) {
        this.shopService = shopService;
    }

    public void start() {
        System.out.println("Welcome to the Shopping System");
        boolean running = true;
        do {
            System.out.println("What would you like to do?");
            System.out.println("0. View the products catalog");
            System.out.println("1. Create Order");
            System.out.println("2. Delete Order");
            System.out.println("3. Update Order");
            System.out.println("4. Exit");
            System.out.println("Enter your choice");
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 0:
                    showProducts();
                    break;
                case 1:
                    createOrder();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Good Bye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (running);
    }

    public void showProducts() {
        System.out.println("We have today:");
        shopService.printAllProducts();
    }

    public void createOrder() {
        boolean running = true;
        Map<String, Integer> productsIdAndQuantityToOrder = new HashMap<>();
        do {
            System.out.println("Enter the product id:");
            String productId = input.nextLine();

            System.out.println("Enter quantity:");
            Integer productQuantity = Integer.parseInt(input.nextLine());
            productsIdAndQuantityToOrder.put(productId, productQuantity);
            System.out.println("Now you can 1-add new product, 2-delete product, " +
                    "3-update quantity of this product, 4-place the order, 5-exit");
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    productsIdAndQuantityToOrder.remove(productId);
                    System.out.println("Product " + productId + " has been deleted");
                    break;
                case 3:
                    System.out.println("Enter quantity:");
                    int quantity = Integer.parseInt(input.nextLine());
                    productsIdAndQuantityToOrder.replace(productId, quantity);
                    System.out.println("Quantity has been replaced");
                    break;
                case 4:
                    String orderId = shopService.placeOrder(productsIdAndQuantityToOrder);
                    shopService.printOrder(orderId);
                    return;
                case 5:
                    System.out.println("Good Bye!");
                    running = false;
            }
        } while (running);


    }
}
