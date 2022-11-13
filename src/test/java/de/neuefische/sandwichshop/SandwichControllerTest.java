package de.neuefische.sandwichshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class SandwichControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    SandwichRepo sandwichRepo;

    @Test
   void getAllSandwiches_whenListExists_thenListOfSandwiches() throws Exception{
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Falaffeln");
        Sandwich sandwich1 = new Sandwich();
        sandwich1.setId("1");
        sandwich1.setName("sandwich1");
        sandwich1.setIngredients(ingredients);
        sandwich1.setNumberOfCheeseLayers(3);
        sandwich1.setBunIsGrilled(true);
        sandwichRepo.saveSandwich(sandwich1);
        Sandwich sandwich2 = new Sandwich();
        sandwich2.setId("2");
        sandwich2.setName("sandwich2");
        sandwich2.setIngredients(ingredients);
        sandwich2.setNumberOfCheeseLayers(2);
        sandwich2.setBunIsGrilled(false);
        sandwichRepo.saveSandwich(sandwich2);
        sandwichRepo.getAllSandwiches();
        mvc.perform(get("http://localhost:8080/api/sandwiches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                          [{
                          "id": "1",
                          "name": "sandwich1",
                          "ingredients": ["Falaffeln"],
                          "numberOfCheeseLayers": 3,
                          "bunIsGrilled": true
                        },{
                           "id": "2",
                           "name": "sandwich2",
                           "ingredients": ["Falaffeln"],
                           "numberOfCheeseLayers": 2,
                           "bunIsGrilled": false
                         }]
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                          "id": "1",
                          "name": "sandwich1",
                          "ingredients": ["Falaffeln"],
                          "numberOfCheeseLayers": 3,
                          "bunIsGrilled": true
                        },{
                           "id": "2",
                           "name": "sandwich2",
                           "ingredients": ["Falaffeln"],
                           "numberOfCheeseLayers": 2,
                           "bunIsGrilled": false
                         }]
                        """));
    }




    @Test
    void getSandwichById() throws Exception{
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Falaffeln");
        Sandwich sandwich = new Sandwich();
        sandwich.setId("2");
        sandwich.setName("Falaffel Spezial");
        sandwich.setIngredients(ingredients);
        sandwich.setNumberOfCheeseLayers(3);
        sandwich.setBunIsGrilled(true);
        sandwichRepo.saveSandwich(sandwich);
        sandwichRepo.getSandwichById("1");

        mvc.perform(post("http://localhost:8080/api/sandwiches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "id": "2",
                              "name": "Falaffel Spezial",
                              "ingredients": ["Falaffeln"],
                              "numberOfCheeseLayers": 3,
                              "bunIsGrilled": true
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                              "id": "2",
                              "name": "Falaffel Spezial",
                              "ingredients": ["Falaffeln"],
                              "numberOfCheeseLayers": 3,
                              "bunIsGrilled": true
                            }
                            """));
    }

    @Test
    void saveSandwich() throws Exception {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Falaffeln");
        Sandwich sandwich = new Sandwich();
        sandwich.setId("2");
        sandwich.setName("Falaffel Spezial");
        sandwich.setIngredients(ingredients);
        sandwich.setNumberOfCheeseLayers(3);
        sandwich.setBunIsGrilled(true);
        sandwichRepo.saveSandwich(sandwich);

        mvc.perform(post("http://localhost:8080/api/sandwiches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "id": "2",
                              "name": "Falaffel Spezial",
                              "ingredients": ["Falaffeln"],
                              "numberOfCheeseLayers": 3,
                              "bunIsGrilled": true
                            }
                            """))
                        .andExpect(status().isOk())
                        .andExpect(content().json("""
                            {
                              "id": "2",
                              "name": "Falaffel Spezial",
                              "ingredients": ["Falaffeln"],
                              "numberOfCheeseLayers": 3,
                              "bunIsGrilled": true
                            }
                            """));
    }
    @Test
    void updateSandwich() throws Exception{
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Falaffeln");
        Sandwich sandwich = new Sandwich();
        sandwich.setId("1");
        sandwich.setName("Falaffel Spezial");
        sandwich.setIngredients(ingredients);
        sandwich.setNumberOfCheeseLayers(3);
        sandwich.setBunIsGrilled(true);
        sandwichRepo.saveSandwich(sandwich);
        sandwichRepo.updateSandwich(sandwich,"1");

        mvc.perform(put("http://localhost:8080/api/sandwiches/"+sandwich.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "id": "1",
                              "name": "Falaffel Spezial",
                              "ingredients": ["Falaffeln"],
                              "numberOfCheeseLayers": 1,
                              "bunIsGrilled": true
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                              "id": "1",
                              "name": "Falaffel Spezial",
                              "ingredients": ["Falaffeln"],
                              "numberOfCheeseLayers": 1,
                              "bunIsGrilled": true
                            }
                            """));
    }

    @Test
    void deleteSandwich() throws Exception{
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Falaffeln");
        Sandwich sandwich = new Sandwich();
        sandwich.setId("1");
        sandwich.setName("Falaffel Spezial");
        sandwich.setIngredients(ingredients);
        sandwich.setNumberOfCheeseLayers(3);
        sandwich.setBunIsGrilled(true);
        sandwichRepo.saveSandwich(sandwich);
        sandwichRepo.deleteSandwich("1");
        mvc.perform(delete("http://localhost:8080/api/sandwiches/"+sandwich.getId())

                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                        }"""))
                .andExpect(status().isOk());
    }
}