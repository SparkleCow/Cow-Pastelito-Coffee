package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.CoffeesService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.Coffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.DataUpdateCoffee;

import java.util.List;

public interface CoffeesRepositoryService {
    List<Coffee> findAllCoffees();
    Coffee findCoffeeById(Long id);
    Coffee createCoffee(Coffee coffee);
    Coffee deleteCoffee(Long id);
    Coffee updateCoffee(Long id, Coffee coffeeUpdate);
}
