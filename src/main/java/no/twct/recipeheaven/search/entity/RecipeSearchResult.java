package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RecipeSearchResult extends Result {
    int cookTime;

    public RecipeSearchResult(String name, int cookTime) {
        this.name = name;
        this.cookTime = cookTime;
    }
}
