package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.resources.entity.Image;
import no.twct.recipeheaven.user.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = "recipes")
@EqualsAndHashCode(callSuper = true)
@NamedQuery(name = Recipe.GET_MULTIPLE_RECIPCE, query = "SELECT r FROM Recipe r WHERE r.id IN :ids")
public class Recipe extends CreatableBase {

    public static final String GET_MULTIPLE_RECIPCE = "RecipeGetMultipleIds";


    @NotEmpty
    String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    List<RecipeTag> tags;

    @NotEmpty
    String description;

    @Column(name = "cook_time")
    @Min(0)
    int cookTime = 0;

    @NotNull
    @OneToOne
    Image recipeImage;

    @NotEmpty
    String type;

    @Column(name = "is_public")
    boolean isPublic;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    List<RecipeIngredient> recipeIngredients;

    @NotEmpty
    @Column(name = "cooking_steps")
    String cookingSteps;

    @Column(name = "recommended_drinks")
    String recommendedDrinks;


}
