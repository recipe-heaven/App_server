package no.twct.recipeheaven.search.entity;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Provides methods to perform specific searches in the data base for
 * entities.
 */
public class SearchDAO {

    @PersistenceContext
    EntityManager em;

    //    private static final String restrctView = " not (item.isPublic = false and item.creator.id <> :id) ";
    private static final String restrictView = " item.id not in (select ba.id from CreatableBase as ba where ba.isPublic = false and item.creator.id <> :id) ";

    private static final String baseRecipeQuery = "SELECT new no.twct.recipeheaven.search.entity.RecipeSearchResult( item) from Recipe AS item where " + restrictView;
    private static final String baseMenuQuery = "SELECT new no.twct.recipeheaven.search.entity.MenuSearchResult( item) from Menu AS item where " + restrictView;
    private static final String baseMealQuery = "SELECT new no.twct.recipeheaven.search.entity.MealSearchResult( item) from Meal AS item where " + restrictView;

    private static final String queryUserIdFilter = " AND item.creator.id = :id";
    private static final String queryNameFilter = " AND item.name LIKE :name_q";
    private static final String queryInUserStarFilter = " AND  item.id in (select e.id from UserStatus u JOIN  u.staredEntities e where u.id = :id) ";
    //    private static final String queryInUserStarFilter = "AND  item.id in (select st.id from (select u.staredEntities from UserStatus as u where u.id = :id) as st) ";

    private static final String queryRecipeTypeFilter = " AND item.type LIKE :type_q";
    private static final String queryNameAndTagTypeFilter = " AND item.id in (select rp.id from Recipe as rp where rp.name LIKE :name_q OR rp.id in (select r_tag.id from RecipeTag as r_tag where r_tag.tagName = :name_q))  ";


    /**************
     * RECIPES
     **************/
    public List<RecipeSearchResult> searchRecipesByStared(BigInteger userId) {
        String queryString = baseRecipeQuery + queryInUserStarFilter;
        Query  q           = em.createQuery(queryString);
        q.setParameter("id", userId);
        return q.getResultList();
    }

    /**
     * Searches recipes names and tags containing the provided search string and also is visible.
     * If no match, en empty list is returned.
     *
     * @param searchString the string to search for.
     * @param userId       query user
     * @return list of recipe results or empty list
     */
    public List<RecipeSearchResult> searchRecipesByNameAndTags(String searchString, String recipeType, BigInteger userId) {
        String queryString = baseRecipeQuery + queryNameAndTagTypeFilter + queryRecipeTypeFilter;

        Query q = em.createQuery(queryString);
        q.setParameter("name_q", "%" + searchString + "%");
        q.setParameter("type_q", "%" + recipeType + "%");
        q.setParameter("id", userId);
        return q.getResultList();
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
    public List<RecipeSearchResult> searchRecipesByNameAndTagsOwnerOnly(String searchString, String recipeType, BigInteger userId) {
        String queryString = baseRecipeQuery + queryNameAndTagTypeFilter + queryUserIdFilter;

        Query q = em.createQuery(queryString);
        q.setParameter("name_q", "%" + searchString + "%");
        q.setParameter("id", userId);
        return q.getResultList();
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
    public List<MealSearchResult> searchMealsByName(String searchString, BigInteger userId) {
        String queryString = baseMealQuery + queryNameFilter;

        Query q = em.createQuery(queryString);
        q.setParameter("name_q", "%" + searchString + "%");
        q.setParameter("id", userId);
        return q.getResultList();
    }

    /**
     * Search for meals by its name and the owner of the meal and return the result.
     *
     * @param searchString the string to search for
     * @param userId       query user
     * @return returns search result
     */
    public List<MealSearchResult> searchMealsByNameOwnerOnly(String searchString, BigInteger userId) {
        String queryString = baseMealQuery + queryNameFilter + queryUserIdFilter;

        Query q = em.createQuery(queryString);
        q.setParameter("name_q", "%" + searchString + "%");
        q.setParameter("id", userId);
        return q.getResultList();
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
    public List<MenuSearchResult> searchMenusByName(String searchString, BigInteger userId) {
        String queryString = baseMenuQuery + queryNameFilter;

        Query q = em.createQuery(queryString);
        q.setParameter("name_q", "%" + searchString + "%");
        q.setParameter("id", userId);
        return q.getResultList();
    }

    /**
     * Search for menus by its name and the owner of the menu and return the result.
     *
     * @param searchString the string to search for
     * @param userId       query user
     * @return returns search result
     */
    public List<MenuSearchResult> searchMenusByNameOwnerOnly(String searchString, BigInteger userId) {
        String queryString = baseMenuQuery + queryNameFilter + queryUserIdFilter;

        Query q = em.createQuery(queryString);
        q.setParameter("name_q", "%" + searchString + "%");
        q.setParameter("id", userId);
        return q.getResultList();
    }


}
