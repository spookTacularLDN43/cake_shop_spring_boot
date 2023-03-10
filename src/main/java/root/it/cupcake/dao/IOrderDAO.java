package root.it.cupcake.dao;

import root.it.cupcake.model.Order;

import java.util.List;

public interface IOrderDAO {
    void persistOrder(Order order);

    List<Order> getUserOrders(int userId);

    List<Order> getAllOrders();
}
