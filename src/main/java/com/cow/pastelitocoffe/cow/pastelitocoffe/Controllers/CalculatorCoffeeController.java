package com.cow.pastelitocoffe.cow.pastelitocoffe.Controllers;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.Coffee;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.Calculator.Calculator;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.CoffeesService.CoffeesRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculateCoffee")
public class CalculatorCoffeeController {

    private CoffeesRepositoryService repository;
    private Calculator calculator;

    public CalculatorCoffeeController(CoffeesRepositoryService repository, Calculator calculator){
        this.repository = repository;
        this.calculator = calculator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> calculateCostCoffee(@PathVariable Long id){
        Coffee coffee = repository.findCoffeeById(id);
        if(calculator.calculateCostPerCoffee(coffee)==null){
            return ResponseEntity.notFound().build();
        }
        Double price = calculator.calculateCostPerCoffee(coffee);
        Double gain = calculator.calculateGainPerCoffee(coffee);
        return ResponseEntity.ok("El precio total de creación del café "+coffee.getName()+" es: $"+price+"\n"
            +"La ganancia total del café "+coffee.getName()+" es de: $"+gain
        );
    }
}
