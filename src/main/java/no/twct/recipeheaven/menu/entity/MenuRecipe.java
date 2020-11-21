package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menu_recipes")
@EqualsAndHashCode(callSuper = true)
public class MenuRecipe extends MenuItem {

    @OneToOne
    private Recipe recipe;

    @Override
    public ItemTypes getItemType() {
        return ItemTypes.recipe;
    }
}
