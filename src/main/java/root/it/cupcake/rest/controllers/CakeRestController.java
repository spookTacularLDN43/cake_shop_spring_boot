package root.it.cupcake.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import root.it.cupcake.model.Cake;
import root.it.cupcake.services.IAdminService;
import root.it.cupcake.services.ICakeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cakes")
public class CakeRestController {

    @Autowired
    ICakeService cakeService;
    @Autowired
    IAdminService adminService;

    @GetMapping
    public List<Cake> getAllCakes() {
        return this.cakeService.getAllCakes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cake> getCakeById(@PathVariable int id) {
        Cake cake = this.cakeService.getCakeById(id);
        if (cake == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cake);
        }
    }

    @PutMapping
    public Cake addCake(@RequestBody Cake cake) {
        this.adminService.addCake(cake);
        return cake;
    }

    @PostMapping
    public ResponseEntity<Cake> updateCake(@RequestBody Cake cake) {
        if (this.cakeService.getCakeById(cake.getId()) == null) {
            return ResponseEntity.notFound().build();
        } else {
            this.adminService.updateCake(cake);
            return ResponseEntity.ok(cake);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cake> deleteCake(@PathVariable int id) {
        Cake cake = this.cakeService.getCakeById(id);
        if (cake == null) {
            return ResponseEntity.notFound().build();
        } else {
            this.adminService.deleteCake(cake);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/filter")
    public List<Cake> getCakesWithFilter(@RequestParam String pattern) {
        List<Cake> filteredCakes = this.cakeService.getFilteredCakes(pattern);
        return filteredCakes;

    }
}
