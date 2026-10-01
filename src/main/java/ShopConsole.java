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
            String choice = input.nextLine();
            switch (choice) {
                case "0":
                    showProducts();
                    break;
                case "1":
                    createOrder();
                    break;
                case "2":
                    deleteOrder();
                    break;
                case "3":
                    updateOrder();
                    break;
                case "4":
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

    public void processOrder(String orderId) {
        boolean running = true;
        Map<String, Integer> productsIdAndQuantityToOrder = orderId != null ?
                shopService.getProductsIdAndQuantityToOrder(orderId) : new HashMap<>();
        do {
            String productId;

            System.out.println("You can 1-add new product");
            if (!productsIdAndQuantityToOrder.isEmpty() || orderId != null) {
                System.out.println(", 2-delete product, " +
                        "3-update quantity of the product, 4-place the order");
            }
            System.out.println(", 5-exit");
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter the product id:");
                    productId = input.nextLine();
                    if (!shopService.checkIfProductExists(productId)) {
                        System.out.println("There is no product with that ID");
                        break;
                    }
                    System.out.println("Enter quantity:");
                    Integer productQuantity = Integer.parseInt(input.nextLine());
                    if (productsIdAndQuantityToOrder.containsKey(productId)) {
                        productsIdAndQuantityToOrder.replace(productId, productsIdAndQuantityToOrder.get(productId) + productQuantity);
                    } else {
                        productsIdAndQuantityToOrder.put(productId, productQuantity);
                    }
                    break;
                case "2":
                    if (productsIdAndQuantityToOrder.isEmpty() && orderId == null) {
                        System.out.println("Invalid number");
                        break;
                    }
                    System.out.println("Enter the product id:");
                    productId = input.nextLine();
                    productsIdAndQuantityToOrder.remove(productId);
                    if (orderId != null) {
                        shopService.changeOrder(orderId, productsIdAndQuantityToOrder);
                    }
                    System.out.println("Product " + productId + " has been deleted");
                    break;
                case "3":
                    if (productsIdAndQuantityToOrder.isEmpty()) {
                        System.out.println("Invalid number");
                        break;
                    }
                    System.out.println("Enter the product id:");
                    productId = input.nextLine();
                    System.out.println("Enter quantity:");
                    int quantity = Integer.parseInt(input.nextLine());
                    productsIdAndQuantityToOrder.replace(productId, quantity);
                    if (orderId != null) {
                        shopService.changeOrder(orderId, productsIdAndQuantityToOrder);
                    }
                    System.out.println("Quantity has been replaced");
                    break;
                case "4":
                    if (productsIdAndQuantityToOrder.isEmpty()) {
                        System.out.println("Invalid number");
                        break;
                    }
                    if (orderId != null) {
                        shopService.changeOrder(orderId, productsIdAndQuantityToOrder);
                    } else {
                        orderId = shopService.placeOrder(productsIdAndQuantityToOrder);
                    }
                    System.out.println("Order has been replaced");
                    System.out.println("Your order:\n");
                    shopService.printOrder(orderId);
                    return;
                case "5":
                    System.out.println("Good Bye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (running);
    }

    public void createOrder() {
        processOrder(null);
    }

    public void deleteOrder() {
        System.out.println("Enter the order number you want to cancel:");
        String orderNumber = input.nextLine();
        if (shopService.checkIfOrderExists(orderNumber)) {
            shopService.deleteOrder(orderNumber);
            System.out.println("Order has been deleted");
        } else {
            System.out.println("Invalid order number");
        }

    }

    public void updateOrder() {
        System.out.println("Enter the order number you want to update:");
        String orderId = input.nextLine();
        if (shopService.checkIfOrderExists(orderId)) {
            System.out.println("Ordered products:");
            shopService.printOrder(orderId);
            processOrder(orderId);
        } else {
            System.out.println("Invalid order number");
        }

    }
}
