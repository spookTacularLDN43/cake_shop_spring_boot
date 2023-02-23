package root.it.cupcake.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import root.it.cupcake.services.ICartService;
import root.it.cupcake.session.SessionObject;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;
    @Resource
    SessionObject sessionObject;

    @GetMapping("/{id}")
    public String addToCart(@PathVariable int id) {
        this.cartService.addToBasket(id);
        return "redirect:/main";
    }

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cakes", this.sessionObject.getCart());
        model.addAttribute("user", this.sessionObject.getUser());
        model.addAttribute("bill", this.cartService.calculateBill());
        return "cart";
    }
}
