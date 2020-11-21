package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.meal.entity.Meal;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menu_meals")
public class MenuMeal extends MenuItem {
    @OneToOne
    private Meal meal;

    @Override
    public ItemTypes getItemType() {
        return ItemTypes.meal;
    }
}
