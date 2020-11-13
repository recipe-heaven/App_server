package no.twct.recipeheaven.meal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;

import java.util.List;

/**
 * Full projection of a meal, with all recipes details.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FullMealDTO extends MealDTO {
    List<FullRecipeDTO> recipes;
}
