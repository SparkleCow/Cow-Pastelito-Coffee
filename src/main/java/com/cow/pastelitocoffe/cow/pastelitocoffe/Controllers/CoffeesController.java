package com.cow.pastelitocoffe.cow.pastelitocoffe.Controllers;


import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.Coffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.DataCoffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.CoffeesService.CoffeesRepositoryService;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.IngredientsService.IngredientsRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coffees")
public class CoffeesController {

    private CoffeesRepositoryService repository;
    public CoffeesController(CoffeesRepositoryService repo, IngredientsRepositoryService ingredientsRepository){
        this.repository = repo;
    }

    @GetMapping
    public ResponseEntity<List<Coffee>> coffeeList(){
        return ResponseEntity.ok(repository.findAllCoffees());
    }

    @PostMapping
    public ResponseEntity<Coffee> createCoffee(@RequestBody Coffee coffee){
        return (repository.createCoffee(coffee)==null)?ResponseEntity.badRequest().build():ResponseEntity.ok(repository.createCoffee(coffee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> findCoffeeById(@PathVariable Long id){
        Coffee coffee = repository.findCoffeeById(id);
        return (coffee==null)?ResponseEntity.notFound().build():ResponseEntity.ok(repository.findCoffeeById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Coffee> deleteCoffeeById(@PathVariable Long id){
        return (repository.deleteCoffee(id)==null)?ResponseEntity.notFound().build():ResponseEntity.ok(repository.deleteCoffee(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffeeById(@PathVariable Long id, DataCoffee dataCoffee){
        return (repository.updateIngredient(id, dataCoffee)==null)?ResponseEntity.notFound().build():ResponseEntity.ok(repository.updateIngredient(id, dataCoffee));
    }
}
