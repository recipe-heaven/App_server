package no.twct.recipeheaven.search.control;

import no.twct.recipeheaven.entity.DisplayableEntity;
import no.twct.recipeheaven.meal.control.MealEntityTransformer;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.menu.control.MenuEntityTransformer;
import no.twct.recipeheaven.menu.entity.Menu;
import no.twct.recipeheaven.recipe.control.RecipeEntityTransformer;
import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.search.entity.ResultItem;
import no.twct.recipeheaven.search.entity.SearchDAO;
import no.twct.recipeheaven.search.entity.SearchResultContainer;
import no.twct.recipeheaven.user.boundry.AuthenticationService;
import no.twct.recipeheaven.user.entity.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static no.twct.recipeheaven.Const.*;


/**
 * Responsible for performing search related services.
 */
@Transactional
public class SearchService {

    @Inject
    AuthenticationService authenticationService;

    @Inject
    SearchDAO searchDAO;

    @Inject
    MenuEntityTransformer menuEntityTransformer;

    @Inject
    MealEntityTransformer mealEntityTransformer;

    @Inject
    RecipeEntityTransformer recipeEntityTransformer;

    /**
     * Holds all the results for the search
     */
    List<ResultItem> searchResult = new ArrayList<ResultItem>();

    /**
     * Searches for recipes, menus and meals based on the search options provided.
     * Returns the search result container, containing the result of the search.
     *
     * @param options search options for the search
     * @return returns the result container
     */
    public SearchResultContainer performMealRecipeMenuSearch(SearchOptions options) {
        SearchResultContainer resultContainer = new SearchResultContainer();
        User user = authenticationService.getLoggedInUser();

        if (user == null) {
            options.setOwnedOnly(false);
        }

        if (options.staredOnly) {
            searchStaredRecipes(options, user);
        } else {
            search(options, user);
        }


        resultContainer.setResult(searchResult);
        return resultContainer;
    }


    /**
     * Searches only a users owned recipes, meals, and menus.
     * The provided search options provides conditions for if
     * it should not include any, some or all of the types.
     *
     * @param options search options
     * @param user    the user searching
     * @return returns a list of the result, or empty list
     */
    void search(SearchOptions options, User user) {
        if (options.includeMeals) {
            List<Meal> mealResult;
            if (options.ownedOnly) {
                mealResult = searchDAO.searchMealsByNameOwnerOnly(options.searchString, user.getId());
            } else {
                mealResult = searchDAO.searchMealsByName(options.searchString, user.getId());
            }
            addToResultList(mealResult, MEAL_TYPE_NAME, mealEntityTransformer::createSimpleMealDTO);
        }
        if (options.includeMenus) {
            List<Menu> menuResult;
            if (options.ownedOnly) {
                menuResult = searchDAO.searchMenusByNameOwnerOnly(options.searchString, user.getId());
            } else {
                menuResult = searchDAO.searchMenusByName(options.searchString, user.getId());
            }
            addToResultList(menuResult, MENU_TYPE_NAME, menuEntityTransformer::createSimpleMenuDTO);
        }
        if (options.includeRecipes) {
            List<Recipe> recipeResult;
            if (options.ownedOnly) {
                recipeResult = searchDAO.searchRecipesByNameAndTagsOwnerOnly(options.searchString, options.recipeType, user.getId());
            } else {
                recipeResult = searchDAO.searchRecipesByNameAndTags(options.searchString, options.recipeType, user.getId());
            }
            addToResultList(recipeResult, RECIPE_TYPE_NAME, recipeEntityTransformer::createSimpleRecipeDTO);
        }
    }

    /**
     * Creates a result item list from the results from a search. It adds the provided type and executes the provided
     * transformer method for ech results.
     *
     * @param resultList  search results list
     * @param type        type of the result item
     * @param transformer transform method for conversion
     * @param <T>         the type of the search result
     * @return result item list
     */
    private <T> void addToResultList(List<T> resultList, String type, Function<T, DisplayableEntity> transformer) {
        resultList.forEach(
                resultType ->
                        this.searchResult.add(new ResultItem(type, transformer.apply(resultType)))
        );
    }

    /**
     * Searches for stared recipes only
     *
     * @param options search options
     * @param user    the user performing the search
     * @return result item list of starred recipes
     */
    void searchStaredRecipes(SearchOptions options, User user) {
        var result = searchDAO.searchRecipesByStared(user.getId());
        addToResultList(result, RECIPE_TYPE_NAME, recipeEntityTransformer::createSimpleRecipeDTO);
    }

}
