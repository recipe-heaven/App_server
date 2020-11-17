package no.twct.recipeheaven.search.entity;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Proves database access functions for search where
 * a regular entity object can not be used.
 */

public class SearchDAO {

    @PersistenceContext
    EntityManager em;

    /**************
     * MEALS
     **************/

    private final String mealSearchQueryString =
            "SELECT name, id, array_agg(type) as type" +
                    " FROM (" +
                    " SELECT meals.name, meals.id, recipes.type as type" +
                    " FROM meals," +
                    " recipes," +
                    " meal_recipes" +
                    " WHERE meals.id = meal_recipes.meal_id" +
                    " AND recipes.id = meal_recipes.recipes_id" +
                    " AND meals.name LIKE ?";

    /**
     * Search for meals by its name and return the result.
     *
     * @param searchString the string to search for
     * @return returns search result
     */
    public List<MealSearchResult> searchMealsByName(String searchString) {
        String queryString = mealSearchQueryString;
        queryString += " AND meals.is_public = ?";
        queryString += ") sub GROUP BY name, id;";

        Query query = em.createNativeQuery(queryString, "MealSearchResult");
        query.setParameter(2, true);
        return this.searchMeals(query, searchString);
    }

    /**
     * Search for meals by its name and the owner of the meal and return the result.
     *
     * @param searchString the string to search for
     * @param userId       the owner of the meals
     * @return returns search result
     */
    public List<MealSearchResult> searchMealsByNameOwnerOnly(String searchString, BigInteger userId) {
        String queryString = mealSearchQueryString;
        queryString += " AND meals.creator = ?";
        queryString += " ) sub GROUP BY name, id;";
        Query query = em.createNativeQuery(queryString, "MealSearchResult");

        query.setParameter(2, userId);
        return this.searchMeals(query, searchString);
    }

    /**
     * Injects the search string and execute the search and returns the result
     *
     * @param query        the query to execute
     * @param searchString the string to search for
     * @return returns the search result
     */
    private List<MealSearchResult> searchMeals(Query query, String searchString) {
        query.setParameter(1, "%" + searchString + "%");
        return query.getResultList();
    }

    /**************
     * RECIPES
     **************/

    private final String recipeSearchQueryString =
            "SELECT DISTINCT recipes.id, recipes.name, recipes.cook_time FROM recipes, recipetag, recipes_recipetag" +
                    " WHERE recipes.id = recipes_recipetag.recipe_id AND recipetag.id = recipes_recipetag.tags_id" +
                    " AND (recipes.name LIKE ? OR recipetag.tagname = ?)";


    /**
     * Searches recipes names and tags containing the provided search string and also is visible.
     * If no match, en empty list is returned.
     *
     * @param searchString the string to search for.
     * @return list of recipe results or empty list
     */

    public List<RecipeSearchResult> searchRecipesByNameAndTags(String searchString, String recipeType) {
        String queryString = recipeSearchQueryString + " AND recipes.is_public = true";
        if (!recipeType.isBlank()) {
            queryString += " AND recipes.type = ?";
        }
        Query query = em.createNativeQuery(queryString, "ScheduleResult");
        if (!recipeType.isBlank()) query.setParameter(3, recipeType);
        return this.searchRecipes(query, searchString);
    }

    /**
     * Searches recipes names and tags containing the provided search string owned by the user with the provided id.
     * Includes also the users not visible recipes.
     * If no match, en empty list is returned.
     *
     * @param searchString the string to search for.
     * @return list of recipe results or empty list
     */
    public List<RecipeSearchResult> searchRecipesByNameAndTagsOwnerOnly(String searchString, String recipeType, int userId) {
        String queryString = recipeSearchQueryString + " AND recipes.creator_id = ?";
        if (!recipeType.isBlank()) {
            queryString += " AND recipes.type = ?";
        }
        Query query = em.createNativeQuery(queryString, "ScheduleResult");
        if (!recipeType.isBlank()) query.setParameter(4, recipeType);
        query.setParameter(3, userId);
        return this.searchRecipes(query, searchString);
    }

    /**
     * Injects the search string for tags, and recipe names and execute the search and returns the result
     *
     * @param query        the query to execute
     * @param searchString the string to search for
     * @return returns the search result
     */
    private List<RecipeSearchResult> searchRecipes(Query query, String searchString) {
        query.setParameter(1, "%" + searchString + "%");
        query.setParameter(2, searchString);
        return query.getResultList();
    }

    /**************
     * MENUS
     **************/

    private String menuSearchString = "SELECT name, id, array_agg(day) as days FROM (" +
            " SELECT menus.name, menus.id, menus.creator, menus.is_public, menu_meals.day as day FROM menus, menu_meals, menu_meal_link" +
            " WHERE (menu_meals.id = menu_meal_link.meals_id" +
            " AND menus.id = menu_meal_link.menu_id)" +
            " UNION" +
            " SELECT menus.name, menus.id, menus.creator, menus.is_public, menu_recipes.day as day FROM menus, menu_recipes, menu_recipe_link" +
            " WHERE (menu_recipes.id = menu_recipe_link.recipes_id" +
            " AND menus.id = menu_recipe_link.menu_id)" +
            ") sub" +
            " WHERE name LIKE ?";


    /**
     * Search for menus by its name and return the result.
     *
     * @param searchString the string to search for
     * @return returns search result
     */
    public List<MenuSearchResult> searchMenusByName(String searchString) {
        String queryString = menuSearchString;
        queryString += " AND sub.is_public = ?";
        queryString += " group by name, id;";
        Query query = em.createNativeQuery(queryString, "MenuSearchResult");
        query.setParameter(2, true);
        return this.searchMenus(query, searchString);
    }

    /**
     * Search for menus by its name and the owner of the menu and return the result.
     *
     * @param searchString the string to search for
     * @param userId       the owner of the menus
     * @return returns search result
     */
    public List<MenuSearchResult> searchMenusByNameOwnerOnly(String searchString, BigInteger userId) {
        String queryString = menuSearchString;
        queryString += " AND sub.creator = ?";
        queryString += " group by name, id;";
        Query query = em.createNativeQuery(queryString, "MenuSearchResult");
        query.setParameter(2, userId);
        return this.searchMenus(query, searchString);
    }

    /**
     * Injects the search string and execute the search and returns the result
     *
     * @param query        the query to execute
     * @param searchString the string to search for
     * @return returns the search result
     */
    private List<MenuSearchResult> searchMenus(Query query, String searchString) {
        query.setParameter(1, "%" + searchString + "%");
        return query.getResultList();
    }

}
