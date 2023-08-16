package com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.AmountPerIngredient;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients.Ingredient;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "coffees")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @Builder
@EqualsAndHashCode @ToString
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @Column(name = "url_image")
    private String urlImage;
    @Column(name = "shipping_price")
    private Double shippingPrice;

    @ElementCollection
    @CollectionTable(name = "coffee_amount_per_ingredients", joinColumns = @JoinColumn(name = "coffee_id"))
    private List<AmountPerIngredient> amountPerIngredients = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "coffee_ingredients", joinColumns = @JoinColumn(name = "coffees_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private List<Ingredient> ingredients = new ArrayList<>();

    /*Method that allows us to update our coffee object from a dataUpdateCoffee record*/
    public void updateCoffee(Coffee coffeeUpdate){
        if(coffeeUpdate.getName()!=null&&(!coffeeUpdate.getName().equals(""))){
            this.name = coffeeUpdate.getName();
        }
        if(coffeeUpdate.getDescription()!=null&&(!coffeeUpdate.getDescription().equals(""))){
            this.description = coffeeUpdate.getDescription();
        }
        if(coffeeUpdate.getPrice()!=null){
            this.price = coffeeUpdate.getPrice();
        }
        if(coffeeUpdate.getUrlImage()!=null&&(!coffeeUpdate.getUrlImage().equals(""))){
            this.urlImage = coffeeUpdate.getUrlImage();
        }
        if(coffeeUpdate.getShippingPrice()!=null){
            this.shippingPrice = coffeeUpdate.getShippingPrice();
        }
        if(coffeeUpdate.getAmountPerIngredients()!=null){
            this.amountPerIngredients = coffeeUpdate.getAmountPerIngredients();
        }
    }
}
