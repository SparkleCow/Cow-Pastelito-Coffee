package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.IngredientsService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.DataIngredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.Ingredient;

import java.util.List;

public interface IngredientsRepositoryService {
    List<Ingredient> findAllIngredients();
    List<Ingredient> findAllIngredientsById(List<Long> idIngredients);
    Ingredient findIngredientById(Long id);
    Ingredient createIngredient(Ingredient ingredient);
    Ingredient deleteIngredient(Long id);
    Ingredient updateIngredientById(Long id, DataIngredient dataIngredient);
}
