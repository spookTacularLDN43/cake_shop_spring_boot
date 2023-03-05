package root.it.cupcake.services;

import root.it.cupcake.model.Order;
import root.it.cupcake.model.User;

import java.util.List;

public interface IOrderService {

    void confirmOrder();

    List<Order> getOrders();

    List<Order> getAllOrders();

    List<Order> getOrderForUserById(int id);
}
