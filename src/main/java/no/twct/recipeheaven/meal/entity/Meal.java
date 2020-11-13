package no.twct.recipeheaven.meal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.*;
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
public class Meal extends CreatableBase {

    String name;

    @Column(name = "is_public")
    boolean isPublic;

    @ManyToMany
    @JoinTable(name = "meal_recipes")
    List<Recipe> recipes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

}

