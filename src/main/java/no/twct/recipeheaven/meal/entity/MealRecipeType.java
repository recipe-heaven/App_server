package no.twct.recipeheaven.meal.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@NoArgsConstructor
@Table(name = "meal_recipe_types")
public class MealRecipeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @ManyToOne
    private Recipe recipe;

    private String type;

}
