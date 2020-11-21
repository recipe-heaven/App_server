package no.twct.recipeheaven.menu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.meal.entity.Meal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menu_meals")
@EqualsAndHashCode(callSuper = true)
public class MenuMeal extends MenuItem {

    @OneToOne
    private Meal meal;

    @Override
    public ItemTypes getItemType() {
        return ItemTypes.meal;
    }
}
