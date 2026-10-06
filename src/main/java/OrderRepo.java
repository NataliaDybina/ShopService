import java.util.List;

public interface OrderRepo {
    public void addOrder(Order order);

    public void removeOrder(Order order);

    public Order getOrderById(String id);

    public int getOrdersCount();

    public List<Order> getOrders();

    public String toString();
}
