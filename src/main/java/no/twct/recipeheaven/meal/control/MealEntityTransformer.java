package no.twct.recipeheaven.meal.control;

import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.meal.entity.MinifiedMealDTO;
import no.twct.recipeheaven.recipe.control.RecipeEntityTransformer;
import no.twct.recipeheaven.recipe.entity.MinifiedRecipeDTO;

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

    /**
     * Creates a minified projection of a meal entity, with all recipes minified as well.
     *
     * @param meal the meal to transform
     * @return returns the minified projection
     */
    public MinifiedMealDTO createMealDetailsDTO(Meal meal) {
        MinifiedMealDTO mealSimpleDetailsDTO = new MinifiedMealDTO();
        mealSimpleDetailsDTO.setName(meal.getName());
        mealSimpleDetailsDTO.setOwner(meal.getCreator().getId());
        List<MinifiedRecipeDTO> recipes = meal.getRecipes().stream().map(recipeEntityTransformer::createSimpleRecipeDTO).collect(Collectors.toList());
        mealSimpleDetailsDTO.setRecipes(recipes);
        return mealSimpleDetailsDTO;
    }
}
