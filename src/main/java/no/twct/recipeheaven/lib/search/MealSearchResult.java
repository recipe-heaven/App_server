package no.twct.recipeheaven.lib.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MealSearchResult extends Result {
    int starters;
    int courses;
    int desserts;
}
