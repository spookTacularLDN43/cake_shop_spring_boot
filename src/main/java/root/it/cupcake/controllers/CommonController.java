package root.it.cupcake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import root.it.cupcake.database.ICakeRepository;
import root.it.cupcake.model.Cake;

import java.util.List;

@Controller
public class CommonController {

    @Autowired
    ICakeRepository cakeRepository;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){
        List<Cake> cakeList = this.cakeRepository.getAllCakes();
        model.addAttribute("cakes", cakeList);
        return "main";
    }
}
