package de.neuefische.sandwichshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class SandwichControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    SandwichRepo sandwichRepo;

    @Test
   void getAllSandwiches_whenListExists_thenListOfSandwiches() throws Exception{
        Sandwich sandwich1 = new Sandwich("1","salamiSandwich",
                List.of(new Ingredient("Falaffeln"),
                        new Ingredient("Salat"),
                        new Ingredient("Extra Zwiebeln")),
                3, true );
        Sandwich sandwich2 = new Sandwich("2","salamiSandwich",
                List.of(new Ingredient("Falaffeln"),
                        new Ingredient("Salat"),
                        new Ingredient("Extra Zwiebeln")),
                3, true );
        sandwichRepo.saveSandwich(sandwich1);
        sandwichRepo.saveSandwich(sandwich2);
        sandwichRepo.getAllSandwiches();
        mvc.perform(get("http://localhost:8080/api/sandwich"))

                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                          "id": "1",
                          "name": "Falaffel Spezial",
                          "ingredients": ["Falaffeln", "Salat", "Extra Zwiebeln"],
                          "numberOfCheeseLayers": 3,
                          "bunIsGrilled": true
                        },{
                           "id": "2",
                           "name": "Falaffel Spezial",
                           "ingredients": ["Falaffeln", "Salat", "Extra Zwiebeln"],
                           "numberOfCheeseLayers": 3,
                           "bunIsGrilled": true
                         }]
                        """));
    }




    @Test
    void getSandwichById() {

    }

    @Test
    void saveSandwich() {
    }

    @Test
    void updateSandwich() {
    }

    @Test
    void deleteSandwich() {
    }
}