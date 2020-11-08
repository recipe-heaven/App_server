package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Search options are provided by a client when
 * they perform a search.
 * A search includes the search string, filter for what types to include, and if
 * it should only search for items owned by the current user.
 */
@Data
@NoArgsConstructor
public class SearchOptions {

    String searchString;

    boolean ownedOnly;

    boolean includeMeals;

    boolean includeMenus;

    boolean includeRecipes;

}
