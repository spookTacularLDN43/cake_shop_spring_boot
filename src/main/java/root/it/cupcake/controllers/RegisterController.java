package root.it.cupcake.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import root.it.cupcake.database.IUserRepository;
import root.it.cupcake.model.User;
import root.it.cupcake.model.view.UserRegistrationData;
import root.it.cupcake.session.SessionObject;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    IUserRepository userRepository;
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
        boolean checkResult = this.userRepository.checkIfLoginExists(userRegistrationData.getLogin());
        if (checkResult) {
            this.sessionObject.setMessage("Login is taken");
            return "redirect:/register";
        }
        User user = new User(userRegistrationData.getName(), userRegistrationData.getSurname(), userRegistrationData.getLogin(), userRegistrationData.getPassword());
        this.userRepository.addUser(user);
        this.sessionObject.setMessage("Signed up!");
        return "redirect:/login";
    }
}
