package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.entity.OwnableEntity;

/**
 * Simplified recipe object, which only contains
 * key details for representing a recipe.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MinifiedRecipeDTO extends OwnableEntity {
    String name;

    String description;

    int cookTime = 0;
}
