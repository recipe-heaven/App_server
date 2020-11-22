package no.twct.recipeheaven.meal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.menu.entity.ValidMenuItem;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "meals")
@EqualsAndHashCode(callSuper = true)
@NamedQuery(name = Meal.GET_MULTIPLE_MEALS, query = "SELECT r FROM Meal r WHERE r.id IN :ids")
public class Meal extends ValidMenuItem {


    public static final String GET_MULTIPLE_MEALS = "MealGetMultipleIds";

    String name;

    @ManyToMany
    @JoinTable(name = "meal_recipes")
    List<Recipe> recipes;

}

