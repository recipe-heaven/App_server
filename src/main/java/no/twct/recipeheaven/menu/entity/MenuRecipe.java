package no.twct.recipeheaven.menu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.recipe.entity.Recipe;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRecipe extends MenuItem {
    private Recipe recipe;
}
