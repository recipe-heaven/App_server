package no.twct.recipeheaven.meal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.recipe.entity.MinifiedRecipeDTO;

import java.math.BigInteger;
import java.util.List;

/**
 * Simplified recipe object, which only contains
 * key details for representing a meal.
 */
@Data
@NoArgsConstructor
public class MinifiedMealDTO {
    String name;
    BigInteger owner;
    List<MinifiedRecipeDTO> recipes;

}
