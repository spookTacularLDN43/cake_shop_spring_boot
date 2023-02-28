package root.it.cupcake.services.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.it.cupcake.dao.ICakeDAO;
import root.it.cupcake.dao.IOrderDAO;
import root.it.cupcake.model.Cake;
import root.it.cupcake.model.Order;
import root.it.cupcake.model.OrderPosition;
import root.it.cupcake.services.IOrderService;
import root.it.cupcake.session.SessionObject;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    SessionObject sessionObject;
    @Autowired
    IOrderDAO orderDAO;
    @Autowired
    ICakeDAO cakeDAO;

    @Override
    public void confirmOrder() {
        List<Cake> orderedCakes = this.sessionObject.getCart();
        for (Cake cake:orderedCakes){
            Cake cakeFromDB = this.cakeDAO.getCakeById(cake.getId());
            if(cakeFromDB.getPieces()<cake.getPieces()){
                return;
            }
        }
        Order order = new Order();
        order.setUser(this.sessionObject.getUser());

        double bill = 0;
        for (Cake cake : orderedCakes) {
            bill = bill + cake.getPieces() * cake.getPrice();
        }
        order.setPrice(bill);
        order.setStatus(Order.Status.ORDERED);

        for (Cake cake : orderedCakes) {
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setPieces(cake.getPieces());
            orderPosition.setOrder(order);
            orderPosition.setCake(cake);

            order.getPositions().add(orderPosition);
        }
        this.orderDAO.persistOrder(order);

        for (Cake cake : orderedCakes) {
            Cake cakeFromDB = this.cakeDAO.getCakeById(cake.getId());
            cakeFromDB.setPieces(cakeFromDB.getPieces() - cake.getPieces());
            this.cakeDAO.updateCake(cakeFromDB);
        }
        this.sessionObject.getCart().clear();
    }

    @Override
    public List<Order> getOrders() {
        return this.orderDAO.getCurrentUserOrders(this.sessionObject.getUser().getId());
    }

}
