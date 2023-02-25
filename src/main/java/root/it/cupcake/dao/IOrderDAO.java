package root.it.cupcake.dao;

import root.it.cupcake.model.Order;

public interface IOrderDAO {
    void persistOrder(Order order);
}
