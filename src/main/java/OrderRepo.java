import java.util.List;

public interface OrderRepo {
    void addOrder(Order order);

    void removeOrder(Order order);

    void updateOrder(Order order);

    Order getOrderById(String id);

    int getOrdersCount();

    List<Order> getOrders();

    String toString();
}
