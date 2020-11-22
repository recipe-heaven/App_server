package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.Const;
import no.twct.recipeheaven.menu.entity.ValidMenuItem;
import no.twct.recipeheaven.resources.entity.Image;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = "recipes")
@EqualsAndHashCode(callSuper = true)
@NamedQuery(name = Recipe.GET_MULTIPLE_RECIPCE, query = "SELECT r FROM Recipe r WHERE r.id IN :ids")
public class Recipe extends ValidMenuItem {

    public static final String GET_MULTIPLE_RECIPCE = "RecipeGetMultipleIds";




    String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    List<RecipeTag> tags;

    String description;

    @Column(name = "cook_time")
    int cookTime = 0;

    @OneToOne
    Image recipeImage;

    String type = Const.RECIPE_TYPE_NAME;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    List<RecipeIngredient> recipeIngredients;

    @Column(name = "cooking_steps")
    String cookingSteps;

    @Column(name = "recommended_drinks")
    String recommendedDrinks;


}
