package de.neuefische.sandwichshop;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SandwichController {

    private SandwichService sandwichService;

    public SandwichController(SandwichService sandwichService) {
        this.sandwichService = sandwichService;
    }

    @GetMapping("/sandwiches")
    public List<Sandwich> getAllSandwiches(){
        return sandwichService.getAllSandwiches();
    }
    @GetMapping("/sandwiches/{id}")
    public Sandwich getSandwichById(@PathVariable String id){
        return sandwichService.getSandwichById(id);
    }
    @PostMapping("/sandwiches")
    public Sandwich saveSandwich(@RequestBody Sandwich sandwich){
        return sandwichService.saveSandwich(sandwich);
    }
    @PutMapping("/sandwiches/{id}")
    public Sandwich updateSandwich(@RequestBody Sandwich sandwich, @PathVariable String id){
        return sandwichService.updateSandwich(sandwich, id);
    }
    @DeleteMapping("/sandwiches/{id}")
    public boolean deleteSandwich(@RequestBody @PathVariable String id){
        return sandwichService.deleteSandwich(id);
    }


}
