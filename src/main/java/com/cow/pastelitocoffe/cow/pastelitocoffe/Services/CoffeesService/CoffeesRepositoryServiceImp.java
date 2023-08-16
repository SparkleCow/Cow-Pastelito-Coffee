package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.CoffeesService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.Coffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.DataUpdateCoffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.Ingredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Repository.CoffeesRepository;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.IngredientsService.IngredientsRepositoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeesRepositoryServiceImp implements CoffeesRepositoryService {

    public CoffeesRepository repository;
    private IngredientsRepositoryService ingredientsRepository;
    public CoffeesRepositoryServiceImp(CoffeesRepository repo, IngredientsRepositoryService ingredientsRepository){
        this.repository = repo;
        this.ingredientsRepository = ingredientsRepository;
    }
    @Override
    public List<Coffee> findAllCoffees() {
        return repository.findAll();
    }

    @Override
    public Coffee findCoffeeById(Long id) {
        Optional<Coffee> coffeeOpt = repository.findById(id);
        return coffeeOpt.orElse(null);
    }

    @Override
    public Coffee createCoffee(Coffee coffee){
        System.out.print(coffee);
        if(coffee.getIngredients().isEmpty() || coffee.getAmountPerIngredients().isEmpty()){
            return null;
        }
        coffee.setIngredients(ingredientsRepository.findAllIngredientsById(coffee.getIngredients().
                stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList())));
        return repository.save(coffee);
    }

    @Override
    public Coffee deleteCoffee(Long id) {
        Optional<Coffee> coffeeOpt = repository.findById(id);
        if(coffeeOpt.isPresent()){
            repository.deleteById(id);
            return coffeeOpt.get();
        }
        return null;
    }

    @Override
    public Coffee updateCoffee(Long id, Coffee coffeeUpdate){
        System.out.print(coffeeUpdate);
        Optional<Coffee> coffeeOpt = repository.findById(id);
        if(coffeeOpt.isPresent()){
            Coffee coffee = coffeeOpt.get();
            coffee.updateCoffee(coffeeUpdate);
            if(coffeeUpdate.getIngredients()!=null){
                coffee.setIngredients(ingredientsRepository.findAllIngredientsById(coffeeUpdate.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toList())));
            }
            return repository.save(coffee);
        }
        return null;
    }
}
