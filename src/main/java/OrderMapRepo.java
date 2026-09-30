import java.util.HashMap;
import java.util.Map;

public class OrderMapRepo implements OrderRepo {
    private Map<String, Order> orders;

    public OrderMapRepo() {
        orders = new HashMap<>();
    }

    public Map<String, Order> getAllOrders() {
        return orders;
    }

    public void setOrders(Map<String, Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.put(order.id(), order);
    }

    public void removeOrder(Order order) {
        orders.remove(order.id());
    }

    public Order getOrderById(String id) {
        return orders.get(id);
    }

    @Override
    public int getOrdersCount() {
        return orders.size();
    }

    @Override
    public String toString() {
        return "OrderMapRepo{" +
                "orders=" + orders +
                '}';
    }
}
