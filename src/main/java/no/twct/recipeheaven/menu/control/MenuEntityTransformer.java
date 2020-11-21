package no.twct.recipeheaven.menu.control;

import no.twct.recipeheaven.entity.DisplayableEntity;
import no.twct.recipeheaven.meal.control.MealEntityTransformer;
import no.twct.recipeheaven.menu.entity.*;
import no.twct.recipeheaven.recipe.control.RecipeEntityTransformer;
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
        System.out.println(menu.getCreator().getEmail());
        dto.setOwner(userEntityTransformer.createUserDetailsDTO(menu.getCreator()));
    }

    /**
     * Creates a minified projection of a menu entity, with all recipes and meals minified as well.
     *
     * @param menu the menu to transform
     * @return returns the minified projection
     */
    public MenuSimpleDTO createSimpleMenuDTO(Menu menu) {
        if(menu == null) return null;
        MenuSimpleDTO menuSimpleDTO = new MenuSimpleDTO();
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
     * Creates a full projection of a menu entity, with all recipes details.
     *
     * @param menu the menu to transform
     * @return returns the full projection
     */
//    public FullMealDTO createFullMealDTO(Meal menu) {
//        FullMealDTO menuSimpleDTO = new FullMealDTO();
//        setBaseDtoValues(mealSimpleDetailsDTO, menu);
//        List<FullRecipeDTO> recipes = menu.getRecipes().stream()
//                .map(recipeEntityTransformer::createFullRecipeDTO).collect(Collectors.toList());
//        mealSimpleDetailsDTO.setRecipes(recipes);
//        return mealSimpleDetailsDTO;
//    }

}
