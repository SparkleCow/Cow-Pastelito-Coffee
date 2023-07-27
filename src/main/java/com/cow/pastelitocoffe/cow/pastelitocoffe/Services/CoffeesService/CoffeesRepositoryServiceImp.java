package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.CoffeesService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.Coffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.DataCoffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.Ingredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Repository.CoffeesRepository;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.IngredientsService.IngredientsRepositoryService;
import org.springframework.stereotype.Service;

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
        if(coffee.getIngredients().isEmpty()){
            return null;
        }
        List<Ingredient> existingIngredients = ingredientsRepository.findAllIngredientsById(coffee.getIngredients().
                stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList()));
        coffee.setIngredients(existingIngredients);
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
    public Coffee updateIngredient(Long id, DataCoffee dataCoffee) {
        Optional<Coffee> coffeeOpt = repository.findById(id);
        if(coffeeOpt.isPresent()){
            Coffee coffee = coffeeOpt.get();
            coffee.updateCoffee(dataCoffee);
            repository.save(coffee);
            return coffee;
        }
        return null;
    }
}
