package no.twct.recipeheaven.search.entity;


import no.twct.recipeheaven.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Proves database access functions for search where
 * a regular entity object can not be used.
 */
public class SearchDAO {

    @PersistenceContext
    EntityManager em;

    private final String recipeSearchQueryString =
            "SELECT NEW no.twct.recipeheaven.search.entity.RecipeSearchResult(recipe.id, recipe.name , recipe.cookTime) " +
                    "FROM Recipe recipe WHERE recipe.name LIKE :like OR recipe.tags LIKE :like";

    /**
     * Searches recipes names and tags containing the provided search string and also is visible.
     * If no match, en empty list is returned.
     *
     * @param searchString the string to search for.
     * @return list of recipe results or empty list
     */
    public List<RecipeSearchResult> searchRecipesByNameAndTags(String searchString) {
        var queryString = recipeSearchQueryString + " AND recipe.visible = true";
        var query       = em.createQuery(queryString, RecipeSearchResult.class);
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
    public List<RecipeSearchResult> searchRecipesByNameAndTagsOwnerOnly(String searchString, User user) {
        var queryString = recipeSearchQueryString + " AND recipe.creator = :id";
        var query       = em.createQuery(queryString, RecipeSearchResult.class);
        query.setParameter("id", user);
        return this.searchRecipes(query, searchString);
    }

    private List<RecipeSearchResult> searchRecipes(TypedQuery<RecipeSearchResult> query, String searchString) {
        return query.setParameter("like", "%" + searchString + "%").getResultList();
    }


}
