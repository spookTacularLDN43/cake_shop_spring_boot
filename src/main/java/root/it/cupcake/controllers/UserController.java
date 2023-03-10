package root.it.cupcake.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import root.it.cupcake.model.User;
import root.it.cupcake.model.view.ChangePassData;
import root.it.cupcake.services.IUserService;
import root.it.cupcake.session.SessionObject;

@Controller
@RequestMapping("/login")
public class UserController {

    @Autowired
    IUserService userService;

    @Resource
    SessionObject sessionObject;

    @GetMapping
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("message", this.sessionObject.getMessage());
        return "login";
    }

    @PostMapping
    public String authentication(@ModelAttribute User user) {
        this.sessionObject.setUser(this.userService.authenticate(user));
        if (this.sessionObject.getUser() != null) {
            return "redirect:/main";
        } else {
            this.sessionObject.setMessage("Invalid credentials");
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
            model.addAttribute("message", this.sessionObject.getMessage());
            return "edit";
        }
        return "redirect:/login";
    }

    @PostMapping("/data")
    public String editData(@ModelAttribute User user) {
        user.setLogin(this.sessionObject.getUser().getLogin());
        User updatedUser = this.userService.updateUserData(user);
        this.sessionObject.setUser(updatedUser);
        return "redirect:/login/edit";
    }

    @PostMapping("/pass")
    public String editPass(@ModelAttribute ChangePassData changePassData, Model model) {
        if (!changePassData.getNewPass().equals(changePassData.getRepeatedNewPass())) {
            this.sessionObject.setMessage("Passwords are different");
            return "redirect:/login/edit";
        }
        User user = new User();
        user.setPassword(changePassData.getPass());
        user.setLogin(this.sessionObject.getUser().getLogin());
        User authenticatedUser = this.userService.authenticate(user);
        if (authenticatedUser == null) {
            this.sessionObject.setMessage("Invalid password");
            return "redirect:/login/edit";
        }
        User result = this.userService.updateUserPass(changePassData);
        if (result != null) {
            this.sessionObject.setUser(result);
        } else {
            this.sessionObject.setMessage("Zmiana has??a nieudana !!");
        }

        return "redirect:/login/edit";
    }
}
