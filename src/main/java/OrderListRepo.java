import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderListRepo implements OrderRepo {
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    public void updateOrder(Order order) {
        Order oldOrder = getOrderById(order.id());
        orders.set(this.orders.indexOf(oldOrder), order);
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
