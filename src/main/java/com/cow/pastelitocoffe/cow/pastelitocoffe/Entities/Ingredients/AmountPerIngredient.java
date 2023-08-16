package com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Ingredients;
import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @Builder
@EqualsAndHashCode @ToString
@Embeddable
public class AmountPerIngredient {

    private Long ingredientId;
    private double amount;
}
