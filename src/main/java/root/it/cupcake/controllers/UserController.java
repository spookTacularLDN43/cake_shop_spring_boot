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
import root.it.cupcake.model.view.ChangePassData;
import root.it.cupcake.session.SessionObject;

@Controller
@RequestMapping("/login")
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @Resource
    SessionObject sessionObject;

    @GetMapping
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping
    public String authentication(@ModelAttribute User user) {
        this.sessionObject.setUser(userRepository.authenticate(user));
        if (this.sessionObject.getUser() != null) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/out")
    public String logout() {
        this.sessionObject.setUser(null);
        return "redirect:/login";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        if (this.sessionObject.isLogged()) {
            model.addAttribute("user", this.sessionObject.getUser());
            model.addAttribute("passmodel", new ChangePassData());
            return "edit";
        }
        return "redirect:/login";
    }

    @PostMapping("/data")
    public String editData(@ModelAttribute User user) {
        user.setLogin(this.sessionObject.getUser().getLogin());
        User updatedUser = this.userRepository.updateUserData(user);
        this.sessionObject.setUser(updatedUser);
        return "redirect:/login/edit";
    }

    @PostMapping("/pass")
    public String editPass(@ModelAttribute ChangePassData changePassData) {
        if (changePassData.getNewPass().equals(changePassData.getRepeatedNewPass())) {
            //TODO zle powtorzone haslo
        }
        User user = new User();
        user.setPassword(changePassData.getPass());
        user.setLogin(this.sessionObject.getUser().getLogin());
        User authenticatedUser = this.userRepository.authenticate(user);
        if (authenticatedUser == null) {
            //TODO zle haslo
        }
        user.setPassword(changePassData.getNewPass());
        User updatedUser = this.userRepository.updateUserPass(user);
        this.sessionObject.setUser(updatedUser);
        return "redirect:/login/edit";
    }
}