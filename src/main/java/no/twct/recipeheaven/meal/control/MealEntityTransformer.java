package no.twct.recipeheaven.meal.control;

import no.twct.recipeheaven.meal.entity.FullMealDTO;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.meal.entity.MealDTO;
import no.twct.recipeheaven.meal.entity.SimpleMealDTO;
import no.twct.recipeheaven.recipe.control.RecipeEntityTransformer;
import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;
import no.twct.recipeheaven.recipe.entity.RecipeDTO;
import no.twct.recipeheaven.user.control.UserEntityTransformer;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides transformation methods to create DTO objects
 * of the Meal entity
 */
public class MealEntityTransformer {

    @Inject
    RecipeEntityTransformer recipeEntityTransformer;

    @Inject
    UserEntityTransformer userEntityTransformer;

    private void setBaseDtoValues(MealDTO dto, Meal meal) {
        dto.setName(meal.getName());
        dto.setOwner(userEntityTransformer.createUserDetailsDTO(meal.getCreator()));
    }

    /**
     * Creates a minified projection of a meal entity, with all recipes minified as well.
     *
     * @param meal the meal to transform
     * @return returns the minified projection
     */
    public SimpleMealDTO createSimpleMealDTO(Meal meal) {
        SimpleMealDTO mealSimpleDetailsDTO = new SimpleMealDTO();
        setBaseDtoValues(mealSimpleDetailsDTO, meal);
        List<RecipeDTO> recipes = meal.getRecipes().stream()
                .map(recipeEntityTransformer::createSimpleRecipeDTO).collect(Collectors.toList());
        mealSimpleDetailsDTO.setRecipes(recipes);
        return mealSimpleDetailsDTO;
    }


    /**
     * Creates a full projection of a meal entity, with all recipes details.
     *
     * @param meal the meal to transform
     * @return returns the full projection
     */
    public FullMealDTO createFullMealDTO(Meal meal) {
        FullMealDTO mealSimpleDetailsDTO = new FullMealDTO();
        setBaseDtoValues(mealSimpleDetailsDTO, meal);
        List<FullRecipeDTO> recipes = meal.getRecipes().stream()
                .map(recipeEntityTransformer::createFullRecipeDTO).collect(Collectors.toList());
        mealSimpleDetailsDTO.setRecipes(recipes);
        return mealSimpleDetailsDTO;
    }
}
