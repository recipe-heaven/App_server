package no.twct.recipeheaven.lib.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RecipeSearchResult extends Result {
    int cookTime;
}
