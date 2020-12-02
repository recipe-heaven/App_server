package no.twct.recipeheaven.search.entity;


import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;

/**
 * Provides methods to perform specific searches in the data base for
 * entities.
 */
public class SearchDAO {

    @PersistenceContext
    EntityManager em;

    private static final String restrictView = " item.id not in (select ba.id from CreatableBase as ba where ba.isPublic = false and item.creator.id <> :id) ";

    private static final String baseRecipeQuery = "SELECT item from Recipe AS item where " + restrictView;
    private static final String baseMenuQuery = "SELECT item from Menu AS item where " + restrictView;
    private static final String baseMealQuery = "SELECT item from Meal AS item where " + restrictView;

    private static final String queryUserIdFilter = " AND item.creator.id = :id";
    private static final String queryNameFilter = " AND lower(item.name) LIKE :name_q";
    private static final String queryInUserStarFilter = " AND  item.id in (select e.id from UserMetaInfo u JOIN  u.staredEntities e where u.id = :id) ";

    private static final String queryRecipeTypeFilter = " AND item.type LIKE :type_q";
    private static final String queryNameAndTagTypeFilter = " AND item.id in (select rp.id from Recipe as rp where lower(rp.name) LIKE :name_q OR rp.id in (select r_tag.id from RecipeTag as r_tag where lower(r_tag.tagName) = :tag_q))  ";


    /**************
     * RECIPES
     **************/
    public List<Recipe> searchRecipesByStared(BigInteger userId) {
        String queryString = baseRecipeQuery + queryInUserStarFilter;

        TypedQuery<Recipe> query = em.createQuery(queryString.toLowerCase(), Recipe.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    /**
     * Searches recipes names and tags containing the provided search string and also is visible.
     * If no match, en empty list is returned.
     *
     * @param searchString the string to search for.
     * @param userId       query user
     * @return list of recipe results or empty list
     */
    public List<Recipe> searchRecipesByNameAndTags(String searchString, String recipeType, BigInteger userId) {
        String queryString = baseRecipeQuery + queryNameAndTagTypeFilter + queryRecipeTypeFilter;

        System.out.println(recipeType);
        TypedQuery<Recipe> query = em.createQuery(queryString, Recipe.class);
        query.setParameter("name_q", "%" + searchString.toLowerCase() + "%");
        query.setParameter("tag_q",  searchString.toLowerCase() );
        query.setParameter("type_q",  "%" + recipeType + "%" );
        query.setParameter("id", userId);

        return query.getResultList();
    }


    /**
     * Searches recipes names and tags containing the provided search string owned by the user with the provided id.
     * Includes also the users not visible recipes.
     * If no match, en empty list is returned.
     *
     * @param searchString the string to search for.
     * @param userId       query user
     * @return list of recipe results or empty list
     */
    public List<Recipe> searchRecipesByNameAndTagsOwnerOnly(String searchString, String recipeType, BigInteger userId) {
        String queryString = baseRecipeQuery + queryNameAndTagTypeFilter + queryUserIdFilter + queryRecipeTypeFilter;

        TypedQuery<Recipe> query = em.createQuery(queryString, Recipe.class);
        query.setParameter("name_q", "%" + searchString.toLowerCase() + "%");
        query.setParameter("tag_q",  searchString.toLowerCase() );
        query.setParameter("type_q",  "%" + recipeType + "%" );
        query.setParameter("id", userId);
        return query.getResultList();
    }

    /**************
     * MEALS
     **************/


    /**
     * Search for meals by its name and return the result.
     *
     * @param searchString the string to search for
     * @param userId       query user
     * @return returns search result
     */
    public List<Meal> searchMealsByName(String searchString, BigInteger userId) {
        String queryString = baseMealQuery + queryNameFilter;

        TypedQuery<Meal> query = em.createQuery(queryString, Meal.class);
        query.setParameter("name_q", "%" + searchString.toLowerCase() + "%");
        query.setParameter("id", userId);
        return query.getResultList();
    }

    /**
     * Search for meals by its name and the owner of the meal and return the result.
     *
     * @param searchString the string to search for
     * @param userId       query user
     * @return returns search result
     */
    public List<Meal> searchMealsByNameOwnerOnly(String searchString, BigInteger userId) {
        String queryString = baseMealQuery + queryNameFilter + queryUserIdFilter;

        TypedQuery<Meal> query = em.createQuery(queryString, Meal.class);
        query.setParameter("name_q", "%" + searchString.toLowerCase() + "%");
        query.setParameter("id", userId);
        return query.getResultList();
    }


    /**************
     * MENUS
     **************/

    /**
     * Search for menus by its name and return the result.
     *
     * @param searchString the string to search for
     * @param userId       query user
     * @return returns search result
     */
    public List<Menu> searchMenusByName(String searchString, BigInteger userId) {
        String queryString = baseMenuQuery + queryNameFilter;

        TypedQuery<Menu> query = em.createQuery(queryString, Menu.class);
        query.setParameter("name_q", "%" + searchString.toLowerCase() + "%");
        query.setParameter("id", userId);
        return query.getResultList();
    }

    /**
     * Search for menus by its name and the owner of the menu and return the result.
     *
     * @param searchString the string to search for
     * @param userId       query user
     * @return returns search result
     */
    public List<Menu> searchMenusByNameOwnerOnly(String searchString, BigInteger userId) {
        String queryString = baseMenuQuery + queryNameFilter + queryUserIdFilter;

        TypedQuery<Menu> query = em.createQuery(queryString, Menu.class);
        query.setParameter("name_q", "%" + searchString.toLowerCase() + "%");
        query.setParameter("id", userId);
        return query.getResultList();
    }


}
