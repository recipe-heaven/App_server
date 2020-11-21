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

import static no.twct.recipeheaven.Const.*;


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
        User user = authenticationService.getCurrentUser(jsonWebToken.getName());
        if (user == null) {
            options.setOwnedOnly(false);
        }

        SearchResultContainer resultContainer = new SearchResultContainer();
        List<ResultItem>      results;

        if (options.staredOnly) {
            resultContainer.setResult(getStaredRecipes(options, user));
            return resultContainer;
        }

        if (options.ownedOnly) {
            results = searchOwnedOnly(options, user);
        } else {
            // todo: this may need to be fixed but it seems a non loged in user arent alowed to search anywhay
            results = search(options, user);
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
            searchDAO.searchMealsByNameOwnerOnly(options.searchString, user.getId()).forEach(meal -> results.add(new ResultItem(MEAL_TYPE_NAME, meal)));
        }
        if (options.includeMenus) {
            searchDAO.searchMenusByNameOwnerOnly(options.searchString, user.getId()).forEach(meal -> results.add(new ResultItem(MENU_TYPE_NAME, meal)));
        }
        if (options.includeRecipes) {
            searchDAO.searchRecipesByNameAndTagsOwnerOnly(options.searchString, options.recipeType, user.getId()).forEach(recipe -> {
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
    List<ResultItem> search(SearchOptions options, User user) {
        List<ResultItem> results = new ArrayList<ResultItem>();
        if (options.includeMeals) {
            searchDAO.searchMealsByName(options.searchString, user.getId()).forEach(meal -> results.add(new ResultItem(MEAL_TYPE_NAME, meal)));
        }
        if (options.includeMenus) {
            searchDAO.searchMenusByName(options.searchString, user.getId()).forEach(meal -> results.add(new ResultItem(MENU_TYPE_NAME, meal)));
        }
        if (options.includeRecipes) {
            searchDAO.searchRecipesByNameAndTags(options.searchString, options.recipeType, user.getId()).forEach(recipe -> {
                results.add(new ResultItem(RECIPE_TYPE_NAME, recipe));
            });
        }
        return results;
    }

    List<ResultItem> getStaredRecipes(SearchOptions options, User user) {
        List<ResultItem> results = new ArrayList<ResultItem>();
        searchDAO.searchRecipesByStared(user.getId()).forEach(recipe -> {
            results.add(new ResultItem(RECIPE_TYPE_NAME, recipe));
        });
        return results;
    }

}
