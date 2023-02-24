package root.it.cupcake.services.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import root.it.cupcake.model.Cake;
import root.it.cupcake.services.IOrderService;
import root.it.cupcake.session.SessionObject;

import java.util.List;

@Controller
public class OrderService implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Override
    public void confirmOrder() {
        List<Cake> orderedCakes = this.sessionObject.getCart();
    }
}
