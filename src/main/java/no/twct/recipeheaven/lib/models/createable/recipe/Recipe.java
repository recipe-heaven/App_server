package no.twct.recipeheaven.lib.models.createable.recipe;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.Const;
import no.twct.recipeheaven.lib.models.createable.CreatableBase;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "recipes")
@EqualsAndHashCode(callSuper = true)
public class Recipe extends CreatableBase {

    String name;

    String tags;

    String description;

    int cookTime = 0;

    String type = Const.RECIPE_TYPE_NAME;

    boolean visible;

    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    List<RecipeIngredient> recipeIngredients;

    String cookingSteps;

    String recommendedDrinks;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updated;

}
