package com.cow.pastelitocoffe.cow.pastelitocoffe.Services;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.Coffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.AmountPerIngredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.Ingredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.UnitOfMeasure;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.Calculator.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
public class CalculatorTest {

    private Calculator calculator;
    @BeforeEach
    public void setup(){
        calculator = new Calculator();
    }
    @Test
    public void calculate_costPerCoffee_withAnIngredient(){
        Ingredient ingredient = new Ingredient(1L, "cafe", UnitOfMeasure.GRAMS, 30.0);

        List<Ingredient> ingredientCoffee = List.of(ingredient);
        List<AmountPerIngredient> amountPerIngredients = List.of(new AmountPerIngredient(1L, 30));

        Coffee coffee = new Coffee(1L, "Standard coffee", "Simple but delicious",
                1000.0, "https://i.pinimg.com/564x/f8/4a/70/f84a70f0469e904820773d304c4374f8.jpg",
                500.0, amountPerIngredients, ingredientCoffee);
        Double total = calculator.calculateCostPerCoffee(coffee);
        Assertions.assertEquals(900.00, total, 0.10);
    }

    @Test
    public void calculate_costPerCoffee_withMoreIngredients(){
        Ingredient ingredient1 = new Ingredient(1L, "cafe", UnitOfMeasure.GRAMS, 30.0);
        Ingredient ingredient2 = new Ingredient(2L, "azucar", UnitOfMeasure.GRAMS, 7.0);

        List<Ingredient> ingredientCoffee = List.of(ingredient1, ingredient2);
        List<AmountPerIngredient> amountPerIngredients = List.of(new AmountPerIngredient(1L, 30),
                new AmountPerIngredient(2L, 100));

        Coffee coffee = new Coffee(1L, "Standard coffee", "Simple but delicious",
                1000.0, "https://i.pinimg.com/564x/f8/4a/70/f84a70f0469e904820773d304c4374f8.jpg",
                500.0, amountPerIngredients, ingredientCoffee);

        Double total = calculator.calculateCostPerCoffee(coffee);
        Assertions.assertEquals(1600.00, total, 0.10);
    }

    @Test
    public void calculate_gainPerCoffee(){
        Ingredient ingredient1 = new Ingredient(1L, "cafe", UnitOfMeasure.GRAMS, 30.0);
        Ingredient ingredient2 = new Ingredient(2L, "azucar", UnitOfMeasure.GRAMS, 7.0);

        List<Ingredient> ingredientCoffee = List.of(ingredient1, ingredient2);
        List<AmountPerIngredient> amountPerIngredients = List.of(new AmountPerIngredient(1L, 30),
                new AmountPerIngredient(2L, 100));

        Coffee coffee = new Coffee(1L, "Standard coffee", "Simple but delicious",
                2000.0, "https://i.pinimg.com/564x/f8/4a/70/f84a70f0469e904820773d304c4374f8.jpg",
                500.0, amountPerIngredients, ingredientCoffee);
        Double gain = calculator.calculateGainPerCoffee(coffee);
        Assertions.assertEquals(400, gain, 0.10);
    }
}
