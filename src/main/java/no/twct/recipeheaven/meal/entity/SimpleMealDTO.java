package no.twct.recipeheaven.meal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.recipe.entity.RecipeDTO;

import java.util.List;

/**
 * Simplified meal DTO object, which only contains
 * key details for representing a meal.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SimpleMealDTO extends MealDTO {
    List<RecipeDTO> recipes;
}
