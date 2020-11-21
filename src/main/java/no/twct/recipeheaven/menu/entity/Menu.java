package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.CreatableBase;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
                .filter(menuItem -> menuItem.getItemType().equals(MenuItem.ItemTypes.recipe))
                .map(menuItem -> (MenuRecipe) menuItem)
                .collect(
                        Collectors.toList());
    }

    public List<MenuMeal> getMeals() {
        return menuItems.stream()
                .filter(menuItem -> menuItem.getItemType().equals(MenuItem.ItemTypes.meal))
                .map(menuItem -> (MenuMeal) menuItem)
                .collect(
                        Collectors.toList());
    }

}
