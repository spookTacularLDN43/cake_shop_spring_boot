package root.it.cupcake.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import root.it.cupcake.database.ICakeRepository;
import root.it.cupcake.model.Cake;
import root.it.cupcake.session.SessionObject;

import java.util.List;

@Controller
@RequestMapping("/main")
public class CommonController {

    @Autowired
    ICakeRepository cakeRepository;

    @Resource
    SessionObject sessionObject;

    @GetMapping
    public String main(Model model) {
        List<Cake> cakeList = this.cakeRepository.getAllCakes();
        model.addAttribute("cakes", cakeList);
        model.addAttribute("user", this.sessionObject.getUser());
        return "main";
    }

    @PostMapping
    public String filter(@RequestParam String filter, Model model) {
        List<Cake> cakeList = this.cakeRepository.getFilteredCakes(filter);
        model.addAttribute("cakes", cakeList);
        model.addAttribute("user", this.sessionObject.getUser());
        return "main";
    }
}