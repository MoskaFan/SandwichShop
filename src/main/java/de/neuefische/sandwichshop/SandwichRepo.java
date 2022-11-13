package de.neuefische.sandwichshop;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class SandwichRepo {
    private List<Sandwich> sandwiches;

    public SandwichRepo() {
        sandwiches = new ArrayList<>();
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public List<Sandwich> getAllSandwiches() {
        return sandwiches;
    }
    public Sandwich saveSandwich(Sandwich sandwich){
        for (Sandwich item:sandwiches) {
            if(item.getId().equals(sandwich.getId())){
                return item;
            }
        }
        sandwiches.add(sandwich);
        return sandwich;
    }

    public Sandwich getSandwichById(String id) {
        for (Sandwich sandwich:sandwiches) {
            if(sandwich.getId().equals(id)){
                return sandwich;
            }
        }
        return null;
    }

    public Sandwich updateSandwich(Sandwich sandwich, String id) {
        return sandwiches.set(Integer.parseInt(id), sandwich);
    }

    public boolean deleteSandwich(String id) {
        for (Sandwich sandwich : sandwiches) {
            if(sandwich.getId().equals(id))
                return sandwiches.remove(id);
        }
        return false;
    }
}
