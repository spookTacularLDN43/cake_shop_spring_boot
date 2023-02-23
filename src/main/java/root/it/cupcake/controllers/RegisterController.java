package root.it.cupcake.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import root.it.cupcake.model.view.UserRegistrationData;
import root.it.cupcake.services.IUserService;
import root.it.cupcake.session.SessionObject;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    IUserService userService;
    @Resource
    SessionObject sessionObject;

    @GetMapping
    public String register(Model model) {
        model.addAttribute("registermodel", new UserRegistrationData());
        model.addAttribute("message", this.sessionObject.getMessage());
        return "register";
    }

    @PostMapping
    public String signUp(@ModelAttribute UserRegistrationData userRegistrationData) {
        if (!userRegistrationData.getPassword().equals(userRegistrationData.getRepeatedPass())) {
            this.sessionObject.setMessage("Passwords are different");
            return "redirect:/register";
        }
        boolean checkResult = this.userService.registerUser(userRegistrationData);

        if (!checkResult) {
            this.sessionObject.setMessage("Login is taken");
            return "redirect:/register";
        }

        this.sessionObject.setMessage("Signed up!");
        return "redirect:/login";
    }
}
