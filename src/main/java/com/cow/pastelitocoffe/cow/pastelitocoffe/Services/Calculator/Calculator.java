package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.Calculator;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.Coffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.AmountPerIngredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class Calculator{

    public Calculator(){}
    public Double calculateCostPerCoffee(Coffee coffee){
        if(coffee==null){
            return null;
        }
        List<Ingredient> ingredients = coffee.getIngredients();
        List<AmountPerIngredient> amountPerIngredients = coffee.getAmountPerIngredients();
        List<Double> prices = new ArrayList<>();
        for(Ingredient i : ingredients){
            for(AmountPerIngredient amount : amountPerIngredients){
                if(Objects.equals(i.getId(), amount.getIngredientId())){
                    prices.add(i.getPriceUnitMeasure()* amount.getAmount());
                }
            }
        }
        Optional<Double> pricePerCoffee = prices.stream().reduce(Double::sum);
        return pricePerCoffee.orElseThrow();
    }

    public Double calculateGainPerCoffee(Coffee coffee){
        Double costPerCoffee = calculateCostPerCoffee(coffee);
        return coffee.getPrice()-costPerCoffee;
    }
}
