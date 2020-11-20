package no.twct.recipeheaven.meal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@SqlResultSetMapping(
        name = "MealSearchResult",
        classes = {
                @ConstructorResult(
                        targetClass = no.twct.recipeheaven.search.entity.MealSearchResult.class,
                        columns = {
                                @ColumnResult(name = "id", type = BigInteger.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "type", type = String[].class)})})
@Data
@Entity
@NoArgsConstructor
@Table(name = "meals")
@EqualsAndHashCode(callSuper = true)
@NamedQuery(name = Meal.GET_MULTIPLE_MEALS, query = "SELECT r FROM Meal r WHERE r.id IN :ids")
public class Meal extends CreatableBase {

    public static final String GET_MULTIPLE_MEALS = "MealGetMultipleIds";

    @NotEmpty
    String name;

    @Column(name = "is_public")
    boolean isPublic;

    @ManyToMany
    @JoinTable(name = "meal_recipes")
    List<@Valid Recipe> recipes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

}

