package com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees;

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
    @ManyToMany()
    @JoinTable(name = "coffee_ingredients", joinColumns = @JoinColumn(name = "coffees_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private List<Ingredient> ingredients = new ArrayList<>();

    /*Method that allows us to update our coffee object from a dataCoffee record*/
    public void updateCoffee(DataCoffee dataCoffee){
        if(dataCoffee.name()!=null&&(!dataCoffee.name().equals(""))){
            this.name = dataCoffee.name();
        }
        if(dataCoffee.description()!=null&&(!dataCoffee.description().equals(""))){
            this.description = dataCoffee.description();
        }
        if(dataCoffee.price()!=null){
            this.price = dataCoffee.price();
        }
        if(dataCoffee.urlImage()!=null&&(!dataCoffee.urlImage().equals(""))){
            this.urlImage = dataCoffee.urlImage();
        }
        if(dataCoffee.shippingPrice()!=null){
            this.shippingPrice = dataCoffee.shippingPrice();
        }
    }
}
