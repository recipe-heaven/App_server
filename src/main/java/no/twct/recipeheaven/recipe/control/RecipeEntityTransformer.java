package no.twct.recipeheaven.recipe.control;


import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;
import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.recipe.entity.RecipeDTO;
import no.twct.recipeheaven.user.control.UserEntityTransformer;

import javax.inject.Inject;

/**
 * Provides transformation methods to create DTO objects
 * of the Recipe entity
 */
public class RecipeEntityTransformer {

    @Inject
    UserEntityTransformer userEntityTransformer;

    private void setBaseDtoValues(RecipeDTO dto, Recipe recipe) {
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setType(recipe.getType());
        dto.setPublic(recipe.isPublic());
        dto.setUpdated(recipe.getUpdated());
        dto.setImage(recipe.getRecipeImage());
        dto.setCookTime(recipe.getCookTime());
        dto.setDescription(recipe.getDescription());
        dto.setOwner(userEntityTransformer.createUserDetailsDTO(recipe.getCreator()));
    }

    /**
     * Creates a recipe DTO with minified details
     *
     * @param recipe the recipe to transform
     * @return recipe DTO with minified details
     */
    public RecipeDTO createSimpleRecipeDTO(Recipe recipe) {
        RecipeDTO simpleRecipeDTO = new RecipeDTO();
        setBaseDtoValues(simpleRecipeDTO, recipe);
        return simpleRecipeDTO;
    }

    /**
     * Creates a recipe DTO with all details
     *
     * @param recipe the recipe to transform
     * @return recipe DTO with all details
     */
    public FullRecipeDTO createFullRecipeDTO(Recipe recipe) {
        FullRecipeDTO fullRecipeDTP = new FullRecipeDTO();
        setBaseDtoValues(fullRecipeDTP, recipe);
        fullRecipeDTP.setTags(recipe.getTags());
        fullRecipeDTP.setPublic(recipe.isPublic());
        fullRecipeDTP.setCookingSteps(recipe.getCookingSteps());
        fullRecipeDTP.setRecipeIngredients(recipe.getRecipeIngredients());
        fullRecipeDTP.setRecommendedDrinks(recipe.getRecommendedDrinks());
        return fullRecipeDTP;
    }
}
