package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Full recipe object, with all details
 * belonging to the recipe
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FullRecipeDTO extends RecipeDTO {
    List<RecipeTag> tags;

    boolean isPublic;

    List<RecipeIngredient> recipeIngredients;

    String cookingSteps;

    String recommendedDrinks;
}