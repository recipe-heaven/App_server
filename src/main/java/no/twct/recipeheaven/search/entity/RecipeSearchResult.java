package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import no.twct.recipeheaven.recipe.entity.Recipe;

/**
 * Data class for search result for a recipe.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecipeSearchResult extends Result {
    int cookTime;
    String type;

    public RecipeSearchResult(Recipe result) {
        this.setId(result.getId());
        this.setName(result.getName());
        this.setCookTime(result.getCookTime());
        this.setType(result.getType());
    }
}
