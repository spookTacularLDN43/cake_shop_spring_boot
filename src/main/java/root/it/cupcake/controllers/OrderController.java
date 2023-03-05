package root.it.cupcake.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import root.it.cupcake.model.Order;
import root.it.cupcake.services.IOrderService;
import root.it.cupcake.session.SessionObject;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;
    @Resource
    SessionObject sessionObject;

    @GetMapping
    public String confirmOrder() {
        if(this.sessionObject.isLogged()){
            this.orderService.confirmOrder();
            return "redirect:/main";
        }else {
            return "redirect:/login";
        }
    }

    @GetMapping("/myorders")
    public String orders(Model model){
            model.addAttribute("user", this.sessionObject.getUser());
            List<Order> orders = this.orderService.getOrders();
            model.addAttribute("orders", orders);
            return "orders";
        }

    }

