package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.recipe.entity.Recipe;

/**
 * Data class for search result for a meal.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MealSearchResult extends Result {
    String[] recipeType;

    public MealSearchResult(Meal result) {
        this.setId(result.getId());
        this.setName(result.getName());
        this.setRecipeType(result.getRecipes().stream().map(Recipe::getType).toArray(String[]::new));
    }
}
