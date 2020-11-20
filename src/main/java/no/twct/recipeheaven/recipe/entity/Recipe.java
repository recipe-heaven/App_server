package no.twct.recipeheaven.recipe.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.Const;
import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.resources.entity.Image;
import no.twct.recipeheaven.user.entity.User;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@SqlResultSetMapping(
        name = "ScheduleResult",
        classes = {
                @ConstructorResult(
                        targetClass = no.twct.recipeheaven.search.entity.RecipeSearchResult.class,
                        columns = {
                                @ColumnResult(name = "id", type = BigInteger.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "cook_time", type = Integer.class),
                                @ColumnResult(name = "type", type = String.class)
                        })})
@Data
@Entity
@NoArgsConstructor
@Table(name = "recipes")
@EqualsAndHashCode(callSuper = true)
@NamedQuery(name = Recipe.GET_MULTIPLE_RECIPCE, query = "SELECT r FROM Recipe r WHERE r.id IN :ids")
public class Recipe extends CreatableBase {

    public static final String GET_MULTIPLE_RECIPCE = "RecipeGetMultipleIds";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @ManyToOne
    User creator;

    String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    List<RecipeTag> tags;

    String description;

    @Column(name = "cook_time")
    int cookTime = 0;

    @OneToOne
    Image recipeImage;

    String type = Const.RECIPE_TYPE_NAME;

    @Column(name = "is_public")
    boolean isPublic;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    List<RecipeIngredient> recipeIngredients;

    @Column(name = "cooking_steps")
    String cookingSteps;

    @Column(name = "recommended_drinks")
    String recommendedDrinks;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

}
