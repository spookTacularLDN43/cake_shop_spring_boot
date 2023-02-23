package root.it.cupcake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import root.it.cupcake.model.Cake;
import root.it.cupcake.services.IAdminService;
import root.it.cupcake.services.IUserService;

@Controller
@RequestMapping("/addcake")
public class AdminController {

    @Autowired
    IAdminService adminService;

    @GetMapping
    public String addCake(Model model) {
        model.addAttribute("addproduct", new Cake());
        return "addcake";
    }

    @PostMapping
    public String saveCake(@ModelAttribute Cake cake) {
    this.adminService.addCake(cake);
        return "redirect:/addcake";
    }
}
