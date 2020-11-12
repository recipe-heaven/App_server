package no.twct.recipeheaven.recipe.control;


import no.twct.recipeheaven.recipe.entity.MinifiedRecipeDTO;
import no.twct.recipeheaven.recipe.entity.Recipe;
import no.twct.recipeheaven.user.control.UserEntityTransformer;

import javax.inject.Inject;

/**
 * Provides transformation methods to create DTO objects
 * of the Recipe entity
 */
public class RecipeEntityTransformer {

    @Inject
    UserEntityTransformer userEntityTransformer;

    public MinifiedRecipeDTO createSimpleRecipeDTO(Recipe reci) {
        MinifiedRecipeDTO minifiedRecipeDTO = new MinifiedRecipeDTO();
        minifiedRecipeDTO.setId(reci.getId());
        minifiedRecipeDTO.setName(reci.getName());
        minifiedRecipeDTO.setDescription(reci.getDescription());
        minifiedRecipeDTO.setCookTime(reci.getCookTime());
        minifiedRecipeDTO.setOwner(userEntityTransformer.createUserDetailsDTO(reci.getCreator()));
        return minifiedRecipeDTO;
    }
}
