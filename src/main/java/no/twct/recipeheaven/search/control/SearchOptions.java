package no.twct.recipeheaven.search.control;

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

    /**
     * The string to match for
     */
    String searchString = "";

    /**
     * Recipe type to search for : starter, dessert, main
     */
    String recipeType = "";

    /**
     * Search for user owned items only. Which
     * will be the user currently logged in.
     */
    boolean ownedOnly;

    /**
     * Include meals in the search
     */
    boolean includeMeals;

    /**
     * Include menus in the search
     */
    boolean includeMenus;

    /**
     * Include recipes in the search
     */
    boolean includeRecipes;


    /**
     * Stared only
     */
    boolean staredOnly;

}
