package de.neuefische.sandwichshop;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SandwichService {

    private SandwichRepo sandwichRepo;

    public SandwichService(SandwichRepo sandwichRepo) {
        this.sandwichRepo = sandwichRepo;
    }

    public List<Sandwich> getAllSandwiches() {
        return sandwichRepo.getAllSandwiches();
    }

    public Sandwich getSandwichById(String id) {
        return sandwichRepo.getSandwichById(id);
    }

    public Sandwich saveSandwich(Sandwich sandwich) {
        int number = getAllSandwiches().size()+1;
        return sandwichRepo.saveSandwich(new Sandwich(String.valueOf(number), sandwich.getName(),
                sandwich.getIngredients(), sandwich.getNumberOfCheeseLayers(),
                sandwich.isBunIsGrilled()));
    }

    public Sandwich updateSandwich(Sandwich sandwich, String id) {
        return sandwichRepo.updateSandwich(sandwich, id);
    }

    public boolean deleteSandwich(String id) {
        return sandwichRepo.deleteSandwich(id);
    }
}
