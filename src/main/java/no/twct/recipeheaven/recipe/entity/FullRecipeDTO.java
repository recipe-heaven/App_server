package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.Const;
import no.twct.recipeheaven.entity.OwnableEntity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Full recipe object, with all details
 * belonging to the recipe
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FullRecipeDTO extends OwnableEntity {
    String name;

    String tags;

    String description;

    int cookTime = 0;

    String type = Const.RECIPE_TYPE_NAME;

    boolean isPublic;

    List<RecipeIngredient> recipeIngredients;

    String cookingSteps;

    String recommendedDrinks;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
}