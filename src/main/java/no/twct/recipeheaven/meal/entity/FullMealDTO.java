package no.twct.recipeheaven.meal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.entity.OwnableEntity;
import no.twct.recipeheaven.recipe.entity.FullRecipeDTO;

import java.util.Date;
import java.util.List;

/**
 * Full projection of a meal, with all recipes projections.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FullMealDTO extends OwnableEntity {

    String name;

    String type;

    boolean isPublic;

    List<FullRecipeDTO> recipes;

    private Date created;

    private Date updated;

}
