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
    int starters;
    int courses;
    int desserts;

    public MealSearchResult(BigInteger id, String name, int starters, int courses, int desserts) {
        this.setId(id);
        this.setName(name);
        this.setStarters(starters);
        this.setCourses(courses);
        this.setDesserts(desserts);
    }
}
