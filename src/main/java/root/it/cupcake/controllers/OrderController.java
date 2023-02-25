package root.it.cupcake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import root.it.cupcake.services.IOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @GetMapping
    public String confirmOrder() {
        this.orderService.confirmOrder();
        return "redirect:/main";
    }
}
