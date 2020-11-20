package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.recipe.entity.RecipeDTO;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class MenuRecipeDTO {

    private int day;

    private BigInteger id;

    private RecipeDTO recipe;

}
