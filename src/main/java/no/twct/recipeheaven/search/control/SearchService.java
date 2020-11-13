package no.twct.recipeheaven.search.control;

import no.twct.recipeheaven.search.entity.ResultItem;
import no.twct.recipeheaven.search.entity.SearchDAO;
import no.twct.recipeheaven.search.entity.SearchResultContainer;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import no.twct.recipeheaven.user.entity.User;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static no.twct.recipeheaven.Const.RECIPE_TYPE_NAME;


/**
 * Responsible for performing search related services.
 */
@Transactional
public class SearchService {

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    AuthenticationService authenticationService;

    @Inject
    SearchDAO searchDAO;

    @PersistenceContext
    EntityManager em;

    /**
     * Searches for recipes, menus and meals based on the search options provided.
     * Returns the search result container, containing the result of the search.
     *
     * @param options search options for the search
     * @return returns the result container
     */
    public SearchResultContainer performMealRecipeMenuSearch(SearchOptions options) {
        System.out.println("TOKEN OWNER:" + jsonWebToken.getName());
        User user = authenticationService.getCurrentUser(jsonWebToken.getName());
        if (user == null) {
            System.out.println("NO USER!!!!");
            options.setOwnedOnly(false);
        }
        System.out.println(options.includeMeals);
        System.out.println(options.includeMenus);
        System.out.println(options.includeRecipes);
        System.out.println(options.ownedOnly);
        System.out.println(options.recipeType);
        System.out.println(options.searchString);

        SearchResultContainer resultContainer = new SearchResultContainer();
        List<ResultItem>      results;
        if (options.ownedOnly) {
            results = searchOwnedOnly(options, user);
        } else {
            results = search(options);
        }
        resultContainer.setResult(results);
        return resultContainer;
    }

    /**
     * Searches only a users owned recipes, meals, and menus.
     * The provided search options provides conditions for if
     * it should not include any, some or all of the types.
     *
     * @param options search options
     * @param user    the user which owns the item
     * @return returns a list of the result, or empty list
     */
    List<ResultItem> searchOwnedOnly(SearchOptions options, User user) {
        List<ResultItem> results = new ArrayList<ResultItem>();
        if (options.includeMeals) {

        }
        if (options.includeMenus) {

        }
        if (options.includeRecipes) {
            searchDAO.searchRecipesByNameAndTagsOwnerOnly(options.searchString, options.recipeType, user.getId().intValue()).forEach(recipe -> {
                results.add(new ResultItem(RECIPE_TYPE_NAME, recipe));
            });
        }
        return results;
    }

    /**
     * Searches all recipes, meals, and menus.
     * The provided search options provides conditions for if
     * it should not include any, some or all of the types.
     *
     * @param options search options
     * @return returns a list of the result, or empty list
     */
    List<ResultItem> search(SearchOptions options) {
        List<ResultItem> results = new ArrayList<ResultItem>();
        if (options.includeMeals) {

        }
        if (options.includeMenus) {

        }
        if (options.includeRecipes) {
            searchDAO.searchRecipesByNameAndTags(options.searchString, options.recipeType).forEach(recipe -> {
                results.add(new ResultItem(RECIPE_TYPE_NAME, recipe));
            });
        }
        return results;
    }


}
