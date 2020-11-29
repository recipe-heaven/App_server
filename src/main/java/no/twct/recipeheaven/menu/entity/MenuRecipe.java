package no.twct.recipeheaven.menu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MenuRecipe extends MenuItem {
    private Recipe recipe;

    MenuRecipe(Recipe recipe, int day){
        super(day);
        this.recipe = recipe;

    }
}
