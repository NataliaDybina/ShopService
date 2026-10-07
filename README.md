# ShopService

A Java-based e-commerce order and inventory management service built with modern Java features, clean architecture
principles, and unit testing.

---

## Features

- **Product Inventory Management**: Load products and stock quantities dynamically from CSV files. Safety with
  `Optional<Product>` to prevent null pointer exceptions.
- **Order Lifecycle Management**:
    - Place, update, and cancel orders with real-time inventory adjustments.
    - Track order statuses (`PROCESSING`, `IN_DELIVERY`, `COMPLETED`).
    - Search orders by status using Java Streams API.
    - Find the oldest active order per status (`getOldestOrderPerStatus()`).
- **Flexible Extensions**:
    - Abstract ID Generation strategy (`IdService` interface with `UUIDService` implementation) for easy mock-testing.
    - Polymorphic order persistence (`OrderListRepo` and `OrderMapRepo`).
- **Interactive CLI Console**: An ANSI-colored command-line interface for customer operations.
- **Unit Testing**: Comprehensive test coverage using **JUnit 5**.

---

## Tech Stack & Dependencies

- **Java**: 17+ (using Records, Java Streams API, `Instant`, `Optional`)
- **Lombok**: `@With`, `@Getter`, `@Data`, `@AllArgsConstructor` annotations
- **Testing**: JUnit 5, AssertJ / Standard Assertions
- **Build Tool**: Maven / Gradle

---

## Class Architecture & Diagram

![Class Diagram](images/class_diagram.png)

<details>
<summary>Click to view Mermaid Diagram Code</summary>

```mermaid
classDiagram
    class Product {
        <<record>>
        -String id
        -String name
        -BigDecimal price
    }

    class OrderItem {
        <<record>>
        -Product product
        -int quantity
    }

    class OrderStatus {
        <<enumeration>>
        PROCESSING
        IN_DELIVERY
        COMPLETED
    }

    class Order {
        <<record>>
        -String id
        -Instant date
        -OrderStatus status
        -List~OrderItem~ orderItems
    }

    class OrderRepo {
        <<interface>>
        +addOrder(Order order)*
        +removeOrder(Order order)*
        +updateOrder(Order order)*
        +getOrderById(String id) Order*
        +getOrders() List~Order~*
    }

    class OrderListRepo {
        -List~Order~ orders
    }

    class OrderMapRepo {
        -Map~String, Order~ orders
    }

    class ProductRepo {
        -Map~String, Product~ products
        -Map~String, Integer~ quantities
        +getProductById(String id) Optional~Product~
    }

    class IdService {
        <<interface>>
        +generateId() String*
    }

    class UUIDService {
        +generateId() String
    }

    class ShopService {
        -ProductRepo productRepo
        -OrderRepo orderRepo
        -IdService idService
        +placeOrder(Map~String, Integer~ productsToOrder) String
        +changeOrder(String orderId, Map~String, Integer~ productsToOrder) Order
        +getOrdersByOrderStatus(OrderStatus orderStatus) List~Order~
        +getOldestOrderPerStatus() Map~OrderStatus, Order~
    }

    OrderRepo <|.. OrderListRepo
    OrderRepo <|.. OrderMapRepo
    IdService <|.. UUIDService
    OrderItem *-- Product
    Order *-- OrderItem
    Order *-- OrderStatus
    ShopService --> ProductRepo
    ShopService --> OrderRepo
    ShopService --> IdService
```

</details>