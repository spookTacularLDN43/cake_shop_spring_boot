package root.it.cupcake.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.it.cupcake.model.Order;
import root.it.cupcake.model.OrderPosition;
import root.it.cupcake.model.User;
import root.it.cupcake.rest.model.RestOrder;
import root.it.cupcake.services.IOrderService;
import root.it.cupcake.services.IUserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    @Autowired
    IOrderService orderService;
    @Autowired
    IUserService userService;

    @GetMapping
    public List<RestOrder> getAllOrders() {
        return dBModelListToRestModelList(this.orderService.getAllOrders());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RestOrder>> getOrdersForUser(@PathVariable int id) {
        User user = this.userService.getUserById(id);
        if(user == null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok(dBModelListToRestModelList(this.orderService.getOrderForUserById(id)));
        }
    }

    private List<RestOrder> dBModelListToRestModelList(List<Order> orders) {
        List<RestOrder> restOrders = new ArrayList<>();
        for (Order order : orders) {
            restOrders.add(dBModelToRestModel(order));
        }
        return restOrders;

    }

    private RestOrder dBModelToRestModel(Order order) {
        RestOrder restOrder = new RestOrder();
        restOrder.setId(order.getId());
        restOrder.setPrice(order.getPrice());
        restOrder.setStatus(order.getStatus());
        restOrder.setUser("http://localhost:8080/users/" + order.getUser().getId());
        for (OrderPosition orderPosition : order.getPositions()) {
            String url = "http://localhost:8080/orderpositions/" + orderPosition.getId();
            restOrder.getPositions().add(url);
        }
        return restOrder;
    }
}
