package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.List;

/**
 * Data class for search result for a menu.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuSearchResult extends Result {
    List<String> days;

    public MenuSearchResult(BigInteger id, String name, List<String> days) {
        this.setId(id);
        this.setName(name);
        this.setDays(days);
    }
}
