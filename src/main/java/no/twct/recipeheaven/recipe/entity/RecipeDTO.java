package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.entity.DisplayableEntity;
import no.twct.recipeheaven.resources.entity.Image;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RecipeDTO extends DisplayableEntity {

    String type;

    int cookTime;

    String description;

    private Image image;
}
