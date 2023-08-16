package com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.AmountPerIngredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.Ingredient;

import java.util.List;

public record DataUpdateCoffee(
        String name,
        String description,
        Double price,
        String urlImage,
        Double shippingPrice,
        List<AmountPerIngredient> amountPerIngredients) {
}
