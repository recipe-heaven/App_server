package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * Data class for search result for a recipe.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecipeSearchResult extends Result {
    int cookTime;
    String type;

    public RecipeSearchResult(BigInteger id, String name, int cookTime, String recipeType) {
        this.setId(id);
        this.setName(name);
        this.setCookTime(cookTime);
        this.setType(recipeType);
    }
}
