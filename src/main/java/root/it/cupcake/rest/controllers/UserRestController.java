package root.it.cupcake.rest.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import root.it.cupcake.model.User;
import root.it.cupcake.services.IUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    IUserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = this.userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping
    public User addUser(@RequestBody User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.userService.persistUser(user);
        return user;
    }

    @PostMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if(this.userService.getUserById(user.getId())==null){
            return ResponseEntity.notFound().build();
        }else {
            this.userService.updateUser(user);
            return ResponseEntity.ok(user);
        }
    }
}
