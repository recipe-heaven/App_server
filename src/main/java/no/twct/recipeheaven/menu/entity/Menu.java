package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menus")
@EqualsAndHashCode(callSuper = true)
public class Menu extends CreatableBase {

    String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<MenuItem> menuItems;

    // i am tiered this is temporary

    public List<MenuRecipe> getRecipes() {
        return menuItems.stream()
                        .filter(menuItem -> menuItem.getMenuDayItem() instanceof Recipe)
                        .map(menuItem -> new MenuRecipe((Recipe) menuItem.getMenuDayItem()))
                        .collect(
                                Collectors.toList());
    }

    public List<MenuMeal> getMeals() {
        return menuItems.stream()
                        .filter(menuItem -> menuItem.getMenuDayItem() instanceof Meal)
                        .map(menuItem -> new MenuMeal((Meal) menuItem.getMenuDayItem()))
                        .collect(
                                Collectors.toList());
    }

}
