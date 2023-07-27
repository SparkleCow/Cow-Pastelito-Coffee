package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.IngredientsService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.DataIngredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.Ingredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Repository.IngredientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsRepositoryServiceImp implements IngredientsRepositoryService {

    public IngredientsRepository repository;
    public IngredientsRepositoryServiceImp(IngredientsRepository repo){
        this.repository = repo;
    }

    @Override
    public List<Ingredient> findAllIngredients() {
        return repository.findAll();
    }

    @Override
    public List<Ingredient> findAllIngredientsById(List<Long> idIngredients) {
        return repository.findAllById(idIngredients);
    }

    @Override
    public Ingredient findIngredientById(Long id) {
        Optional<Ingredient> ingredientOpt = repository.findById(id);
        return ingredientOpt.orElse(null);
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @Override
    public Ingredient deleteIngredient(Long id) {
        Optional<Ingredient> ingredient = repository.findById(id);
        if(ingredient.isPresent()){
            repository.deleteById(id);
            return ingredient.get();
        }
        return null;
    }

    @Override
    public Ingredient updateIngredientById(Long id, DataIngredient dataIngredient) {
        Optional<Ingredient> ingredientsOpt = repository.findById(id);
        if(ingredientsOpt.isPresent()){
            Ingredient ingredient = ingredientsOpt.get();
            ingredient.updateIngredient(dataIngredient);
            repository.save(ingredient);
            return ingredient;
        }
        return null;
    }

}
