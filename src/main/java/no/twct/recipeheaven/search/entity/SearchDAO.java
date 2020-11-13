package no.twct.recipeheaven.search.entity;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Proves database access functions for search where
 * a regular entity object can not be used.
 */

public class SearchDAO {

    @PersistenceContext
    EntityManager em;

    private final String recipeSearchQueryString =
            "SELECT DISTINCT recipes.id, recipes.name, recipes.cook_time FROM recipes, recipetag, " +
                    "recipes_recipetag WHERE recipes.id = recipes_recipetag.recipe_id AND recipetag.id = recipes_recipetag.tags_id " +
                    "AND (recipes.name LIKE ? OR recipetag.tagname = ?)";


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

    private List<RecipeSearchResult> searchRecipes(Query query, String searchString) {
        query.setParameter(1, "%" + searchString + "%");
        query.setParameter(2, searchString);
        return query.getResultList();
    }


}
