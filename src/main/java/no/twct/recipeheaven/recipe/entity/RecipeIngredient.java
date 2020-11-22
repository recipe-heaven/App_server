package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigInteger;

@Data
@Entity
@NoArgsConstructor
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    String name;

    @Min(0)
    int amount;

    String unitType;

    String comment;

}