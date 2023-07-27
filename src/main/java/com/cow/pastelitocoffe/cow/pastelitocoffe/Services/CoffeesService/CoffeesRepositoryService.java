package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.CoffeesService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.Coffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.DataCoffee;

import java.util.List;

public interface CoffeesRepositoryService {
    List<Coffee> findAllCoffees();
    Coffee findCoffeeById(Long id);
    Coffee createCoffee(Coffee coffee);
    Coffee deleteCoffee(Long id);
    Coffee updateIngredient(Long id, DataCoffee dataCoffee);
}
