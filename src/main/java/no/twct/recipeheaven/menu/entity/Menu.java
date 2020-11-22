package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;
import no.twct.recipeheaven.meal.entity.Meal;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menus")
@EqualsAndHashCode(callSuper = true)
public class Menu extends CreatableBase {

    @NotEmpty
    String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<@Valid MenuItem> menuItems;

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
