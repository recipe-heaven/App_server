package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * Data class for search result for a meal.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MealSearchResult extends Result {
    String[] recipeType;

    public MealSearchResult(BigInteger id, String name, String[] recipeType) {
        this.setId(id);
        this.setName(name);
        this.setRecipeType(recipeType);
    }
}
