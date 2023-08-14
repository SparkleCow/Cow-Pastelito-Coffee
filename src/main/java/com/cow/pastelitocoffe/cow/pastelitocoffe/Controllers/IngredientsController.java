package com.cow.pastelitocoffe.cow.pastelitocoffe.Controllers;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.DataIngredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.Ingredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.IngredientsService.IngredientsRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class IngredientsController {

    private final IngredientsRepositoryService repositoryService;
    public IngredientsController(IngredientsRepositoryService repository){
        this.repositoryService = repository;
    }

    @GetMapping("/api/ingredients")
    public ResponseEntity<List<Ingredient>> ingredientsList(){
        return ResponseEntity.ok(repositoryService.findAllIngredients());
    }

    @PostMapping("/api/createIngredient")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient){
        if(ingredient.getId()==null){
            return ResponseEntity.ok(repositoryService.createIngredient(ingredient));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/api/ingredient/{id}")
    public ResponseEntity<Ingredient> findIngredientById(@PathVariable Long id){
        Ingredient ingredient = repositoryService.findIngredientById(id);
        return (ingredient == null) ? ResponseEntity.notFound().build():ResponseEntity.ok(repositoryService.findIngredientById(id));
    }

    @DeleteMapping("/api/deleteIngredient/{id}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable Long id){
        return (repositoryService.deleteIngredient(id)==null) ? ResponseEntity.notFound().build():ResponseEntity.ok(repositoryService.deleteIngredient(id));
    }

    @PutMapping("/api/updateIngredient/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, DataIngredient dataIngredient){
        return (repositoryService.updateIngredientById(id, dataIngredient)==null) ?
                ResponseEntity.notFound().build():ResponseEntity.ok(repositoryService.updateIngredientById(id, dataIngredient));
    }
}
