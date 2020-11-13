package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.entity.OwnableEntity;
import no.twct.recipeheaven.resources.entity.Image;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RecipeDTO extends OwnableEntity {
    String name;

    String type;

    int cookTime;

    String description;

    private Date updated;

    private Image image;
}
