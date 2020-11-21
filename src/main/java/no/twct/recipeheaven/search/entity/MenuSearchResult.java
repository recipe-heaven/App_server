package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.menu.entity.MenuItem;

import java.math.BigInteger;
import java.util.List;

/**
 * Data class for search result for a menu.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuSearchResult extends Result {
    Integer[] days;

    public MenuSearchResult(Menu menu) {
        this.setId(menu.getId());
        this.setName(menu.getName());
        this.setDays(menu.getMenuItems().stream().map(MenuItem::getDay).toArray(Integer[]::new));
    }
}
