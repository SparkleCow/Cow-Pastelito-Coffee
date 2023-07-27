package com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees;

public record DataCoffee(
        String name,
        String description,
        Double price,
        String urlImage,
        Double shippingPrice
) {
}
