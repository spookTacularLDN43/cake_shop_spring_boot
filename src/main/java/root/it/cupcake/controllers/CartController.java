package root.it.cupcake.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import root.it.cupcake.database.ICakeRepository;
import root.it.cupcake.model.Cake;
import root.it.cupcake.session.SessionObject;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICakeRepository cakeRepository;
    @Resource
    SessionObject sessionObject;

    @GetMapping("/{name}")
    public String addToCart(@PathVariable String name) {
        if (this.sessionObject.isLogged()) {
            for (Cake cakeFromCart:this.sessionObject.getCart()) {
                if(cakeFromCart.getName().equals(name)){
                    cakeFromCart.setPieces(cakeFromCart.getPieces()+1);
                    return "redirect:/main";
                }
            }
            Cake cake = (Cake) this.cakeRepository.getCakeByName(name).clone();
            cake.setPieces(1);
            this.sessionObject.getCart().add(cake);
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping
    public String showCart(Model model) {
        if (this.sessionObject.isLogged()) {
            model.addAttribute("cakes", this.sessionObject.getCart());
            model.addAttribute("user", this.sessionObject.getUser());
            double bill = 0;
            for(Cake cake:this.sessionObject.getCart()){
                bill = bill + cake.getPrice()*cake.getPieces();
            }
            model.addAttribute("bill", bill);
            return "cart";
        } else {
            return "redirect:/login";
        }
    }
}
