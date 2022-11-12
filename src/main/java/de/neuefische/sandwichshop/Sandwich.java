package de.neuefische.sandwichshop;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Sandwich {
    String id;
    String name;
    List<Ingredient> ingredients;
    int numberOfCheeseLayers;
    boolean bunIsGrilled;
}
