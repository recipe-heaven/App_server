package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * Data class for search result for a menu.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuSearchResult extends Result {
    Integer[] days;

    public MenuSearchResult(BigInteger id, String name, Integer[] days) {
        this.setId(id);
        this.setName(name);
        this.setDays(days);
    }
}
