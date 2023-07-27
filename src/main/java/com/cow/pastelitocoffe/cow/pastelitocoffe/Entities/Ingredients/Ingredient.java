package com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredients")
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @Builder
@EqualsAndHashCode @ToString
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ingredient_name")
    private String ingredientName;
    @Enumerated(EnumType.STRING)
    @Column(name="unit_of_measure")
    private UnitOfMeasure unitOfMeasure;
    @Column(name="price_unit_measure")
    private Double priceUnitMeasure;

    /*Method that allows us to update our Ingredient object from a dataIngredient record*/
    public void updateIngredient(DataIngredient dataIngredient){
        if((dataIngredient.ingredientName()!=null)&&(!dataIngredient.ingredientName().equals(""))){
            this.ingredientName = dataIngredient.ingredientName();
        }
        if(dataIngredient.unitOfMeasure() != null){
            this.unitOfMeasure = dataIngredient.unitOfMeasure();
        }
        if(dataIngredient.priceUnitMeasure()!=null){
            this.priceUnitMeasure = dataIngredient.priceUnitMeasure();
        }
    }
}
