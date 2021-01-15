package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.menu.entity.ValidMenuItem;
import no.twct.recipeheaven.resources.entity.Image;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = "recipes")
@EqualsAndHashCode(callSuper = true)
@NamedQuery(name = Recipe.GET_MULTIPLE_RECIPCE, query = "SELECT r FROM Recipe r WHERE r.id IN :ids")
public class Recipe extends ValidMenuItem {

    public static final String GET_MULTIPLE_RECIPCE = "RecipeGetMultipleIds";


    @NotEmpty
    @Column(columnDefinition = "TEXT")
    String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    List<@Valid RecipeTag> tags;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    String description;

    @Column(name = "cook_time")
    @Min(0)
    int cookTime = 0;

    @NotNull
    @OneToOne
    Image recipeImage;

    @NotEmpty
    String type;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    List<RecipeIngredient> recipeIngredients;

    @NotEmpty
    @Column(name = "cooking_steps", columnDefinition = "TEXT")
    String cookingSteps;

    @Column(name = "recommended_drinks", columnDefinition = "TEXT")
    String recommendedDrinks;


}
