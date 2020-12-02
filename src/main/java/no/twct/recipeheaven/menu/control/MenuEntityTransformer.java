package no.twct.recipeheaven.menu.control;

import no.twct.recipeheaven.entity.DisplayableEntity;
import no.twct.recipeheaven.meal.control.MealEntityTransformer;
import no.twct.recipeheaven.menu.entity.*;
import no.twct.recipeheaven.recipe.control.RecipeEntityTransformer;
import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;
import no.twct.recipeheaven.user.control.UserEntityTransformer;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides transformation methods to create DTO objects
 * of the Menu entity
 */
public class MenuEntityTransformer {

    @Inject
    RecipeEntityTransformer recipeEntityTransformer;

    @Inject
    MealEntityTransformer mealEntityTransformer;

    @Inject
    UserEntityTransformer userEntityTransformer;

    /**
     * Sets the displayable entity fields for the provided DTO object.
     *
     * @param dto  the dto to set values on
     * @param menu the entity to get the values from
     */
    private void setBaseDtoValues(DisplayableEntity dto, Menu menu) {
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setPublic(menu.isPublic());
        dto.setUpdated(menu.getUpdated());
        dto.setOwner(userEntityTransformer.createUserDetailsDTO(menu.getCreator()));
    }

    /**
     * Creates a minified projection of a menu entity, with all recipes and meals minified as well.
     *
     * @param menu the menu to transform
     * @return returns the minified projection
     */
    public MenuDTO createSimpleMenuDTO(Menu menu) {
        if(menu == null) return null;
        MenuDTO menuSimpleDTO = new MenuDTO();
        setBaseDtoValues(menuSimpleDTO, menu);
        List<MenuRecipeDTO> recipes = createSimpleMenuRecipeDTO(menu.getRecipes());
        List<MenuMealDTO>   meals   = createSimpleMenuMealDTO(menu.getMeals());
        menuSimpleDTO.setRecipes(recipes);
        menuSimpleDTO.setMeals(meals);
        return menuSimpleDTO;
    }

    /**
     * Creates a list of menu recipes DTOs with all recipes simplified
     *
     * @param menuRecipes list of menu recipes to transform to DTO
     * @return returns a list of DTOs
     */
    private List<MenuRecipeDTO> createSimpleMenuRecipeDTO(List<MenuRecipe> menuRecipes) {
        return menuRecipes.stream().map(menuRecipe -> {
            var menuRecipeDTO = new MenuRecipeDTO();
            menuRecipeDTO.setDay(menuRecipe.getDay());
            menuRecipeDTO.setRecipe(recipeEntityTransformer.createSimpleRecipeDTO(menuRecipe.getRecipe()));
            return menuRecipeDTO;
        }).collect(Collectors.toList());
    }


    /**
     * Creates a list of menu recipes DTOs with full recipes
     *
     * @param menuRecipes list of menu recipes to transform to DTO
     * @return returns a list of DTOs
     */
    private List<MenuRecipeDTO> createFullMenuRecipeDTO(List<MenuRecipe> menuRecipes) {
        return menuRecipes.stream().map(menuRecipe -> {
            var menuRecipeDTO = new MenuRecipeDTO();
            menuRecipeDTO.setDay(menuRecipe.getDay());
            menuRecipeDTO.setRecipe(recipeEntityTransformer.createFullRecipeDTO(menuRecipe.getRecipe()));
            return menuRecipeDTO;
        }).collect(Collectors.toList());
    }

    /**
     * Creates a list of menu meal DTOs with all meals simplified
     *
     * @param menuMeals list of menu meals to transform to DTO
     * @return returns a list of DTOs
     */
    private List<MenuMealDTO> createSimpleMenuMealDTO(List<MenuMeal> menuMeals) {
        return menuMeals.stream().map(menuRecipe -> {
            var menuMealDTO = new MenuMealDTO();
            menuMealDTO.setDay(menuRecipe.getDay());
            menuMealDTO.setMeal(mealEntityTransformer.createSimpleMealDTO(menuRecipe.getMeal()));
            return menuMealDTO;
        }).collect(Collectors.toList());
    }


    /**
     * Creates a list of menu meal DTOs with all details
     *
     * @param menuMeals list of menu meals to transform to DTO
     * @return returns a list of DTOs
     */
    private List<MenuMealDTO> createFullMenuMealDTO(List<MenuMeal> menuMeals) {
        return menuMeals.stream().map(menuRecipe -> {
            var menuMealDTO = new MenuMealDTO();
            menuMealDTO.setDay(menuRecipe.getDay());
            menuMealDTO.setMeal(mealEntityTransformer.createFullMealDTO(menuRecipe.getMeal()));
            return menuMealDTO;
        }).collect(Collectors.toList());
    }


    /**
     * Creates a full projection of a menu entity, with all recipes and meal details.
     *
     * @param menu the menu to transform
     * @return returns the full projection
     */
    public MenuDTO createFullMenuDTO(Menu menu) {
        if(menu == null) return null;
        MenuDTO menuFullDTO = new MenuDTO();
        setBaseDtoValues(menuFullDTO, menu);
        List<MenuRecipeDTO> recipes = createFullMenuRecipeDTO(menu.getRecipes());
        List<MenuMealDTO>   meals   = createFullMenuMealDTO(menu.getMeals());
        menuFullDTO.setRecipes(recipes);
        menuFullDTO.setMeals(meals);
        return menuFullDTO;
    }

}
