import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo {
    private List<Order> orders;

    public OrderListRepo() {
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    public Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public int getOrdersCount() {
        return orders.size();
    }

    @Override
    public String toString() {
        return "OrderListRepo{" +
                "orders=" + orders +
                '}';
    }
}
