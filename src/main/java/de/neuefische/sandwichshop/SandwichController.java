package de.neuefische.sandwichshop;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sandwich")
public class SandwichController {

    private SandwichService sandwichService;
    @GetMapping
    public List<Sandwich> getAllSandwiches(){
        return sandwichService.getAllSandwiches();
    }
    @GetMapping("/{id}")
    public Sandwich getSandwichById(@PathVariable String id){
        return sandwichService.getSandwichById(id);
    }
    @PostMapping
    public Sandwich saveSandwich(@RequestBody Sandwich sandwich){
        return sandwichService.saveSandwich(sandwich);
    }
    @PutMapping("/{id}")
    public Sandwich updateSandwich(@RequestBody Sandwich sandwich, @PathVariable String id){
        return sandwichService.updateSandwich(sandwich, id);
    }
    @DeleteMapping("/{id}")
    public boolean deleteSandwich(@RequestBody @PathVariable String id){
        return sandwichService.deleteSandwich(id);
    }


}
