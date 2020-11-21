package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.recipe.entity.Recipe;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menu_recipes")
public class MenuRecipe extends MenuItem {

    @OneToOne
    private Recipe recipe;


    @Override
    public ItemTypes getItemType() {
        return ItemTypes.recipe;
    }
}
